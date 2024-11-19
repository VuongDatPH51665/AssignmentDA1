package com.example.du_an_1.Model;

public class SanPham {
    private int id;
    private String ten;
    private double gia;
    private String size;
    private int soLuong;
    private String moTa;

    public SanPham(){
    }
    public SanPham(int id, String ten, double gia, String size, int soLuong, String moTa) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.size = size;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}