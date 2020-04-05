/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;


import java.util.Set;
import java.util.TreeSet;

/**
 * Repositorio del Set de productos
 * @author migue
 */
public class RepositoryProduct {
   private static RepositoryProduct instance = null;
    public Set<Product> products;
   

    private RepositoryProduct() {
        products = new TreeSet<>();
    }
    public static RepositoryProduct getInstance(){
        if(instance == null){
            instance = new RepositoryProduct();
        }
        return instance;
    }
   
    
}
