package com.example.application.views.list;

import com.example.application.data.obj.Company;
import com.example.application.data.obj.Contact;
import com.example.application.data.obj.Status;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;

import java.util.List;
/*

Первая страница List где отображаются колонки с Именами, Email, Компаниями и Статусами контактов

*/
public class ContactForm extends FormLayout {
  TextField firstName = new TextField("First name");
  TextField lastName = new TextField("Last name");
  EmailField email = new EmailField("Email");
  ComboBox<Status> status = new ComboBox<>("Status");
  ComboBox<Company> company = new ComboBox<>("Company");

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");


  public ContactForm(List<Company> companies, List<Status> statuses) {
    addClassName("contact-form");

    company.setItems(companies);
    company.setItemLabelGenerator(Company::getName);
    status.setItems(statuses);
    status.setItemLabelGenerator(Status::getName);

    add(firstName,
            lastName,
            email,
            company,
            status,
            createButtonsLayout());
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    return new HorizontalLayout(save, delete, close);
  }

    public void setContact(Contact marcUsher) {
    }
  // Events
  public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
    private Contact contact;

    protected ContactFormEvent(ContactForm source, Contact contact) {
      super(source, false);
      this.contact = contact;
    }

    public Contact getContact() {
      return contact;
    }
  }

  public static class SaveEvent extends ContactFormEvent {
    SaveEvent(ContactForm source, Contact contact) {
      super(source, contact);
    }
  }

  public static class DeleteEvent extends ContactFormEvent {
    DeleteEvent(ContactForm source, Contact contact) {
      super(source, contact);
    }

  }

  public static class CloseEvent extends ContactFormEvent {
    CloseEvent(ContactForm source) {
      super(source, null);
    }
  }

  public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
    return addListener(DeleteEvent.class, listener);
  }

  public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
    return addListener(SaveEvent.class, listener);
  }
  public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
    return addListener(CloseEvent.class, listener);
  }
}