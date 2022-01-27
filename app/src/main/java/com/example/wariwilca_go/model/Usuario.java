package com.example.wariwilca_go.model;

public class Usuario {
    private String uID;
    private String Nombre;
    private String Email;
    private String Puntos;

    public Usuario() {
    }


    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPuntos() {
        return Puntos;
    }

    public void setPuntos(String puntos) {
        Puntos = puntos;
    }

    @Override
    public String toString() {
        return Puntos;
    }
}
