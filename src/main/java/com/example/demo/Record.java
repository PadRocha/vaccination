package com.example.demo;

import java.io.Serializable;
import java.time.LocalDate;
// import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Record implements Serializable {
    @NotNull
    @NotEmpty
    private String brand;
    @NotNull
    @NotEmpty
    private int batch;
    @NotNull
    @NotEmpty
    private String dose;
    @NotNull
    @NotEmpty
    private LocalDate date;

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getBatch() {
        return batch;
    }

    public String getBrand() {
        return brand;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDose() {
        return dose;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + batch;
        result = prime * result + ((brand == null) ? 0 : brand.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((dose == null) ? 0 : dose.hashCode());
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
        Record other = (Record) obj;
        if (batch != other.batch)
            return false;
        if (brand == null) {
            if (other.brand != null)
                return false;
        } else if (!brand.equals(other.brand))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (dose == null) {
            if (other.dose != null)
                return false;
        } else if (!dose.equals(other.dose))
            return false;
        return true;
    }
}