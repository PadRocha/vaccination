package com.example.demo;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Adress implements Serializable {
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((municipality == null) ? 0 : municipality.hashCode());
        result = prime * result + ((n_exterior == null) ? 0 : n_exterior.hashCode());
        result = prime * result + ((n_interior == null) ? 0 : n_interior.hashCode());
        result = prime * result + postal_code;
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((suburb == null) ? 0 : suburb.hashCode());
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
        Adress other = (Adress) obj;
        if (municipality == null) {
            if (other.municipality != null)
                return false;
        } else if (!municipality.equals(other.municipality))
            return false;
        if (n_exterior == null) {
            if (other.n_exterior != null)
                return false;
        } else if (!n_exterior.equals(other.n_exterior))
            return false;
        if (n_interior == null) {
            if (other.n_interior != null)
                return false;
        } else if (!n_interior.equals(other.n_interior))
            return false;
        if (postal_code != other.postal_code)
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        if (suburb == null) {
            if (other.suburb != null)
                return false;
        } else if (!suburb.equals(other.suburb))
            return false;
        return true;
    }

    @NotNull
    @NotEmpty
    private String street;
    @NotNull
    @NotEmpty
    private String n_exterior;
    @NotNull
    @NotEmpty
    private String n_interior;
    @NotNull
    @NotEmpty
    private String suburb;
    @NotNull
    @NotEmpty
    private int postal_code;
    @NotNull
    @NotEmpty
    private String municipality;
    @NotNull
    @NotEmpty
    private String state;

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setN_exterior(String n_exterior) {
        this.n_exterior = n_exterior;
    }

    public void setN_interior(String n_interior) {
        this.n_interior = n_interior;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getN_exterior() {
        return n_exterior;
    }

    public String getN_interior() {
        return n_interior;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() {
        return suburb;
    }
}
