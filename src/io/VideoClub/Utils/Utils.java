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

    public void listReservationS(Set<Reservation> reservations) {
        if (reservations != null) {
            if (reservations.isEmpty()) {
                for (Reservation r : reservations) {
                    System.out.println(r);
                }
            } else {
                System.out.println("Repositorio reservations vacio, comorueba que haya reservations guardados");
            }
        }
    }

    public void ListProductM(Map<Product, Integer> products) {
        for (Map.Entry<Product, Integer> m : products.entrySet()) {
            System.out.println(m.getKey());
            System.out.println(m.getValue());
        }

    }

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
