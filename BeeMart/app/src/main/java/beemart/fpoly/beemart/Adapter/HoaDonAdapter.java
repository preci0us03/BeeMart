package beemart.fpoly.beemart.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import beemart.fpoly.beemart.DAO.HoaDonDAO;
import beemart.fpoly.beemart.DAO.KhachHangDAO;
import beemart.fpoly.beemart.DTO.HoaDon;
import beemart.fpoly.beemart.DTO.KhachHang;
import beemart.fpoly.beemart.Fragment.FragmentChoXacNhan;
import beemart.fpoly.beemart.HoaDonChiTietActivity;
import beemart.fpoly.beemart.MainActivity;
import beemart.fpoly.beemart.R;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HoaDon> list;
    private HoaDonDAO hoaDonDAO;
    FragmentChoXacNhan fragmentChoXacNhan;


    public HoaDonAdapter(Context context, ArrayList<HoaDon> list, HoaDonDAO hoaDonDAO) {
        this.context = context;
        this.list = list;
        this.hoaDonDAO = hoaDonDAO;
    }

    public HoaDonAdapter(FragmentChoXacNhan fragmentChoXacNhan) {
        this.fragmentChoXacNhan = fragmentChoXacNhan;
    }

    @NonNull
    @Override
    public HoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_hoa_don_final, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        HoaDon hoaDon = list.get(position);
        if (hoaDon == null) {
            return;
        } else {
            KhachHangDAO khachHangDAO = new KhachHangDAO(context);
            KhachHang khachHang = khachHangDAO.getID(hoaDon.getMaKH() + "");
            if (khachHang != null) {
                holder.tvTenKhachHang.setText("Khách Hàng: " + khachHang.getTenKH());
            }
            holder.tvMaHoaDon.setText("Mã hoá đơn: " + hoaDon.getMaHoaDon());
            holder.tvTongTien.setText("Tổng Tiền: " + hoaDon.getTongTien());
            int tinhTrang = hoaDon.getTrangThai();
            if (tinhTrang == 1) {
                holder.tvTinhTrang.setText("Chờ xác nhận");
                holder.tvTinhTrang.setTextColor(Color.RED);
            } else if (tinhTrang == 2) {
                holder.tvTinhTrang.setText("Đã xác nhận");
                holder.tvTinhTrang.setTextColor(Color.YELLOW);
            } else if (tinhTrang == 3) {
                holder.tvTinhTrang.setText("Đang giao");
                holder.tvTinhTrang.setTextColor(Color.GREEN);
            } else if (tinhTrang == 4) {
                holder.tvTinhTrang.setText("Đã giao");
                holder.tvTinhTrang.setTextColor(Color.GREEN);
            }
            holder.tvNgayMua.setText("Ngày mua: " + hoaDon.getNgayMua());
            holder.imgChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogXacNhanDonHang(context, Gravity.CENTER, position);
                }
            });

            holder.layoutHoaDonChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, HoaDonChiTietActivity.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenKhachHang;
        private TextView tvTongTien;
        private TextView tvTinhTrang;
        private ImageView imgChiTiet;
        private TextView tvMaHoaDon;
        private TextView tvNgayMua;
        private LinearLayout layoutHoaDonChiTiet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenKhachHang = itemView.findViewById(R.id.tvTenKhachHang);
            tvTongTien = itemView.findViewById(R.id.tvTongTien);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
            imgChiTiet = itemView.findViewById(R.id.imgChiTiet);
            tvMaHoaDon = itemView.findViewById(R.id.tvMaHoaDon);
            tvNgayMua = itemView.findViewById(R.id.tvNgayMua);
            layoutHoaDonChiTiet = itemView.findViewById(R.id.layoutHoaDonChiTiet);
        }
    }

    private void dialogXacNhanDonHang(Context context, int gravity, int i) {
        Button btnXacNhan;
        Button btnHuy;
        Dialog dialog = new Dialog(context, R.style.PauseDialogAnimation);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_xac_nhan_don);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.shadowDialog)));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        btnXacNhan = dialog.findViewById(R.id.btnXacNhan);
        btnHuy = dialog.findViewById(R.id.btnHuy);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
                HoaDon hoaDon = list.get(i);
                hoaDon.setTrangThai(2);
                hoaDonDAO.update(hoaDon);
                list.remove(i);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
