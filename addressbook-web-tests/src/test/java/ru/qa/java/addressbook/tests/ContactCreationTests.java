package ru.qa.java.addressbook.tests;

import org.testng.annotations.Test;
import ru.qa.java.addressbook.model.ContactDate;
import ru.qa.java.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    ContactDate contact = new ContactDate()
            .withLastName("Arsen").withFirstName("Wenger")
            .withAddress("London").withEmail("arsen_wenger@gmail.com").withPhone2("+78304458345435");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() +1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
