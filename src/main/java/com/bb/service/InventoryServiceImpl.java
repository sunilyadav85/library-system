package com.bb.service;

import com.bb.bean.LibraryStock;
import com.bb.bean.UserItem;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InventoryServiceImpl implements InventoryService {

    private final Map<Long, LibraryStock> libraryStockMap;

    public InventoryServiceImpl(final List<LibraryStock> libraryStock) {
        // I have assumed we would have received libraryStock list from LibraryStock Service/Dao
        libraryStockMap = Collections.unmodifiableMap(
                libraryStock.stream().collect(
                        Collectors.toMap(LibraryStock::getId, Function.identity())
                )
        );
    }

    @Override
    public List<LibraryStock> getLoanableItems(final List<UserItem> allItemsUsersBorrowed) {
        List<Long> itemsBorrowedByUsers = Collections.unmodifiableList(allItemsUsersBorrowed).stream()
                .map(UserItem::getStockId).collect(Collectors.toList());
        return libraryStockMap.entrySet().stream()
                .filter(e -> !itemsBorrowedByUsers.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<LibraryStock> getOverdueItems(final LocalDate overdueDate, final List<UserItem> allItemsUsersBorrowed) {
        List<Long> stockIds = Collections.unmodifiableList(allItemsUsersBorrowed).stream()
                .filter(e -> e.isItemOverDue(overdueDate))
                .map(UserItem::getStockId).collect(Collectors.toList());
        return libraryStockMap.entrySet().stream()
                .filter(e -> stockIds.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<LibraryStock> getItemsUserBorrowed(final long userId, final List<UserItem> allItemsUsersBorrowed) {
        List<Long> itemsBorrowedByUser = Collections.unmodifiableList(allItemsUsersBorrowed).stream()
                .filter(e -> e.getUserId() == userId)
                .map(UserItem::getStockId).collect(Collectors.toList());

        return libraryStockMap.entrySet().stream()
                .filter(e -> itemsBorrowedByUser.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
