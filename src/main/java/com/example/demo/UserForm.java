package com.example.demo;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

public class UserForm extends HorizontalLayout {
    TextField name = new TextField();
    TextField surname = new TextField();
    TextField second_surname = new TextField();
    Binder<User> binder = new BeanValidationBinder<>(User.class);

    public UserForm() {
        name.setLabel("Nombre");
        name.setPlaceholder("Nombre de usuario");
        name.setRequired(true);
        binder.forField(name).bind(User::getName, User::setName);
        surname.setLabel("Apellido");
        surname.setPlaceholder("Apellido de usuario");
        surname.setRequired(true);
        binder.forField(surname).bind(User::getSurname, User::setSurname);
        second_surname.setLabel("Segundo Apellido");
        second_surname.setPlaceholder("Segundo Apellido de usuario");
        second_surname.setRequired(true);
        binder.forField(second_surname).bind(User::getSecond_surname, User::setSecond_surname);

        add(name, surname, second_surname);
    }

    public Binder<User> getBinder() {
        return binder;
    }

    public void read(User user) {
        try {
            binder.writeBean(user);
        } catch (ValidationException e) {
        }
    }

    public boolean isValid() {
        return binder.isValid();
    }
}
