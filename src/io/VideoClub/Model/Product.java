/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;
import java.util.UUID;

public abstract class Product extends Item implements Cloneable {

    public enum Status {
        AVAILABLE,
        RESERVED
    }
    protected String key;
    protected Status status;

    public Product() {}

    public Product(String name, String description, double prize) {
        super(name, description, prize);
        this.key = generateRandom16Chars();
    }

    private String generateRandom16Chars() {
        return (String) UUID.randomUUID().toString().subSequence(0, 16);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        boolean result = false;
        if (o != null) {
            if (o instanceof Product) {
                Product other = (Product) o;
                if (other.key.equals(other.key)) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Product clone = (Product) super.clone(); //To change body of generated methods, choose Tools | Templates.
        clone.key = generateRandom16Chars();
        return (Object) clone;
    }

    @Override
    public String toString() {
        return super.toString() + "status=" + status + '}';
    }

}
