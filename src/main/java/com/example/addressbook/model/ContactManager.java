package com.example.addressbook.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ContactManager class manages a collection of Contact object
 */
public class ContactManager {
    private IContactDAO contactDAO;
    public ContactManager(IContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    /**
     * Method to retrieve a list of contact objects
     * @param query - The identifier to select contacts
     * @return List of contact objects matching the query
     */
    public List<Contact> searchContacts(String query) {
        return contactDAO.getAllContacts()
                .stream()
                .filter(contact -> isContactMatched(contact, query))
                .toList();
    }

    private boolean isContactMatched(Contact contact, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = contact.getFullName()
                + " " + contact.getEmail()
                + " " + contact.getPhone();
        return searchString.toLowerCase().contains(query);
    }

    /**
     * Adds a contact to the database
     * @param contact
     */
    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    public void deleteContact(Contact contact) {
        contactDAO.deleteContact(contact);
    }

    public void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
    }

    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }
}