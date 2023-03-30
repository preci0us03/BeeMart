package beemart.fpoly.beemart.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beemart.fpoly.beemart.DBHelper.DBHelper;
import beemart.fpoly.beemart.DTO.HoaDon;

public class HoaDonDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

    public HoaDonDAO(Context context){
        this.context=context;
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    public long insert(HoaDon obj){
        ContentValues values=new ContentValues();
        values.put("maNV", obj.getMaNV());
        values.put("maTV", obj.getMaTV());
        values.put("maSP", obj.getMaSP());
        values.put("ngayMua", sdf.format(obj.getNgayMua()));
        values.put("tongTien", obj.getTongTien());

        return db.insert("HoaDon",null,values);
    }

    public int update(HoaDon obj){
        ContentValues values=new ContentValues();
        values.put("maNV", obj.getMaNV());
        values.put("maTV", obj.getMaTV());
        values.put("maSP", obj.getMaSP());
        values.put("ngayMua", sdf.format(obj.getNgayMua()));
        values.put("tongTien", obj.getTongTien());

        return db.update("HoaDon",values,"maHoaDon=?", new String[]{String.valueOf(obj.getMaHoaDon())});
    }

    public int delete(String id){
        return db.delete("HoaDon", "maHoaDon=?",new String[]{id});
    }

    //get data nhieu tham so

    @SuppressLint("Range")
    public List<HoaDon> getData(String sql, String...selectionArgs){
        List<HoaDon> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            HoaDon obj=new HoaDon();
            obj.setMaHoaDon(Integer.parseInt(c.getString(c.getColumnIndex("maHoaDon"))));
            obj.setMaNV(c.getString(c.getColumnIndex("maNV")));
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            obj.setMaSP(Integer.parseInt(c.getString(c.getColumnIndex("maSP"))));
            obj.setTongTien(Integer.parseInt(c.getString(c.getColumnIndex("tongTien"))));
            try {
                obj.setNgayMua(sdf.parse(c.getString(c.getColumnIndex("ngayMua"))));
            }catch (ParseException e){
                e.printStackTrace();
            }
            list.add(obj);
        }
        return list;
    }

    // get tat ca data
    public List<HoaDon> getAll(){
        String sql="SELECT * FROM HoaDon";
        return getData(sql);
    }

    //get tat ca id
    public HoaDon getID(String id){
        String sql="SELECT * FROM HoaDon WHERE maHoaDon=?";
        List<HoaDon> list=getData(sql,id);
        return list.get(0);
    }
}
