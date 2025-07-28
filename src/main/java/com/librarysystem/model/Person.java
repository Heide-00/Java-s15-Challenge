package com.librarysystem.model;

//Abstract bir davranışı tarif eder.
public abstract class Person {
    protected String name;

    public Person(String name){
        this.name=name;
    }
    //Sınıf dışından isim bilgisine erişme.
    public String getName(){
        return name;
    }
    //her alt sınıf kendine göre tanımlar.
    public abstract String whoYouAre();
}
