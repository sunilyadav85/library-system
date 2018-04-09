package com.bb.bean;

import java.util.Objects;

public final class Cd {

    private final long id;
    private final String name;

    public Cd(final long id, final String name) {
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
        Cd cd = (Cd) o;
        return id == cd.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
