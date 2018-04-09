package com.bb.bean;

import java.time.LocalDate;
import java.util.Objects;

public final class UserItem {

    private final long userId;
    private final long stockId;
    private final int lendingDays;
    private final LocalDate borrowingDate;
    private final LocalDate overdueDate;

    public UserItem(final long userId,
                    final long stockId,
                    final LocalDate borrowingDate,
                    final int lendingDays) {
        this.userId = userId;
        this.stockId = stockId;
        this.lendingDays = lendingDays;
        this.borrowingDate = borrowingDate;
        this.overdueDate = borrowingDate.plusDays(lendingDays + 1);
    }

    public long getUserId() {
        return userId;
    }

    public long getStockId() {
        return stockId;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public LocalDate getOverdueDate() {
        return overdueDate;
    }

    public int getLendingDays() {
        return lendingDays;
    }

    public boolean isItemOverDue(LocalDate date) {
        return date.isAfter(borrowingDate.plusDays(lendingDays));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserItem userItem = (UserItem) o;
        return userId == userItem.userId &&
                stockId == userItem.stockId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, stockId);
    }
}
