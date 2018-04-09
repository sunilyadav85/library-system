package com.bb.service;

import com.bb.bean.LibraryStock;
import com.bb.bean.UserItem;

import java.time.LocalDate;
import java.util.List;

public interface InventoryService {

    List<LibraryStock> getLoanableItems(final List<UserItem> allItemsUsersBorrowed);

    List<LibraryStock> getOverdueItems(final LocalDate overdueDate, final List<UserItem> allItemsUsersBorrowed);

    List<LibraryStock> getItemsUserBorrowed(final long userId, final List<UserItem> allItemsUsersBorrowed);
}
