package com.librarysystem.model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    //List interface Book nesnelerini tutuyor.
    private final List<Book> books;
    private final MemberRecord memberRecord;

    public Reader(String name, String memberID, MemberType memberType) {
        super(name);
        //Sınıftaki books nesnesini olduğu alana yeni bir ArrayList atıyoruz.
        this.books = new ArrayList<>();
        this.memberRecord = new MemberRecord(memberID, memberType);
    }

    //Kitap ödünç alma.
    public String borrowBook(Book book) {
        if (book == null) return "Geçersiz kitap.";
        if (book.getStatus() != BookStatus.AVAILABLE) {
            return "Kitap müsait değil: " + book.getTitle();
        }
        book.updateStatus(BookStatus.BORROWED);
        book.changeOwner(this);
        books.add(book);
        return getName() + " kitabı ödünç aldı: " + book.getTitle();
    }

   //Kitap iade.
    public String returnBook(Book book) {
        if (book == null) return " Geçersiz kitap.";
        if (!books.contains(book)) {
            return "Bu kitap bu kullanıcıya ait değil.";
        }
        book.updateStatus(BookStatus.AVAILABLE);
        book.changeOwner(null);
        books.remove(book);
        return getName() + " kitabı iade etti: " + book.getTitle();
    }

    //Kitaplara  dışarıdan erişim sağlıyoruz.
    public List<Book> getBooks() {
        return books;
    }

    public MemberRecord getMemberRecord() {
        return memberRecord;
    }

    @Override
    public String whoYouAre() {
        return "Ben bir okuyucuyum: " + getName();
    }
}