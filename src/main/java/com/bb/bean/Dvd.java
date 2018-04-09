package com.bb.bean;

import java.util.Objects;

public final class Dvd {

    private final long id;
    private final String name;

    public Dvd(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvd dvd = (Dvd) o;
        return id == dvd.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
