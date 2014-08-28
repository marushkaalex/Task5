package com.epam.am;

import com.epam.am.dao.DaoException;
import com.epam.am.dao.DaoFactory;
import com.epam.am.dao.H2DaoFactory;
import com.epam.am.dao.PaintingDao;
import com.epam.am.entity.Painting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

//TODO servlet
//TODO database
//TODO actions
//TODO properties X
//TODO logs X
//TODO custom exception type
//TODO java-version
//TODO filter for charset X
//TODO DAO
//TODO sql scripts
//TODO registration

public class Runner {
    private static Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) throws SQLException, DaoException {
//        Gallery gallery = new Gallery();
//        Painting painting = new Painting();
//        painting.setPath("ololo/alala/uploads/image.gif");
//        gallery.add(painting);
//        System.out.println(gallery.getLinks());
        DaoFactory daoFactory = new H2DaoFactory();
        PaintingDao paintingDao = daoFactory.getPaintingDao();
        List<Painting> userLikes = paintingDao.getUserLikes(210);
        System.out.println(userLikes);
    }

    private static void listPrint(List list) {
        list.forEach(System.out::println);
    }
}
