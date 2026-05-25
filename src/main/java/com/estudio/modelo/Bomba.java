package com.estudio.modelo;

import com.estudio.enums.Estados;

public class Bomba extends Cuadricula {

    //Constructor
    Bomba(){
        presionado = false;
        estado = Estados.VACIO;
    }

    //Getters
    public boolean getPresionado(){
        return presionado;
    }

    public Estados getEstado(){
        return estado;
    }

    //Setters
    public void setPresionado(boolean presionado){
         this.presionado = presionado;
    }

    public void setEstado(Estados estado){
        this.estado = estado;
    }

    //Metodos
    public boolean presionar(){
        presionado = true;
        return true;
    }
}
