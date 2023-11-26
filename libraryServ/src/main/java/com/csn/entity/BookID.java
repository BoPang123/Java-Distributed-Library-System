/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.entity;

/**
 *
 * @author Ning
 * create table bookID(
 * bookID varchar(50) not null primary key,
 * ISBN varchar(50) not null,
 * available boolean not null,
 * foreign key (ISBN) references library(ISBN)
 * );
 */
public class BookID {
    public String bookID;
    public String ISBN;
    public boolean ava;
    
    public String getBookID() {
        return bookID;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAva() {
        return ava;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAva(boolean ava) {
        this.ava = ava;
    }

    @Override
    public String toString() {
        return "BookID{" + "bookID=" + bookID + ", ISBN=" + ISBN + ", ava=" + ava + '}';
    }
    
}
