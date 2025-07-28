package com.librarysystem.model;
import java.time.LocalDate;

public class StudyBook extends Book {
    private final String subject;
    private final boolean hasPracticeSection;

    public StudyBook(String bookID, String title, String author,
                     double price, String edition, LocalDate dateOfPurchase,
                     String subject,boolean hasPracticeSection) {
        super(bookID, title, author, price, edition, dateOfPurchase);
        this.subject = subject;
        this.hasPracticeSection=hasPracticeSection;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String getCategoryName() {
        return "studybook";
    }

    @Override
    public String getFormattedInfo() {
        return "[StudyBook] " + getTitle() +
                " | Ders: " + subject +
                " | Pratik bölüm: " +(hasPracticeSection ? "Var": "Yok") +
                " | Yazar: " + getAuthor() +
                " | Durum: " + getStatus() +
                " | Sahip: " + (getOwner() != null ? getOwner().getName() : "YOK");
    }
}

