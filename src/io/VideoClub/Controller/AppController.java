/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Controller;

import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Reservation;
import io.VideoClub.Model.Store;
import java.time.LocalDate;
import io.VideoClub.Model.Movie;
import io.VideoClub.Model.Game;
import io.VideoClub.Model.Otros;
import io.VideoClub.View.GUI;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author pedro
 */
public class AppController implements IAppController {

    Store s = Store.getInstance();

    @Override
    public Set<Product> listAllProducts() {
        return s.products;
    }

    @Override
    public Set<Product> listAllProducts(Comparator c) {
        return s.products;
    }

    @Override
    public Set<Product> listAllByType(ProductsTypes type) {
        Set<Product> p = new TreeSet<>();
        for(Product aux:s.products){
            if(aux.getType().equals(type)){
                p.add(aux);
            }
        }
        /*for (Product pr : s.products) {
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
                    p.addAll(s.products);
                    break;

            }
        }*/
        return p;
    }

    @Override
    public Set<Product> listAllByName(String name) {
        Set<Product> p = new TreeSet<>();
        for (Product pr : s.products) {
            if (name.equals(pr.getName())) {
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
        for (Product pr : s.products) {
            switch (status) {
                case AVAILABLE:
                    if (pr.getStatus().equals(Product.Status.AVAILABLE)) {
                        p.add(pr);
                    }
                    break;
                case RESERVED:
                    if (pr.getStatus().equals(Product.Status.RESERVED)) {
                        p.add(pr);
                    }
                    break;
                default:
                    p.addAll(s.products);
                    break;

            }
        }
        return p;
    }

    @Override
    public List<Product> listAllDifferentProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> listAllDifferentMovies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> listAllDifferentGames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Product, Integer> listAllAmountOfProducts(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Product, Integer> listAllAmountOfProducts(ProductsTypes type, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<IClient> listAllClients() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<IClient> listAllClients(Comparator c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<IClient> listAllClientsWithReservationsNotFinished() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Reservation> listAllReservations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Reservation> listAllReservations(Comparator c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Reservation> listAllReservations(Reservation.StatusReserve status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getIncommings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getIncommings(LocalDate from) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public boolean createProduct(String name, String description, double prize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.   
    }

    @Override
    public boolean createMovie(ProductsTypes type, String name, String description,double precio, MovieCategory cat, int minAge) {
        return s.products.add(new Movie( name,  description, precio, cat,  minAge));
    }

    @Override
    public boolean createGame(ProductsTypes type, String name, String description, double precio, GameCategory cat, int minAge) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createClient(String id, String name, String phone, LocalDateTime time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeClient(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editClient(IClient e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addProduct(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeProduct(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editProduct(String key, Product newP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product isAvailableProduct(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean reserveProduct(Product prod, IClient client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
