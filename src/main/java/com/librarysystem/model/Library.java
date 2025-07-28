package com.librarysystem.model;

import java.util.*;

public class Library {
    private final List<MediaItem> mediaItems;
    private final List<Reader> readers;
    private final Set<String> mediaIDs;
    private final Map<String, Book> bookMap;
    private final Map<Reader, List<Invoice>> invoiceMap;

    public Library() {
        mediaItems = new ArrayList<>();
        readers = new ArrayList<>();
        mediaIDs = new HashSet<>();
        bookMap = new HashMap<>();
        invoiceMap = new HashMap<>();
    }

    public String addMediaItem(MediaItem item) {
        if (mediaIDs.contains(item.getId())) {
            return "Bu yayın zaten kayıtlı: " + item.getTitle();
        }
        mediaItems.add(item);
        mediaIDs.add(item.getId());

        //item değişkeni bir Book nesnesi ise book isminde yeni bir değişken olarak tanımlama.
        if (item instanceof Book book) {
            bookMap.put(book.getId(), book);
        }
        return "Yeni yayın eklendi: " + item.getTitle();
    }

    public String addReader(Reader reader) {
        readers.add(reader);
        return "Yeni okuyucu eklendi: " + reader.getName();
    }

    public Book findBookById(String bookID) {
        return bookMap.get(bookID);
    }

    public List<Book> findBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        //Book book->Her bir Book nesnesini book değişkeninde dolaşır.
        //bookMap.values()->Map'teki tüm book nesnelerini döndürür.
        for (Book book : bookMap.values()) {
            //Kitap başlığını ve arama terimini küçük harfe çevir başlık bu terimi içeriyormu?
            //Kullanıcını  yazdığı arama terimi ile kitap başlığı karşılaştırmasında büyük/küçük harf farkı önemsiz.
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByCategory(String categoryName) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.getCategoryName().equalsIgnoreCase(categoryName)) {
                result.add(book);
            }
        }
        return result;
    }

    public String updateBookInfo(String bookID, String newTitle, String newAuthor) {
        Book book = bookMap.get(bookID);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            return "Kitap bilgisi güncellendi: " + "ID:" + bookID +
                    " | Yeni Başlık: " + book.getTitle() +
                    " | Yeni Yazar: " + book.getAuthor();
        }
        return "Kitap bulunamadı: " + bookID;
    }

    public String removeBookById(String bookID) {
        Book book = bookMap.get(bookID);
        if (book != null) {
            mediaItems.remove(book);
            mediaIDs.remove(bookID);
            bookMap.remove(bookID);
            return "Kitap kaldırıldı: " + book.getTitle();
        }
        return "Kitap bulunamadı: " + bookID;
    }
    public String lendBook(Book book, Reader reader, Librarian librarian) {
        if (book.getStatus() != BookStatus.AVAILABLE) {
            return "Kitap şu an müsait değil.";
        }

        if (reader.getBooks().size() >= reader.getMemberRecord().getMaxBorrowLimit()) {
            return "Limit aşıldı! " + reader.getName()
                    + " en fazla " + reader.getMemberRecord().getMaxBorrowLimit() + " kitap alabilir.";
        }

        // Kitap ödünç alınıyor.
        String borrowResult = reader.borrowBook(book);

        // Fatura oluşturuluyor
        Invoice invoice = new Invoice("INV-" + UUID.randomUUID(), reader, librarian, book, book.getPrice());

        // Ödeme.
        invoice.setPaid(true);
        invoice.setRefunded(false);

        // Fatura kaydetme.
        recordInvoice(reader, invoice);

        //Çıktı sunma
        return borrowResult + "\n" + invoice.getFormattedInfo() +
                "\nKitap ödünç verildi ve fatura oluşturuldu.";
    }

    //İade etme.
    public String returnBook(Book book, Reader reader) {
        if (!reader.getBooks().contains(book)) {
            return "Bu kitap kullanıcıda değil.";
        }

        String returnResult = reader.returnBook(book);
        Invoice refundInvoice = new Invoice("INV-R-" + UUID.randomUUID(), reader,
                new Librarian("Aslı", "bnm214ur"), book, book.getPrice());
        refundInvoice.setPaid(true);
        recordInvoice(reader, refundInvoice);
        return returnResult + "\n" + refundInvoice.refund();

   }

    public void recordInvoice(Reader reader, Invoice invoice) {
        //invoiceMap adlı Map yapısında reader anahtarı yoksa!
        invoiceMap.computeIfAbsent(reader, r -> new ArrayList<>()).add(invoice);
    }

    public List<Invoice> getInvoicesByReader(Reader reader) {
        //invoiceMap de Map<Reader, List<Invoice>> yapısında reader anahtarı ile ilişkili fatura listesi varsa döndür.
        return invoiceMap.getOrDefault(reader, new ArrayList<>());
    }

}
