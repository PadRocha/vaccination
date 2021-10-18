package com.example.demo;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

public class AdressForm extends HorizontalLayout {
    private TextField street = new TextField();
    private TextField n_exterior = new TextField();
    private TextField n_interior = new TextField();
    private TextField suburb = new TextField();
    private TextField postal_code = new TextField();
    private TextField municipality = new TextField();
    private TextField state = new TextField();
    private Binder<Adress> binder = new BeanValidationBinder<>(Adress.class);

    public AdressForm() {
        street.setLabel("Calle");
        street.setRequired(true);
        n_exterior.setLabel("N° exterior");
        n_exterior.setRequired(true);
        n_interior.setLabel("N° interior");
        n_interior.setRequired(true);
        suburb.setLabel("Colonia");
        suburb.setRequired(true);
        postal_code.setLabel("C.P.");
        postal_code.setRequired(true);
        municipality.setLabel("Municipio");
        municipality.setRequired(true);
        state.setLabel("Estado");
        state.setRequired(true);
        add(street, n_exterior, n_interior, suburb, postal_code, municipality, state);
    }

    public Binder<Adress> getBinder() {
        return binder;
    }

    public void read(Adress adress) {
        try {
            binder.writeBean(adress);
        } catch (ValidationException e) {
        }
    }

    public boolean isValid() {
        return binder.isValid();
    }
}
