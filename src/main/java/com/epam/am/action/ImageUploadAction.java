package com.epam.am.action;

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
import java.util.List;

public class ImageUploadAction implements Action {

    private static final Logger LOG = LoggerFactory.getLogger(ImageUploadAction.class);
    private static final int PICTURE_MAX_SIZE = 1024 * 1024;
    private static final ActionResult result = new ActionResult("home");

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);

        if (isMultipart) {
            ServletContext servletContext = req.getServletContext();

            FileCleaningTracker fileCleaningTracker =
                    FileCleanerCleanup.getFileCleaningTracker(servletContext);

            DiskFileItemFactory factory = new DiskFileItemFactory();
//            File repository = (File) req.getServletContext().getAttribute("javax.servlet.context.tempdir");
            factory.setSizeThreshold(PICTURE_MAX_SIZE);
//            factory.setRepository(repository);
            factory.setFileCleaningTracker(fileCleaningTracker);

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(PICTURE_MAX_SIZE);

            try {
                List<FileItem> items = upload.parseRequest(req);

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        processFormField(item);
                    } else {
                        processUploadedFile(item, req);
                    }
                }

            } catch (FileUploadException e) {
                LOG.error("exception while file loading: {}", e);
            }
            return result;
        }
        req.setAttribute("error", "file loading error");
        return result;
    }

    private void processFormField(FileItem item) {
        String name = item.getFieldName();
        String value = item.getString();
    }

    private void processUploadedFile(FileItem item, HttpServletRequest req) {
        String fieldName = item.getFieldName();
        String fileName = item.getName();
        String contentType = item.getContentType();
        boolean isInMemory = item.isInMemory();
        long sizeInBytes = item.getSize();
        LOG.debug("fieldName: {}", fieldName);
        LOG.debug("fileName: {}", fileName);
        LOG.debug("contentType: {}", contentType);
        LOG.debug("sizeInBytes: {}", sizeInBytes);

        String root = req.getServletContext().getRealPath("/");
        File path = new File(root + "/uploads");
        if (!path.exists()) {
            boolean status = path.mkdirs();
        }

        File uploadedFile = new File(path + "/" + fileName);
        String absolutePath = uploadedFile.getAbsoluteFile().toString();
        LOG.debug("uploaded file absolute path: {}", absolutePath);

        try {
            item.write(uploadedFile);
            LOG.debug("uploaded file has been written");
        } catch (Exception e) {
            LOG.debug("exception while file uploading");
            e.printStackTrace();
        }
    }
}
