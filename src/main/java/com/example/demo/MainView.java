package com.example.demo;

// import com.vaadin.flow.component.Text;
// import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
// import com.vaadin.flow.component.checkbox.Checkbox;
// import com.vaadin.flow.component.html.H1;
// import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
// import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends HorizontalLayout {

    public MainView() {
        Div record_div = new Div();
        TextField brand = new TextField();
        brand.setPlaceholder("Marca de la vacuna");
        // brand.setId();
        DatePicker date = new DatePicker();
        Button send = new Button("Add");
        record_div.add(brand, date, send);
        add(record_div);
        send.addClickListener(click -> {
            Record record = new Record();
            record.setBrand(brand.getValue());
            record.setDate(date.getValue());
            Div text = new Div();
            // Text
            Paragraph p = new Paragraph();
            p.add(record.getBrand());
            text.add(text);
            add(text);

            // System.out.println(record.getBrand());
        });
    }
}
