package io.VideoClub.View;

import java.util.Scanner;

public class GUI {

    public static void principal(int teclado) {
        int opcion;
        opcion = teclado;
        menuI1(opcion);
        System.out.println("1)Crear producto");
        System.out.println("2)Editar producto");
        System.out.println("3)Listar producto");

    }

    public void edita(int teclado) {

        int opcion;

        opcion = teclado;
        menuI2(opcion);

        System.out.println("1)Borrar");
        System.out.println("2)Estado");
    }

    public void listar(int teclado) {
        int opcion;
        opcion = teclado;
        menuI3(opcion);
        System.out.println("1)Ordenar por nombre");
        System.out.println("2)Ordenar por estado");
        System.out.println("3)Ordenar por Género");
    }

    public static void menuI1(int opcion) {
        switch (opcion) {
            case 1: {
                addProduct();

            }
            case 2: {
                editProduct();

            }
            case 3: {
                listAllProduct();
            }

        }
    }

    public void menuI2(int opcion) {
        switch (opcion) {

            case 1: {
                removeProduct();

            }
            case 2: {
                reserveProduct();
            }

        }
    }

    public void menuI3(int opcion) {
        switch (opcion) {
            case 1: {
                listAllByName();

            }
            case 2: {
                listAllByType();

            }
            case 3: {
                ListAllByStatus();
            }

        }
    }

    public int teclado() {
        int result;
        Scanner teclado = new Scanner(System.in);
        result = teclado.nextInt();
        return result;
    }

}
