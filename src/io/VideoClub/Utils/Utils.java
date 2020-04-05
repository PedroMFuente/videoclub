/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Utils;

import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Reservation;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author migue
 */
public class Utils {

    public Utils() {
    }
    
    /**
     * Muestra uno a uno los productos del Set que recibe
     * @param products Set de productos
     */
    public void listProductS(Set<Product> products) {
        if (products != null) {
            if (!products.isEmpty()) {
                for (Product p : products) {
                    System.out.println(p);
                }
            } else {
                System.out.println("Repositorio productos vacio, comorueba que haya productos guardados");
            }
        }
    }

    /**
     * Muestra uno a uno los productos del List que recibe
     * @param products List de productos
     */
    public void listProductL(List<Product> products) {
        if (products != null) {
            if (!products.isEmpty()) {
                for (Product p : products) {
                    System.out.println(p);
                }
            } else {
                System.out.println("Repositorio productos vacio, comorueba que haya productos guardados");
            }
        }
    }

    /**
     * Muestra uno a uno los Clientes del Set que recibe
     * @param clients Set de clientes
     */
    public void listClientS(Set<IClient> clients) {
        if (clients != null) {
            if (!clients.isEmpty()) {
                for (IClient c : clients) {
                    System.out.println(c);
                }
            } else {
                System.out.println("Repositorio clientes vacio, comorueba que haya clientes guardados");
            }
        }
    }

    /**
     * Muestra una a una las reservas del Set que recibe
     * @param reservations Set de reservas
     */
    public void listReservationS(Set<Reservation> reservations) {
        if (reservations != null) {
            if (!reservations.isEmpty()) {
                for (Reservation r : reservations) {
                    System.out.println(r);
                }
            } else {
                System.out.println("Repositorio reservations vacio, comorueba que haya reservations guardados");
            }
        }
    }
    
    /**
     * Muestra la key y el value de cada producto del Map que recibe
     * @param products Map de productos y su posición
     */
    public void ListProductM(Map<Product, Integer> products) {
        for (Map.Entry<Product, Integer> m : products.entrySet()) {
            System.out.println(m.getKey());
            System.out.println(m.getValue());
        }

    }

    /**
     * Scanner que lee por teclado el número entero (int) que introduce
     * @return el número entero (int) leido por teclado
     */
    public int tecladoI() {
        int result = 0;
        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        while (!salir) {
            try {
                result = teclado.nextInt();
                salir = true;
            } catch (Exception e) {
                System.out.println("Error");
                teclado = new Scanner(System.in);
            }
        }

        return result;
    }

    /**
     * Scanner que lee por teclado el número con decimales (double) que introduce
     * @return el número double leído por teclado
     */
    public double tecladoD() {
        double result = 0;
        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        while (!salir) {
            try {
                result = teclado.nextDouble();
                salir = true;
            } catch (Exception e) {
                System.out.println("Error");
                teclado = new Scanner(System.in);
            }
        }
        return result;
    }

    /**
     * Scanner que lee por teclado un String
     * @return String leído por teclado
     */
    public String tecladoS() {
        String result = "";
        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        while (!salir) {
            try {
                result = teclado.next();
                salir = true;
            } catch (Exception e) {
                System.out.println("Error");
                teclado = new Scanner(System.in);
            }
        }
        return result;
    }

}
