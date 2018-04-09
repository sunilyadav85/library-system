package com.bb.bean;

import java.util.Objects;

public final class Vhs {

    private final long id;
    private final String name;

    public Vhs(final long id, final String name) {
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
        Vhs vhs = (Vhs) o;
        return id == vhs.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
