package com.epam.am.dao;

import com.epam.am.entity.Painting;
import com.epam.am.entity.User;

import java.sql.Connection;
import java.util.List;

public class H2PaintingDao implements PaintingDao {

    private final Connection connection;

    public H2PaintingDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long add(Painting painting) throws DaoException {
        return 0;
    }

    @Override
    public Painting findById(long id) {
        return null;
    }

    @Override
    public List<Painting> findByName(String name) {
        return null;
    }

    @Override
    public void remove(Painting painting) {

    }

    @Override
    public void removeByID(long id) {

    }

    @Override
    public void update(Painting painting) {

    }

    @Override
    public List<User> getPaintingList() {
        return null;
    }
}
