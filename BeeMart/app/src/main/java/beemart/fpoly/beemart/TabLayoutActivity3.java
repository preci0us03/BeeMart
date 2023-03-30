package beemart.fpoly.beemart;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import beemart.fpoly.beemart.Adapter.ViewPagerAdapter2;
import beemart.fpoly.beemart.Adapter.ViewPagerAdapter3;

public class TabLayoutActivity3 extends AppCompatActivity {

    private TabLayout tabLayout3;
    private ViewPager viewPager3;
    private Toolbar idToolBarQuanLyBeePay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout2);
        if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);

        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        tabLayout3 = findViewById(R.id.tabLayout2);
        viewPager3= findViewById(R.id.viewPager2);
        idToolBarQuanLyBeePay = findViewById(R.id.idToolBarVi);
        setSupportActionBar(idToolBarQuanLyBeePay);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        idToolBarQuanLyBeePay.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ViewPagerAdapter3 viewPagerAdapter3 = new ViewPagerAdapter3(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager3.setAdapter(viewPagerAdapter3);
        tabLayout3.setupWithViewPager(viewPager3);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}