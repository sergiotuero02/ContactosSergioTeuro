package com.example.myschoolnotes.entities;

public class Nota {

    String titulo, contenido;

    public Nota(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "titulo='" + titulo + '\'' +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
