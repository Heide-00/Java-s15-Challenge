package com.librarysystem.util;

import com.librarysystem.model.Book;
import com.librarysystem.model.Journal;
import com.librarysystem.model.Magazine;
import com.librarysystem.model.StudyBook;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookMapStorage {
    //Map inteface'i kullanarak yeni bir HashMap nesnesi oluşturuyoruz.
    private static final Map<String, Book> BOOKS = new HashMap<>();

    // Kitapları ekleme
    static {
        BOOKS.put("B777", new StudyBook("B777", "Bilim ve Teknoloji", "Ayşe Can", 75, "1.Baskı",
                LocalDate.of(2023, 6, 20), "Bilim",true));

        BOOKS.put("B778", new StudyBook("B778", "Uzay ve Zaman", "Ali Kemal", 85, "2.Baskı",
                LocalDate.of(2023, 7, 1), "Fizik",true));

        BOOKS.put("B779", new StudyBook("B779", "Modern Bilim", "Ayşe Can", 80, "1.Baskı",
                LocalDate.of(2023, 7, 5), "Bilim",true));

        BOOKS.put("B780", new StudyBook("B780", "Fiziksel Evrim", "Zeynep T.", 82, "3.Baskı",
                LocalDate.of(2023, 8, 10), "Fizik",true));

        BOOKS.put("B781", new StudyBook("B781", "Kimya Rehberi", "Barış Kaya", 68, "2.Baskı",
                LocalDate.of(2023, 9, 15), "Kimya",false));

        BOOKS.put("M001", new Magazine("M001", "Teknoloji Trendleri", "Editör: Arda", 45.0, "1.Sayı",
                LocalDate.of(2023, 5, 12), "Teknoloji", true));

        BOOKS.put("M002", new Magazine("M002", "Sanat Dergisi", "Editör: Deniz", 42.0, "2.Sayı",
                LocalDate.of(2023, 6, 18), "Sanat", false));

        BOOKS.put("J001", new Journal("J001", "Bilimsel Düşünce", "Prof. Öztürk", 90.0, "1.Baskı",
                LocalDate.of(2023, 9, 1), "Felsefe", 12));

        BOOKS.put("J002", new Journal("J002", "Mühendislik Günlüğü", "Dr. Vural", 75.0, "2.Baskı",
                LocalDate.of(2023, 10, 5), "Teknik", 4));
    }

    // 🔍 Belirli bir ID ile kitap getir
    public static Book getById(String id) {
        return BOOKS.get(id);
    }

    // 📚 Tüm kitapları koleksiyon olarak getir
    public static Collection<Book> getAll() {
        return BOOKS.values();
    }

}