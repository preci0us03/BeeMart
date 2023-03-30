package beemart.fpoly.beemart.DTO;

import java.util.Date;

public class HoaDon {
     int maHoaDon;
     Date ngayMua;
     int tongTien;
     int maSP;
     int maTV;
     String maNV;

    public HoaDon(int maHoaDon, Date ngayMua, int tongTien, int maSP, int maTV, String maNV) {
        this.maHoaDon = maHoaDon;
        this.ngayMua = ngayMua;
        this.tongTien = tongTien;
        this.maSP = maSP;
        this.maTV = maTV;
        this.maNV = maNV;
    }

    public HoaDon() {
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
}
