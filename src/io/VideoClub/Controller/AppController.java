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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author pedro
 */
public class AppController implements IAppController {

    private RepositoryProduct pr = RepositoryProduct.getInstance();
    private RepositoryClient cl = RepositoryClient.getInstance();
    private RepositoryReserve re = RepositoryReserve.getInstance();

    @Override
    public Set<Product> listAllProducts() {
        return pr.products;
    }

    @Override
    public Set<Product> listAllProducts(Comparator c) {
        Set<Product> ordenado = new TreeSet<>(c);
        ordenado.addAll(pr.products);
        return ordenado;
    }

    @Override
    public Set<Product> listAllByType(ProductsTypes type) {
        Set<Product> p = new TreeSet<>();
        for (Product aux : pr.products) {
            if (aux != null && aux.getType().equals(type)) {
                p.add(aux);
            }
        }
        /*for (Product pr : pr.products) {
            switch (type) {
                case Peliculas:
                    if (pr instanceof Movie) {
                        p.add(pr);
                    }
                    break;
                case Juegos:
                    if (pr instanceof Game) {
                        p.add(pr);
                    }
                    break;
                case Otros:
                    if (pr instanceof Otros) {
                        p.add(pr);
                    }
                    break;
                default:
                    p.addAll(pr.products);
                    break;

            }
        }*/
        return p;
    }

    @Override
    public Set<Product> listAllByName(String name) {
        Set<Product> p = new TreeSet<>();
        for (Product pr : pr.products) {
            if (pr != null && name.equals(pr.getName())) {
                p.add(pr);
            }
        }
        return p;
    }

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

    @Override
    public List<Product> listAllDifferentProducts() {
        Set<Product> p = listAllByType(ProductsTypes.Otros);
        List<Product> list = new ArrayList<>();
        list.addAll(p);
        return list;
    }

    @Override
    public List<Product> listAllDifferentMovies() {
        Set<Product> p = listAllByType(ProductsTypes.Peliculas);
        List<Product> list = new ArrayList<>();
        list.addAll(p);
        return list;
    }

    @Override
    public List<Product> listAllDifferentGames() {
        Set<Product> p = listAllByType(ProductsTypes.Juegos);
        List<Product> list = new ArrayList<>();
        list.addAll(p);
        return list;
    }

    @Override
    public Map<Product, Integer> listAllAmountOfProducts(String name) {
        int cont = 0;
        Map<Product, Integer> aux = new TreeMap<>();
        Product aux2 = null;
        if (name != null) {
            for (Product pr : pr.products) {
                if (pr != null && pr.getName().equals(name)) {
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

    @Override
    public Map<Product, Integer> listAllAmountOfProducts(ProductsTypes type, String name) {
        int cont = 0;
        Map<Product, Integer> aux = new TreeMap<>();
        Product aux2 = null;

        for (Product pr : pr.products) {
            if (pr != null && pr.getName().equals(name) && pr.getType().equals(type)) {
                if (aux2 == null) {
                    aux2 = pr;
                }
                cont++;
            }
        }
        aux.put(aux2, cont);
        return aux;
    }

    @Override
    public Set<IClient> listAllClients() {
        return cl.clients;
    }

    @Override
    public Set<IClient> listAllClients(Comparator c) {
        Set<IClient> ordenado = new TreeSet<>(c);
        ordenado.addAll(cl.clients);
        return ordenado;
    }

    @Override
    public Set<IClient> listAllClientsWithReservationsNotFinished() {
        Set<IClient> client = new TreeSet<>();
        for(Reservation reserves : re.reserves){
            
        }
    }

    @Override
    public Set<Reservation> listAllReservations() {
        return re.reserves;
    }

    @Override
    public Set<Reservation> listAllReservations(Comparator c) {
        Set<Reservation> ordenado = new TreeSet<>(c);
        ordenado.addAll(re.reserves);
        return ordenado;
    }

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

    @Override
    public double getIncommings() {
        double result = 0;
        Set<Reservation> aux = listAllReservations(Reservation.StatusReserve.FINISHED);
        for (Reservation reserve : aux) {
            result = result + reserve.pro.getPrize();
        }
        Set<Reservation> aux1 = listAllReservations(Reservation.StatusReserve.PENDING);
        
        
        for (Reservation reserve : aux1) {
            
                result = result + 3;
        
        
       

    }
         return result;
    }

    @Override
    public double getIncommings(LocalDate from) {
        double result = 0;
        for (Reservation reserve : re.reserves) {
            if (reserve.end.isEqual(from)) {
                
            }

        }

        return result;
    }

    @Override
    public double getIncommings(LocalDate from, LocalDate to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<IClient, Double> resumeAllIncomingsByClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createProduct(String name, String description, double prize) { //otros
        return pr.products.add(new Others(name, description, prize));
    }

    @Override
    public boolean createMovie(ProductsTypes type, String name, String description, double precio, MovieCategory cat, int minAge) {
        return pr.products.add(new Movie(name, description, precio, cat, minAge));
    }

    @Override
    public boolean createGame(ProductsTypes type, String name, String description, double precio, GameCategory cat, int minAge) {
        return pr.products.add(new Game(name, description, precio, cat, minAge));
    }

    @Override
    public boolean createClient(String id, String name, String phone, LocalDateTime time) {
        return cl.clients.add(new Client(id, name, phone, time));
    }

    
    //FALTA COMPROBAR QUE NO TENGA RESERVAS
    @Override
    public boolean removeClient(String id) {
        boolean result = false;

        if (id != null) {
            for (IClient c : cl.clients) {
                if (c != null && c.getID().equals(id)) {
                    cl.clients.remove(c);
                    result = true;
                    break;
                }
            }

        }
        return result;
    }

    
    //FALTA 
    @Override
    public boolean editClient(IClient e) {
        boolean result = false;
        if (e != null) {
            for (IClient c : cl.clients) {
                if (c != null && c.equals(e)) {
                    c.setName("");
                    c.setPhone("");
                    c.setTime(LocalDateTime.now());
                    result = true;
                    break;
                }
            }

        }
        return result;
    }

    //FALTA
    @Override
    public boolean addProduct(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    //ESTA BIEN?
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

    @Override
    public Product isAvailableProduct(String name) {
        Product p = null;

        for (Product pr : pr.products) {
            if (pr != null && p.getName().equals(name) && p.getStatus().equals(Product.Status.AVAILABLE)) {
                p = pr;
                break;
            }
        }
        return p;
    }

    @Override
    public boolean reserveProduct(Product prod, IClient client) {
        if (prod != null && client != null) {
            return re.reserves.add(new Reservation(prod, client));
        } else {
            return false;
        }

    }

    @Override
    public double closeReservation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loadCatalogFromDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loadClientsFromDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loadReservationsFromDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loadAllDDBB() {
        return loadCatalogFromDDBB() && loadClientsFromDDBB() && loadReservationsFromDDBB();
    }

    @Override
    public boolean saveCatalogFromDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveClientsFromDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveReservationsFromDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveAllDDBB() {
        return saveCatalogFromDDBB() && saveClientsFromDDBB() && saveReservationsFromDDBB();
    }

}
