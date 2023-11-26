/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.entity;

/**
 *
 * @author Ning
 * 存储Lending表的类
 * create table lending(
 * ID varchar(50) not null,
 * bookID varchar(50) not null primary key, 
 * title varchar(50) not null,
 * foreign key (ID) references clientInfo(ID),
 * foreign key (bookID) references bookID(bookID)
 * );
 */
public class Lending {
  public String client_ID;
     public String book_ID;
     public String title;
     
    @Override
    public String toString() {
        return "Lending{" + "client_ID=" + client_ID + ", book_ID=" + book_ID + ", title=" + title + '}';
    }
     
    public String getClient_ID() {
        return client_ID;
    }

    public String getBook_ID() {
        return book_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setClient_ID(String client_ID) {
        this.client_ID = client_ID;
    }

    public void setBook_ID(String book_ID) {
        this.book_ID = book_ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
