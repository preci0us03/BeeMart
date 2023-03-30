package beemart.fpoly.beemart.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import beemart.fpoly.beemart.DAO.HoaDonChiTietDAO;
import beemart.fpoly.beemart.DAO.HoaDonDAO;
import beemart.fpoly.beemart.DTO.HoaDon;
import beemart.fpoly.beemart.DTO.HoaDonChiTiet;
import beemart.fpoly.beemart.R;

public class HoaDonChiTietAdapter extends RecyclerView.Adapter<HoaDonChiTietAdapter.ViewHolder>{
    private Context context;
    private ArrayList<HoaDonChiTiet> list;
    private HoaDonChiTietDAO hoaDonChiTietDAO;

    public HoaDonChiTietAdapter(Context context, ArrayList<HoaDonChiTiet> list, HoaDonChiTietDAO hoaDonChiTietDAO) {
        this.context = context;
        this.list = list;
        this.hoaDonChiTietDAO = hoaDonChiTietDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_list_hoa_don_chi_tiet, parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoaDonChiTiet hoaDonChiTiet = list.get(position);
        if(hoaDonChiTiet == null){
            return;
        }else{
            holder.tvMaHoaDon.setText("Mã hoá đơn: "+hoaDonChiTiet.getMaHoaDon());
            holder.tvTongTien.setText("Tổng Tiền: "+hoaDonChiTiet.getMaSP());
            HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
            HoaDon hoaDon = hoaDonDAO.getID(hoaDonChiTiet.getMaHoaDon()+"");
            holder.tvNgayMua.setText("Ngày mua: "+hoaDonChiTiet.getSoLuong());
            int tinhTrang = hoaDon.getTrangThai();
            if(tinhTrang == 1){
                holder.tvTinhTrang.setText("Chờ xác nhận");
                holder.tvTinhTrang.setTextColor(Color.RED);
            }else if(tinhTrang == 2){
                holder.tvTinhTrang.setText("Đã xác nhận");
                holder.tvTinhTrang.setTextColor(Color.GREEN);
            }else if(tinhTrang == 3){
                holder.tvTinhTrang.setText("Đang giao");
                holder.tvTinhTrang.setTextColor(Color.GREEN);
            }else if(tinhTrang == 4){
                holder.tvTinhTrang.setText("Đã giao");
                holder.tvTinhTrang.setTextColor(Color.GREEN);
            }
        }
    }
    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTongTien;
        private TextView tvTinhTrang;
        private TextView tvMaHoaDon;
        private TextView tvNgayMua;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTongTien = itemView.findViewById(R.id.tvTongTien);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
            tvMaHoaDon = itemView.findViewById(R.id.tvMaHoaDon);
            tvNgayMua = itemView.findViewById(R.id.tvNgayMua);
        }
    }
}
