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
    
    public static void principal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("1)Crear producto");
            System.out.println("2)Editar producto");
            System.out.println("3)Listar producto");
            System.out.println("4)Salir");
            int opcion = teclado();
            if (opcion != 4) {
                menuI1(opcion);
            } else {
                System.out.println("Hasta luego");
                salir = true;
            }

        }
    }

    public void sortClient() {
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
        for(IClient c : muestra){
            System.out.println(c);
        }
    }

    public void sortProduct() {
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
        for(Product p : muestra){
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
        for(Reservation r : muestra){
            System.out.println(r);
        }
    }
    

    public void edita(int teclado) {

        int opcion;

        opcion = teclado;
        menuI2(opcion);

        System.out.println("1)Borrar");
        System.out.println("2)Estado");
    }

    public void listar(int teclado) {
        System.out.println("1)Ordenar por nombre");
        System.out.println("2)Ordenar por estado");
        System.out.println("3)Ordenar por Género");
        int opcion;
        opcion = teclado;
        menuI3(opcion);
    }

    private static void menuI1(int opcion) {
        switch (opcion) {
            case 1: {
                menucreateproduct();
                System.out.println("Producto añadido");
                break;
            }
            case 2: {
                System.out.println(control.listAllProducts());
                System.out.println("producto editar");

                //control.editProduct("hey",hey); //recibe String key, Product newP
                break;
            }
            case 3: {
                System.out.println(control.listAllProducts()); //devuelve un lista set
                break;
            }
            default:
                System.out.println("Acción no válida");
                break;

        }
    }

    public static void menuI2(int opcion) {
        switch (opcion) {

            case 1: {
                control.removeProduct();//recibe string name

            }
            case 2: {
                control.reserveProduct();// recibe Product prod, IClient client
            }

        }
    }

    public static void menuI3(int opcion) {
        switch (opcion) {
            case 1: {
                control.listAllByName();//devuelve lista set

            }
            case 2: {
                control.listAllByType();//devuelve lista set

            }
            case 3: {
                control.listAllByStatus();//devuelve lista set
            }

        }
    }

    public static void menucreateproduct() {
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
