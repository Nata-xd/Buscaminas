package com.estudio.modelo;

import com.estudio.enums.Estados;

public abstract class Cuadricula {

    //Metodos
    protected boolean presionado;
    protected Estados estado;

    //Constructor
    Cuadricula(){
        presionado = false;
        estado = Estados.VACIO; 
    }

    public abstract Estados getEstado();

    public abstract void setEstado(Estados estado);

    //Metodos (Abstractos)
    public abstract boolean presionar();


}
