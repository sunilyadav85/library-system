package com.bb.service;

import com.bb.enums.LendingResultType;

import java.time.LocalDate;

public interface LendingService {

    LendingResultType borrowItem(long userId, long stockId, LocalDate date, int days);

    LendingResultType returnItem(long stockId);
}
