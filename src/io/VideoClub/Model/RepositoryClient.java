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
public class RepositoryClient {
    private static RepositoryClient instance = null;
    public Set<IClient> clients;
    
    private RepositoryClient() {
        clients = new TreeSet<>();
    }
    public static RepositoryClient getInstance(){
        if(instance == null){
            instance = new RepositoryClient();
        }
        return instance;
    }
}
