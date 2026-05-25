package com.estudio.modelo;

import com.estudio.enums.Estados;

public class Numero extends Cuadricula {
    
    //Atributos
    private int numeroBombasAlrededor;

    //Constructor
    Numero(){
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

    public int getNumeroBombasAlrededor() {
        return numeroBombasAlrededor;
    }

    //Setters
    public void setPresionado(boolean presionado){
         this.presionado = presionado;
    }

    public void setEstado(Estados estado){
        this.estado = estado;
    }

    public void setNumeroBombasAlrededor(int numeroBombasAlrededor) {
        this.numeroBombasAlrededor = numeroBombasAlrededor;
    }

    //Metodos
    public boolean presionar(){
        presionado = true;
        return false;
    }


}
