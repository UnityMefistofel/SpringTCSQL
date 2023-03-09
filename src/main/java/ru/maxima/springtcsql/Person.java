package ru.maxima.springtcsql;

public class Person {

    private Long id;
    private String fio;
    private int birthyear;
    private int booksCnt;


    public Person() {}

    public Person(String fio, int birthyear) {
        this.fio = fio;
        this.birthyear = birthyear;
    }

    public Person(Long id, String fio, int birthyear) {
        this.id = id;
        this.fio = fio;
        this.birthyear = birthyear;
    }

    public Person(Long id, String fio, int birthyear, int booksCnt) {
        this.id = id;
        this.fio = fio;
        this.birthyear = birthyear;
        this.booksCnt = booksCnt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public int getBooksCnt() { return booksCnt; }

    public void setBooksCnt(int booksCnt) { this.booksCnt = booksCnt; }

    @Override
    public String toString() {
        return fio+", "+birthyear;
    }
}
