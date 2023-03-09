package ru.maxima.springtcsql;

import ru.maxima.springtcsql.DAO.MainDAO;

public class Book {

    public MainDAO mainDAO;

    private Long id;

    private String name;

    private String author;

    private int releaseyear;

    private Long owner;

    public Book() {}

    public Book(Long id, String name, String author, int releaseyear, Long owner) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.releaseyear = releaseyear;
        this.owner = owner;
    }

    public Book(String name, String author, int releaseyear, Long owner) {
        this.name = name;
        this.author = author;
        this.releaseyear = releaseyear;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(int releaseyear) {
        this.releaseyear = releaseyear;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return name+", "+author+", "+releaseyear;
    }
}
