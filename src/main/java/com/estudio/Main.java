package com.estudio;

import java.util.Scanner;
import com.estudio.modelo.Juego;

public class Main {
    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.println(mensaje);

            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }

            System.out.println("Valor no valido, ingrese un numero entero.");
            scanner.nextLine();
        }
    }

    private static int leerEnteroEnRango(Scanner scanner, String mensaje, int minimo, int maximo) {
        int valor = 0;
        while (true) {
            valor = leerEntero(scanner, mensaje);

            if (valor >= minimo && valor <= maximo) {
                return valor;
            }

            System.out.println("Valor no valido, debe estar entre " + minimo + " y " + maximo + ".");
        }
    }

    public static void main(String[] args) {

        System.out.println("================ BUSCA MINAS ================");

        try {

            Scanner scanner = new Scanner(System.in);
            Juego juego = null;
            int respuesta = 0;
            int fila = 0;
            int columna = 0;
            int bombas = 0;

            while (true) {
                respuesta = leerEnteroEnRango(scanner, """
                        Ingrese el numero de la dificultad en la que quiere jugar:
                        1). Facil(9x9 con 10 bombas)
                        2). Medio(16x16 con 40 bombas)
                        3). Dificil(16x30 con 99 bombas)
                        4). Personalizado
                        """, 1, 4);

                switch (respuesta) {
                    case 1:
                        juego = new Juego(10, 9, 9);
                        break;
                    case 2:
                        juego = new Juego(40, 16, 16);
                        break;
                    case 3:
                        juego = new Juego(99, 16, 30);
                        break;
                    case 4:
                        fila = leerEnteroEnRango(scanner, "Ingrese el numero de filas que desea: ", 1, Integer.MAX_VALUE);
                        columna = leerEnteroEnRango(scanner, "Ingrese el numero de columnas que desea: ", 1, Integer.MAX_VALUE);
                        bombas = leerEnteroEnRango(scanner,
                                "Ingrese el numero de bombas que desea: ", 0, (fila * columna) - 1);

                        juego = new Juego(bombas, fila, columna);
                        break;
                }

                while (!juego.isWin() && !juego.isGameOver()) {

                    System.out.println(juego.imprimirJuego());

                    respuesta = leerEnteroEnRango(scanner, """
                            Ingrese el numero de la accion que desea hacer:
                            1). Presionar Casilla
                            2). Colocar Bandera
                            3). Quitar Bandera
                            """, 1, 3);

                    try {
                        switch (respuesta) {
                            case 1:
                                fila = leerEnteroEnRango(scanner, "Ingrese la fila de la casilla que desea presionar: ", 1,
                                        juego.getCuadricula().length) - 1;
                                columna = leerEnteroEnRango(scanner, "Ingrese la columna de la casilla que desea presionar: ", 1,
                                        juego.getCuadricula()[0].length) - 1;

                                juego.presionarCasilla(fila, columna);

                                if (juego.isGameOver()) {
                                    System.out.println("Fin del juego, se presiono una bomba");
                                    System.out.println(juego.imprimirCuadricula());
                                }
                                if (juego.isWin()) {
                                    System.out.println("Fin del juego, Ha ganado !!");
                                    System.out.println(juego.imprimirCuadricula());
                                }

                                break;

                            case 2:
                                fila = leerEnteroEnRango(scanner, "Ingrese la fila de la casilla a la que desea poner bandera: ", 1,
                                        juego.getCuadricula().length) - 1;
                                columna = leerEnteroEnRango(scanner, "Ingrese la columna de la casilla a la que desea poner bandera: ", 1,
                                        juego.getCuadricula()[0].length) - 1;

                                juego.ponerBandera(fila, columna);

                                break;

                            case 3:
                                fila = leerEnteroEnRango(scanner, "Ingrese la fila de la casilla a la que desea quitar bandera: ", 1,
                                        juego.getCuadricula().length) - 1;
                                columna = leerEnteroEnRango(scanner, "Ingrese la columna de la casilla a la que desea quitar bandera: ", 1,
                                        juego.getCuadricula()[0].length) - 1;

                                juego.quitarBandera(fila, columna);
                                break;

                            default:
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error \n" + e.toString());
        }
    }
}