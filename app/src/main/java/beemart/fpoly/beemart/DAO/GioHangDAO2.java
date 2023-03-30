package beemart.fpoly.beemart.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import beemart.fpoly.beemart.DBHelper.DBHelper;
import beemart.fpoly.beemart.DTO.GioHang;
import beemart.fpoly.beemart.DTO.HoaDon;
import beemart.fpoly.beemart.DTO.KhachHang;
import beemart.fpoly.beemart.DTO.NhanVien;
import beemart.fpoly.beemart.DTO.SanPham;
import beemart.fpoly.beemart.Interface.ChangeNumberItemCartList;

public class GioHangDAO2 {
    private SQLiteDatabase db;



    public GioHangDAO2(Context context){
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long insertGioHang(beemart.fpoly.beemart.DTO.GioHang obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put(GioHang.COL_MASP,obj.getMaSP());
        contentValues.put(GioHang.COL_ANHSP,obj.getAnhSP());
        contentValues.put(GioHang.COL_TENSP,obj.getTenSP());
        contentValues.put(GioHang.COL_SOLUONG,obj.getSoLuong());
        contentValues.put(GioHang.COL_GIASP,obj.getGiaSP());
        contentValues.put(GioHang.COL_MAKH,obj.getMaKH());
        contentValues.put(GioHang.COL_MANV,obj.getMaNV());
        contentValues.put(GioHang.COL_MAHD,obj.getMaHoaDon());
        long res = db.insert(GioHang.TB_NAME,null,contentValues);
        return res;
    }
    public int updateSoLuong(beemart.fpoly.beemart.DTO.GioHang obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put(GioHang.COL_MASP,obj.getMaSP());
        contentValues.put(GioHang.COL_ANHSP,obj.getAnhSP());
        contentValues.put(GioHang.COL_TENSP,obj.getTenSP());
        contentValues.put(GioHang.COL_SOLUONG,obj.getSoLuong());
        contentValues.put(GioHang.COL_GIASP,obj.getGiaSP());
        contentValues.put(GioHang.COL_MAKH,obj.getMaKH());
        contentValues.put(GioHang.COL_MANV,obj.getMaNV());
        contentValues.put(GioHang.COL_MAHD,obj.getMaHoaDon());
        return db.update(GioHang.TB_NAME, contentValues,"maSP=?", new String[]{String.valueOf(obj.getMaSP())});
    }
    @SuppressLint("Range")
    public List<GioHang> getData(String sql, String...selectionArgs){
        List<GioHang> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            GioHang obj=new GioHang();
            obj.setMaSP(Integer.parseInt(c.getString(c.getColumnIndex(GioHang.COL_MASP))));
            obj.setAnhSP(c.getBlob(c.getColumnIndex(GioHang.COL_ANHSP)));
            obj.setTenSP(c.getString(c.getColumnIndex(GioHang.COL_TENSP)));
            obj.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex(GioHang.COL_SOLUONG))));
            obj.setGiaSP(Integer.parseInt(c.getString(c.getColumnIndex(GioHang.COL_GIASP))));
            obj.setMaKH(Integer.parseInt(c.getString(c.getColumnIndex(GioHang.COL_MAKH))));
            obj.setMaNV(Integer.parseInt(c.getString(c.getColumnIndex(GioHang.COL_MANV))));
            obj.setMaHoaDon(Integer.parseInt(c.getString(c.getColumnIndex(GioHang.COL_MAHD))));
            list.add(obj);
        }
        return list;
    }
    public int checkValue(String id){
        String sql="SELECT * FROM GioHang WHERE maSP=?";
        List<GioHang> list=getData(sql,id);
        if (list.size() == 0)
            return -1;
        return 1;
    }
    public int delete(GioHang obj){
        return db.delete(GioHang.TB_NAME, "maSP=?",new String[]{obj.getMaSP()+""});
    }
    public List<GioHang> getAll(){
        String sql="SELECT * FROM GioHang";
        return getData(sql);
    }
    public ArrayList<GioHang> getListCart() {
        return (ArrayList<GioHang>) getAll();
    }
    public void plusNumber(ArrayList<GioHang> list, int position, ChangeNumberItemCartList changeNumberItemCartList){
        list.get(position).setSoLuong(list.get(position).getSoLuong()+1);
        updateSoLuong(list.get(position));
        changeNumberItemCartList.changed();
    }
    public void minusNumber(ArrayList<GioHang> list, int position,Context context, ChangeNumberItemCartList changeNumberItemCartList){
        if(list.get(position).getSoLuong()==1){
            delete(list.get(position));
            list.remove(position);


        }else{
            list.get(position).setSoLuong(list.get(position).getSoLuong() - 1);
            updateSoLuong(list.get(position));
        }

        changeNumberItemCartList.changed();
    }
    public int getTolalFee(){
        ArrayList<GioHang> listSanPham = getListCart();
        int fee = 0;
        for(int i = 0 ; i  <listSanPham.size();i++){
            fee = fee+(listSanPham.get(i).getGiaSP()*listSanPham.get(i).getSoLuong());
        }
        return  fee;
    }

}
