package beemart.fpoly.beemart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import beemart.fpoly.beemart.Adapter.AdapterNhanVien;
import beemart.fpoly.beemart.Adapter.CartListComfirmAdapter;
import beemart.fpoly.beemart.Adapter.SpinnerPhuongThucThanhToanAdapter;
import beemart.fpoly.beemart.DAO.GioHangDAO2;
import beemart.fpoly.beemart.DAO.KhachHangDAO;
import beemart.fpoly.beemart.DAO.NhanVienDAO;
import beemart.fpoly.beemart.DTO.GioHang;
import beemart.fpoly.beemart.DTO.KhachHang;
import beemart.fpoly.beemart.DTO.NhanVien;
import beemart.fpoly.beemart.DTO.Order;
import beemart.fpoly.beemart.DTO.PhuongThucThanhToan;

public class XacNhanThongTinGioHang extends AppCompatActivity {
    private TextView tvKhachHang,tvSoDienThoai,tvDiaChi,tvDiemThuongHoaDon;
    private RecyclerView recyclerViewChiTietHoaDon;
    private Button btnXacNhan,btnXacNhanDiemThuong;
    private ArrayList<GioHang> list;
    private CartListComfirmAdapter cartListAdapter;
    private GioHangDAO2 gioHangDAO2;
    private Toolbar toolbar;
    private double tax;
    private KhachHangDAO khachHangDAO;
    private KhachHang objKhachHang;
    private List<PhuongThucThanhToan> listThanhToan;
    private SpinnerPhuongThucThanhToanAdapter spinnerPhuongThucThanhToanAdapter;
    private Spinner spinnerThanhToan;
    private EditText edDiemThuongHoaDon;
    private String tenKh,quyen,diachi;
    private int maGioHang,maNV,maKH ;
    private Long soDienThoai;
    private String titlePhuongThucThanhToan;
    private ConstraintLayout constraintManHinhXacNhan;
    private int maQuyen;
    private LinearLayout linearLayoutDiaChi,linerDialog;
    private NhanVien objNhanVien;
    private NhanVienDAO nhanVienDAO;
    private double diemThuongAll =0;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private int diemThuongSend;
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
        nhanVienDAO = new NhanVienDAO(this);
        thongTinHoaDon();
        thongTinNhanHang();
        gioHangDAO2 = new GioHangDAO2(this);
        list = (ArrayList<GioHang>) gioHangDAO2.getListCart(maGioHang+"",maQuyen);
        initList();
        tinhTien();
        Log.d("zzzz", "Tong tien" + tinhTien());

        loadSpinner();
        if(maQuyen == 2){
            thongTinKhachHangOffline();
        }
        btnXacNhanDiemThuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(diemThuongAll == 0){
                    snackBar(R.layout.custom_snackbar_error2,"Điểm thưởng hiện tại 0 điểm");
                    return;
                }
                if(edDiemThuongHoaDon.getText().toString().isEmpty()){
                    snackBar(R.layout.custom_snackbar_error2,"Bạn chưa nhập điểm thưởng");
                    return;
                }
                if(Double.parseDouble(edDiemThuongHoaDon.getText().toString()) >= diemThuongAll){
                    snackBar(R.layout.custom_snackbar_error2,"Điểm thưởng của bạn không đủ ");
                    return;
                }
                cusTomXacNhan(XacNhanThongTinGioHang.this,Gravity.CENTER,edDiemThuongHoaDon.getText().toString());

            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongTinNhanHang();
                if(edDiemThuongHoaDon.isEnabled() == true && !(edDiemThuongHoaDon.getText().toString().isEmpty())){
                    snackBar(R.layout.custom_snackbar_error2,"Bạn chưa xác nhận điểm thưởng");
                    return;
                }
                if(edDiemThuongHoaDon.getText().toString().isEmpty()){
                    diemThuongSend = 0;
                }else{
                    diemThuongSend = Integer.parseInt(edDiemThuongHoaDon.getText().toString());

                }
                openBottomSheetFragment();

            }
        });

    }
    private double setDiemThuong(double diemThuong){
        tvDiemThuongHoaDon.setText("Điểm thưởng hiện có: "+ df.format(diemThuong) +" điểm");
        return diemThuong;
    }

    private void thongTinKhachHangOffline(){
        tvKhachHang.setText("Khách hàng:chưa có dữ liệu");
        tvSoDienThoai.setText("Số điện thoại:chưa có dữ liệu");
        tvDiaChi.setText("Click để thêm -->");
        int colorText = Color.parseColor("#F5EBE0");
        tvDiaChi.setTextColor(colorText);
        linearLayoutDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCheckKhachHang(XacNhanThongTinGioHang.this,Gravity.CENTER);
            }
        });
    }

    private void dialogCheckKhachHang(Context context,int gravity) {
        Dialog dialog = new Dialog(context, R.style.PauseDialogAnimation);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_check_so_dien_thoai_khach_hang);

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
        EditText edSoDienThoai = dialog.findViewById(R.id.edSoDienThoai);
        Button btnCheck = dialog.findViewById(R.id.btnCheck);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        KhachHangDAO khachHangDAO = new KhachHangDAO(context);
        linerDialog = dialog.findViewById(R.id.linerDialog);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int colorText = Color.parseColor("#FFFBC1");
                KhachHang objKhachHangCheckSoDienThoai = new KhachHang();
                String soDienThoaiDialog = edSoDienThoai.getText().toString();
                if(soDienThoaiDialog.isEmpty()){
                    snackBar(R.layout.custom_snackbar_error2,"Bạn chưa nhập số điện thoại");
                    return;
                }
                try {
                    objKhachHangCheckSoDienThoai = khachHangDAO.getSoDienThoai(Long.parseLong(soDienThoaiDialog)+"");
                }catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                    snackBar(R.layout.custom_snackbar_error2,"Số điện thoại không tồn tại trong hệ thống");
                    return;
                }
                if(objKhachHangCheckSoDienThoai != null){
                    btnCheck.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.boder_button_check));
                    btnCheck.setTextColor(colorText);
                    btnCheck.setText("OK");
                    btnHuy.setText("Thoát");
                    btnHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    tvKhachHang.setText("Khách hàng: "+objKhachHangCheckSoDienThoai.getTenKH());
                    tvSoDienThoai.setText("Số điện thoại: 0"+objKhachHangCheckSoDienThoai.getSoDienThoai()+"");
                    linearLayoutDiaChi.setEnabled(false);
                    tvDiaChi.setText("");
                    tvDiemThuongHoaDon.setText("Điểm thượng hiện có: " +objKhachHangCheckSoDienThoai.getDiemThuong() + " Điểm");
                    snackBarDialog(R.layout.custom_snackbar_check_mark_thanh_cong,"Khách hàng tồn tại trong hệ thống");
                    diemThuongAll = objKhachHangCheckSoDienThoai.getDiemThuong();
                    tenKh = objKhachHangCheckSoDienThoai.getTenKH();
                    soDienThoai = objKhachHangCheckSoDienThoai.getSoDienThoai();
                    diachi = objKhachHangCheckSoDienThoai.getDiaChi();
                    maKH = objKhachHangCheckSoDienThoai.getMaKH();

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


    public void snackBar(int layout,String s) {
        Snackbar snackbar = Snackbar.make(constraintManHinhXacNhan, "", Snackbar.LENGTH_LONG);
        View custom = getLayoutInflater().inflate(layout, null);
        TextView tvError = custom.findViewById(R.id.tvError);
        tvError.setText(s);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(25, 25, 25, 25);
        snackbarLayout.addView(custom, 0);
        snackbar.show();
    }
    public void snackBarDialog(int layout,String s) {
        Snackbar snackbar = Snackbar.make(linerDialog, "", Snackbar.LENGTH_LONG);
        View custom = getLayoutInflater().inflate(layout, null);
        TextView tvError = custom.findViewById(R.id.tvError);
        tvError.setText(s);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(25, 25, 25, 25);
        snackbarLayout.addView(custom, 0);
        snackbar.show();
    }
    private void openBottomSheetFragment() {
        Order order = new Order(list,tenKh,soDienThoai,diachi,tongTienSauKhiGiam(),titlePhuongThucThanhToan,maKH,maNV,maGioHang,maQuyen,diemThuongSend);
        BottomSheetDialogXacNhanHoaDon bottomSheetDialogXacNhanHoaDon = BottomSheetDialogXacNhanHoaDon.newInstance(order);
        bottomSheetDialogXacNhanHoaDon.show(getSupportFragmentManager(),bottomSheetDialogXacNhanHoaDon.getTag());
        bottomSheetDialogXacNhanHoaDon.setCancelable(false);
    }

    public void cusTomXacNhan(Context context, int gravity,String diemThuong) {
        Dialog dialog = new Dialog(context, R.style.PauseDialogAnimation);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_diem_thuong);

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
        TextView tvDiemThuong = dialog.findViewById(R.id.tvDiemThuong);

        tvDiemThuong.setText(diemThuong+" điểm thưởng");
        Button btnDongY = dialog.findViewById(R.id.btnDongY);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongTienSauKhiGiam();
                Log.d("zzzz", "Tong tien sau khi giam" + tongTienSauKhiGiam());
                btnXacNhanDiemThuong.setEnabled(false);
                edDiemThuongHoaDon.setEnabled(false);
                setDiemThuong(diemThuongAll-Double.parseDouble(diemThuong));
                dialog.dismiss();
            }


        });
        dialog.show();
    }
    private void loadSpinner() {
        listThanhToan = new ArrayList<>();
        listThanhToan.add(new PhuongThucThanhToan("Ví Beepay",R.drawable.icon_vi));
        listThanhToan.add(new PhuongThucThanhToan("Thanh toán khi nhận hàng",R.drawable.icon_cod));
        spinnerPhuongThucThanhToanAdapter = new SpinnerPhuongThucThanhToanAdapter(this, (ArrayList<PhuongThucThanhToan>) listThanhToan);
        spinnerThanhToan.setAdapter(spinnerPhuongThucThanhToanAdapter);
        spinnerThanhToan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                titlePhuongThucThanhToan = listThanhToan.get(position).getTitle();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }





    private void thongTinNhanHang() {
        SharedPreferences preferences = getSharedPreferences("NAMEUSER",MODE_PRIVATE);
        String userName = (preferences.getString("userNameAcount",""));
        quyen = (preferences.getString("quyen",""));
        if(quyen.equals("nhanvien")){
            maQuyen = 2;
            objNhanVien = nhanVienDAO.getUserName(userName);
            if(objNhanVien != null){
                maGioHang = objNhanVien.getMaNV();

            }
        }else if(quyen.equals("khachhang")){
            maQuyen = 1;
            objKhachHang = khachHangDAO.getUserName(userName);
            if(objKhachHang != null){
                tvKhachHang.setText("Khách hàng : " +objKhachHang.getTenKH());
                tenKh = objKhachHang.getTenKH();
                tvSoDienThoai.setText("Số điện thoại : 0" +objKhachHang.getSoDienThoai() +"");
                soDienThoai = objKhachHang.getSoDienThoai();
                maGioHang = objKhachHang.getMaKH();
                setDiemThuong(objKhachHang.getDiemThuong());
                diemThuongAll = objKhachHang.getDiemThuong();
                diachi = objKhachHang.getDiaChi();

            }
        }



    }

    private double tinhTien(){
        double TongTien;
        double thue = 0.02;
        double phidichvu = 2000;
        tax = Math.round((gioHangDAO2.getTolalFee(maGioHang+"",maQuyen)*thue)*100)/100;
        double total = Math.round(((gioHangDAO2.getTolalFee(maGioHang+"",maQuyen)+tax)*100)/100);
        btnXacNhan.setText(total+phidichvu +" VND");
        TongTien = total+phidichvu;

        return TongTien;

    }
    private Double tongTienSauKhiGiam(){

        Double finalDiemThuong = Double.valueOf(0);
        String diemThuong = edDiemThuongHoaDon.getText().toString();
        if(diemThuong.isEmpty()){
            diemThuong = String.valueOf(0);
        }
        finalDiemThuong = tinhTien()-Double.parseDouble(diemThuong);
        btnXacNhan.setText(finalDiemThuong  +" VND");
        return  finalDiemThuong;
    }
    public void anhXaView(){
        tvKhachHang = findViewById(R.id.tvKhachHang);
        tvSoDienThoai = findViewById(R.id.tvSoDienThoai);
        recyclerViewChiTietHoaDon = findViewById(R.id.recyclerViewChiTietHoaDon);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        toolbar = findViewById(R.id.idToolBar);
        spinnerThanhToan = findViewById(R.id.spinerPhuongThucThanhToan);
        edDiemThuongHoaDon = findViewById(R.id.edDiemThuongHoaDon);
        btnXacNhanDiemThuong = findViewById(R.id.btnXacNhanDiemThuong);
        constraintManHinhXacNhan = findViewById(R.id.constraintManHinhXacNhan);
        linearLayoutDiaChi = findViewById(R.id.linearLayoutDiaChi);
        tvDiaChi = findViewById(R.id.tvDiaChi);
        tvDiemThuongHoaDon = findViewById(R.id.tvDiemThuongHoaDon);
    }
    public void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewChiTietHoaDon.setLayoutManager(linearLayoutManager);
        cartListAdapter = new CartListComfirmAdapter(list);
        recyclerViewChiTietHoaDon.setAdapter(cartListAdapter);
        RecyclerView.ItemDecoration itemDecoration =  new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerViewChiTietHoaDon.addItemDecoration(itemDecoration);

    }
    private void thongTinHoaDon(){
        SharedPreferences preferences = getSharedPreferences("NAMEUSER",MODE_PRIVATE);
        String userName = (preferences.getString("userNameAcount",""));
        String quyen = (preferences.getString("quyen",""));
        if(quyen.equals("nhanvien")){
            objNhanVien = nhanVienDAO.getUserName(userName);
            if(objNhanVien != null){
                maNV = objNhanVien.getMaNV();
            }
        }else if(quyen.equals("khachhang")){
            objKhachHang = khachHangDAO.getUserName(userName);
            if(objKhachHang != null){
                maKH = objKhachHang.getMaKH();

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}