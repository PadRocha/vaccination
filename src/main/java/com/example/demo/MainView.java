package com.example.demo;

// import com.vaadin.flow.component.Text;
// import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
// import com.vaadin.flow.component.checkbox.Checkbox;
// import com.vaadin.flow.component.html.H1;
// import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
// import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;
// import com.vaadin.flow.component.html.Div;
// import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends FlexLayout {

    public MainView() {
        VerticalLayout row = new VerticalLayout();

        // *------------------------------------------------------------------*/
        // * Record
        // *------------------------------------------------------------------*/

        HorizontalLayout record_content = new HorizontalLayout();
        DatePicker date = new DatePicker();
        date.setLabel("Fecha");
        date.setPlaceholder("Fecha de vacunación");
        date.isRequired();
        TextField brand = new TextField();
        brand.setLabel("Marca");
        brand.setPlaceholder("Marca de la vacuna");
        IntegerField batch = new IntegerField();
        batch.setLabel("Lote");
        batch.setPlaceholder("Lote de la vacuna");
        RadioButtonGroup<String> dose = new RadioButtonGroup<>();
        dose.setLabel("Dosis");
        dose.setItems("Primera", "Segunda", "Única");
        record_content.add(date, brand, batch, dose);

        // *------------------------------------------------------------------*/
        // * User
        // *------------------------------------------------------------------*/

        HorizontalLayout user_content = new HorizontalLayout();
        TextField name = new TextField();
        name.setLabel("Nombre");
        name.setPlaceholder("Nombre de usuario");
        TextField surname = new TextField();
        surname.setLabel("Apellido");
        surname.setPlaceholder("Apellido de usuario");
        TextField second_surname = new TextField();
        second_surname.setLabel("Segundo Apellido");
        second_surname.setPlaceholder("Segundo Apellido de usuario");
        user_content.add(name, surname, second_surname);

        // *------------------------------------------------------------------*/
        // * Email
        // *------------------------------------------------------------------*/

        HorizontalLayout email_content = new HorizontalLayout();
        TextField email = new TextField();
        email.setLabel("Correo Electrónico");
        email.setPlaceholder("correo@correo.com");
        email_content.add(email);

        // *------------------------------------------------------------------*/
        // * Adress
        // *------------------------------------------------------------------*/

        HorizontalLayout adress_content = new HorizontalLayout();
        TextField street = new TextField();
        street.setLabel("Calle");
        TextField n_exterior = new TextField();
        n_exterior.setLabel("N° exterior");
        TextField n_interior = new TextField();
        n_interior.setLabel("N° interior");
        TextField suburb = new TextField();
        suburb.setLabel("Colonia");
        TextField postal_code = new TextField();
        postal_code.setLabel("C.P.");
        TextField municipality = new TextField();
        municipality.setLabel("Municipio");
        TextField state = new TextField();
        state.setLabel("Estado");
        adress_content.add(street, n_exterior, n_interior, suburb, postal_code, municipality, state);

        // *------------------------------------------------------------------*/
        // * Comment
        // *------------------------------------------------------------------*/

        HorizontalLayout ailments_content = new HorizontalLayout();
        CheckboxGroup<String> ailments = new CheckboxGroup<>();
        ailments.setLabel("Padecimientos");
        ailments.setItems("Diabetes", "Hipertensión");
        ailments_content.add(ailments);

        Button send = new Button("Add");

        row.add(record_content, user_content, email_content, adress_content, ailments_content, send);
        add(row);
        send.addClickListener(click -> {
            Record record = new Record();
            record.setBrand(brand.getValue());
            record.setBatch(batch.getValue());
            record.setDate(date.getValue());

            // System.out.println(record.getBrand());
        });
    }
}
