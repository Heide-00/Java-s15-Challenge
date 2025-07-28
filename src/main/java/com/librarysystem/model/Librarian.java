package com.librarysystem.model;

public class Librarian extends Person {

    public final String password;

    public Librarian(String name, String password) {
        super(name);
        this.password = password;
    }


    @Override
    public String whoYouAre() {
        return "Ben bir kütüphaneciyim: " + getName();

    }

}
