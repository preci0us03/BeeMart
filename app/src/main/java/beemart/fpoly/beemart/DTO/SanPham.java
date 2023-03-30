package beemart.fpoly.beemart.DTO;

import java.io.Serializable;
import java.util.Date;

public class SanPham implements Serializable {
    int maSP;
    byte[] anhSP;
    String tenSP;
    int giaSP;
    int giamGia;
    int soLuong;
    int maLoai;
    String ngaySanXuat;
    String hanSuDung;

    public SanPham(int maSP, byte[] anhSP, String tenSP, int giaSP, int giamGia, int soLuong, int maLoai, String ngaySanXuat, String hanSuDung) {
        this.maSP = maSP;
        this.anhSP = anhSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.giamGia = giamGia;
        this.soLuong = soLuong;
        this.maLoai = maLoai;
        this.ngaySanXuat = ngaySanXuat;
        this.hanSuDung = hanSuDung;
    }

    public SanPham() {
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public byte[] getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(byte[] anhSP) {
        this.anhSP = anhSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(String ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public String getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(String hanSuDung) {
        this.hanSuDung = hanSuDung;
    }


    public int giaTien(int giamGia,int giaGoc){
        int giamGia2 =   giaGoc - (giaGoc*giamGia/100);
        return giamGia2;
    }
}
