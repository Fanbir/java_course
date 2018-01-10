package ru.qa.java.addressbook.tests;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.qa.java.addressbook.model.ContactDate;
import ru.qa.java.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecanditions() {
        app.contact().HomePage();
        if( app.contact().all().size() == 0) {
            app.contact().create(new ContactDate().withLastName("Arsen").withFirstName("Wenger")
                    .withAddress("London").withEmail("arsen_wenger@gmail.com").withPhone2("+78304458345435"));
        }
    }

    @Test
    public void ContactDeletionTests() {
        Contacts before = app.contact().all();
        ContactDate deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() -1);
        assertThat(after, equalTo(before.withOut(deletedContact)));
    }
}
