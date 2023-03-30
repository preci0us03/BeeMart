package beemart.fpoly.beemart.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import beemart.fpoly.beemart.DAO.LoaiSanPhamDAO;
import beemart.fpoly.beemart.DAO.SanPhamDAO;
import beemart.fpoly.beemart.DTO.LoaiSanPham;
import beemart.fpoly.beemart.DTO.SanPham;
import beemart.fpoly.beemart.Fragment.FragmentSanPham;
import beemart.fpoly.beemart.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolderSanPham>{
    private ArrayList<SanPham> list;
    Context context;
    private ArrayList<SanPham> oldList;
    private SanPhamDAO sanphamDAO;
    FragmentSanPham fragmentSanPham;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");


    public SanPhamAdapter(Context context, ArrayList<SanPham> list, SanPhamDAO sanPhamDAO, FragmentSanPham fragmentSanPham) {
        this.context = context;
        this.list = list;
        this.sanphamDAO = sanPhamDAO;
        this.fragmentSanPham = fragmentSanPham;

    }

    @NonNull
    @Override
    public ViewHolderSanPham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_san_pham, parent, false);
        return new ViewHolderSanPham(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSanPham holder, @SuppressLint("RecyclerView") int position) {
        final SanPham objSanPham = list.get(position);
        if(objSanPham == null){
            return;

        }
        LoaiSanPhamDAO loaiSanPhamDAO=new LoaiSanPhamDAO(context);
        final LoaiSanPham loaiSanPham = loaiSanPhamDAO.getID(objSanPham.getMaLoai()+"");

        if(loaiSanPham == null){
            holder.tvLoaiSP.setText("Không có dữ liệu");
        }else{
            holder.tvLoaiSP.setText(loaiSanPham.getTenLoaiSP());
        }

        
        holder.tvTenSP.setText(objSanPham.getTenSP());
        holder.tvGiaSP.setText(objSanPham.getGiaSP()+"");
        holder.tvGiamGia.setText(objSanPham.getGiamGia()+"");
        holder.tvSoluong.setText(objSanPham.getSoLuong()+"");
//        holder.tvNSX.setText(objSanPham.getNgaySanXuat());
//        holder.tvHSD.setText(objSanPham.getHanSuDung());


        holder.iconDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSanPham.showDialogXoaLoaiSP(position,list,context,Gravity.CENTER,objSanPham,SanPhamAdapter.this);
            }
        });

        holder.iconEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              fragmentSanPham.showDialogSuaLoaiSP(position,list,context,Gravity.CENTER,objSanPham,SanPhamAdapter.this,loaiSanPham);
            }
        });

        Bitmap bitmap = BitmapFactory.decodeByteArray(objSanPham.getAnhSP(),0,objSanPham.getAnhSP().length);
        holder.imageAvataSanpham.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ViewHolderSanPham extends RecyclerView.ViewHolder{
        private TextView tvTenSP,tvLoaiSP,tvGiaSP,tvGiamGia,tvSoluong,tvNSX,tvHSD;
        ImageView iconEdit,iconDelete;
        private ImageView imageAvataSanpham;
        public ViewHolderSanPham(@NonNull View itemView) {
            super(itemView);
            tvTenSP = itemView.findViewById(R.id.tvTensanpham);
            tvLoaiSP=itemView.findViewById(R.id.tvTenLoaiSP);
            tvGiaSP=itemView.findViewById(R.id.tvGiaSP);
            tvGiamGia=itemView.findViewById(R.id.tvGiamGiaSP);
            tvSoluong=itemView.findViewById(R.id.tvSoLuong);
//            tvNSX=itemView.findViewById(R.id.tvNSX);
//            tvHSD=itemView.findViewById(R.id.tvHSD);
            imageAvataSanpham = itemView.findViewById(R.id.imageAvataSanPham);
            iconEdit=itemView.findViewById(R.id.iconUpdate);
            iconDelete = itemView.findViewById(R.id.iconDelete);
        }
    }
}
