package beemart.fpoly.beemart.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import beemart.fpoly.beemart.DBHelper.DBHelper;
import beemart.fpoly.beemart.DTO.HoaDonChiTiet;

public class HoaDonChiTietDAO {
    private SQLiteDatabase db;
    private Context context;

    public HoaDonChiTietDAO(Context context){
        this.context = context;
        DBHelper dbHelper = new DBHelper(context);
        db= dbHelper.getWritableDatabase();
    }
    public long insert(HoaDonChiTiet obj){
        ContentValues contentValues =  new ContentValues();
        contentValues.put("maHoaDon",obj.getMaHoaDon());
        contentValues.put("maSP",obj.getMaSP());
        contentValues.put("donGia",obj.getDonGia());
        contentValues.put("soLuong",obj.getSoLuong());
        return db.insert("ChiTietDonHang",null,contentValues);
    }
}
