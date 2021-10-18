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
import com.vaadin.flow.router.Route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("")
public class MainView extends FlexLayout {
    VerticalLayout row = new VerticalLayout();
    Logger logger = LoggerFactory.getLogger(MainView.class);
    List<Expediente> expedientes = new ArrayList<>();

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
        email_content.add(email);

        // *------------------------------------------------------------------*/
        // * Comment
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

        row.add(record_form, user_form, email_content, adress_form, ailments_content, send);
        add(row);

        record_form.binder.addStatusChangeListener(status -> {
            send.setEnabled(record_form.isValid());
        });

        send.setEnabled(false);
        send.addClickListener(click -> {
            if (record_form.isValid() && user_form.isValid()) {
                List<Record> records = new ArrayList<>();
                Record record = new Record();
                record_form.read(record);
                records.add(record);

                User user = new User();
                user_form.read(user);

                Adress adress = new Adress();
                adress_form.read(adress);
                // logger.info(record.getBrand());
                Set<String> ails = _ailments.getValue();
                Ailments ailments = new Ailments();
                ailments.setDiabetes(ails.contains("Diabetes"));
                ailments.setHypertension(ails.contains("Hipertensión"));

                Expediente expediente = new Expediente(user, adress, records, ailments);
                expediente.setEmail(email.getValue());
                expedientes.add(expediente);
            }
            ;
        });
    }
}
