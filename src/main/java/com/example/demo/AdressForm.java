package com.example.demo;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

public class AdressForm extends HorizontalLayout {
    private TextField street = new TextField();
    private TextField n_exterior = new TextField();
    private TextField n_interior = new TextField();
    private TextField suburb = new TextField();
    private IntegerField postal_code = new IntegerField();
    private TextField municipality = new TextField();
    private TextField state = new TextField();
    private Binder<Adress> binder = new BeanValidationBinder<>(Adress.class);

    public AdressForm() {
        street.setLabel("Calle");
        street.setRequired(true);
        binder.forField(street).bind(Adress::getStreet, Adress::setStreet);
        n_exterior.setLabel("N° exterior");
        binder.forField(n_exterior).bind(Adress::getN_exterior, (adress, n_exterior) -> {
            adress.setN_exterior(n_exterior.isEmpty() ? "n/a" : n_exterior);
        });
        n_interior.setLabel("N° interior");
        binder.forField(n_interior).bind(Adress::getN_interior, (adress, n_interior) -> {
            adress.setN_interior(n_interior.isEmpty() ? "n/a" : n_interior);
        });
        suburb.setLabel("Colonia");
        suburb.setRequired(true);
        binder.forField(suburb).bind(Adress::getSuburb, Adress::setSuburb);
        postal_code.setLabel("C.P.");
        postal_code.setRequiredIndicatorVisible(true);
        binder.forField(postal_code).bind(Adress::getPostal_code, Adress::setPostal_code);
        municipality.setLabel("Municipio");
        municipality.setRequired(true);
        binder.forField(municipality).bind(Adress::getMunicipality, Adress::setMunicipality);
        state.setLabel("Estado");
        state.setRequired(true);
        binder.forField(state).bind(Adress::getState, Adress::setState);

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
