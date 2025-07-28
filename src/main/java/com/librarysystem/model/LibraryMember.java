package com.librarysystem.model;

//interface->Sınıfın hangi metotları uygulayacağını tanımlar ama metotların nasıl çalıştığıyla ingilenmez.
//signature içerir.
public interface LibraryMember {
        String getMemberID();
        String getName();
        String getMembershipInfo();
    }
