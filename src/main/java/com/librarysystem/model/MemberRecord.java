package com.librarysystem.model;
import java.time.LocalDate;

public class MemberRecord {
    private final String memberID;
    private final LocalDate dateOfMembership;
    private final MemberType memberType;
    private final int maxBorrowLimit;

    public MemberRecord(String memberID, MemberType memberType) {
        this.memberID = memberID;
        this.dateOfMembership = LocalDate.now();
        this.memberType = memberType;
        this.maxBorrowLimit = memberType.getBorrowLimit();
    }

    public String getMemberID() {
        return memberID;
    }

    public int getMaxBorrowLimit() {
        return maxBorrowLimit;
    }

    //Bilgiler.
    public String getFormattedInfo() {
        return "Üye Bilgisi | ID: " + memberID +
                " | Tip: " + memberType +
                " | Katılım: " + dateOfMembership +
                " | Maksimum ödünç hakkı: " + maxBorrowLimit;
    }
}
