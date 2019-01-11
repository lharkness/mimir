package com.leeharkness.mimir.datalayer;

import com.leeharkness.mimir.model.MimirContact;

import java.util.Collections;
import java.util.List;

public class ContactDao {
    public boolean addContact(MimirContact contact) {
        System.out.println("*** Adding contact");
        return true;
    }

    public MimirContact retrieveContact(String userName) {
        System.out.println("*** Retrieving contact");
        return MimirContact.builder().build();
    }

    public boolean deleteContact(String userName) {
        System.out.println("*** removing contact");
        return true;
    }

    public List<MimirContact> searchContacts(String target) {
        return Collections.emptyList();
    }
}
