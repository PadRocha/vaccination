package com.example.demo;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

public class AdressForm extends HorizontalLayout {
    TextField street = new TextField();
    TextField n_exterior = new TextField();
    TextField n_interior = new TextField();
    TextField suburb = new TextField();
    TextField postal_code = new TextField();
    TextField municipality = new TextField();
    TextField state = new TextField();
    Binder<Adress> binder = new BeanValidationBinder<>(Adress.class);

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
