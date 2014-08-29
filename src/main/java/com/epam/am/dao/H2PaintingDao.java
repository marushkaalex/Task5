package com.epam.am.dao;

import com.epam.am.database.DBHelper;
import com.epam.am.entity.Painting;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.am.dao.DaoUtil.*;
import static com.epam.am.database.DBHelper.PaintingTable.*;

public class H2PaintingDao implements PaintingDao {

    private static final String ADD = "INSERT INTO " + TABLE + " (" + ARTIST_ID + "," + LIKES + "," + PATH + ","
            + NAME + "," + DESCRIPTION + "," + DATE + ") VALUES (?,0,?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE " + ID + "=?";
    private static final String FIND_BY_NAME = "SELECT * FROM " + TABLE + " WHERE " + NAME + "=?";
    private static final String REMOVE = "DELETE FROM " + TABLE + " WHERE " + ID + "=?";
    private static final String REMOVE_LIKES = "DELETE FROM " + DBHelper.UserPaintingTable.TABLE
            + " WHERE " + DBHelper.UserPaintingTable.PAINTING_ID + "=?";
    private static final String UPDATE = "UPDATE " + TABLE + " SET " + ARTIST_ID + "=?," + LIKES + "=?," + PATH + "=?,"
            + NAME + "=?," + DESCRIPTION + "=? WHERE " + ID + "=?";
    private static final String GET_ALL = "SELECT * FROM " + TABLE;
    private static final String ARTISTS_PAINTINGS = "SELECT * FROM " + TABLE + " WHERE " + ARTIST_ID + "=?";
    private static final String ADD_LIKE = "UPDATE " + TABLE + "SET LIKES=LIKES+1 WHERE " + ID + "=?";
    private static final String ADD_ARTIST_PAINTING = "INSERT INTO " + USER_PAINTING_TABLE + "VALUES (?,?)";
    //    private static final String GET_USER_LIKES = "select id,ARTIST_ID ,LIKES ,PATH ,NAME ,DESCRIPTION ,DATE  from painting join USER_PAINTING  on painting.id=USER_PAINTING.PAINTING_ID where USER_ID =210"; //TODO
    private static final String GET_USER_LIKES = "SELECT " + ID + "," + ARTIST_ID + "," + LIKES + ","
            + PATH + "," + NAME + "," + DESCRIPTION + "," + DATE + " FROM " + TABLE + " JOIN "
            + DBHelper.UserPaintingTable.TABLE + " ON " + TABLE + "." + ID + "=" + DBHelper.UserPaintingTable.TABLE + "."
            + DBHelper.UserPaintingTable.PAINTING_ID + " WHERE " + DBHelper.UserPaintingTable.USER_ID + "=?";

    private final Connection connection;

    public H2PaintingDao(Connection connection) {
        this.connection = connection;
    }

    private void checkConnection() throws DaoException {
        DaoUtil.checkConnection(connection);
    }

    @Override
    public long add(Painting painting) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, ADD, true,
                    painting.getArtistId(),
                    painting.getPath(),
                    painting.getName(),
                    painting.getDescription(),
                    toSqlDate(painting.getDate()));
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Adding painting failed: no rows affected");
            }
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            long id = -1;
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
                painting.setId(id);
            } else {
                throw new DaoException("Creating user failed: no ID obtained");
            }
            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public Painting findById(long id) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, FIND_BY_ID, false, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createPainting(resultSet) : null;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    private Painting createPainting(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(ID);
            long userId = resultSet.getLong(ARTIST_ID);
            int likes = resultSet.getInt(LIKES);
            String path = resultSet.getString(PATH);
            String name = resultSet.getString(NAME);
            String description = resultSet.getString(DESCRIPTION);
            Date date = resultSet.getDate(DATE);
            return new Painting.Builder()
                    .id(id)
                    .userId(userId)
                    .likes(likes)
                    .path(path)
                    .name(name)
                    .description(description)
                    .date(date)
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Painting> findByName(String name) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            List<Painting> resultList = new ArrayList<>();
            preparedStatement = prepareStatement(connection, FIND_BY_NAME, false, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultList.add(createPainting(resultSet));
            }
            return resultList;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void remove(Painting painting) throws DaoException {
        removeByID(painting.getId());
    }

    @Override
    public void removeByID(long id) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, REMOVE, false, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void update(Painting painting) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, UPDATE, false,
                    painting.getArtistId(),
                    painting.getLikes(),
                    painting.getPath(),
                    painting.getName(),
                    painting.getDescription(),
                    painting.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public List<Painting> getPaintingList() throws DaoException {
        List<Painting> resultList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                resultList.add(createPainting(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultList;
    }

    @Override
    public void addLike(long userId, long paintingId) throws DaoException {
        checkConnection();
        PreparedStatement addLike = null;
        PreparedStatement addUserPainting = null;
        try {
            connection.setAutoCommit(false);
            addLike = prepareStatement(connection, ADD_LIKE, false, paintingId);
            addLike.execute();
            addUserPainting = prepareStatement(connection, ADD_ARTIST_PAINTING, false, userId, paintingId);
            addUserPainting.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(addLike);
            close(addUserPainting);
        }
    }

    @Override
    public List<Painting> getUserLikes(long userId) throws DaoException {
        checkConnection();
        List<Painting> result = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, GET_USER_LIKES, false, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(createPainting(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return result;
    }

    @Override
    public List<Painting> getArtistsPaintings(long artistId) throws DaoException {
        checkConnection();
        List<Painting> result = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, ARTISTS_PAINTINGS, false, artistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(createPainting(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
        return result;
    }

    @Override
    public void removeLikes(long paintingId) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, REMOVE_LIKES, false, paintingId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }


}
