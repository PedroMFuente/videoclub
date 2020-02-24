/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import java.time.LocalDateTime;

/**
 *
 * @author migue
 */
public class Client implements IClient {
    private String ID;
    private String name;
    private LocalDateTime time;
    private String phone;

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
    
}
