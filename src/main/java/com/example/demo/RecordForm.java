package com.example.demo;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;

public class RecordForm extends HorizontalLayout {
    private DatePicker date = new DatePicker();
    private TextField brand = new TextField();
    private IntegerField batch = new IntegerField();
    private RadioButtonGroup<String> dose = new RadioButtonGroup<>();
    private Binder<Record> binder = new BeanValidationBinder<>(Record.class);

    public RecordForm() {
        date.setLabel("Fecha");
        date.setPlaceholder("Fecha de vacunación");
        date.setRequired(true);
        binder.forField(date).asRequired("La fecha es requerida").bind(Record::getDate, Record::setDate);
        brand.setLabel("Marca");
        brand.setPlaceholder("Marca de la vacuna");
        brand.setRequired(true);
        binder.forField(brand).bind(Record::getBrand, Record::setBrand);
        batch.setLabel("Lote");
        batch.setPlaceholder("Lote de la vacuna");
        batch.setRequiredIndicatorVisible(true);
        binder.forField(batch).bind(Record::getBatch, Record::setBatch);
        dose.setLabel("Dosis");
        dose.setItems("Primera", "Segunda", "Única");
        dose.setRequired(true);
        binder.forField(dose).bind(Record::getDose, Record::setDose);

        add(date, brand, batch, dose);
    }

    public Binder<Record> getBinder() {
        return binder;
    }

    public void read(Record record) {
        try {
            binder.writeBean(record);
        } catch (ValidationException e) {
        }
    }

    public boolean isValid() {
        return binder.isValid();
    }
}
