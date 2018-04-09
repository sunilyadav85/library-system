package com.bb.enums;

public enum ItemType {

    BOOK(1), DVD(2), CD(3), VHS(4);

    private int id;

    ItemType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
