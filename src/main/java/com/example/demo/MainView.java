package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.router.Route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("")
public class MainView extends FlexLayout {
    VerticalLayout row = new VerticalLayout();
    Logger logger = LoggerFactory.getLogger(MainView.class);
    List<Expediente> expedientes = new ArrayList<>();
    Grid<Expediente> grid = new Grid<>(Expediente.class);

    public MainView() {
        RecordForm record_form = new RecordForm();
        UserForm user_form = new UserForm();
        AdressForm adress_form = new AdressForm();

        // *------------------------------------------------------------------*/
        // * Email
        // *------------------------------------------------------------------*/

        HorizontalLayout email_content = new HorizontalLayout();
        TextField email = new TextField();
        email.setLabel("Correo Electrónico");
        email.setPlaceholder("correo@correo.com");
        email.setRequired(true);
        email_content.add(email);

        // *------------------------------------------------------------------*/
        // * Ailments
        // *------------------------------------------------------------------*/

        HorizontalLayout ailments_content = new HorizontalLayout();
        CheckboxGroup<String> _ailments = new CheckboxGroup<>();
        _ailments.setLabel("Padecimientos");
        _ailments.setItems("Diabetes", "Hipertensión");
        Checkbox diabetes = new Checkbox();
        diabetes.setLabel("Diabetes");
        diabetes.getValue();
        ailments_content.add(_ailments);

        Button send = new Button("Add");

        row.add(record_form, user_form, email_content, adress_form, ailments_content);
        row.add(send, new Hr());
        add(row);

        // *------------------------------------------------------------------*/
        // * Changes
        // *------------------------------------------------------------------*/

        record_form.getBinder().addStatusChangeListener(status -> {
            send.setEnabled(record_form.isValid() && user_form.isValid() && adress_form.isValid() && !email.isEmpty());
        });

        user_form.getBinder().addStatusChangeListener(status -> {
            send.setEnabled(record_form.isValid() && user_form.isValid() && adress_form.isValid() && !email.isEmpty());
        });

        adress_form.getBinder().addStatusChangeListener(status -> {
            send.setEnabled(record_form.isValid() && user_form.isValid() && adress_form.isValid() && !email.isEmpty());
        });

        email.addValueChangeListener(status -> {
            send.setEnabled(record_form.isValid() && user_form.isValid() && adress_form.isValid() && !email.isEmpty());
        });

        send.setEnabled(false);
        send.addClickListener(click -> {
            if (record_form.isValid() && user_form.isValid() && adress_form.isValid() && !email.isEmpty()) {
                List<Record> records = new ArrayList<>();
                Record record = new Record();
                record_form.read(record);
                records.add(record);

                User user = new User();
                user_form.read(user);

                Adress adress = new Adress();
                adress_form.read(adress);

                Set<String> ails = _ailments.getValue();
                Ailments ailments = new Ailments();
                ailments.setDiabetes(ails.contains("Diabetes"));
                ailments.setHypertension(ails.contains("Hipertensión"));

                Expediente expediente = new Expediente(user, adress, records, ailments);
                expediente.setEmail(email.getValue());
                expedientes.add(expediente);

                grid.setItems(expedientes);
            }
        });

        grid.removeAllColumns();
        grid.addColumn(expediente -> {
            User user = expediente.getUser();
            return user.getName() + " " + user.getSurname() + " " + user.getSecond_surname();
        }).setHeader("Usuario");
        grid.addColumn(Expediente::getEmail).setHeader("Email");
        grid.addColumn(expediente -> {
            Adress adress = expediente.getAdress();
            return adress.getStreet() + " " + adress.getN_exterior() + " " + adress.getN_interior() + " "
                    + adress.getSuburb() + " " + adress.getPostal_code() + " " + adress.getMunicipality() + " "
                    + adress.getState();
        }).setHeader("Dirección");
        row.add(grid);

        getStyle().set("padding", "0 5rem");
    }
}
