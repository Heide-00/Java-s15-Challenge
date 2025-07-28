package com.librarysystem.model;

import java.time.LocalDate;

public class Magazine extends Book {
    private final String theme;
    private final boolean isMonthly;

    public Magazine(String bookID, String title, String author,
                    double price, String edition, LocalDate dateOfPurchase,
                    String theme, boolean isMonthly) {
        super(bookID, title, author, price, edition, dateOfPurchase);
        this.theme = theme;
        this.isMonthly = isMonthly;
    }

    public String getTheme() {
        return theme;
    }


    @Override
    public String getCategoryName() {
        return "magazine";
    }


    @Override
    public String getFormattedInfo() {
        return "[Magazine] " + getTitle()
                + " | Tema: " + theme
                + " | Yayın tipi: " + (isMonthly ? "Aylık" : "Özel Sayı")
                + " | Yazar: " + getAuthor()
                + " | Fiyat: " + getPrice() + "₺"
                + " | Durum: " + getStatus()
                + " | Sahip: " + (getOwner() != null ? getOwner().getName() : "YOK");
    }


    @Override
    public String getFormattedInfo(SearchType context) {
        switch (context) {
            case CATEGORY:
                return "[Magazine] Kategori: " + getCategoryName()
                        + " | Başlık: " + getTitle()
                        + " | Tema: " + theme
                        + " | Yayın tipi: " + (isMonthly ? "Aylık" : "Özel Sayı")
                        + " | Yazar: " + getAuthor()
                        + " | Kitap ID: " + getId()
                        + " | Durum: " + getStatus()
                        + " | Sahip: " + (getOwner() != null ? getOwner().getName() : "YOK");
            default:
                return super.getFormattedInfo(context);
        }
    }
}