package com.librarysystem.model;

//Abstraction bir davranışı tarif eder,implementasyon bu davranışı somutlaştırır.
public class Student implements LibraryMember {
    private final MemberRecord memberRecord;
    private final String name;

    public Student(String name, String memberID) {
        this.name = name;
        this.memberRecord = new MemberRecord(memberID, MemberType.STUDENT);
    }

    @Override
    public String getMemberID() {
        return memberRecord.getMemberID();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMembershipInfo() {
        return memberRecord.getFormattedInfo();
    }

    public MemberRecord getMemberRecord() {
        return memberRecord;
    }
}
