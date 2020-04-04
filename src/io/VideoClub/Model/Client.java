/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author migue
 */
public class Client implements IClient{

    private String ID;
    private String name;
    private LocalDateTime time;
    private String phone;

    public Client(String ID, String name, String phone, LocalDateTime time) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.time = time;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String n) {
        this.name = n;
    }

    @Override
    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public void setTime(LocalDateTime t) {
        this.time = t;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String p) {
        this.phone = p;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "ID=" + ID + ", name=" + name + ", time=" + time + ", phone=" + phone + '}';
    }

    @Override
    public int compareTo(IClient o) {
        return this.ID.compareTo(o.getID());
    }

}
