package com.epam.am.action;

import com.epam.am.dao.*;
import com.epam.am.entity.Painting;
import com.epam.am.entity.User;
import com.epam.am.util.PropertyManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.epam.am.database.DBHelper.PaintingTable.DESCRIPTION;
import static com.epam.am.database.DBHelper.PaintingTable.NAME;

public class ImageUploadAction implements Action {

    private final static String ENV = "environment.var";
    private final static String PATH = "path";
    private static final Logger LOG = LoggerFactory.getLogger(ImageUploadAction.class);
    private static final int PICTURE_MAX_SIZE = 1024 * 1024;
    private static final ActionResult result = new ActionResult("gallery", true);

    private Painting painting = new Painting();

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            ServletContext servletContext = req.getServletContext();

            FileCleaningTracker fileCleaningTracker =
                    FileCleanerCleanup.getFileCleaningTracker(servletContext);

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(PICTURE_MAX_SIZE);
            factory.setFileCleaningTracker(fileCleaningTracker);

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(PICTURE_MAX_SIZE);

            DaoFactory daoFactory = new H2DaoFactory();
            try (DaoManager daoManager = daoFactory.getDaoManager()) {
                List<FileItem> items = upload.parseRequest(req);
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        processFormField(item);
                    } else {
                        processUploadedFile(item, req);
                    }
                }
                PaintingDao paintingDao = daoManager.getPaintingDao();
                paintingDao.add(painting);
            } catch (FileUploadException | DaoException e) {
                req.setAttribute("error", "file loading error");
                LOG.error("exception while file loading: {}", e);
            }
            return result;
        }
        req.setAttribute("error", "file loading error");
        return result;
    }

    private void processFormField(FileItem item) {
        String name = item.getFieldName();
        String value = null;
        try {
            value = new String(item.getString().getBytes("iso-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        switch (name) {
            case NAME:
                painting.setName(value);
                break;
            case DESCRIPTION:
                painting.setDescription(value);
                break;
        }
    }

    private void processUploadedFile(FileItem item, HttpServletRequest req) throws ActionException {

        String mimeType = req.getServletContext().getMimeType(item.getName());
        if (mimeType == null || !mimeType.startsWith("image")) throw new ActionException("NOPE!");

        String fieldName = item.getFieldName();
        String contentType = item.getContentType();
        long sizeInBytes = item.getSize();

        User user = (User) req.getSession().getAttribute("user");
        if (user == null || user.getRole() == User.Role.CLIENT) throw new ActionException("You must be logged in");

        String itemName = item.getName();
        int dot = itemName.lastIndexOf(".");
        String extenstion = dot >= 0 ? itemName.substring(dot) : "jpg";
        String date = new SimpleDateFormat("yyyy-MM-dd-k-m-s").format(new Date());
        String fileName = (user.getId() + "-" + date + extenstion);
        LOG.debug("fieldName: {}", fieldName);
        LOG.debug("fileName: {}", fileName);
        LOG.debug("contentType: {}", contentType);
        LOG.debug("sizeInBytes: {}", sizeInBytes);

        PropertyManager manager = PropertyManager.getManager(PropertyManager.STORAGE);

        String root = System.getenv(manager.getProperty(ENV)) + manager.getProperty(PATH);
        File path = new File(root + "/uploads");
        if (!path.exists()) {
            path.mkdirs();
        }

        File uploadedFile = new File(path + "/" + fileName);
        String absolutePath = uploadedFile.getAbsoluteFile().toString();
        LOG.debug("uploaded file absolute path: {}", absolutePath);

        try {
            item.write(uploadedFile);
            LOG.debug("uploaded file has been written");
            painting.setPath(absolutePath);
            painting.setArtistId(((User) req.getSession().getAttribute("user")).getId());
            painting.setDate(new Date());
        } catch (Exception e) {
            LOG.error("exception while file uploading", e);
            throw new ActionException(e);
        }
    }
}
