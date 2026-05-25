package com.estudio.modelo;

import com.estudio.enums.Estados;

public class Juego {
    // Atributos
    private boolean gameOver;
    private boolean win;
    private int numBombas;
    private Cuadricula[][] cuadricula;

    // Contructor
    public Juego(int numBombas, int filas, int columnas) throws Exception {
        validarParametrosIniciales(numBombas, filas, columnas);

        this.numBombas = numBombas;
        gameOver = false;
        win = false;
        cuadricula = new Cuadricula[filas][columnas];

        this.ponerBombas();
        this.ponerCasillas();
        this.detectarMinasTotales();
    }

    // Getters
    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWin() {
        return win;
    }

    public int getNumBombas() {
        return numBombas;
    }

    public Cuadricula[][] getCuadricula() {
        return cuadricula;
    }

    // Setters
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setNumBombas(int numBombas) {
        this.numBombas = numBombas;
    }

    public void setCuadricula(Cuadricula[][] cuadricula) {
        this.cuadricula = cuadricula;
    }

    // Metodos
    private void validarParametrosIniciales(int numBombas, int filas, int columnas) {
        if (filas <= 0 || columnas <= 0) {
            throw new IllegalArgumentException("Las filas y columnas deben ser mayores que cero");
        }

        if (numBombas < 0) {
            throw new IllegalArgumentException("El numero de bombas no puede ser negativo");
        }

        int totalCasillas = filas * columnas;
        if (numBombas >= totalCasillas) {
            throw new IllegalArgumentException("El numero de bombas debe ser menor que la cantidad total de casillas");
        }
    }

    private void validarCoordenadas(int fila, int columna) {
        if (fila < 0 || fila >= cuadricula.length || columna < 0 || columna >= cuadricula[0].length) {
            throw new IllegalArgumentException("Las coordenadas estan fuera del tablero");
        }
    }

    private void validarNumero(int fila, int columna) {
        validarCoordenadas(fila, columna);

        if (!(cuadricula[fila][columna] instanceof Numero)) {
            throw new IllegalArgumentException("La casilla seleccionada no es un numero");
        }
    }

    // Poner Bombas (REVISAR)
    public void ponerBombas() throws Exception {
        try {

            int numBombs = numBombas;
            int fila = 0;
            int columna = 0;

            while (numBombs > 0) {
                fila = (int) (Math.random() * ((cuadricula.length - 1) - 0 + 1));
                columna = (int) (Math.random() * ((cuadricula[0].length - 1) - 0 + 1));
                if (cuadricula[fila][columna] == null) {
                    cuadricula[fila][columna] = new Bomba();
                    numBombs--;
                }
            }
            System.out.println("Se colocaron exitosamente las minas");
        } catch (Exception e) {
            throw new Exception("Error al colocar las bombas: " + e.toString());
        }
    }

    // Llenar numeros(REVISAR)
    public void ponerCasillas() throws Exception {
        try {

            for (int i = 0; i <= cuadricula.length - 1; i++) {
                for (int j = 0; j <= cuadricula[0].length - 1; j++) {
                    if (cuadricula[i][j] == null) {
                        cuadricula[i][j] = new Numero();
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al poner casillas: " + e.toString());
        }
    }

    // Detectar Minas Alrededor
    public void detectarMinasAlrededor(int fila, int columna) throws Exception {
        try {
            validarNumero(fila, columna);

            int bombasAlrededor = 0;
            Cuadricula casilla = cuadricula[fila][columna];

            if (casilla instanceof Numero numero) {
                // Arriba
                if ((fila - 1) >= 0 && (columna - 1) >= 0 && cuadricula[fila - 1][columna - 1] instanceof Bomba) {
                    bombasAlrededor++;
                }
                if ((columna - 1) >= 0 && cuadricula[fila][columna - 1] instanceof Bomba) {
                    bombasAlrededor++;
                }
                if ((fila + 1) < cuadricula.length && (columna - 1) >= 0
                        && cuadricula[fila + 1][columna - 1] instanceof Bomba) {
                    bombasAlrededor++;
                }

                // Abajo
                if ((fila - 1) >= 0 && (columna + 1) < cuadricula[0].length
                        && cuadricula[fila - 1][columna + 1] instanceof Bomba) {
                    bombasAlrededor++;
                }
                if ((columna + 1) < cuadricula[0].length && cuadricula[fila][columna + 1] instanceof Bomba) {
                    bombasAlrededor++;
                }
                if ((fila + 1) < cuadricula.length && (columna + 1) < cuadricula[0].length
                        && cuadricula[fila + 1][columna + 1] instanceof Bomba) {
                    bombasAlrededor++;
                }

                // Laterales
                if ((fila - 1) >= 0 && cuadricula[fila - 1][columna] instanceof Bomba) {
                    bombasAlrededor++;
                }
                if ((fila + 1) < cuadricula.length && cuadricula[fila + 1][columna] instanceof Bomba) {
                    bombasAlrededor++;
                }

                numero.setNumeroBombasAlrededor(bombasAlrededor);
            } else {
                throw new IllegalArgumentException("La casilla es una bomba");
            }

        } catch (Exception e) {
            throw new Exception("Error al detectar minas alrededor: " + e.toString());
        }

    }

    // Detectar minas totales
    public void detectarMinasTotales() throws Exception {
        try {

            for (int i = 0; i <= cuadricula.length - 1; i++) {
                for (int j = 0; j <= cuadricula[0].length - 1; j++) {
                    if (cuadricula[i][j] instanceof Numero) {
                        detectarMinasAlrededor(i, j);
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al detectar minas totales: " + e.toString());
        }
    }

    // Imprimir Cuadricula (prueba)
    public String imprimirCuadricula() throws Exception {

        try {

            String retorno = "";
            Cuadricula casilla = null;

            for (int i = 0; i <= cuadricula.length - 1; i++) {
                for (int j = 0; j <= cuadricula[0].length - 1; j++) {

                    casilla = cuadricula[i][j];

                    if (casilla instanceof Numero numero) {
                        if (numero.getNumeroBombasAlrededor() == 0)
                            retorno += "[ ]";
                        else
                            retorno += "[" + numero.getNumeroBombasAlrededor() + "]";
                    } else {
                        retorno += "[" + (char) 208 + "]";
                    }
                }
                retorno += "\n";
            }

            return retorno;
        } catch (Exception e) {
            throw new Exception("Error al imprimir: " + e.toString());
        }
    }

    public void presionarCasilla(int fila, int columna) {

        validarCoordenadas(fila, columna);

        Cuadricula casilla = cuadricula[fila][columna];

        if (casilla.getEstado().equals(Estados.BANDERA)) {
            throw new IllegalStateException("No se puede presionar una casilla con bandera");
        }

        // Va a revisar si es bomba
        if (casilla instanceof Bomba)
            perder();
        else {
            Numero numero = (Numero) cuadricula[fila][columna];

            // Revisa que sea un espacio vacio
            if (numero.getNumeroBombasAlrededor() > 0)
                numero.presionar();
            else {
                numero.presionar();

                // Accion cascada
                // Arriba
                if ((fila - 1) >= 0 && (columna - 1) >= 0 && cuadricula[fila - 1][columna - 1] instanceof Numero num) {

                    if (num.getNumeroBombasAlrededor() == 0 && !num.getPresionado()) {
                        presionarCasilla(fila - 1, columna - 1);
                    } else
                        num.presionar();
                }
                if ((columna - 1) >= 0 && cuadricula[fila][columna - 1] instanceof Numero num) {
                    if (num.getNumeroBombasAlrededor() == 0 && !num.getPresionado()) {
                        presionarCasilla(fila, columna - 1);
                    } else
                        num.presionar();
                }
                if ((fila + 1) < cuadricula.length && (columna - 1) >= 0
                        && cuadricula[fila + 1][columna - 1] instanceof Numero num) {
                    if (num.getNumeroBombasAlrededor() == 0 && !num.getPresionado()) {
                        presionarCasilla(fila + 1, columna - 1);
                    } else
                        num.presionar();
                }

                // Abajo
                if ((fila - 1) >= 0 && (columna + 1) < cuadricula[0].length
                        && cuadricula[fila - 1][columna + 1] instanceof Numero num) {
                    if (num.getNumeroBombasAlrededor() == 0 && !num.getPresionado()) {
                        presionarCasilla(fila - 1, columna + 1);
                    } else
                        num.presionar();
                }
                if ((columna + 1) < cuadricula[0].length && cuadricula[fila][columna + 1] instanceof Numero num) {
                    if (num.getNumeroBombasAlrededor() == 0 && !num.getPresionado()) {
                        presionarCasilla(fila, columna + 1);
                    } else
                        num.presionar();
                }
                if ((fila + 1) < cuadricula.length && (columna + 1) < cuadricula[0].length
                        && cuadricula[fila + 1][columna + 1] instanceof Numero num) {
                    if (num.getNumeroBombasAlrededor() == 0 && !num.getPresionado()) {
                        presionarCasilla(fila + 1, columna + 1);
                    } else
                        num.presionar();
                }

                // Laterales
                if ((fila - 1) >= 0 && cuadricula[fila - 1][columna] instanceof Numero num) {
                    if (num.getNumeroBombasAlrededor() == 0 && !num.getPresionado()) {
                        presionarCasilla(fila - 1, columna);
                    } else
                        num.presionar();
                }
                if ((fila + 1) < cuadricula.length && cuadricula[fila + 1][columna] instanceof Numero num) {
                    if (num.getNumeroBombasAlrededor() == 0 && !num.getPresionado()) {
                        presionarCasilla(fila + 1, columna);
                    } else
                        num.presionar();
                }
            }
        }
        revisarFinJuego();
    }

    public String imprimirJuego() {

        String retorno = "";
        Cuadricula casilla = null;

        for (int i = 0; i <= cuadricula.length - 1; i++) {
            for (int j = 0; j <= cuadricula[0].length - 1; j++) {
                casilla = cuadricula[i][j];

                if (casilla instanceof Numero numero && numero.getPresionado()) {
                    if (numero.getNumeroBombasAlrededor() > 0) {
                        retorno += "[" + numero.getNumeroBombasAlrededor() + "]";
                    } else {
                        retorno += "[ ]";
                    }

                } else if (casilla.getEstado().equals(Estados.BANDERA))
                    retorno += "[!]";
                else
                    retorno += "[?]";
            }
            retorno += "\n";
        }
        return retorno;
    }

    public void revisarFinJuego() {

        boolean finJuego = true;
        for (int i = 0; i <= cuadricula.length - 1; i++) {
            for (int j = 0; j <= cuadricula[0].length - 1; j++) {
                if (cuadricula[i][j] instanceof Numero numero) {
                    if (!numero.getPresionado()) {
                        finJuego = false;
                    }
                }
            }
        }
        setWin(finJuego);
    }

    public void ponerBandera(int fila, int columna) {
        validarCoordenadas(fila, columna);

        Cuadricula casilla = cuadricula[fila][columna];

        if (casilla instanceof Numero numero && numero.getPresionado()) {
            throw new IllegalStateException("No se puede poner bandera en una casilla ya presionada");
        }

        casilla.setEstado(Estados.BANDERA);

    }

    public void quitarBandera(int fila, int columna) {
        validarCoordenadas(fila, columna);

        Cuadricula casilla = cuadricula[fila][columna];
        casilla.setEstado(Estados.VACIO);

    }

    public void ganar() {
        setWin(true);
    }

    public void perder() {
        setGameOver(true);
    }

}