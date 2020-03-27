/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author migue
 */
public class RepositoryReserve {
    private static RepositoryReserve instance = null;
    public Set<Reservation> reserves;
    
    private RepositoryReserve() {
        reserves = new TreeSet<>();
    }
    public static RepositoryReserve getInstance(){
        if(instance == null){
            instance = new RepositoryReserve();
        }
        return instance;
    }
}
