package com.librarysystem;
import com.librarysystem.model.*;
import com.librarysystem.util.BookMapStorage;
import com.librarysystem.util.ValidationUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
public class Main {
    public static void main(String[] args) {
        //  VALIDATION TESTLERİ
        System.out.println("VALIDATION TESTLERİ");

        Reader testReader = new Reader("Gül", "RD-001", MemberType.STUDENT);
        Librarian testLibrarian = new Librarian("Ahmet", "LIB-001");
        StudyBook studyBook = new StudyBook("STB-001", "Algoritma", "Yazar", 72.0,
                "1. Baskı", LocalDate.of(2024, 2, 10), "Yazılım", true);
        Magazine magazine = new Magazine("MAG-002", "DesignX", "Editör", 48.5,
                "2. Baskı", LocalDate.of(2023, 11, 5), "Grafik", true);
        Journal journal = new Journal("JRN-003", "Bilim ve Teknoloji", "Prof. Yılmaz", 60.0,
                "5. Baskı", LocalDate.of(2022, 3, 1), "Fizik", 21);
        Invoice invoice = new Invoice("INV-101", testReader, testLibrarian, studyBook, studyBook.getPrice());
        Student student = new Student("Elif", "ST-321");
        LibraryMember member = new Student("Zeynep", "LM-789");

        System.out.println("Reader geçerli mi? -> " + ValidationUtil.validateReader(testReader));

        System.out.println("Librarian geçerli mi? -> " + ValidationUtil.validateLibrarian(testLibrarian));
        System.out.println("StudyBook geçerli mi? -> " + ValidationUtil.validateStudyBook(studyBook));

        System.out.println("Magazine geçerli mi? -> " + ValidationUtil.validateMagazine(magazine));
        System.out.println("Journal geçerli mi? -> " + ValidationUtil.validateJournal(journal));
        System.out.println("Invoice geçerli mi? > " + ValidationUtil.validateInvoice(invoice));
        System.out.println("Student geçerli mi? > " + ValidationUtil.validateStudent(student));
        System.out.println("SearchType (TITLE) geçerli mi? -> " + ValidationUtil.validateSearchType(SearchType.TITLE));
        System.out.println("LibraryMember geçerli mi? -> " + ValidationUtil.validateLibraryMember(member));

        System.out.println("VALIDATION TESTLERİ TAMAMLANDI");

        Faculty gul = new Faculty("Gül", "MEM-2025-GUL");
        Student student1 = new Student("Ahmet", "LM-789");

        System.out.println("FAKÜLTE ÜYELİK BİLGİLERİ\n");

        System.out.println("AKADEMİSYEN ÜYELİK BİLGİSİ:");
        System.out.println(gul.getMembershipInfo());

        System.out.println("\n ÖĞRENCİ ÜYELİK BİLGİSİ:");
        System.out.println(student1.getMembershipInfo());

        // Kütüphaneci ve okuyucu oluşturma.
        Library library = new Library();
        Librarian librarian = new Librarian("Aslı", "bnm214ur");
        Reader reader = new Reader("Gül", "RD-001", MemberType.STUDENT);
        library.addReader(reader);

        //  Kitapları sisteme BookMapStorage üzerinden ekleme.
        for (Book book : BookMapStorage.getAll()) {
            library.addMediaItem(book);
        }

        //  Arama testi
        System.out.println("\n ARAMA TESTİ:");
        Book target = BookMapStorage.getById("B777");

        Book byId = library.findBookById("B777");
        Book byTitle = library.findBooksByTitle("Bilim ve Teknoloji").stream()
                .filter(b -> b.getId().equals("B777")).findFirst().orElse(null);
        Book byAuthor = library.findBooksByAuthor("Ayşe Can").stream()
                .filter(b -> b.getId().equals("B777")).findFirst().orElse(null);

        System.out.println("ID İLE: " + byId.getFormattedInfo(SearchType.ID));
        System.out.println("BAŞLIKLA: " + byTitle.getFormattedInfo(SearchType.TITLE));
        System.out.println("YAZARLA: " + byAuthor.getFormattedInfo(SearchType.AUTHOR));
        if (byId == byTitle && byTitle == byAuthor) {
            System.out.println("Hepsi aynı kitapla eşleşti.");
        }

        // Kitap bilgisi güncelleme.
        System.out.println("\n KİTAP BİLGİSİ GÜNCELLEME");
        System.out.println(library.updateBookInfo("B779", "Bilimsel Dönüşüm", "Ayşe C."));

        //  Kitap silme
        System.out.println("\n KiTAP SİLME");
        System.out.println(library.removeBookById("B778"));

        //  Tüm kategorilere göre listeleme
        System.out.println("\n TÜM KATEGORİLERE GÖRE KİTAPLAR");
        Set<String> kategoriler = new TreeSet<>();
        for (Book b : BookMapStorage.getAll()) {
            String kategori = b.getCategoryName();
            if (kategori != null && !kategori.isBlank()) {
                kategoriler.add(kategori);
            }
        }

        for (String kategori : kategoriler) {
            List<Book> kitaplar = library.getBooksByCategory(kategori);
            System.out.println("\n " + kategori + " kategorisindeki kitaplar:");
            for (Book b : kitaplar) {
                System.out.println(b.getFormattedInfo(SearchType.CATEGORY));
            }
        }

        //  Belirli yazara göre listeleme
        System.out.println("\n Ayşe C. adlı yazarın kitapları:");
        for (Book b : library.findBooksByAuthor("Ayşe C.")) {
            System.out.println(b.getFormattedInfo(SearchType.AUTHOR));
        }

        //  Kitap ödünç alma
        System.out.println("\n KİTAP ÖDÜNÇ ALMA:");
        System.out.println(library.lendBook(target, reader, librarian));
        System.out.println(" Sahip → " + target.getOwner().getName());

        //  Kitap iade
        System.out.println("\n KİTAP İADE:");
        System.out.println(library.returnBook(target, reader));

        //  Gül’ün fatura geçmişi
        System.out.println("\n GÜL'ÜN FATURA GEÇMİŞİ");
        List<Invoice> readerInvoices = library.getInvoicesByReader(reader);
        for (Invoice inv : readerInvoices) {
            System.out.println(inv.getFormattedInfo());
        }

        //  Kitap limiti testi
        System.out.println("\n KİTAP LİMİTİ TESTİ");
        for (int i = 0; i < 5; i++) {
            Book extra = new StudyBook("BX" + i, "Test Kitap " + i, "Testçi", 50, "1.Baskı",
                    LocalDate.of(2023, 1, i + 1), "Deney", false);
            library.addMediaItem(extra);
            System.out.println(library.lendBook(extra, reader, librarian));
        }

        Book limitExceed = new StudyBook("BX999", "Limit Aşımı", "Testçi", 50, "1.Baskı",
                LocalDate.of(2023, 6, 6), "Deney", false);
        library.addMediaItem(limitExceed);
        System.out.println("\n 6.KİTAP DENEMESİ:");
        System.out.println(library.lendBook(limitExceed, reader, librarian));

        //  Kitap satın alma işlemi
        System.out.println("\n KİTAP SATIN ALMA");


        Book bookToBuy = BookMapStorage.getById("B777");

        // Fatura oluşturma.
        //System.out.println(newInvoice.getFormattedInfo());
        Invoice newInvoice = new Invoice(UUID.randomUUID().toString(), reader, librarian, bookToBuy, bookToBuy.getPrice());
        // Ödeme bilgisi fatura nesnesine işleniyor.
        newInvoice.setPaid(true);
        newInvoice.setRefunded(false);
        // Satın alma işlemini tamamlama
        System.out.println(reader.getName() + " kitabı satın aldı: " + bookToBuy.getTitle());

        //  Fatura çıktısı
        System.out.println( newInvoice.getFormattedInfo());

        System.out.println("YAZAR YENİ KİTAP EKLEDİ:");
        Author author = new Author("Gül Yılmaz");
        System.out.println(author.newBook("BK001", "Mimari Düşünce", 120.0, "1. Baskı", LocalDate.of(2023, 5, 10)));
        System.out.println(author.newBook("BK002", "Kodun Anlamı", 135.0, "2. Baskı", LocalDate.of(2023, 11, 3)));
        System.out.println(author.newBook("BK001", "Çakışan ID", 99.0, "Özel Baskı", LocalDate.of(2024, 1, 1)));

        System.out.println("\nYazarın kitapları:");
        for (String bilgi : author.getAuthoredBookInfoList()) {
            System.out.println(bilgi);
        }

        System.out.println("\nBK002 kitap detayı:");
        System.out.println(author.getBookById("BK002").getFormattedInfo());

        System.out.println("\nYazar tanımı:");
        System.out.println(author.whoYouAre());
    }

}
































































































