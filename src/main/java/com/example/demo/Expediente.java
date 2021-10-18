package com.example.demo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class Expediente implements Serializable {
    private static final long serialVersionUID = 1L;
    private User user;
    private Adress adress;
    private List<Record> records;
    private Ailments ailments;
    @Email
    @NotNull
    String email;

    public Expediente(User user, Adress adress, List<Record> records, Ailments ailments) {
        this.user = user;
        this.adress = adress;
        this.records = records;
        this.ailments = ailments;
    }

    @Override
    public String toString() {
        String text = "Expediente {\n";
        text += "\tUsuario: {\n";
        text += "\t\t" + "nombre: " + user.getName() + ",\n";
        text += "\t\t" + "apellido: " + user.getSurname() + ",\n";
        text += "\t\t" + "segundo apellido: " + user.getSecond_surname() + "\n";
        text += "\t},\n";
        text += "\tDirección: {\n";
        text += "\t\t" + "calle: " + adress.getStreet() + ",\n";
        text += "\t\t" + "n° exterior: " + adress.getN_exterior() + ",\n";
        text += "\t\t" + "n° interior: " + adress.getN_interior() + ",\n";
        text += "\t\t" + "colonia: " + adress.getSuburb() + ",\n";
        text += "\t\t" + "código postal: " + adress.getPostal_code() + ",\n";
        text += "\t\t" + "municipio: " + adress.getMunicipality() + ",\n";
        text += "\t\t" + "estado: " + adress.getState() + ",\n";
        text += "\t},\n";
        text += "\tcorreo: " + email + ",\n";
        text += "\tRegistros: [\n";
        for (Record record : records) {
            text += "\t\t{\n";
            text += "\t\t\t" + "marca: " + record.getBrand() + ",\n";
            text += "\t\t\t" + "lote: " + record.getBatch() + ",\n";
            text += "\t\t\t" + "dosis: " + record.getDose() + ",\n";
            text += "\t\t\t" + "fecha: " + record.getDate() + "\n";
            text += "\t\t},\n";
        }
        text += "\t],\n";
        text += "\tPadecimientos: {\n";
        text += "\t\t" + "Diabetes: " + (ailments.getDiabetes() ? "Sí" : "No") + ",\n";
        text += "\t\t" + "Hypertención: " + (ailments.getHypertension() ? "Sí" : "No") + "\n";
        text += "\t}\n";
        text += "}";
        return text;
    }

    public String getUser() {
        return user.getName() + " " + user.getSurname() + " " + user.getSecond_surname();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adress == null) ? 0 : adress.hashCode());
        result = prime * result + ((ailments == null) ? 0 : ailments.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((records == null) ? 0 : records.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Expediente other = (Expediente) obj;
        if (adress == null) {
            if (other.adress != null)
                return false;
        } else if (!adress.equals(other.adress))
            return false;
        if (ailments == null) {
            if (other.ailments != null)
                return false;
        } else if (!ailments.equals(other.ailments))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (records == null) {
            if (other.records != null)
                return false;
        } else if (!records.equals(other.records))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
