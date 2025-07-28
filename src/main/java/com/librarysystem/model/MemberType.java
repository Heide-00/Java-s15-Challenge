package com.librarysystem.model;

public enum MemberType {
    STUDENT(5),
    ACADEMIC(6);

    private final int borrowLimit;

   MemberType(int borrowLimit){
       this.borrowLimit=borrowLimit;
   }

   public int getBorrowLimit(){
       return borrowLimit;
   }
}
