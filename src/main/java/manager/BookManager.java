package com.epam.library.manager;

import java.util.List;

public interface BookManager<I, E> extends Manager<I, E> {


    List<E> getUserAllBooks(I id);

    List<E> getAllUnassignedBooks();

    List<E> getAllAssignedBooks();

    void delete(I id1, I id2);

    void unAssign(E book);

    E getByUserId(I id);
}
