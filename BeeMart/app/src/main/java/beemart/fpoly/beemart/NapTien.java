package beemart.fpoly.beemart;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import beemart.fpoly.beemart.DAO.KhachHangDAO;
import beemart.fpoly.beemart.DTO.KhachHang;

public class NapTien extends AppCompatActivity {
    Toolbar toolbar;
    private KhachHangDAO khachHangDAO;
    private KhachHang objKhachHang;
    TextView tvSoDuVi;
    EditText edNhapSoTien;
    Button btn50,btn100,btn200,btn500,btn1000,btn2000,btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_tien);
        if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);

        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        anhXa();
        khachHangDAO = new KhachHangDAO(this);
        toolbar = findViewById(R.id.idToolBar);

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
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLog(NapTien.this,Gravity.CENTER);
                napTien();
            }
        });

        btn50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNhapSoTien.setText("50000");
            }
        });
        btn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNhapSoTien.setText("100000");
            }
        });
        btn200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNhapSoTien.setText("200000");
            }
        });
        btn500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNhapSoTien.setText("500000");
            }
        });
        btn1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNhapSoTien.setText("1000000");
            }
        });
        btn2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNhapSoTien.setText("2000000");
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    public void anhXa(){
        tvSoDuVi= findViewById(R.id.tvSoDuVi);
        edNhapSoTien= findViewById(R.id.edNhapSoTien);
        btn50= findViewById(R.id.btn50);
        btn100= findViewById(R.id.btn100);
        btn200= findViewById(R.id.btn200);
        btn500= findViewById(R.id.btn500);
        btn1000= findViewById(R.id.btn1000);
        btn2000= findViewById(R.id.btn2000);
        btnXacNhan= findViewById(R.id.btnXacNhan);
    }
    private void napTien(){
        SharedPreferences preferences = getSharedPreferences("NAMEUSER",MODE_PRIVATE);
        String userName = (preferences.getString("userNameAcount",""));
        khachHangDAO = new KhachHangDAO(this);
        objKhachHang = khachHangDAO.getUserName(userName);
        Log.d("aaa", "Khach Hang: "+objKhachHang.getTenKH());
        Log.d("mk", "mat  khau la: "+objKhachHang.getPasswordKH());
        String soTien = edNhapSoTien.getText().toString();

        if (edNhapSoTien.getText().toString().isEmpty()){
            Toast.makeText(this, "vui long nhap so tien", Toast.LENGTH_SHORT).show();
            return;
        }
        int tienCu= objKhachHang.getViTien();
        objKhachHang.setViTien(Integer.parseInt(soTien)+tienCu);
        int kq = khachHangDAO.update(objKhachHang);
        if (kq>0){
            Log.d("bbb", "tien: "+objKhachHang.getViTien());



        }
        else {
            Toast.makeText(this, "that bai", Toast.LENGTH_SHORT).show();
        }

    }
    public void showDiaLog(Context context, int gravity){
        Dialog dialog = new Dialog(context, R.style.PauseDialogAnimation);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_xac_nhan_nap_tien);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.shadowDialog)));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        EditText edMatKhau=dialog.findViewById(R.id.edMatKhau);
        Button btnThem = dialog.findViewById(R.id.btnLuu);
        Button btnHuy =dialog.findViewById(R.id.btnHuy);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matKhau = edMatKhau.getText().toString().trim();
                if (!objKhachHang.getPasswordKH().equals(matKhau)) {
                    Toast.makeText(context, "mat khau sai", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    dialog.dismiss();
                    finish();
                    context.startActivity(new Intent(getApplicationContext(),ChoXacNhan.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }



}