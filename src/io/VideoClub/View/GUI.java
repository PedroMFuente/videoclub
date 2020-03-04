package io.VideoClub.View;

import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import java.time.LocalDateTime;
import java.util.Scanner;

public class GUI {
            AppController control = new AppController();
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

    public void menucreateproduct() {
        System.out.println("Tipo de producto : ");
        System.out.println("1)peli \n 2)juego \n 3)otro");
        int categoria = teclado();
        
        System.out.println("Nombre");
        String nombre = tecladoS();
        
        System.out.println("descripción");
        String descripcion = tecladoS();
        
        System.out.println("Insertar precio:");
        double precio = tecladoD();
        
        switch (categoria) {
            case 1:
                System.out.println("Creando Película : ");
                System.out.println("movie category");
                System.out.println("1)Horror \n 2)love \n 3)action \n 4)SciFi");
                int categoriaP = teclado();
                MovieCategory categoriaPelicula = MovieCategory.Horror;
                switch(categoriaP){
                    case 1:
                        categoriaPelicula = MovieCategory.Horror;
                        break;
                    case 2:
                        categoriaPelicula = MovieCategory.Love;
                        break;
                    case 3:
                        categoriaPelicula = MovieCategory.Action;
                        break;
                    case 4:
                        categoriaPelicula = MovieCategory.SciFi;
                        break;
                }
                System.out.println("edad minima");
                int edadP = teclado();
                control.createMovie(ProductsTypes.Peliculas, nombre, descripcion, precio, categoriaPelicula, edadP);
                break;
            case 2:
                System.out.println("Creando Juego : ");
                
                System.out.println("Categoría del juego: ");
                System.out.println("1)Adventures \n 2)Cars \n 3)Shooter");
                int categoriaJ = teclado();
                GameCategory categoriaJuego= GameCategory.Adventures;
                switch(categoriaJ){
                    case 1:
                        categoriaJuego = GameCategory.Adventures;
                        break;
                    case 2:
                        categoriaJuego = GameCategory.Cars;
                        break;
                    case 3:
                        categoriaJuego = GameCategory.Shooter;
                        break;
                }
                System.out.println("edad minima");
                int edadJ = teclado();
                control.createGame(ProductsTypes.Juegos, nombre, descripcion, precio, categoriaJuego, edadJ);
                break;
            case 3:
                //enum categoria product
                System.out.println("otros");
                control.createProduct(nombre, descripcion, precio);
                break;
        }
    }
    
    public void createClient(){
        //ID haremos una funcion aleatoria seguramente
        System.out.println("Introduce el ID del cliente");
        String idC = tecladoS();
        
        System.out.println("Introduce el nombre");
        String nameC = tecladoS();
        
        System.out.println("Introduce numero de telefono");
        String phoneC = tecladoS();
        
        control.createClient(idC, nameC, phoneC, LocalDateTime.now());
        
    }
    public int teclado() {
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
        String texto;
        Scanner teclado = new Scanner(System.in);
        texto = teclado.next();
        return texto;
    }

}
