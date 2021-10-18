package com.example.demo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class Ailments implements Serializable {
    @NotNull
    private boolean diabetes;
    @NotNull
    private boolean hypertension;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (diabetes ? 1231 : 1237);
        result = prime * result + (hypertension ? 1231 : 1237);
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
        Ailments other = (Ailments) obj;
        if (diabetes != other.diabetes)
            return false;
        if (hypertension != other.hypertension)
            return false;
        return true;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public void setHypertension(boolean hypertension) {
        this.hypertension = hypertension;
    }

    public boolean getDiabetes() {
        return diabetes;
    }

    public boolean getHypertension() {
        return hypertension;
    }
}
