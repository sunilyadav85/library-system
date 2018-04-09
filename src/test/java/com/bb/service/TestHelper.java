package com.bb.service;

import com.bb.bean.*;
import com.bb.enums.ItemType;
import com.google.common.collect.Lists;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestHelper {

    static final long EFFECTIVE_JAVA_BOOK1_ID = 1;
    static final long EFFECTIVE_JAVA_BOOK2_ID = 2;
    static final long EFFECTIVE_JAVA_BOOK3_ID = 3;
    static final long CONCURRENCY_IN_PRACTICE_BOOK1_ID = 4;
    static final long CONCURRENCY_IN_PRACTICE_BOOK2_ID = 5;
    static final long SPRING_IN_ACTION_BOOK_ID = 6;

    static final long DARK_KNIGHT_CD1_ID = 1;
    static final long DARK_KNIGHT_CD2_ID = 2;
    static final long DEADPOOL_CD_ID = 3;
    static final long AVENGERS_CD_ID = 4;

    static final long LAST_JEDI_DVD1_ID = 1;
    static final long LAST_JEDI_DVD2_ID = 2;
    static final long BIG_SHORT_DVD_ID = 3;

    static final long RETURN_JEDI_VHS1_ID = 1;
    static final long RETURN_JEDI_VHS2_ID = 2;
    static final long GOD_FATHER_VHS_ID = 3;

    public boolean containStock(List<LibraryStock> libraryStock, long itemId, ItemType itemType) {
        return libraryStock.stream().anyMatch(e -> e.getItemId() == itemId && e.getItemType() == itemType);
    }

    public LibraryStock getLibraryStock(ItemType itemType, long itemId) {
        return getLibraryStock().stream().filter(e -> e.getItemType() == itemType && e.getItemId() == itemId).findFirst().get();
    }

    public List<LibraryStock> getLibraryStock() {

        List<LibraryStock> libraryStock = new ArrayList<>();
        long stockId = 1;

        List<Book> books = getBooks();
        for (Book book : books) {
            libraryStock.add(new LibraryStock(stockId++, book.getId(), ItemType.BOOK));
        }

        List<Dvd> dvds = getDvds();
        for (Dvd dvd : dvds) {
            libraryStock.add(new LibraryStock(stockId++, dvd.getId(), ItemType.DVD));

        }

        List<Cd> cds = getCds();
        for (Cd cd : cds) {
            libraryStock.add(new LibraryStock(stockId++, cd.getId(), ItemType.CD));
        }

        List<Vhs> vhsList = getVhs();
        for (Vhs vhs : vhsList) {
            libraryStock.add(new LibraryStock(stockId++, vhs.getId(), ItemType.VHS));
        }

        return libraryStock;
    }

    enum UserName {
        David, Chris, Mark
    }

    public long getStockId(List<LibraryStock> libraryStock, long itemId, ItemType itemType) {
        return libraryStock.stream().filter(e -> e.getItemId() == itemId && e.getItemType() == itemType).
                findFirst().get().getId();
    }

    public List<UserItem> getUserItems() {
        User david = getUser(UserName.David);
        User chris = getUser(UserName.Chris);
        User mark = getUser(UserName.Mark);

        return Lists.newArrayList(
                new UserItem(david.getId(), getStockId(getLibraryStock(), EFFECTIVE_JAVA_BOOK1_ID, ItemType.BOOK),
                        LocalDate.of(2018, 1, 1), 7),
                new UserItem(chris.getId(), getStockId(getLibraryStock(), EFFECTIVE_JAVA_BOOK2_ID, ItemType.BOOK),
                        LocalDate.of(2018, 1, 2), 7),
                new UserItem(mark.getId(), getStockId(getLibraryStock(), CONCURRENCY_IN_PRACTICE_BOOK1_ID, ItemType.BOOK),
                        LocalDate.of(2018, 1, 3), 7),
                new UserItem(chris.getId(), getStockId(getLibraryStock(), CONCURRENCY_IN_PRACTICE_BOOK2_ID, ItemType.BOOK),
                        LocalDate.of(2018, 1, 1), 7),
                new UserItem(chris.getId(), getStockId(getLibraryStock(), SPRING_IN_ACTION_BOOK_ID, ItemType.BOOK),
                        LocalDate.of(2018, 1, 3), 7),
                new UserItem(mark.getId(), getStockId(getLibraryStock(), LAST_JEDI_DVD2_ID, ItemType.DVD),
                        LocalDate.of(2018, 1, 1), 7),
                new UserItem(david.getId(), getStockId(getLibraryStock(), BIG_SHORT_DVD_ID, ItemType.DVD),
                        LocalDate.of(2018, 1, 5), 7),
                new UserItem(mark.getId(), getStockId(getLibraryStock(), DARK_KNIGHT_CD1_ID, ItemType.CD),
                        LocalDate.of(2018, 1, 3), 7),
                new UserItem(chris.getId(), getStockId(getLibraryStock(), DARK_KNIGHT_CD2_ID, ItemType.CD),
                        LocalDate.of(2018, 1, 2), 7),
                new UserItem(mark.getId(), getStockId(getLibraryStock(), DEADPOOL_CD_ID, ItemType.CD),
                        LocalDate.of(2018, 1, 3), 7),
                new UserItem(david.getId(), getStockId(getLibraryStock(), RETURN_JEDI_VHS1_ID, ItemType.VHS),
                        LocalDate.of(2018, 1, 5), 7),
                new UserItem(chris.getId(), getStockId(getLibraryStock(), GOD_FATHER_VHS_ID, ItemType.VHS),
                        LocalDate.of(2018, 1, 4), 7)
        );
    }

    public List<User> getUsers() {
        return Lists.newArrayList(
                new User(1, UserName.David.name()),
                new User(2, UserName.Chris.name()),
                new User(3, UserName.Mark.name())

        );
    }

    public User getUser(UserName userName) {
        return getUsers().stream().filter(e -> e.getName().equals(userName.name())).findAny().get();
    }

    private List<Vhs> getVhs() {
        Vhs returnJediVhs1 = new Vhs(RETURN_JEDI_VHS1_ID, "Star Wars: The Return of Jedi");
        Vhs returnJediVhs2 = new Vhs(RETURN_JEDI_VHS2_ID, "Star Wars: The Return of Jedi");

        Vhs godFatherVhs = new Vhs(GOD_FATHER_VHS_ID, "The GodFather");
        return Lists.newArrayList(returnJediVhs1, returnJediVhs2, godFatherVhs);
    }


    private List<Dvd> getDvds() {
        Dvd lastJediDvd1 = new Dvd(LAST_JEDI_DVD1_ID, "Star Wars: The Last Jedi");
        Dvd lastJediDvd2 = new Dvd(LAST_JEDI_DVD2_ID, "Star Wars: The Last Jedi");

        Dvd bigShortDvd = new Dvd(BIG_SHORT_DVD_ID, "The Big Short");
        return Lists.newArrayList(lastJediDvd1, lastJediDvd2, bigShortDvd);
    }

    private List<Cd> getCds() {
        Cd darkKnightCd1 = new Cd(DARK_KNIGHT_CD1_ID, "The Dark Knight");
        Cd darkKnightCd2 = new Cd(DARK_KNIGHT_CD2_ID, "The Dark Knight");

        Cd deadPoolCd = new Cd(DEADPOOL_CD_ID, "DeadPool");
        Cd avengersCd = new Cd(AVENGERS_CD_ID, "Avengers");
        return Lists.newArrayList(darkKnightCd1, darkKnightCd2, deadPoolCd, avengersCd);

    }

    private List<Book> getBooks() {
        Book effectiveJavaBook1 = new Book(EFFECTIVE_JAVA_BOOK1_ID, "Effective Java: Third Edition");
        Book effectiveJavaBook2 = new Book(EFFECTIVE_JAVA_BOOK2_ID, "Effective Java: Third Edition");
        Book effectiveJavaBook3 = new Book(EFFECTIVE_JAVA_BOOK3_ID, "Effective Java: Third Edition");

        Book concurrencyInPracticeBook1 = new Book(CONCURRENCY_IN_PRACTICE_BOOK1_ID, "Java Concurrency in Practice");
        Book concurrencyInPracticeBook2 = new Book(CONCURRENCY_IN_PRACTICE_BOOK2_ID, "Java Concurrency in Practice");

        Book springInActionBook = new Book(SPRING_IN_ACTION_BOOK_ID, "Spring in Action");
        return Lists.newArrayList(effectiveJavaBook1,
                effectiveJavaBook2,
                effectiveJavaBook3,
                concurrencyInPracticeBook1,
                concurrencyInPracticeBook2,
                springInActionBook);
    }
}
