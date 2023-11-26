/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.entity;

/**
 *
 * @author Ning
 * create table clientInfo(
ID varchar(50) not null primary key, 
userName varchar(50) not null,
userPassword varchar(70) not null,
email varchar(50) not null,
telephone varchar(50) not null
);
 */
public class Client {

    public String getClient_ID() {
        return client_ID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTeleph() {
        return teleph;
    }

    public void setClient_ID(String client_ID) {
        this.client_ID = client_ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTeleph(String teleph) {
        this.teleph = teleph;
    }

    @Override
    public String toString() {
        return "Client{" + "client_ID=" + client_ID + ", name=" + name + ", password=" + password + ", email=" + email + ", teleph=" + teleph + '}';
    }
      public String client_ID;
     public String name;
     public String password;
     public String email;
     public String teleph;
}
