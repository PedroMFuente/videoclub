package io.VideoClub.View;

import io.VideoClub.Comparators.ClientComparator;
import io.VideoClub.Comparators.ProductComparator;
import io.VideoClub.Comparators.ReservationComparator;
import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Enums.SortOptions;
import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Reservation;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Set;

public class GUI {

    static AppController control = new AppController();

    private ProductComparator productComparator;
    private ReservationComparator reservationComparator;
    private ClientComparator clientComparator;

    public static void inicio() {
        int opcion = 0;

        do {
            System.out.println("Que desea saber ");
            System.out.println("1) Producto");
            System.out.println("2) Cliente");
            System.out.println("3) Reserva");
            System.out.println("4) Salir");
            opcion = teclado();

        } while (opcion < 1 || opcion > 4);

        switch (opcion) {
            case 1:
                product();
                break;

            case 2:
                client();
                break;

            case 3:
                reserve();
                break;

            case 4:
                break;

        }
    }

    public static void product() {
        int opcion = 0;
        do {
            System.out.println("1) Crear producto");
            System.out.println("2) Editar producto");
            System.out.println("3) Listar producto");
            System.out.println("4) Borrar producto");
            System.out.println("5) Salir");

        } while (opcion < 1 || opcion > 5);

        switch (opcion) {
            case 1:
                createProduct();
                break;

            case 2:

                editProduct();
                break;

            case 3:
                listProduct();
                break;

            case 4:
                break;

        }

    }

    public static void editProduct() {
        System.out.println("Introduce la KEY del producto");
        String key = tecladoS();
        control.editProduct(key);         //Hace falta meter un producto
    }

    public static void client() {

        boolean salir = false;
        while (!salir) {
            System.out.println("1) Crear cliente");
            System.out.println("2) Editar cliente");
            System.out.println("3) Listar cliente");
            System.out.println("4) Borrar cliente");
            System.out.println("5) Salir");
            int opcion = teclado();
            if (opcion != 5) {
                menuI1(opcion);
            } else {
                System.out.println("Hasta luego");
                salir = true;
            }

        }
    }

    public static void reserve() {

        boolean salir = false;
        while (!salir) {
            System.out.println("1) Crear reserva");
            System.out.println("2) Listar reserva");
            System.out.println("3) Terminar reserva");
            System.out.println("4) Salir");
            int opcion = teclado();
            if (opcion != 4) {
                menuI1(opcion);
            } else {
                System.out.println("Hasta luego");
                salir = true;
            }

        }
    }

    public static void sortClient() {
        SortOptions result = null;
        int opcion = 0;
        do {
            System.out.println("-----Ordenar Clientes-----");
            System.out.println("1)Ordenar de la A la Z a los clientes ");
            System.out.println("2)Ordenar de la Z a la A a los clientes");
            System.out.println("3)Ordenar por telefono a los clientes");
            System.out.println("4)Ordenar ID de menor a mayor a los clientes");
            System.out.println("5)Ordenar ID de mayor a menor a los clientes");
            opcion = teclado();
        } while (opcion < 1 || opcion > 5);
        switch (opcion) {
            case 1:
                result = SortOptions.AToZC;
                clientComparator.setOption(result);
                break;
            case 2:
                result = SortOptions.AToZP;
                clientComparator.setOption(result);
                break;
            case 3:
                result = SortOptions.Phone;
                clientComparator.setOption(result);
                break;
            case 4:
                result = SortOptions.IDMinToMax;
                clientComparator.setOption(result);
                break;
            case 5:
                result = SortOptions.IDMaxToMin;
                clientComparator.setOption(result);
                break;
            default:
                break;
        }
        Set<IClient> muestra = control.listAllClients(new ClientComparator());
        for (IClient c : muestra) {
            System.out.println(c);
        }
    }

    public static void sortProduct() {
        SortOptions result = null;
        int opcion = 0;
        do {
            System.out.println("-----Ordenar Productos-----");
            System.out.println("1)Ordenar de la A la Z a los productos");
            System.out.println("2)Ordenar de la Z a la A a los productos");
            opcion = teclado();
        } while (opcion < 1 || opcion > 2);
        switch (opcion) {
            case 1:
                result = SortOptions.AToZP;
                productComparator.setOption(result);
                break;
            case 2:
                result = SortOptions.ZtoAP;
                productComparator.setOption(result);
                break;
            default:
                break;
        }
        Set<Product> muestra = control.listAllProducts(new ProductComparator());
        for (Product p : muestra) {
            System.out.println(p);
        }

    }

    public void sortReservation() {
        SortOptions result = null;
        int opcion = 0;
        do {
            System.out.println("-----Ordenar Reservas-----");
            System.out.println("1)Ordenar de la A la Z a los clientes ");
            System.out.println("2)Ordenar de la Z a la A a los clientes");
            System.out.println("3)Ordenar por telefono a los clientes");
            System.out.println("4)Ordenar ID de menor a mayor a los clientes");
            System.out.println("5)Ordenar ID de mayor a menor a los clientes");
            System.out.println("6)Ordenar de la A la Z a los productos");
            System.out.println("7)Ordenar de la Z a la A a los productos");
            opcion = teclado();
        } while (opcion < 1 || opcion > 7);
        switch (opcion) {
            case 1:
                result = SortOptions.AToZC;
                reservationComparator.setOption(result);
                break;
            case 2:
                result = SortOptions.AToZP;
                reservationComparator.setOption(result);
                break;
            case 3:
                result = SortOptions.Phone;
                reservationComparator.setOption(result);
                break;
            case 4:
                result = SortOptions.IDMinToMax;
                reservationComparator.setOption(result);
                break;
            case 5:
                result = SortOptions.IDMaxToMin;
                reservationComparator.setOption(result);
                break;
            case 6:
                result = SortOptions.AToZP;
                reservationComparator.setOption(result);
                break;
            case 7:
                result = SortOptions.ZtoAP;
                reservationComparator.setOption(result);
                break;
            default:
                break;

        }
        Set<Reservation> muestra = control.listAllReservations(new ReservationComparator());
        for (Reservation r : muestra) {
            System.out.println(r);
        }
    }

    public static void listProduct() {
        int opcion = 0;
        int aux = 0;
        ProductsTypes type = null;
        String name;
        Product.Status status = null;

        do {

            System.out.println("1) Listar todos los productos");
            System.out.println("2) Listar todos los productos con filtro");
            System.out.println("3) Listar todos los productos por tipo");
            System.out.println("4) Listar por nombre");
            System.out.println("5) Listar por nombre y tipo");
            System.out.println("6) Listar por estado");
            System.out.println("7) Listar todos los productos diferentes");
            System.out.println("8) Listar todos los peliculas diferentes");
            System.out.println("9) Listar todos los juegos diferentes");
            System.out.println("10) Listar toda la cantidad de productos por nombre");
            System.out.println("11) Listar toda la cantidad de productos por nombre y tipo");
            System.out.println("12) Salir");
            opcion = teclado();

        } while (opcion < 1 || opcion > 12);

        switch (opcion) {

            case 1:
                control.listAllProducts();
                break;
            case 2:
                sortProduct();
                break;
            case 3:
                do {
                    System.out.println("---¿Que categoría de producto buscas?---");
                    System.out.println("1) Peliculas");
                    System.out.println("2) Juegos");
                    System.out.println("3) Otros ");
                    aux = teclado();
                } while (aux < 1 || aux > 3);
                if (aux == 1) {
                    type = ProductsTypes.Peliculas;
                } else if (aux == 2) {
                    type = ProductsTypes.Juegos;
                } else if (aux == 3) {
                    type = ProductsTypes.Otros;
                }

                control.listAllByType(type);
                break;

            case 4:
                System.out.println("Dime el nombre del producto");
                name = tecladoS();
                control.listAllByName(name);
                break;
            case 5:

                System.out.println("Dime el nombre del producto");
                name = tecladoS();

                do {
                    System.out.println("---¿Que categoría de producto buscas?---");
                    System.out.println("1) Peliculas");
                    System.out.println("2) Juegos");
                    System.out.println("3) Otros ");
                    aux = teclado();
                } while (aux < 1 || aux > 3);
                if (aux == 1) {
                    type = ProductsTypes.Peliculas;
                } else if (aux == 2) {
                    type = ProductsTypes.Juegos;
                } else if (aux == 3) {
                    type = ProductsTypes.Otros;
                }

                control.listAllByName(name, type);

                break;
            case 6:

                do {
                    System.out.println("1) Disponibles");
                    System.out.println("2) Reservados");

                    aux = teclado();
                } while (aux < 1 || aux > 2);
                if (aux == 1) {
                    status = Product.Status.AVAILABLE;
                } else if (aux == 2) {
                    status = Product.Status.RESERVED;
                }

                control.listAllByStatus(status);

                break;
            case 7:
                control.listAllDifferentProducts();
                break;
            case 8:
                control.listAllDifferentMovies();
                break;
            case 9:
                control.listAllDifferentGames();
                break;
            case 10:
                System.out.println("¿Que nombre buscas?");
                name = tecladoS();
                control.listAllAmountOfProducts(name);
                break;
            case 11:
                System.out.println("¿Que nombre buscas?");
                name = tecladoS();
                do {
                    System.out.println("---¿Que categoría de producto buscas?---");
                    System.out.println("1) Peliculas");
                    System.out.println("2) Juegos");
                    System.out.println("3) Otros ");
                    aux = teclado();
                } while (aux < 1 || aux > 3);
                if (aux == 1) {
                    type = ProductsTypes.Peliculas;
                } else if (aux == 2) {
                    type = ProductsTypes.Juegos;
                } else if (aux == 3) {
                    type = ProductsTypes.Otros;
                }

                control.listAllAmountOfProducts(type, name);
                break;
            case 12:
                break;

        }

    }

    public void removeProduct() {

    }

    public static void createProduct() {
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
                switch (categoriaP) {
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
                GameCategory categoriaJuego = GameCategory.Adventures;
                switch (categoriaJ) {
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

    public static void createClient() {
        //ID haremos una funcion aleatoria seguramente
        System.out.println("Introduce el ID del cliente");
        String idC = tecladoS();

        System.out.println("Introduce el nombre");
        String nameC = tecladoS();

        System.out.println("Introduce numero de telefono");
        String phoneC = tecladoS();

        control.createClient(idC, nameC, phoneC, LocalDateTime.now());
    }

    public static int teclado() {
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

    public static double tecladoD() {
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

    public static String tecladoS() {
        String texto;
        Scanner teclado = new Scanner(System.in);
        texto = teclado.next();
        return texto;
    }

}
