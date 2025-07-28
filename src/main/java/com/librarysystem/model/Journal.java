package com.librarysystem.model;

import java.time.LocalDate;//O anın tarihi

public class Journal extends Book {
    private final String field;
    private final int issueNumber;

    public Journal(String bookID, String title, String author,
                   double price, String edition, LocalDate dateOfPurchase,
                   String field, int issueNumber) {
        super(bookID, title, author, price, edition, dateOfPurchase);
        //Journal sınıfından türeyen journal nesnesine ait field alanına field değişkenini atama.
        this.field = field;
        this.issueNumber = issueNumber;
    }

    //Veriye dışarıdan erişim sağlama.
    public String getField() {
        return field;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    //Book da  kullandığımız metotları override ediyoruz.
    @Override
    public String getCategoryName() {
        return "journal";
    }

    @Override
    public String getFormattedInfo() {
        return "[Journal] " + getTitle()
                + " | Alan: " + field
                + " | Sayı: " + issueNumber
                + " | Yazar: " + getAuthor()
                + " | Fiyat: " + getPrice() + "₺"
                + " | Durum: " + getStatus()
                + " | Sahip: " + (getOwner() != null ? getOwner().getName() : "YOK");
    }

    @Override
    public String getFormattedInfo(SearchType context) {
        switch (context) {
            case CATEGORY:
                return "[Journal] Kategori: " + getCategoryName()
                        + " | Başlık: " + getTitle()
                        + " | Alan: " + field
                        + " | Sayı: " + issueNumber
                        + " | Yazar: " + getAuthor()
                        + " | Kitap ID: " + getId()
                        + " | Durum: " + getStatus()
                        + " | Sahip: " + (getOwner() != null ? getOwner().getName() : "YOK");
            default:
                return super.getFormattedInfo(context);
        }
    }
}