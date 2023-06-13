package com.example.myschoolnotes.entities;

public class User {
    public String nombre;
    public String alias;
    public String centro;
    public int curso;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "User{" +
                "nombre='" + nombre + '\'' +
                ", alias='" + alias + '\'' +
                ", centro='" + centro + '\'' +
                ", curso=" + curso +
                '}';
    }
}
