package com.example.namazlog.Models;

public class Namaz {
    public int id;
    public String date;
    public int namaz_num;
    public int ada;
    public int jama;
    public int raqat;
    public Namaz(int id, String date, int namaz_num, int ada, int jamat, int raqat)
    {
        this.id = id;
        this.date = date;
        this.namaz_num = namaz_num;
        this.ada = ada;
        this.jama = jamat;
        this.raqat = raqat;
    }
}
