package com.example.myschoolnotes.entities;

import java.util.List;

public class Asignatura {
    public String nombre;
    public List<Double> notas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
