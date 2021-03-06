/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Controller;

import io.VideoClub.Model.Client;
import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Reservation;
import io.VideoClub.Model.RepositoryProduct;
import java.time.LocalDate;
import io.VideoClub.Model.Movie;
import io.VideoClub.Model.Game;
import io.VideoClub.Model.Others;
import io.VideoClub.Model.RepositoryClient;
import io.VideoClub.Model.RepositoryReserve;
import io.VideoClub.Model.Reservation.StatusReserve;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author pedro
 */
public class AppController implements IAppController {

    private RepositoryProduct pr = RepositoryProduct.getInstance();
    private RepositoryClient cl = RepositoryClient.getInstance();
    private RepositoryReserve re = RepositoryReserve.getInstance();

    /**
     * @return Devuelve el Set de productos
     */
    @Override
    public Set<Product> listAllProducts() {

        return pr.products;
    }

    /**
     * Lista los productos que estén Available y según un comparador
     *
     * @param c Comparator para ordenarlos en un orden en concreto
     * @return Set de productos available y ordenados según el Comparator c
     */
    @Override
    public Set<Product> listAllProducts(Comparator c) {
        Set<Product> ordenado = new TreeSet<>(c);
        Set<Product> aux = new TreeSet<>();

        for (Product p : pr.products) {
            if (p.getStatus().equals(Product.Status.AVAILABLE)) {
                aux.add(p);
            }
        }
        ordenado.addAll(aux);
        return ordenado;
    }

    /**
     * Ordena todos los productos según su tipo
     *
     * @param type tipo que queremos ordenar
     * @return Set de productos según el tipo type
     */
    @Override
    public Set<Product> listAllByType(ProductsTypes type) {
        Set<Product> p = new TreeSet<>();
        for (Product aux : pr.products) {
            if (aux != null && aux.getType().equals(type) && aux.getStatus().equals(Product.Status.AVAILABLE)) {
                p.add(aux);
            }
        }
        return p;
    }

    /**
     * Ordena los productos por un nombre
     *
     * @param name nombre que queremos ordenar
     * @return Set de productos según name
     */
    @Override
    public Set<Product> listAllByName(String name) {
        Set<Product> p = new TreeSet<>();
        for (Product pr : pr.products) {
            if (pr != null && name.equals(pr.getName()) && pr.getStatus().equals(Product.Status.AVAILABLE)) {
                p.add(pr);
            }
        }
        return p;
    }

    /**
     * ordena los productos según su nombre y su tipo
     *
     * @param name nombre del producto
     * @param type tipo del producto
     * @return Set de productos según su nombre y tipo
     */
    @Override
    public Set<Product> listAllByName(String name, ProductsTypes type) {
        Set<Product> aux2 = listAllByType(type);
        Set<Product> aux = listAllByName(name);
        Set<Product> p = new TreeSet<>();

        for (Product pr : aux) {
            for (Product pr2 : aux2) {
                if (pr.equals(pr2)) {
                    p.add(pr);
                }
            }
        }
        return p;
    }

    /**
     * ordena los productos según su estado (Avalieable, Reserved)
     *
     * @param status estado que queremos mostrar
     * @return Set de productos según status
     */
    @Override
    public Set<Product> listAllByStatus(Product.Status status) {
        Set<Product> p = new TreeSet<>();

        for (Product pr : pr.products) {
            if (pr != null && pr.getStatus().equals(status)) {
                p.add(pr);
            }
        }

        return p;
    }

    /**
     * Ordena los productos de tipo Otros
     *
     * @return Set de productos de tipo Otros
     */
    @Override
    public List<Product> listAllDifferentProducts() {
        Set<Product> p = listAllByType(ProductsTypes.Otros);
        List<Product> list = new ArrayList<>();
        list.addAll(p);
        return list;
    }

    /**
     * Ordena los productos de tipo Película
     *
     * @return Set de productos de tipo Película
     */
    @Override
    public List<Product> listAllDifferentMovies() {
        Set<Product> p = listAllByType(ProductsTypes.Peliculas);
        List<Product> list = new ArrayList<>();
        list.addAll(p);
        return list;
    }

    /**
     * Ordena los productos de tipo Juego
     *
     * @return Set de productos de tipo Juego
     */
    @Override
    public List<Product> listAllDifferentGames() {
        Set<Product> p = listAllByType(ProductsTypes.Juegos);
        List<Product> list = new ArrayList<>();
        list.addAll(p);
        return list;
    }

    /**
     * Ordena los productos según su nobmre en un Map
     *
     * @param name nombre del producto
     * @return Map según los productos y su posición
     */
    @Override
    public Map<Product, Integer> listAllAmountOfProducts(String name) {
        int cont = 0;
        Map<Product, Integer> aux = new TreeMap<>();
        Product aux2 = null;
        if (name != null) {
            for (Product pr : pr.products) {
                if (pr != null && pr.getName().equals(name) && pr.getStatus().equals(Product.Status.AVAILABLE)) {
                    if (aux2 == null) {
                        aux2 = pr;
                    }
                    cont++;
                }
            }
            aux.put(aux2, cont);
        }
        return aux;
    }

    /**
     * Ordena los productos según su tipo y su nombre en un Map
     *
     * @param type tipo del producto
     * @param name nombre del producto
     * @return devuelve un Map según el producto y su posición
     */
    @Override
    public Map<Product, Integer> listAllAmountOfProducts(ProductsTypes type, String name) {
        int cont = 0;
        Map<Product, Integer> aux = new TreeMap<>();
        Product aux2 = null;

        for (Product pr : pr.products) {
            if (pr != null && pr.getName().equals(name) && pr.getType().equals(type) && pr.getStatus().equals(Product.Status.AVAILABLE)) {
                if (aux2 == null) {
                    aux2 = pr;
                }
                cont++;
            }
        }
        aux.put(aux2, cont);
        return aux;
    }

    /**
     * @return Devuelve el Set de clientes
     */
    @Override
    public Set<IClient> listAllClients() {
        return cl.clients;
    }

    /**
     * Ordena los clientes según un comparator
     *
     * @param c Comparator que recibe para ordenar a los clientes
     * @return Set de clientes según el Comparator c
     */
    @Override
    public Set<IClient> listAllClients(Comparator c) {
        Set<IClient> ordenado = new TreeSet<>(c);
        ordenado.addAll(cl.clients);
        return ordenado;
    }

    /**
     * Ordena a todos los clientes que tienen una reserva no finalizada
     *
     * @return Set de clientes con reserva no finalizada
     */
    @Override
    public Set<IClient> listAllClientsWithReservationsNotFinished() {
        Set<IClient> ordenado = new TreeSet<>();

        for (IClient c : cl.clients) {
            for (Reservation r : re.reserves) {
                if (r.getCli() == c && r.getStatus() != Reservation.StatusReserve.FINISHED) {
                    ordenado.add(c);
                }
            }
        }
        return ordenado;
    }

    /**
     * Comprueba si el código es de un producto del Set
     *
     * @param key código del producto
     * @return True si el código coincide con un producto. False si no coincide
     */
    public boolean isAvailableProductb(String key) {
        boolean result = false;

        for (Product p : pr.products) {
            if (p.getKey().equals(key)) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Comprueba si el ID coincide con el de algún cliente
     *
     * @param id id de un cliente
     * @return True si coincide con algún cliente. False si no coincide
     */
    public boolean isAvailableClient(String id) {
        boolean result = false;

        for (IClient c : cl.clients) {
            if (c.getID().equals(id)) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * @return Devuelve el Set de reservas
     */
    @Override
    public Set<Reservation> listAllReservations() {
        return re.reserves;
    }

    /**
     * Ordena todas las reservas según un comparador
     *
     * @param c Comparator que recibe para ordenar a las reservas
     * @return Set según el Comparator c
     */
    @Override
    public Set<Reservation> listAllReservations(Comparator c) {
        Set<Reservation> ordenado = new TreeSet<>(c);
        ordenado.addAll(re.reserves);
        return ordenado;
    }

    /**
     * Ordena todas las reservas según su estado
     *
     * @param status Estado que recibe para ordenar las reservas
     * @return Set según el estado de las reservas
     */
    @Override
    public Set<Reservation> listAllReservations(Reservation.StatusReserve status) {
        Set<Reservation> r = new TreeSet<>();

        for (Reservation aux : re.reserves) {
            if (aux.getStatus().equals(status)) {
                r.add(aux);
            }
        }
        return r;
    }

    /**
     * Ganancias de la empresa
     *
     * @return ganancia en doube
     */
    @Override
    public double getIncommings() {
        double result = 0;
        Set<Reservation> aux = listAllReservations(Reservation.StatusReserve.FINISHED);
        for (Reservation reserve : aux) {
            result = result + reserve.pro.getPrize();
        }
        Set<Reservation> aux1 = listAllReservations(Reservation.StatusReserve.PENDING);

        for (Reservation reserve : aux1) {

            result = result + reserve.pro.getPrize() + 10;

        }
        return result;
    }

    /**
     * Ganancias de la empresa en una fecha en concreto
     *
     * @param from fecha de donde queremos sacar las ganancias
     * @return ganancias en double
     */
    @Override
    public double getIncommings(LocalDate from) {
        double result = 0;

        Set<Reservation> aux = listAllReservations(Reservation.StatusReserve.FINISHED);
        for (Reservation reserve : aux) {
            if (reserve.end.isEqual(from)) {
                result = result + reserve.pro.getPrize();

            }
        }
        Set<Reservation> aux1 = listAllReservations(Reservation.StatusReserve.PENDING);

        for (Reservation reserve : aux1) {

            if (reserve.end.isEqual(from)) {
                result = result + reserve.pro.getPrize() + 10;

            }

        }

        return result;
    }

    /**
     * ganancias de la empresa en un tiempo en concreto
     *
     * @param from donde empieza a contar las ganancias
     * @param to donde termina de contar las ganancias
     * @return ganancias en double
     */
    @Override
    public double getIncommings(LocalDate from, LocalDate to) {
        double result = 0;

        for (LocalDate desde = from; from.isBefore(to) || from.isEqual(to);
                from = from.plusDays(1)) {
            result = result + getIncommings(from);

        }

        return result;
    }

    @Override
    public Map<IClient, Double> resumeAllIncomingsByClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Crea un producto Other y lo introduce en el Set
     *
     * @param name nombre del producto
     * @param description descripcion del producto
     * @param prize precio del producto
     * @return True si se ha podido introducir en el Set. False si no se ha
     * podido introducir
     */
    @Override
    public boolean createProduct(String name, String description, double prize) { //otros
        return pr.products.add(new Others(name, description, prize));
    }

    /**
     * Crea un producto Pelicula y lo introduce en el Set de productos
     *
     * @param type tipo (pelicula)
     * @param name nombre del producto
     * @param description descripcion del producto
     * @param precio precio del producto
     * @param cat categoria de película del producto
     * @param minAge edad mínima del producto
     * @return True si se ha introducido en el Set. False si no se ha podido
     * introducir
     */
    @Override
    public boolean createMovie(ProductsTypes type, String name, String description, double precio, MovieCategory cat, int minAge) {
        Movie m = new Movie(name, description, precio, cat, minAge);
        m.setType(ProductsTypes.Peliculas);
        return pr.products.add(m);
    }

    /**
     * Crea un producto Juego y lo introduce en el Set de productos
     *
     * @param type tipo (juego)
     * @param name nombre del producto
     * @param description descripcion del producto
     * @param precio precio del producto
     * @param cat categoriía de juego del producto
     * @param minAge edad mínima del producto
     * @return True si se ha introducido en el Set. False si no se ha podido
     * introducir
     */
    @Override
    public boolean createGame(ProductsTypes type, String name, String description, double precio, GameCategory cat, int minAge) {
        Game g = new Game(name, description, precio, cat, minAge);
        g.setType(ProductsTypes.Peliculas);
        return pr.products.add(g);
    }

    /**
     * Crea un Cliente y lo introduce en el Set de clientes
     *
     * @param id id del cliente
     * @param name nombre del cliente
     * @param phone teléfono del cliente
     * @param time fecha actual
     * @return True si se ha introducido en el Set de clientes. False si no se
     * ha podido introducir
     */
    @Override
    public boolean createClient(String id, String name, String phone, LocalDateTime time) {
        return cl.clients.add(new Client(id, name, phone, time));
    }

    /**
     * Elimina un cliente según su id
     *
     * @param id id de un cliente
     * @return True si se ha eliminado del Set. False si no se ha podido
     * eliminar
     */
    @Override
    public boolean removeClient(String id) {
        boolean result = true;

        if (id != null) {
            for (Reservation r : re.reserves) {
                if (r.getCli().getID().equals(id)) {
                    result = false;
                    break;
                }
            }

            if (result) {
                for (IClient c : cl.clients) {
                    if (c.getID().equals(id)) {
                        cl.clients.remove(c);
                        result = true;
                        break;
                    }
                }

            }

        }
        return result;
    }

    /**
     * Edita un cliente del Set
     *
     * @param e cliente con los cambios
     * @return True si se ha podido modificar. False si no se ha podido
     * modificar
     */
    @Override
    public boolean editClient(IClient e) {
        boolean result = false;
        if (e != null) {
            for (IClient c : cl.clients) {
                if (c != null && c.equals(e)) {
                    c.setName(e.getName());
                    c.setPhone(e.getPhone());
                    c.setTime(LocalDateTime.now());
                    result = true;
                    break;
                }
            }

        }
        return result;
    }

    /**
     * Clona un producto del Set
     *
     * @param name nombre del producto a clonar
     * @return True si se ha podido clonar. False si no se ha podido
     */
    @Override
    public boolean addProduct(String name) {
        boolean result = false;

        for (Product p : pr.products) {
            if (p.getName().equals(name)) {
                if (p.getType() == ProductsTypes.Juegos) {
                    Game aux = (Game) p;
                    try {
                        aux.clone();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pr.products.add(aux);
                    result = true;
                } else if (p.getType() == ProductsTypes.Peliculas) {
                    Movie aux = (Movie) p;
                    try {
                        aux.clone();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pr.products.add(aux);
                    result = true;
                } else if (p.getType() == ProductsTypes.Otros) {
                    Others aux = (Others) p;
                    try {
                        aux.clone();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pr.products.add(aux);
                    result = true;
                }
            }
        }

        return result;
    }

    /**
     * Elimina un producto del Set
     *
     * @param name nombre del producto a eliminar
     * @return True si se ha podido eliminar. False si no se ha podido eliminar
     */
    @Override
    public boolean removeProduct(String name) {
        boolean result = false;

        for (Product p : pr.products) {
            if (p != null && p.getName().equals(name)) {
                pr.products.remove(p);
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Edita un producto del Set
     *
     * @param key código del producto a editar
     * @param newP el producto con las modificaciones
     * @return True si se ha podidio modificar. False si no se ha podidio
     * modificar
     */
    @Override
    public boolean editProduct(String key, Product newP) {
        boolean result = false;
        if (newP != null) {
            for (Product p : pr.products) {
                if (p.getKey().equals(key)) {
                    p.setName(newP.getName());
                    p.setDescription(newP.getDescription());
                    p.setPrize(newP.getPrize());
                    p.setStatus(newP.getStatus());
                    p.setType(newP.getType());
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Busca si un producto existe en el Set
     *
     * @param name nombre del producto que se busca
     * @return devuelve el producto buscado si existe. Null si no lo encuentra
     */
    @Override
    public Product isAvailableProduct(String name) {
        Product p = null;

        for (Product pr : pr.products) {
            if (pr != null && pr.getName().equals(name) && pr.getStatus().equals(Product.Status.AVAILABLE)) {
                p = pr;
                break;
            }
        }
        return p;
    }

    /**
     * Reserva un producto del Set
     *
     * @param prod producto que se va a reservar
     * @param client cliente que hace la reserva
     * @return True si se ha podido añadir la reserva. False si no se ha podido
     * añadir
     */
    @Override
    public boolean reserveProduct(Product prod, IClient client) {
        Reservation r = new Reservation(prod, client);
        prod.setStatus(Product.Status.RESERVED);
        return re.reserves.add(r);

    }

    /**
     * Cierra una reserva del Set de reservas
     *
     * @param reserve reserva que se quiere cerrar
     * @return el precio de la reserva
     */
    @Override
    public double closeReservation(Reservation reserve) {
        double result = 0;
        if (reserve != null) {
            reserve.setFinished(LocalDate.now());
            reserve.setStatus(Reservation.StatusReserve.FINISHED);
            reserve.pro.setStatus(Product.Status.AVAILABLE);
            result = reserve.pro.getPrize();

        }

        return result;
    }

    /**
     * Lee la base de datos en XML de productos
     *
     * @return true si la lee. False si no la puede leer
     */
    @Override
    public boolean loadCatalogFromDDBB() {
        boolean aux = false;
        try {
            File archivo = new File("productos.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);

            document.getDocumentElement().normalize();

            NodeList e = document.getElementsByTagName("Producto");
            if (e != null) {
                for (int i = 0; i < e.getLength(); i++) {
                    Node nodo = e.item(i);
                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) nodo;

                        String n = element.getElementsByTagName("Nombre").item(0).getTextContent();
                        String d = element.getElementsByTagName("Descripcion").item(0).getTextContent();
                        double p = Integer.valueOf(element.getElementsByTagName("Precio").item(0).getTextContent());
                        String k = element.getElementsByTagName("Key").item(0).getTextContent();
                        //Status es= Status.valueOf(element.getElementsByTagName("Estado").item(0).getTextContent());
                        ProductsTypes t = ProductsTypes.valueOf(element.getElementsByTagName("Tipo").item(0).getTextContent());

                        if (t == ProductsTypes.Juegos) {
                            GameCategory g = GameCategory.valueOf(element.getElementsByTagName("GameCategory").item(0).getTextContent());
                            int ed = Integer.valueOf(element.getElementsByTagName("Edad minima").item(0).getTextContent());
                            Game ass = new Game(n, d, p, g, ed);
                            pr.products.add(ass);
                            ass.setKey(k);
                            //  ass.setStatus();

                        } else if (t == ProductsTypes.Peliculas) {
                            MovieCategory m = MovieCategory.valueOf(element.getElementsByTagName("MovieCategory").item(0).getTextContent());
                            int ed = Integer.valueOf(element.getElementsByTagName("Edad minima").item(0).getTextContent());
                            Movie ass = new Movie(n, d, p, m, ed);
                            pr.products.add(ass);
                            ass.setKey(k);
                        } else {
                            Others o = new Others(n, d, p);
                            o.setKey(k);
                            pr.products.add(o);

                        }

                        aux = true;
                    }
                }
            }

        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }

    /**
     * Lee una base de datos en XML de clientes
     *
     * @return True si la ha podido leer. False si no ha podido leerla.
     */
    @Override
    public boolean loadClientsFromDDBB() {
        boolean aux = false;
        try {
            File archivo = new File("clientes.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);

            document.getDocumentElement().normalize();

            NodeList e = document.getElementsByTagName("Cliente");
            if (e != null) {
                for (int i = 0; i < e.getLength(); i++) {
                    Node nodo = e.item(i);

                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) nodo;
                        String id = element.getElementsByTagName("ID").item(0).getTextContent();
                        String n = element.getElementsByTagName("Nombre").item(0).getTextContent();
                        String t = element.getElementsByTagName("Telefono").item(0).getTextContent();
                        LocalDateTime l = LocalDateTime.parse(element.getElementsByTagName("Fecha").item(0).getTextContent());

                        Client c = new Client(id, n, t, l);
                        cl.clients.add(c);
                        System.out.println("");
                        aux = true;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return aux;
    }

    /**
     * Lee una base de datos en XML de reservas
     *
     * @return True si ha podido leerla. False si no ha podido
     */
    @Override
    public boolean loadReservationsFromDDBB() {
        boolean aux = false;
        try {
            File archivo = new File("reservations.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);

            document.getDocumentElement().normalize();

            NodeList e = document.getElementsByTagName("Producto");

            if (e != null) {
                for (int i = 0; i < e.getLength(); i++) {
                    Node nodo = e.item(i);

                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) nodo;

                        StatusReserve r = StatusReserve.valueOf(element.getElementsByTagName("Estado").item(0).getTextContent());
                        Product p = Product.class.cast(element.getElementsByTagName("Producto").item(0).getTextContent());
                        IClient c = IClient.class.cast(element.getElementsByTagName("Cliente").item(0).getTextContent());
                        LocalDate in = LocalDate.parse(element.getElementsByTagName("FechaInicio").item(0).getTextContent());
                        LocalDate ed = LocalDate.parse(element.getElementsByTagName("FechaFinal").item(0).getTextContent());
                        LocalDate fi = LocalDate.parse(element.getElementsByTagName("FechaEntrega").item(0).getTextContent());

                        Reservation ass = new Reservation(p, c);
                        ass.setIni(in);
                        ass.setEnd(ed);
                        ass.setFinished(fi);
                        re.reserves.add(ass);
                        aux = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;
    }

    /**
     * Lee las tres bases de datos a la vez
     *
     * @return True si ha podido leerlas. False si no
     */
    @Override
    public boolean loadAllDDBB() {
        return loadCatalogFromDDBB() && loadClientsFromDDBB() && loadReservationsFromDDBB();
    }

    /**
     * Guarda el Set de productos en una base de datos XML
     *
     * @return True si ha podido guardarla. False si no ha podido
     */
    @Override
    public boolean saveCatalogFromDDBB() {
        boolean aux = false;
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build;
            build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("Repositorio");

            for (Product products : pr.products) {

                Element con = doc.createElement("Producto");

                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(products.getName()));
                con.appendChild(nombre);

                Element description = doc.createElement("Descripcion");
                description.appendChild(doc.createTextNode(products.getDescription()));
                con.appendChild(description);

                Element prize = doc.createElement("Precio");
                prize.appendChild(doc.createTextNode(String.valueOf(products.getPrize())));
                con.appendChild(prize);

                Element key = doc.createElement("Key");
                key.appendChild(doc.createTextNode(products.getKey()));
                con.appendChild(key);

                Element status = doc.createElement("Estado");
                status.appendChild(doc.createTextNode(products.getStatus().toString()));
                con.appendChild(status);

                Element type = doc.createElement("Tipo");
                type.appendChild(doc.createTextNode(products.getType().toString()));
                con.appendChild(type);

                root.appendChild(con);
            }
            doc.appendChild(root);

            Source source = new DOMSource(doc);

            Result result = new StreamResult(new java.io.File("productos.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            aux = true;

        } catch (ParserConfigurationException e) {

        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }

    /**
     * Guarda el Set de clientes en una base de datos XML
     *
     * @return True si ha podido guardarla. False si no ha podido
     */
    @Override
    public boolean saveClientsFromDDBB() {
        boolean aux = false;
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build;
            build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("Repositorio");

            for (IClient clients : cl.clients) {

                Element con = doc.createElement("Cliente");

                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(clients.getName()));
                con.appendChild(nombre);

                Element id = doc.createElement("ID");
                id.appendChild(doc.createTextNode(clients.getID()));
                con.appendChild(id);

                Element phone = doc.createElement("Telefono");
                phone.appendChild(doc.createTextNode(clients.getPhone()));
                con.appendChild(phone);

                Element date = doc.createElement("Fecha");
                date.appendChild(doc.createTextNode(clients.getTime().toString()));
                con.appendChild(date);

                root.appendChild(con);
            }
            doc.appendChild(root);

            Source source = new DOMSource(doc);

            Result result = new StreamResult(new java.io.File("clientes.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            aux = true;
        } catch (ParserConfigurationException e) {

        } catch (TransformerException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }

    /**
     * Guarda el Set de reservas en una base de datos XML
     *
     * @return True si ha podido guardarla. False si no ha podido
     */
    @Override
    public boolean saveReservationsFromDDBB() {
        boolean aux = false;
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build;
            build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("Repositorio");

            for (Reservation reserves : re.reserves) {

                Element con = doc.createElement("Reservas");

                Element status = doc.createElement("Estado");
                status.appendChild(doc.createTextNode(reserves.getStatus().toString()));
                con.appendChild(status);

                Element product = doc.createElement("Producto");
                product.appendChild(doc.createTextNode(reserves.getPro().toString()));
                con.appendChild(product);

                Element iclient = doc.createElement("Cliente");
                iclient.appendChild(doc.createTextNode(reserves.getCli().toString()));
                con.appendChild(iclient);

                Element ini = doc.createElement("Fecha inicio");
                ini.appendChild(doc.createTextNode(reserves.getIni().toString()));
                con.appendChild(ini);

                Element end = doc.createElement("Fecha final");
                end.appendChild(doc.createTextNode(reserves.getEnd().toString()));
                con.appendChild(end);

                Element finished = doc.createElement("Fecha entrega");
                finished.appendChild(doc.createTextNode(reserves.getFinished().toString()));
                con.appendChild(finished);

                root.appendChild(con);
            }
            doc.appendChild(root);

            Source source = new DOMSource(doc);

            Result result = new StreamResult(new java.io.File("productos.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            aux = true;

        } catch (ParserConfigurationException e) {

        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }

    @Override
    public boolean saveAllDDBB() {
        return saveCatalogFromDDBB() && saveClientsFromDDBB() && saveReservationsFromDDBB();
    }

}
