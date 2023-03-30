package beemart.fpoly.beemart.DTO;

public class ThongKe {
    String ngay;
    double doanhThu;

    public ThongKe() {
    }

    public ThongKe(String ngay, double doanhThu) {
        this.ngay = ngay;
        this.doanhThu = doanhThu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }
}
