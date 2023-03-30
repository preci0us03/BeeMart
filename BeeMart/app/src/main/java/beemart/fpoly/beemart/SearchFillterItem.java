package beemart.fpoly.beemart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

import beemart.fpoly.beemart.Adapter.SanPhamAdapterSearchFillter;
import beemart.fpoly.beemart.DAO.SanPhamDAO;
import beemart.fpoly.beemart.DTO.SanPham;

public class SearchFillterItem extends AppCompatActivity {
    private Toolbar idToolBarSearch;
    private RecyclerView recViewItemSearch;
    private SanPhamDAO sanPhamDAO;
    private ArrayList<SanPham> list;
    SearchView searchView;
    private SanPhamAdapterSearchFillter sanPhamAdapterSearchFillter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_fillter_item);
        if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);

        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        idToolBarSearch = findViewById(R.id.idToolBarSearch);
        recViewItemSearch = findViewById(R.id.recViewItemSearch);

        setSupportActionBar(idToolBarSearch);
        getSupportActionBar().setTitle("Tìm kiếm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        idToolBarSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        sanPhamDAO = new SanPhamDAO(this);
        list = (ArrayList<SanPham>) sanPhamDAO.getAll();
        sanPhamAdapterSearchFillter = new SanPhamAdapterSearchFillter(this,list,sanPhamDAO,this);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recViewItemSearch.setLayoutManager(linearLayoutManager2);
        recViewItemSearch.setAdapter(sanPhamAdapterSearchFillter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_search,menu);
        MenuItem item = menu.findItem(R.id.iconSearch);

         searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sanPhamAdapterSearchFillter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (searchView != null && !searchView.isIconified()) {
            searchView.setIconified(true);
        } else {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}