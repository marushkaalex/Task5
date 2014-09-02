package com.epam.am.dao;

import com.epam.am.entity.Painting;

import java.util.List;

public interface PaintingDao {
    long add(Painting painting) throws DaoException;

    Painting findById(long id) throws DaoException;

    List<Painting> findByName(String name) throws DaoException;

    void remove(Painting painting) throws DaoException;

    void removeByID(long id) throws DaoException;

    void update(Painting painting) throws DaoException;

    List<Painting> getPaintingList() throws DaoException;

    List<Painting> getPaintingList(int count) throws DaoException;

    List<Painting> getUserLikes(long userId) throws DaoException;

    void addLike(long userId, long paintingId) throws DaoException;

    List<Painting> getArtistsPaintings(long artistId) throws DaoException;

    List<Painting> getTop(int count) throws DaoException;

    void removeLikes(long paintingId) throws DaoException;
}
