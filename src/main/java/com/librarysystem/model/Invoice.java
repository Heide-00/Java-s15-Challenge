package com.librarysystem.model;

import java.time.LocalDate;

public class Invoice {
    private final String invoiceID;
    private final Reader member;
    private final Librarian librarian;
    private final Book book;
    private final double amount;
    private final LocalDate issueDate;

    private boolean isPaid;
    private boolean isRefunded;

    public Invoice(String invoiceID, Reader member, Librarian librarian, Book book, double amount) {
        this.invoiceID = invoiceID;
        this.member = member;
        this.librarian = librarian;
        this.book = book;
        this.amount = amount;
        this.issueDate = LocalDate.now();
        this.isPaid = false;
        this.isRefunded = false;
    }

    public Reader getMember() {
        return member;
    }
    public Book getBook() {
        return book;
    }
    public double getAmount() {
        return amount;
    }
    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }
    public void setRefunded(boolean refunded) {
        this.isRefunded = refunded;
    }

    // İade işlemi.
    public String refund() {
        if (isPaid && !isRefunded) {
            setRefunded(true);
            return "Kitap iade edildi ve ücret geri ödendi.";
        } else if (!isPaid) {
            return "Fatura daha önce ödenmemiş, iade yapılamaz.";
        }
        return "Kitap iade edildi ve ücret geri ödendi.";
    }

    // Bilgiler.
    public String getFormattedInfo() {
        return "Fatura [" + invoiceID + "]" +
                " | Üye: " + member.getName() +
                " | Kitap: " + book.getTitle() +
                " | Kütüphaneci: " + (librarian != null ? librarian.getName() : "Tanımsız") +
                " | Tutar: " + amount + "₺" +
                " | Tarih: " + issueDate +
                " | Ödendi mi?: " + (isPaid ? "Evet" : "Hayır") +
                " | İade Edildi mi?: " + (isRefunded ? "Evet" : "Hayır");
    }
    //getFormattedInfo() metodundaki bilgileri yansıtmak.
    @Override
    public String toString() {
        return getFormattedInfo();
    }
}






