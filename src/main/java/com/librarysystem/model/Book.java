package com.librarysystem.model;

import java.time.LocalDate;

public class Book extends MediaItem {

    private final String edition;
    private BookStatus status;
    private Reader currentOwner;

    public Book(String id, String title, String author, double price, String edition, LocalDate dateOfPurchase) {
        super(id, title, author, price, dateOfPurchase);
        //Dışarıdan gelen bir değeri nesneye ait alana atıyoruz.
        this.edition = edition;
        this.status = BookStatus.AVAILABLE;
        this.currentOwner = null;
    }

    public BookStatus getStatus() {
        return status;
    }

    //book instance yeni bir güncelleme yapılıyor.
    public void updateStatus(BookStatus newStatus) {
        this.status = newStatus;
    }

    public Reader getOwner() {
        return currentOwner;
    }
   //setChangeOwner atama mı veya değiştirmemi yapıyor şeklinde karışıklığa neden olur,bu nedenle changeOwner ise kitapta bir sahiplik güncellemesini net bir şekilde oluşturur.
    public void changeOwner(Reader reader) {
        this.currentOwner = reader;
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }

    public void setAuthor(String author) {
        super.setAuthor(author);
    }

    // Kitabın ait olduğu kategori ismini veriyoruz.
    public String getCategoryName() {
        return getClass().getSimpleName().toLowerCase();
    }

    //Kitabın tüm bilgilerini veriyoruz.
    public String getFormattedInfo() {
        return "Kitap: " + getTitle()
                + " | Yazar: " + getAuthor()
                + " | Fiyat: " + getPrice() + "₺"
                + " | Durum: " + status
                + " | Baskı: " + edition
                + " | Eklenme Tarihi: " + getDateOfPurchase()
                + " | Sahip: " + (currentOwner != null ? currentOwner.getName() : "YOK");
    }

    //Arama bağlamına göre birden fazla field gösterilir.
    public String getFormattedInfo(SearchType context) {
        switch (context) {
            case AUTHOR:
                return "Yazar: " + getAuthor()
                        + " | Başlık: " + getTitle()
                        + " | Kitap ID: " + getId();
            case TITLE:
                return "Başlık: " + getTitle()
                        + " | Yazar: " + getAuthor()
                        + " | Kitap ID: " + getId();
            case ID:
                return "Kitap ID: " + getId()
                        + " | Başlık: " + getTitle()
                        + " | Yazar: " + getAuthor();
            case CATEGORY:
                return "Kategori: " + getCategoryName()
                        + " | Başlık: " + getTitle()
                        + " | Yazar: " + getAuthor()
                        + " | Kitap ID: " + getId()
                        + " | Durum: " + status;
            default:
                return getFormattedInfo();
        }
    }


}