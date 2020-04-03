/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

/**
 *
 * @author migue
 */
public class Others extends Product{

    public Others(String name, String description, double prize) {
        super(name, description, prize);
    }
    public Others clone()throws CloneNotSupportedException {
        Product clone = (Product) super.clone(); //To change body of generated methods, choose Tools | Templates.
        return (Others) clone;
   }
    
}
