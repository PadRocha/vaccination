package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("")
public class MainView extends FlexLayout {
    VerticalLayout row = new VerticalLayout();
    Logger logger = LoggerFactory.getLogger(MainView.class);
    List<Expediente> expedientes = new ArrayList<>();
    Grid<Expediente> grid = new Grid<>(Expediente.class);
    Dialog dialog = new Dialog();
    TextField filter = new TextField();

    public MainView() {
        init();
        RecordForm record_form = new RecordForm();
        UserForm user_form = new UserForm();
        AdressForm adress_form = new AdressForm();

        HorizontalLayout email_content = new HorizontalLayout();
        TextField email = new TextField();
        email.setLabel("Correo Electrónico");
        email.setPlaceholder("correo@correo.com");
        email.setRequired(true);
        email_content.add(email);

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

        // *------------------------------------------------------------------*/
        // * Send Event
        // *------------------------------------------------------------------*/

        send.setEnabled(false);
        send.addClickListener(click -> {
            if (record_form.isValid() && user_form.isValid() && adress_form.isValid() && !email.isEmpty()) {
                List<Record> records = new ArrayList<>();
                Record record = new Record();
                record_form.read(record);
                records.add(record);
                record_form.clear();

                User user = new User();
                user_form.read(user);
                user_form.clear();

                Adress adress = new Adress();
                adress_form.read(adress);
                adress_form.clear();

                Set<String> ails = _ailments.getValue();
                Ailments ailments = new Ailments();
                ailments.setDiabetes(ails.contains("Diabetes"));
                ailments.setHypertension(ails.contains("Hipertensión"));
                _ailments.clear();

                Expediente expediente = new Expediente(user, adress, records, ailments);
                expediente.setEmail(email.getValue());
                expedientes.add(expediente);
                email.clear();

                filter.clear();
                grid.setItems(expedientes);
            }
        });

        // *------------------------------------------------------------------*/
        // * Filter
        // *------------------------------------------------------------------*/

        filter.setLabel("Buscar");
        filter.setPlaceholder("correo@correo.com");
        filter.setTitle("Buscar por correo");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(txt -> {
            grid.setItems(expedientes.stream().filter(expediente -> {
                return expediente.getEmail().matches("^" + filter.getValue() + ".*");
            }).collect(Collectors.toList()));
        });
        row.add(filter);

        // *------------------------------------------------------------------*/
        // * Grid config
        // *------------------------------------------------------------------*/

        grid.removeAllColumns();
        grid.addColumn(Expediente::getEmail).setHeader("Email");
        grid.addColumn(expediente -> {
            User user = expediente.getUser();
            return user.getName() + " " + user.getSurname() + " " + user.getSecond_surname();
        }).setHeader("Usuario");
        grid.addColumn(expediente -> {
            Adress adress = expediente.getAdress();
            return adress.getStreet() + " " + adress.getN_exterior() + " " + adress.getN_interior() + " "
                    + adress.getSuburb() + " " + adress.getPostal_code() + " " + adress.getMunicipality() + " "
                    + adress.getState();
        }).setHeader("Dirección");
        grid.addColumn(expediente -> {
            List<String> ails = new ArrayList<>();
            Ailments ailments = expediente.getAilments();
            if (ailments.getDiabetes()) {
                ails.add("Diabetes");
            }
            if (ailments.getHypertension()) {
                ails.add("Hypertensión");
            }
            return ails.size() < 1 ? "ninguno" : String.join(", ", ails);
        }).setHeader("Padecimientos");

        // *------------------------------------------------------------------*/
        // * Grid Button
        // *------------------------------------------------------------------*/

        VerticalLayout row_dialog = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        Button update = new Button("Update");
        List<RecordForm> forms = new ArrayList<>();
        update.getStyle().set("display", "none");
        dialog.add(row_dialog);
        buttons.add(new Button("Add", click -> {
            RecordForm popup_record = new RecordForm();
            row_dialog.add(popup_record);
            forms.add(popup_record);
            update.getStyle().set("display", "block");
        }), update);
        dialog.add(buttons);
        grid.addComponentColumn(expediente -> {
            return new Button("Ver más", clickEvent -> {
                row_dialog.removeAll();
                expediente.getRecords().forEach(record -> {
                    RecordForm popup_record = new RecordForm();
                    popup_record.write(record);
                    popup_record.disable();
                    row_dialog.add(popup_record);
                });
                update.addClickListener(click -> {
                    update.getStyle().set("display", "none");
                    forms.forEach((form) -> {
                        if (form.isValid()) {
                            Record _record = new Record();
                            form.read(_record);
                            form.disable();
                            expediente.addRecord(_record);
                        } else {
                            update.getStyle().set("display", "block");
                        }
                    });
                });
                dialog.open();
            });
        });

        row.add(grid);
        getStyle().set("padding", "0 5rem");
    }

    private void init() {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type,
                    JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        try {
            Type listType = new TypeToken<ArrayList<Expediente>>() {
            }.getType();
            String path = "D:\\proch\\Desktop\\GIT\\Java\\spring\\demo\\src\\main\\java\\com\\example\\demo\\expedientes.json";
            List<Expediente> json = gson.fromJson(new FileReader(path), listType);
            expedientes.addAll(json);
        } catch (JsonSyntaxException e) {
            logger.info("Syntax");
            logger.info(e.getMessage());
        } catch (JsonParseException e) {
            logger.info("Parse");
        } catch (FileNotFoundException e) {
            logger.info("Not Found it");
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            grid.setItems(expedientes);
        }
    }
}
