package beemart.fpoly.beemart.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import beemart.fpoly.beemart.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentThongTinCaNhan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentThongTinCaNhan extends Fragment {

    EditText edUserNameKH,edSDTKH,edEmailKH,edDiaChiKH,edDiemThuongKH;
    TextView tvTenKH, tvDiaChi2KH;


    public FragmentThongTinCaNhan() {
        // Required empty public constructor
    }


    public static FragmentThongTinCaNhan newInstance() {
        FragmentThongTinCaNhan fragment = new FragmentThongTinCaNhan();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_tin_ca_nhan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTenKH=view.findViewById(R.id.tenKhachHang);
        tvDiaChi2KH=view.findViewById(R.id.diaChi2);
        edUserNameKH=view.findViewById(R.id.edUserNameKH);
        edEmailKH=view.findViewById(R.id.edEmailKH);
        edDiaChiKH=view.findViewById(R.id.edDiaChiKH);
        edDiemThuongKH=view.findViewById(R.id.edDiemThuongKH);
        edSDTKH=view.findViewById(R.id.edSDTKH);

        edUserNameKH.setEnabled(false);
        edEmailKH.setEnabled(false);
        edDiaChiKH.setEnabled(false);
        edDiemThuongKH.setEnabled(false);
        edSDTKH.setEnabled(false);
    }
}