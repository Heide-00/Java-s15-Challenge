package com.librarysystem.util;

import com.librarysystem.model.*;

public final class ValidationUtil {

    // Bu sınıfın dışarıdan örneği oluşturulamaz.
    private ValidationUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    // string kontrolü
    private static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    //  Reader doğrulama
    public static boolean validateReader(Reader reader) {
        return reader != null &&
                isNonEmpty(reader.getName()) &&
                reader.getMemberRecord() != null;
    }

    //  Book doğrulama
    public static boolean validateBook(Book book) {
        return book != null &&
                isNonEmpty(book.getId());
    }

    //  MemberRecord doğrulama
    public static boolean validateMemberRecord(MemberRecord record) {
        return record != null &&
                isNonEmpty(record.getMemberID()) &&
                record.getMaxBorrowLimit() > 0;
    }

    // Librarian doğrulama
    public static boolean validateLibrarian(Librarian librarian) {
        return librarian != null &&
                isNonEmpty(librarian.getName());
    }

    //  StudyBook doğrulama
    public static boolean validateStudyBook(StudyBook book) {
        return validateBook(book) &&
                isNonEmpty(book.getSubject());
    }

    // Magazine doğrulama
    public static boolean validateMagazine(Magazine mag) {
        return validateBook(mag) &&
                isNonEmpty(mag.getTheme());
    }

    //  Journal doğrulama
    public static boolean validateJournal(Journal journal) {
        return validateBook(journal) &&
                isNonEmpty(journal.getField()) &&
                journal.getIssueNumber() > 0;
    }

    // Invoice doğrulama
    public static boolean validateInvoice(Invoice invoice) {
        return invoice != null &&
                invoice.getAmount() > 0 &&
                invoice.getMember() != null &&
                invoice.getBook() != null;
    }

    // Student doğrulama
    public static boolean validateStudent(Student student) {
        return student != null &&
                isNonEmpty(student.getName()) &&
                validateMemberRecord(student.getMemberRecord());
    }

    // SearchType doğrulama
    public static boolean validateSearchType(SearchType type) {
        return type != null;
    }

    // LibraryMember doğrulama
    public static boolean validateLibraryMember(LibraryMember member) {
        return member != null &&
                isNonEmpty(member.getMemberID()) &&
                isNonEmpty(member.getName());
    }
}
