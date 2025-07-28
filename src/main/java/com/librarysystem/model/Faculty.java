package com.librarysystem.model;
//Faculty sınıfı LibraryMember adlı interface'in tanımlandığı metotları gerçekleştirir.
public class Faculty implements LibraryMember {
    private final MemberRecord memberRecord;
    private final String name;

    public Faculty(String name, String memberID) {
        this.name = name;
        this.memberRecord = new MemberRecord(memberID, MemberType.ACADEMIC);
    }
    //Metorların davaranışlarını bu  sınıfa özgü hale getirmek için override ediyoruz.
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
}
