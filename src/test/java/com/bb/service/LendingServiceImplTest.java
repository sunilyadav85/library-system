package com.bb.service;

import com.bb.bean.LibraryStock;
import com.bb.bean.UserItem;
import com.bb.enums.ItemType;
import com.bb.enums.LendingResultType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.locks.Lock;

import static com.bb.enums.LendingResultType.*;
import static com.bb.service.TestHelper.EFFECTIVE_JAVA_BOOK3_ID;
import static com.bb.service.TestHelper.SPRING_IN_ACTION_BOOK_ID;
import static com.bb.service.TestHelper.UserName.David;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LendingServiceImplTest {

    private TestHelper testHelper = new TestHelper();
    private List<UserItem> userItems = testHelper.getUserItems();

    @InjectMocks
    private LendingService classToTest = new LendingServiceImpl(userItems);

    @Mock
    private Lock lock;

    @Test
    public void shouldBorrowItemSuccessfully() {
        // Given
        long userId = testHelper.getUser(David).getId();
        LibraryStock effectiveJavaBook3 = testHelper.getLibraryStock(ItemType.BOOK, EFFECTIVE_JAVA_BOOK3_ID);
        LocalDate borrowingDate = LocalDate.of(2018, 3, 10);
        int days = 7;
        when(lock.tryLock()).thenReturn(true);

        // When
        LendingResultType result = classToTest.borrowItem(userId, effectiveJavaBook3.getId(), borrowingDate, days);

        // Then
        assertThat(result, equalTo(SUCCESSFUL_LEND));
    }

    @Test
    public void shouldNotBorrowItemWhenItemUnAvailable() {
        // Given
        long userId = testHelper.getUser(David).getId();
        LibraryStock springInAction = testHelper.getLibraryStock(ItemType.BOOK, SPRING_IN_ACTION_BOOK_ID);
        LocalDate borrowingDate = LocalDate.of(2018, 3, 10);
        int days = 7;
        when(lock.tryLock()).thenReturn(true);

        // When
        LendingResultType result = classToTest.borrowItem(userId, springInAction.getId(), borrowingDate, days);

        // Then
        assertThat(result, equalTo(UNSUCCESSFUL_UNAVAILABLE_ITEM_LEND));
    }

    @Test
    public void shouldNotBorrowItemWhenLockBusy() {
        // Given
        long userId = testHelper.getUser(David).getId();
        LibraryStock effectiveJavaBook3 = testHelper.getLibraryStock(ItemType.BOOK, EFFECTIVE_JAVA_BOOK3_ID);
        LocalDate borrowingDate = LocalDate.of(2018, 3, 10);
        int days = 7;
        when(lock.tryLock()).thenReturn(false);

        // When
        LendingResultType result = classToTest.borrowItem(userId, effectiveJavaBook3.getId(), borrowingDate, days);

        // Then
        assertThat(result, equalTo(UNSUCCESSFUL_BUSY_LOCK_LEND));
    }

    @Test
    public void shouldReturnItemSuccessfully() {
        // Given
        LibraryStock springInAction = testHelper.getLibraryStock(ItemType.BOOK, SPRING_IN_ACTION_BOOK_ID);
        when(lock.tryLock()).thenReturn(true);

        // When
        LendingResultType result = classToTest.returnItem(springInAction.getId());

        // Then
        assertThat(result, equalTo(SUCCESSFUL_RETURN));
    }

    @Test
    public void shouldNotReturnInvalidItem() {
        // Given
        long invalidStockId = 100;
        when(lock.tryLock()).thenReturn(true);

        // When
        LendingResultType result = classToTest.returnItem(invalidStockId);

        // Then
        assertThat(result, equalTo(UNSUCCESSFUL_INVALID_ITEM_RETURN));
    }

    @Test
    public void shouldNotReturnItemWhenLockBusy() {
        // Given
        LibraryStock springInAction = testHelper.getLibraryStock(ItemType.BOOK, SPRING_IN_ACTION_BOOK_ID);
        when(lock.tryLock()).thenReturn(false);

        // When
        LendingResultType result = classToTest.returnItem(springInAction.getId());

        // Then
        assertThat(result, equalTo(UNSUCCESSFUL_BUSY_LOCK_RETURN));
    }
}