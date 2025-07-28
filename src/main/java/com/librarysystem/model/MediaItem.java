package com.librarysystem.model;
import java.time.LocalDate;
//Yapılacakları tanılıyor,nasıl yapılacağını ise alt sınıflara  bırakıyoruz.
//Abstract davranışlarla ilgilenir.
public abstract class MediaItem {
    private final  String id;
    private String title;
    private String author;
    private final double price;
    private final LocalDate dateOfPurchase;

    public MediaItem(String id, String title, String author, double price, LocalDate dateOfPurchase) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.dateOfPurchase = dateOfPurchase;
    }

    //Alt sınıflar  bu metodu kullanarak kendi nesnelerine  özel bilgileri verecek.
    public abstract String getFormattedInfo();

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
