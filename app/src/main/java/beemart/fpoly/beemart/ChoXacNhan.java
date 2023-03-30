package beemart.fpoly.beemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ChoXacNhan extends AppCompatActivity {
    Button btnDong;
    TextView tvThoiGian,tvMaDG;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cho_xac_nhan);
        btnDong= findViewById(R.id.btnDong);
        tvThoiGian= findViewById(R.id.tvThoiGian);
        tvMaDG = findViewById(R.id.tvMaGD);
        imgBack= findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoXacNhan.this,NapTien.class);
                startActivity(intent);
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy '-'HH:mm:ss");

        String currentDateAndTime = sdf.format(new Date());
        tvThoiGian.setText(currentDateAndTime);

        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoXacNhan.this,NapTien.class);
                startActivity(intent);

            }
        });
        Random random = new Random();
        int val = random.nextInt(1000000000);
        tvMaDG.setText(Integer.toString(val));
    }
}