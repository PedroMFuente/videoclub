/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author migue
 */
public class Store {
   private static Store instance = null;
    Set<Product> products;

    private Store() {
        products = new HashSet<>();
    }
    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }
        return instance;
    }
    
   
}
