package com.epam.am.entity;

import java.util.Date;

public class Painting {
    private long id;
    private long artistId;
    private int likes;
    private String path;
    private String name;
    private String description;
    private Date date;

    public Painting() {
    }

    public Painting(long id, long artistId, int likes, String path, String name, String description, Date date) {
        this.id = id;
        this.artistId = artistId;
        this.likes = likes;
        this.path = path;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "id=" + id +
                ", artistId=" + artistId +
                ", likes=" + likes +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    public static class Builder {
        private long id;
        private long userId;
        private int likes;
        private String path;
        private String name;
        private String description;
        private Date date;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder likes(int likes) {
            this.likes = likes;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Painting build() {
            return new Painting(id, userId, likes, path, name, description, date);
        }
    }
}
