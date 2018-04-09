package com.bb.bean;

import com.bb.enums.ItemType;

import java.util.Objects;

public final class LibraryStock {

    private final long id;
    private final long itemId;
    private final ItemType itemType;

    public LibraryStock(final long id,
                        final long itemId,
                        final ItemType itemType) {
        this.id = id;
        this.itemId = itemId;
        this.itemType = itemType;
    }

    public long getId() {
        return id;
    }

    public long getItemId() {
        return itemId;
    }

    public ItemType getItemType() {
        return itemType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryStock that = (LibraryStock) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
