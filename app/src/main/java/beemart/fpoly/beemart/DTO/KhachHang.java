package beemart.fpoly.beemart.DTO;

import java.sql.Blob;

public class KhachHang {
    int maKH;
    String tenKH;
    byte[] anhKH;
    Long soDienThoai;
    int diemThuong;
    String userNameKH;
    String passwordKH;
    int viTien;

    public KhachHang(int maKH, String tenKH, byte[] anhKH, Long soDienThoai, int diemThuong, String userNameKH, String passwordKH, int viTien) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.anhKH = anhKH;
        this.soDienThoai = soDienThoai;
        this.diemThuong = diemThuong;
        this.userNameKH = userNameKH;
        this.passwordKH = passwordKH;
        this.viTien = viTien;
    }

    public KhachHang() {
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public byte[] getAnhKH() {
        return anhKH;
    }

    public void setAnhKH(byte[] anhKH) {
        this.anhKH = anhKH;
    }

    public Long getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(Long soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getDiemThuong() {
        return diemThuong;
    }

    public void setDiemThuong(int diemThuong) {
        this.diemThuong = diemThuong;
    }

    public String getUserNameKH() {
        return userNameKH;
    }

    public void setUserNameKH(String userNameKH) {
        this.userNameKH = userNameKH;
    }

    public String getPasswordKH() {
        return passwordKH;
    }

    public void setPasswordKH(String passwordKH) {
        this.passwordKH = passwordKH;
    }

    public int getViTien() {
        return viTien;
    }

    public void setViTien(int viTien) {
        this.viTien = viTien;
    }
}
