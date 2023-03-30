package beemart.fpoly.beemart.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import beemart.fpoly.beemart.DAO.KhachHangDAO;
import beemart.fpoly.beemart.DTO.KhachHang;
import beemart.fpoly.beemart.NapTien;
import beemart.fpoly.beemart.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentVi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentVi extends Fragment {

    Button btnNapTien;
    TextView tvSoDuVi;
    ImageView imAnHien;
    private KhachHangDAO khachHangDAO;
    private KhachHang objKhachHang;
    Boolean check = true;


    public FragmentVi() {
        // Required empty public constructor
    }

    public static FragmentVi newInstance() {
        FragmentVi fragment = new FragmentVi();

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
        return inflater.inflate(R.layout.fragment_vi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = getActivity().getSharedPreferences("NAMEUSER",MODE_PRIVATE);
        String userName = (preferences.getString("userNameAcount",""));
        khachHangDAO = new KhachHangDAO(getContext());
        objKhachHang = khachHangDAO.getUserName(userName);
        btnNapTien= view.findViewById(R.id.btnNapTien);
        tvSoDuVi = view.findViewById(R.id.tvSoDuVi);
        imAnHien = view.findViewById(R.id.imAnHien);

        btnNapTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NapTien.class));
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        imAnHien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check == true){
                    imAnHien.setImageResource(R.drawable.eye);
                    tvSoDuVi.setText(objKhachHang.getViTien()+"");
                    check = false;
                }else{
                    imAnHien.setImageResource(R.drawable.eye_hide);
                    tvSoDuVi.setText("*******");
                    check = true;
                }
            }
        });
    }
}