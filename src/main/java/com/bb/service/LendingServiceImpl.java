package com.bb.service;

import com.bb.bean.UserItem;
import com.bb.enums.LendingResultType;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.bb.enums.LendingResultType.*;

// This service scope is SingleTon
public class LendingServiceImpl implements LendingService {

    private Lock lock = new ReentrantLock();

    // I have assumed we would have received userItems list from UserItem Service/Dao
    private List<UserItem> userItems;

    public LendingServiceImpl(List<UserItem> userItems) {
        this.userItems = new LinkedList<>(userItems);
    }

    @Override
    public LendingResultType borrowItem(long userId, long stockId, LocalDate date, int days) {
        if (lock.tryLock()) {
            try {
                if (userItems.stream().anyMatch(e -> e.getStockId() == stockId)) {
                    return UNSUCCESSFUL_UNAVAILABLE_ITEM_LEND;
                } else {
                    userItems.add(new UserItem(userId, stockId, date, days));
                    return SUCCESSFUL_LEND;
                }
            } finally {
                lock.unlock();
            }
        } else {
            return UNSUCCESSFUL_BUSY_LOCK_LEND;
        }
    }

    @Override
    public LendingResultType returnItem(long stockId) {
        if (lock.tryLock()) {
            try {
                boolean result = userItems.removeIf(e -> e.getStockId() == stockId);
                if (result) {
                    return SUCCESSFUL_RETURN;
                } else {
                    return UNSUCCESSFUL_INVALID_ITEM_RETURN;
                }
            } finally {
                lock.unlock();
            }
        } else {
            return UNSUCCESSFUL_BUSY_LOCK_RETURN;
        }
    }
}