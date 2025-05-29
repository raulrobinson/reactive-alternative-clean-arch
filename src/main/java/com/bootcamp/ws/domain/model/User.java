package com.bootcamp.ws.domain.model;

public class User {
    private Long id;
    private String name;

    public User(Long id, String name) { this.id = id; this.name = name; }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private Long id;
        private String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public User build() {
            return new User(id, name);
        }
    }
}
