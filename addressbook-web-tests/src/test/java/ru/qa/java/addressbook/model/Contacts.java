package ru.qa.java.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;

  public Contacts(Contacts contact) {
    this.delegate = new HashSet<ContactData>(contact.delegate);
  }

  public Contacts(Collection<ContactData> contacts) {
    this.delegate = new HashSet<>(contacts);
  }
  public Contacts() {
    this.delegate = new HashSet<>();
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
}

  public Contacts withOut(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove( contact);
    return contacts;
  }
}
