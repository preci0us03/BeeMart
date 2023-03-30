package beemart.fpoly.beemart.ThongBao;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

import beemart.fpoly.beemart.Adapter.AdapterThongBaoDonHang;
import beemart.fpoly.beemart.Adapter.SanPhamAdapter;
import beemart.fpoly.beemart.DAO.HoaDonDAO;
import beemart.fpoly.beemart.DTO.HoaDon;
import beemart.fpoly.beemart.DTO.ThongBaoDonHang;
import beemart.fpoly.beemart.Fragment.FragmentSanPham;
import beemart.fpoly.beemart.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DonHangFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class DonHangFragment extends Fragment {
    private RecyclerView recyclerView;
    private HoaDonDAO hoaDonDAO;
    private AdapterThongBaoDonHang adapterThongBaoDonHang;
    private ArrayList<HoaDon> listThongBao;



    public static DonHangFragment newInstance(String param1, String param2) {
        DonHangFragment fragment = new DonHangFragment();
        return fragment;
    }

    public DonHangFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_don_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        hoaDonDAO = new HoaDonDAO(getContext());
        listThongBao = (ArrayList<HoaDon>) hoaDonDAO.getAll();
        Collections.reverse(listThongBao);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
       adapterThongBaoDonHang=new AdapterThongBaoDonHang(listThongBao,getContext());
        recyclerView.setAdapter(adapterThongBaoDonHang);
        RecyclerView.ItemDecoration itemDecoration =  new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }



    private void initView(View view){
        recyclerView = view.findViewById(R.id.recyclierViewThongBaoDonHang);
    }

}