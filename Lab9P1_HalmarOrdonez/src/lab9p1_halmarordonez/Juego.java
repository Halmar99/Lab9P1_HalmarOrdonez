package lab9p1_halmarordonez;

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Random;

public final class Juego {

    static Scanner read = new Scanner(System.in);
    static Random rng = new Random();

    public Juego() {
        char syn = 's';
        while (syn == 's' || syn == 'S') {
            int ele = 1;
            String inst = "";
            int fila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de filas: "));
            int colu = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de columnas: "));
            if (fila > 10 || fila < 4 || colu > 10 || colu < 4) {
                JOptionPane.showMessageDialog(null, "Dimensiones invalidas", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                char[][] matriz = constructor(fila, colu);
                while (ele == 1) {
                    ele = Integer.parseInt(JOptionPane.showInputDialog(null, "Matriz:\n" + imprimir(matriz) + "\n"
                            + "\n 1. Ingresar instruccion\n 2. Ejecutar instrucciones"));
                    switch (ele) {
                        case 1 -> {
                            inst = JOptionPane.showInputDialog(null, "Matriz:\n" + imprimir(matriz) + "\n \n \n Ingrese la instrucciones: ");
                            matriz=mostrarPaso(matriz,inst);
                        }
                        case 2 -> {
                            int ele2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Matriz:\n" + imprimir(matriz) + "\n\n "
                                    + "1. ver el siguiente paso \n 2. Ver paso anterior \n 3. Seleccionar paso \n 4. Volver al menu"));
                        }
                        default -> {
                            JOptionPane.showMessageDialog(null, "Opcion invalida", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }

            int resp = JOptionPane.showConfirmDialog(null, "Desea volver a usar el programa?", "Reiniciar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                syn = 's';
            } else {
                syn = 'n';
            }
        }
    }

    public char[][] mostrarPaso(char[][] matriz, String inst) {
        char[][] temp = matriz;
        char n = inst.charAt(0);
        char let = inst.charAt(1);
        int num = n - '0';
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (temp[i][j] == '$') {
                    switch (let) {
                        case 'U':
                        case 'u':
                            temp[i][j] = ' ';
                            temp[i - num][j] = '$';
                            break;
                        case 'D':
                        case 'd':
                            temp[i][j] = ' ';
                            temp[i + num][j] = '$';
                            break;
                        case 'r':
                        case 'R':
                            temp[i][j] = ' ';
                            temp[i][j + num] = '$';
                            break;
                        case 'l':
                        case 'L':
                            temp[i][j] = ' ';
                            temp[i][j - num] = '$';
                        default:
                            break;
                    }
                }
            }
        }
        return temp;
    }

    public static char[][] constructor(int fila, int colu) {
        //tablero inicial
        char fill = ' ';
        char[][] temp = new char[fila][colu];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                temp[i][j] = fill;

            }
        }
        //obstaculos
        int num1 = fila - 1, num2 = colu - 1;
        int lim;
        if (fila >= colu) {
            lim = rng.nextInt((fila - colu) + 1) + colu;
        } else {
            lim = rng.nextInt((colu - fila) + 1) + colu;
        }
        for (int i = 0; i < lim; i++) {
            int rnd1 = rng.nextInt(num1 - 1);
            int rnd2 = rng.nextInt(num2 - 1);
            temp[rnd1][rnd2] = '#';
        }
        //manzana
        boolean place = false;
        while (place == false) {
            int rnd1 = rng.nextInt(num1 - 0);
            int rnd2 = rng.nextInt(num2 - 0);
            int rnd3 = rng.nextInt(num1 - 0);
            int rnd4 = rng.nextInt(num2 - 0);

            if (temp[rnd1][rnd2] != '#' && temp[rnd3][rnd4] != '#' && rnd1 != rnd3 && rnd2 != rnd4) {
                temp[rnd1][rnd2] = 'O';
                temp[rnd3][rnd4] = '$';
                place = true;
            } else {
                place = false;
            }
        }
        return temp;
    }

    public static String imprimir(char[][] matriz) {
        StringBuilder tableroString = new StringBuilder();
        String acum = "";

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                tableroString.append("[").append(matriz[i][j]).append("]");
            }
            tableroString.append("\n");
        }
        acum += tableroString;
        return acum;
    }//FIN IMPRIMIR
}
