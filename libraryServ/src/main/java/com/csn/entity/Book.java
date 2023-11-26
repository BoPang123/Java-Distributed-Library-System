/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csn.entity;

/**
 *
 * @author Ning
 * 储存书的类
 * create table library(
 * ISBN varchar(50) not null primary key, 
 * title varchar(50) not null,
 * author varchar(70) not null,
 * theDate integer not null,
 * publisher varchar(50) not null,
 * inventoryQuantity integer not null,
 * lendingQuantity integer not null,
 * position varchar(50) not null,
 * );
 */
public class Book {

  

    public String ISBN;
    public String title;
    public String author;
    public int theDate;
    public String publisher;
    public int inventoryQuantity;
    public int lendingQuantity;
    public String position;
    //public String cover;

    @Override
    public String toString() {
        return "Book{" + "ISBN=" + ISBN + ", title=" + title + ", author=" + author + ", theDate=" + theDate + ", publisher=" + publisher + ", inventoryQuantity=" + inventoryQuantity + ", lendingQuantity=" + lendingQuantity + ", position=" + position + '}';
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTheDate(int theDate) {
        this.theDate = theDate;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public void setLendingQuantity(int lendingQuantity) {
        this.lendingQuantity = lendingQuantity;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    //public void setCover(String cover) {
    //    this.cover = cover;
    //}

    //public String getCover() {
    //    return cover;
    //}

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTheDate() {
        return theDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public int getLendingQuantity() {
        return lendingQuantity;
    }

    public String getPosition() {
        return position;
    }
    
  
}
