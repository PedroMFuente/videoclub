package io.VideoClub.View;

import io.VideoClub.Comparators.ClientComparator;
import io.VideoClub.Comparators.ProductComparator;
import io.VideoClub.Comparators.ReservationComparator;
import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Client;
import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Enums.SortOptions;
import io.VideoClub.Model.Game;
import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Movie;
import io.VideoClub.Model.Others;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.RepositoryClient;
import io.VideoClub.Model.RepositoryProduct;
import io.VideoClub.Model.RepositoryReserve;
import io.VideoClub.Model.Reservation;
import io.VideoClub.Utils.Utils;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Set;

public class GUI {

    static AppController control = new AppController();

    private static Utils u = new Utils();
    private static RepositoryProduct pr = RepositoryProduct.getInstance();
    private static RepositoryClient cl = RepositoryClient.getInstance();
    private static RepositoryReserve re = RepositoryReserve.getInstance();
    private static ProductComparator productComparator;
    private static ReservationComparator reservationComparator;
    private static ClientComparator clientComparator;

    public static void principal() {
        int opcion = 0;

        do {
            System.out.println("Que desea saber ");
            System.out.println("1) Producto");
            System.out.println("2) Cliente");
            System.out.println("3) Reserva");
            System.out.println("4) Salir");
            opcion = u.tecladoI();

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
            opcion = u.tecladoI();
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
                removeProduct();
                break;

            case 5:

                break;

        }

    }

    public static void editProduct() {
        System.out.println("Introduce la clave del producto que quieres editar");
        String key = u.tecladoS();
        Product p = createProducts();
        p.setKey(key);
        if (control.editProduct(key, p)) {
            System.out.println("Producto editado correctamente");
        } else {
            System.out.println("Comprueba que la key este correcta");
        }

    }

    public static void client() {
        int opcion = 0;
        do {
            System.out.println("1) Crear cliente");
            System.out.println("2) Editar cliente");
            System.out.println("3) Listar cliente");
            System.out.println("4) Borrar cliente");
            System.out.println("5) FALTAA"); //FALTA
            System.out.println("6) Salir");
            opcion = u.tecladoI();
        } while (opcion < 1 || opcion > 6);

        switch (opcion) {
            case 1:
                createClient();
                break;
            case 2:
                editClient();
                break;

            case 3:
                listClient();
                break;

            case 4:
                removeClient();
                break;

            case 5:
                break;

        }

    }

    //FALTA
    public static void editClient() {
        System.out.println("Introduce el id del cliente a modificar: ");
        String ID = u.tecladoS();
        IClient c = createClients();
        if (control.editClient(c)) {
            System.out.println("Cliente editado correctamente");
        } else {
            System.out.println("No se ha podido editar el cliente");
        }

    }

    public static void removeClient() {
        System.out.println("Introduce el ID del cliente que quieres borrar");
        String id = u.tecladoS();
        if (control.removeClient(id)) {
            System.out.println("Cliente borrado con exito");
        } else {
            System.out.println("No se ha podido borrar el cliente, comprueba que existe el ID y que no tiene reservas pendientes");
        }

    }

    public static void listClient() {
        int opcion = 0;

        do {
            System.out.println("Listado de clientes");
            System.out.println("1)Listar todos los clientes");
            System.out.println("2)Listar todos los clientes ordenados");
            System.out.println("3)Listar todos los clientes con reservas no finalizadas");
            System.out.println("4)Salir");
            opcion = u.tecladoI();
        } while (opcion < 1 || opcion > 3);

        switch (opcion) {
            case 1:
                u.listClientS(control.listAllClients());
                break;
            case 2:
                sortClient();
                break;
            case 3:
                u.listClientS(control.listAllClientsWithReservationsNotFinished());
                break;
            case 4:
                break;
        }
    }

    public static void reserve() {
        int opcion = 0;

        do {
            System.out.println("1) Crear reserva");
            System.out.println("2) Listar reserva");
            System.out.println("3) Terminar reserva");
            System.out.println("4) Salir");

            opcion = u.tecladoI();

        } while (opcion < 1 || opcion > 4);

        switch (opcion) {
            case 1:
                createReserve();
                break;

            case 2:
                listReserve();
                break;

            case 3:
                finishReserve();
                break;

            case 4:
                break;

        }
    }

    
    public static void createReserve() {    
        int opcion = 0;
        Set<Product> aux = null;
        Product p = null;
        IClient c = null;
        System.out.println("Introduce el nombre del producto que quieres reservar:");
        String name = u.tecladoS();
        do{
        System.out.println("-----Tipo de producto-------");
        System.out.println("1)Pelicula \n 2)Juego \n 3)Otros");
        opcion = u.tecladoI();
        }while(opcion < 1 || opcion > 3);
        
        switch(opcion){
            case 1:
                aux = control.listAllByName(name, ProductsTypes.Peliculas);
                break;
            case 2:
                aux = control.listAllByName(name, ProductsTypes.Juegos);
                break;
            case 3:
                aux = control.listAllByName(name, ProductsTypes.Otros);
                break;
        }
        
        
        if(aux.isEmpty()){
            System.out.println("No se encuentra ningun producto con ese nombre");
        } else {
             System.out.println("----------Productos----------");
             for(Product pr : aux){
                 p = pr;
                 break;
             }
             System.out.println("Introduce tu dni");
             String nameC = u.tecladoS();
             
             for(IClient client : cl.clients){
                 if(client.getID().equals(nameC)){
                     c = client;
                     break;
                 }   
             }
             
          if(c != null && p != null){
                 control.reserveProduct(p, c);
             }
             
        }
        
    }

    public static void listReserve() {
        int opcion = 0;
        int aux = 0;
        Reservation.StatusReserve status = null;

        do {

            System.out.println("1) Listar todos los reservas");
            System.out.println("2) Listar todos los reservas por estado");
            System.out.println("3) Ordenar las reservas");
            System.out.println("4) Salir");

            opcion = u.tecladoI();

        } while (opcion < 1 || opcion > 4);

        switch (opcion) {

            case 1:
                u.listReservationS(control.listAllReservations());
                break;
            case 2:
                do {
                    System.out.println("---¿Que estado de reserva buscas?---");
                    System.out.println("1) Activas ");
                    System.out.println("2) Finalizadas");
                    System.out.println("3) Pendientes por pagar (Pasadas de tiempo limite) ");
                    aux = u.tecladoI();
                } while (aux < 1 || aux > 3);
                if (aux == 1) {
                    status = Reservation.StatusReserve.ACTIVE;
                } else if (aux == 2) {
                    status = Reservation.StatusReserve.FINISHED;
                } else if (aux == 3) {
                    status = Reservation.StatusReserve.PENDING;
                }

                u.listReservationS(control.listAllReservations(status));
                break;

            case 3:
                sortReservation();
                break;

        }
    }

    public static void finishReserve() {
        System.out.println("Dime tu nombre ");
        String name = u.tecladoS();
        System.out.println("Dime el nombre del producto");
        String name2 = u.tecladoS();

        for (Reservation reserve : re.reserves) {
            if (reserve.cli.getName().equals(name) && reserve.pro.getName().equals(name2)) {
                System.out.println("El total de su reserva ha sido: "+control.closeReservation(reserve)+"€" );
                break;
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
            opcion = u.tecladoI();
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
            opcion = u.tecladoI();
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

    public static void sortReservation() {
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
            System.out.println("8)Ordenar por fecha de reserva mas reciente");
            System.out.println("9)Ordenar por fecha de reserva mas antigua");
            opcion = u.tecladoI();
        } while (opcion < 1 || opcion > 9);
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
            case 8:
                result = SortOptions.DatePlusR;
                reservationComparator.setOption(result);
                break;
            case 9:
                result = SortOptions.DateLessR;
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
            opcion = u.tecladoI();

        } while (opcion < 1 || opcion > 12);

        switch (opcion) {

            case 1:
                u.listProductS(control.listAllProducts());
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
                    aux = u.tecladoI();
                } while (aux < 1 || aux > 3);
                if (aux == 1) {
                    type = ProductsTypes.Peliculas;
                } else if (aux == 2) {
                    type = ProductsTypes.Juegos;
                } else if (aux == 3) {
                    type = ProductsTypes.Otros;
                }

                u.listProductS(control.listAllByType(type));
                break;

            case 4:
                System.out.println("Dime el nombre del producto");
                name = u.tecladoS();
                ;
                u.listProductS(control.listAllByName(name));
                break;
            case 5:

                System.out.println("Dime el nombre del producto");
                name = u.tecladoS();
                do {
                    System.out.println("---¿Que categoría de producto buscas?---");
                    System.out.println("1) Peliculas");
                    System.out.println("2) Juegos");
                    System.out.println("3) Otros ");
                    aux = u.tecladoI();
                } while (aux < 1 || aux > 3);
                if (aux == 1) {
                    type = ProductsTypes.Peliculas;
                } else if (aux == 2) {
                    type = ProductsTypes.Juegos;
                } else if (aux == 3) {
                    type = ProductsTypes.Otros;
                }

                u.listProductS(control.listAllByName(name, type));

                break;
            case 6:

                do {
                    System.out.println("1) Disponibles");
                    System.out.println("2) Reservados");

                    aux = u.tecladoI();
                } while (aux < 1 || aux > 2);
                if (aux == 1) {
                    status = Product.Status.AVAILABLE;
                } else if (aux == 2) {
                    status = Product.Status.RESERVED;
                }

                u.listProductS(control.listAllByStatus(status));

                break;
            case 7:
                u.listProductL(control.listAllDifferentProducts());
                break;
            case 8:
                u.listProductL(control.listAllDifferentMovies());
                break;
            case 9:
                u.listProductL(control.listAllDifferentGames());
                break;
            case 10:
                System.out.println("¿Que nombre buscas?");
                name = u.tecladoS();
                control.listAllAmountOfProducts(name);
                break;
            case 11:
                System.out.println("¿Que nombre buscas?");
                name = u.tecladoS();
                do {
                    System.out.println("---¿Que categoría de producto buscas?---");
                    System.out.println("1) Peliculas");
                    System.out.println("2) Juegos");
                    System.out.println("3) Otros ");
                    aux = u.tecladoI();
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

    public static void removeProduct() {
        Product p = null;
        System.out.println("Introduce el producto que quieres borrar");
        String name = u.tecladoS();
        p = control.isAvailableProduct(name);
        if (p != null) {
            pr.products.remove(p);
            System.out.println("El producto ha sido borrado con exito");
        } else {
            System.out.println("El producto no existe, comprueba que has introducido un nombre correcto");
        }
    }

    public static void createProduct() {
        System.out.println("Tipo de producto : ");
        System.out.println("1)Pelicula \n 2)Juego \n 3)Otro");
        int categoria = u.tecladoI();

        System.out.println("Nombre:");
        String nombre = u.tecladoS();

        System.out.println("Descripción: ");
        String descripcion = u.tecladoS();

        System.out.println("Precio:");
        double precio = u.tecladoD();

        switch (categoria) {
            case 1:
                System.out.println("Creando película : ");
                System.out.println("Movie Category");
                System.out.println("1)Horror \n 2)Love \n 3)Action \n 4)SciFi");
                int categoriaP = u.tecladoI();
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
                System.out.println("Edad minima");
                int edadP = u.tecladoI();
                control.createMovie(ProductsTypes.Peliculas, nombre, descripcion, precio, categoriaPelicula, edadP);
                break;
            case 2:
                System.out.println("Creando Juego : ");

                System.out.println("Categoría del juego: ");
                System.out.println("1)Adventures \n 2)Cars \n 3)Shooter");
                int categoriaJ = u.tecladoI();
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
                System.out.println("Edad minima");
                int edadJ = u.tecladoI();
                control.createGame(ProductsTypes.Juegos, nombre, descripcion, precio, categoriaJuego, edadJ);
                break;
            case 3:
                //enum categoria product
                System.out.println("Otros");
                control.createProduct(nombre, descripcion, precio);
                break;
        }
    }

    public static Product createProducts() {
        Product p = null;

        System.out.println("Tipo de producto : ");
        System.out.println("1)Pelicula \n 2)Juego \n 3)Otro");
        int categoria = u.tecladoI();

        System.out.println("Nombre:");
        String nombre = u.tecladoS();

        System.out.println("Descripción: ");
        String descripcion = u.tecladoS();

        System.out.println("Precio:");
        double precio = u.tecladoD();

        switch (categoria) {
            case 1:
                System.out.println("Creando película : ");
                System.out.println("Movie Category");
                System.out.println("1)Horror \n 2)Love \n 3)Action \n 4)SciFi");
                int categoriaP = u.tecladoI();
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
                System.out.println("Edad minima");
                int edadP = u.tecladoI();
                p = new Movie(nombre, descripcion, precio, categoriaPelicula, edadP);
                break;
            case 2:
                System.out.println("Creando Juego : ");

                System.out.println("Categoría del juego: ");
                System.out.println("1)Adventures \n 2)Cars \n 3)Shooter");
                int categoriaJ = u.tecladoI();
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
                System.out.println("Edad minima");
                int edadJ = u.tecladoI();
                p = new Game(nombre, descripcion, precio, categoriaJuego, edadJ);
                break;
            case 3:
                //enum categoria product
                System.out.println("Otros");
                p = new Others(nombre, descripcion, precio);
                break;
        }
        return p;
    }

    public static void createClient() {

        System.out.println("Introduce el ID del cliente");
        String idC = u.tecladoS();

        System.out.println("Introduce el nombre");
        String nameC = u.tecladoS();

        System.out.println("Introduce numero de telefono");
        String phoneC = u.tecladoS();

        control.createClient(idC, nameC, phoneC, LocalDateTime.now());
    }

    public static IClient createClients() {
        System.out.println("Introduce el ID del cliente");
        String idC = u.tecladoS();

        System.out.println("Introduce el nombre");
        String nameC = u.tecladoS();

        System.out.println("Introduce numero de telefono");
        String phoneC = u.tecladoS();

        Client c = new Client(idC, nameC, phoneC, LocalDateTime.now());

        return c;
    }

}
