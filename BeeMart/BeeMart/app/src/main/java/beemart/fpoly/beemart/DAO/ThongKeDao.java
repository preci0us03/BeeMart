package beemart.fpoly.beemart.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beemart.fpoly.beemart.DBHelper.DBHelper;
import beemart.fpoly.beemart.DTO.ThongKe;
import beemart.fpoly.beemart.DTO.ThongKeDoanhThu;

public class ThongKeDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDao(Context context) {
        this.context = context;
        DBHelper dpHelper = new DBHelper(context);
        db = dpHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<ThongKeDoanhThu> getTop (String tuNgay, String denNgay){
        String sqlTop = "SELECT HoaDon.maHoaDon ," +
                "HoaDon.ngayMua, HoaDon.tongTien , " +
                "ChiTietDonHang.maSP ," +
                "ChiTietDonHang.soLuong FROM HoaDon INNER JOIN ChiTietDonHang ON HoaDon.maHoaDon = ChiTietDonHang.maHoaDon WHERE HoaDon.ngayMua BETWEEN ? AND ? GROUP BY ChiTietDonHang.maHoaDon ORDER BY ChiTietDonHang.soLuong DESC LIMIT 10";
        List<ThongKeDoanhThu> list = new ArrayList<ThongKeDoanhThu>();
        Cursor c = db.rawQuery(sqlTop,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            ThongKeDoanhThu obj = new ThongKeDoanhThu();
            obj.setMaSP(Integer.parseInt(c.getString(c.getColumnIndex("maSP"))));
            obj.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            obj.setDoanhThu(Integer.parseInt(c.getString(c.getColumnIndex("tongTien"))));
            obj.setNgay(c.getString(c.getColumnIndex("ngayMua")));
            list.add(obj);
        }
        return list;
    }
}
