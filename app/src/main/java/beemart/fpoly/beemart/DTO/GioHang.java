package beemart.fpoly.beemart.DTO;

public class GioHang {
    int maSP;
    byte[] anhSP;
    String tenSP;
    int soLuong;
    int giaSP;
    int maKH;
    int maNV;
    int maHoaDon;

    public static final String TB_NAME = "GioHang";
    public static final String COL_MASP = "maSP";
    public static final String COL_ANHSP = "anhSP";
    public static final String COL_TENSP = "tenSP";
    public static final String COL_SOLUONG = "soLuong";
    public static final String COL_GIASP = "giaSP";
    public static final String COL_MAKH = "maKH";
    public static final String COL_MANV = "maNV";
    public static final String COL_MAHD = "maHoaDon";



    public GioHang() {
    }

    public GioHang(int maSP, byte[] anhSP, String tenSP, int soLuong, int giaSP, int maKH, int maNV, int maHoaDon) {
        this.maSP = maSP;
        this.anhSP = anhSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaSP = giaSP;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maHoaDon = maHoaDon;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
}
