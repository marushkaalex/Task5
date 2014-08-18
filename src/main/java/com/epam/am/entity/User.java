package com.epam.am.entity;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String username;
    private String email;
    private String password;
    private Role role;

    public User(UUID uuid, String username, String email, String password, Role role) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {


        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public enum Role {
        ADMIN, CLIENT
    }

    public static class Builder {
        private UUID uuid;
        private String username;
        private String email;
        private Role role;
        private String password;

        public Builder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(uuid, username, email, password, role);
        }
    }
}
