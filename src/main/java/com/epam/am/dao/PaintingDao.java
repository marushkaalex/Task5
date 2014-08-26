package com.epam.am.dao;

import com.epam.am.entity.Painting;
import com.epam.am.entity.User;

import java.util.List;

public interface PaintingDao {
    long add(Painting painting) throws DaoException;

    Painting findById(long id);

    List<Painting> findByName(String name);

    void remove(Painting painting);

    void removeByID(long id);

    void update(Painting painting);

    List<User> getPaintingList();
}
