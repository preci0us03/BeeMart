package beemart.fpoly.beemart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import beemart.fpoly.beemart.Adapter.CartListAdapter;
import beemart.fpoly.beemart.Adapter.CartListComfirmAdapter;
import beemart.fpoly.beemart.DAO.GioHangDAO2;
import beemart.fpoly.beemart.DAO.KhachHangDAO;
import beemart.fpoly.beemart.DTO.GioHang;
import beemart.fpoly.beemart.DTO.HoaDon;
import beemart.fpoly.beemart.DTO.KhachHang;
import beemart.fpoly.beemart.Interface.ChangeNumberItemCartList;

public class XacNhanThongTinGioHang extends AppCompatActivity {
    private TextView tvKhachHang,tvSoDienThoai;
    private RecyclerView recyclerViewChiTietHoaDon;
    private Button btnXacNhan;
    private ArrayList<GioHang> list;
    private CartListComfirmAdapter cartListAdapter;
    private GioHangDAO2 gioHangDAO2;
    private Toolbar toolbar;
    private double tax;
    private KhachHangDAO khachHangDAO;
    private KhachHang objKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_thong_tin_gio_hang);
        if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);

        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        anhXaView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        khachHangDAO = new KhachHangDAO(this);

        gioHangDAO2 = new GioHangDAO2(this);
        list = (ArrayList<GioHang>) gioHangDAO2.getAll();
        initList();
        tinhTien();
        thongTinNhanHang();


    }

    private void thongTinNhanHang() {
        SharedPreferences preferences = getSharedPreferences("NAMEUSER",MODE_PRIVATE);
        String userName = (preferences.getString("userNameAcount",""));
        String quyen = (preferences.getString("quyen",""));
        objKhachHang = khachHangDAO.getUserName(userName);
        if(objKhachHang != null){
            tvKhachHang.setText("Khách hàng : " +objKhachHang.getTenKH());
            tvSoDienThoai.setText("Số điện thoại : " +objKhachHang.getSoDienThoai() +"");
        }
    }

    private void tinhTien(){
        double thue = 0.02;
        double phidichvu = 2000;
        tax = Math.round((gioHangDAO2.getTolalFee()*thue)*100)/100;
        double total = Math.round(((gioHangDAO2.getTolalFee()-tax)*100)/100);
        btnXacNhan.setText(total-phidichvu +" VND");

    }
    public void anhXaView(){
        tvKhachHang = findViewById(R.id.tvKhachHang);
        tvSoDienThoai = findViewById(R.id.tvSoDienThoai);
        recyclerViewChiTietHoaDon = findViewById(R.id.recyclerViewChiTietHoaDon);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        toolbar = findViewById(R.id.idToolBar);
    }
    public void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewChiTietHoaDon.setLayoutManager(linearLayoutManager);
        cartListAdapter = new CartListComfirmAdapter(list);
        recyclerViewChiTietHoaDon.setAdapter(cartListAdapter);
        RecyclerView.ItemDecoration itemDecoration =  new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerViewChiTietHoaDon.addItemDecoration(itemDecoration);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}