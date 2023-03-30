package beemart.fpoly.beemart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;

import beemart.fpoly.beemart.Adapter.HoaDonChiTietAdapter;
import beemart.fpoly.beemart.DAO.HoaDonChiTietDAO;
import beemart.fpoly.beemart.DAO.HoaDonDAO;
import beemart.fpoly.beemart.DTO.HoaDon;
import beemart.fpoly.beemart.DTO.HoaDonChiTiet;

public class HoaDonChiTietActivity extends AppCompatActivity {
    private RecyclerView rcyHoaDonChiTiet;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);

        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        rcyHoaDonChiTiet = findViewById(R.id.rcyHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getApplicationContext());
        loadData();
    }

    private void loadData() {
        ArrayList<HoaDonChiTiet> list = (ArrayList<HoaDonChiTiet>) hoaDonChiTietDAO.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcyHoaDonChiTiet.setLayoutManager(linearLayoutManager);
        HoaDonChiTietAdapter hoaDonChiTietAdapter = new HoaDonChiTietAdapter(getApplicationContext(), list, hoaDonChiTietDAO);
        rcyHoaDonChiTiet.setAdapter(hoaDonChiTietAdapter);
    }
}