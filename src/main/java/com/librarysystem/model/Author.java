package com.librarysystem.model;
import java.time.LocalDate;
import java.util.*;
public class Author extends Person {
    // Yazara ait kitapları tutuyoruz.
    private final Map<String, Book> authoredBooks;
    public Author(String name) {
        super(name);
        this.authoredBooks = new HashMap<>();
    }

    // Yeni kitap oluşturuluyor ve listeye ekleniyor.
    public String newBook(String bookID, String title, double price, String edition, LocalDate purchaseDate) {
        if (authoredBooks.containsKey(bookID)) {
            return "Kitap ID zaten kayıtlı: " + bookID;
        }

        Book newBook = new Book(bookID, title, getName(), price, edition, purchaseDate);
        authoredBooks.put(bookID, newBook);
        return " "+getName() + " adlı yazar yeni kitap ekledi: " + title;
    }

    //Kitap bilgileri.
    public List<String> getAuthoredBookInfoList() {
        List<String> infoList = new ArrayList<>();
        //authoredBooks adlı Map de tanımlanmış Book nesnelerini tek tek döngüye alır.
        for (Book book : authoredBooks.values()) {
            infoList.add(book.getFormattedInfo());
        }
        return infoList;
    }

    // Belirli ID ile kitap bulma.
    public Book getBookById(String bookID) {
        return authoredBooks.get(bookID);
    }


    @Override
    public String whoYouAre() {
        return "Ben bir yazarım: " + getName();
    }
}



