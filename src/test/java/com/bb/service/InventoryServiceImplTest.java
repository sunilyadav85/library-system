package com.bb.service;

import com.bb.bean.LibraryStock;
import com.bb.enums.ItemType;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static com.bb.service.TestHelper.*;
import static com.bb.service.TestHelper.UserName.David;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class InventoryServiceImplTest {

    private TestHelper testHelper = new TestHelper();

    @Test
    public void shouldGetLoanableItems() {
        // Given
        InventoryService classToTest = new InventoryServiceImpl(testHelper.getLibraryStock());

        // When
        List<LibraryStock> result = classToTest.getLoanableItems(testHelper.getUserItems());

        // Then
        assertThat(result.size(), equalTo(4));
        assertThat(testHelper.containStock(result, EFFECTIVE_JAVA_BOOK3_ID, ItemType.BOOK), equalTo(true));
        assertThat(testHelper.containStock(result, LAST_JEDI_DVD1_ID, ItemType.DVD), equalTo(true));
        assertThat(testHelper.containStock(result, AVENGERS_CD_ID, ItemType.CD), equalTo(true));
        assertThat(testHelper.containStock(result, RETURN_JEDI_VHS2_ID, ItemType.VHS), equalTo(true));
    }

    @Test
    public void getOverdueItems() {
        // Given
        InventoryService classToTest = new InventoryServiceImpl(testHelper.getLibraryStock());

        // When
        List<LibraryStock> result = classToTest.getOverdueItems(LocalDate.of(2018, 1, 9), testHelper.getUserItems());

        // Then
        assertThat(result.size(), equalTo(3));
        assertThat(testHelper.containStock(result, EFFECTIVE_JAVA_BOOK1_ID, ItemType.BOOK), equalTo(true));
        assertThat(testHelper.containStock(result, CONCURRENCY_IN_PRACTICE_BOOK2_ID, ItemType.BOOK), equalTo(true));
        assertThat(testHelper.containStock(result, LAST_JEDI_DVD2_ID, ItemType.DVD), equalTo(true));
    }

    @Test
    public void getItemsUserBorrowed() {
        // Given
        InventoryService classToTest = new InventoryServiceImpl(testHelper.getLibraryStock());

        // When
        List<LibraryStock> result = classToTest.getItemsUserBorrowed(testHelper.getUser(David).getId(), testHelper.getUserItems());

        // Then
        assertThat(result.size(), equalTo(3));
        assertThat(testHelper.containStock(result, EFFECTIVE_JAVA_BOOK1_ID, ItemType.BOOK), equalTo(true));
        assertThat(testHelper.containStock(result, BIG_SHORT_DVD_ID, ItemType.DVD), equalTo(true));
        assertThat(testHelper.containStock(result, RETURN_JEDI_VHS1_ID, ItemType.VHS), equalTo(true));
    }
}