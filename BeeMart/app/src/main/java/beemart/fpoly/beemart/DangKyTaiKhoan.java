package beemart.fpoly.beemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import beemart.fpoly.beemart.DAO.KhachHangDAO;
import beemart.fpoly.beemart.DTO.KhachHang;
import beemart.fpoly.beemart.DangKyTaiKhoanKhachHang.VerifiOTPDangKy;

public class DangKyTaiKhoan extends AppCompatActivity {
    private ImageView imgBack;
    EditText edUserName,edPassWord,edSoDienThoai,edRePassWord,edTenKhachHangDangKy;
    Button btnDangKyTaiKhoan;
    RelativeLayout dangKyTaiKhoanActitivty;
    KhachHangDAO khachHangDAO;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_tai_khoan);
        if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);

        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        imgBack = findViewById(R.id.imgBack);
        edPassWord = findViewById(R.id.edPassDangKy);
        edRePassWord = findViewById(R.id.edRePassDangKy);
        edUserName = findViewById(R.id.edUserNameDangKy);
        edSoDienThoai = findViewById(R.id.edSoDienThoaiDangKy);
        btnDangKyTaiKhoan = findViewById(R.id.btnDangKy);
        dangKyTaiKhoanActitivty = findViewById(R.id.dangKyTaiKhoanActitivty);
        edTenKhachHangDangKy = findViewById(R.id.edTenKhachHangDangKy);
        khachHangDAO = new KhachHangDAO(this);
        btnDangKyTaiKhoan.setOnClickListener(v -> checkValidate());
        imgBack.setOnClickListener(v -> onBackPressed());

    }



    private void checkValidate() {
        String userName = edUserName.getText().toString().trim();
        String passWored = edPassWord.getText().toString().trim();
        String rePass = edRePassWord.getText().toString().trim();
        String soDienThoai = edSoDienThoai.getText().toString().trim();
        String tenKhachHang = edTenKhachHangDangKy.getText().toString().trim();

        if(userName.length() == 0 ||
                passWored.length() == 0 ||
                rePass.length() == 0 ||
                soDienThoai.length() == 0 ||
                tenKhachHang.length() == 0) {
            snackBar(R.layout.custom_snackbar_error2, "Không được bỏ trống");
            return;
        }else {
            if(!(soDienThoai.length() >= 10 && soDienThoai.length() <=12)){
                snackBar(R.layout.custom_snackbar_error2,"Số điện thoại độ dài khoảng 10-12 ký tự");
                return;
            }


            if( !(passWored.equals(rePass) && rePass.equals(passWored) )){
                snackBar(R.layout.custom_snackbar_error2,"Mật khẩu không trùng khớp");
                return;
            }
            KhachHang objKhachHang = new KhachHang();
            objKhachHang.setUserNameKH(userName);
            objKhachHang.setPasswordKH(passWored);
            objKhachHang.setTenKH(tenKhachHang);
            objKhachHang.setSoDienThoai(Long.valueOf(soDienThoai));
//            PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                    "+84"+soDienThoai,60,
//                    TimeUnit.SECONDS,
//                    DangKyTaiKhoan.this,
//                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
//                        @Override
//                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//                        }
//
//                        @Override
//                        public void onVerificationFailed(@NonNull FirebaseException e) {
//                            Toast.makeText(DangKyTaiKhoan.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                            super.onCodeSent(s, forceResendingToken);
//                            Intent intent = new Intent(DangKyTaiKhoan.this, VerifiOTPDangKy.class);
//                            intent.putExtra("objKhachHang",objKhachHang);
//                            intent.putExtra("otp",s);
//                            startActivity(intent);
//                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                        }
//                    }
//            );
            long kq = khachHangDAO.insert(objKhachHang);
            if(kq >0){
                snackBar(R.layout.custom_snackbar_check_mark_thanh_cong,"Đăng ký tài khoản thành công");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                },1400);
            }else{
                snackBar(R.layout.custom_snackbar_error2,"Đăng ký thất bại");
            }
        }

    }
    public void snackBar(int layout,String s) {
        Snackbar snackbar = Snackbar.make(dangKyTaiKhoanActitivty, "", Snackbar.LENGTH_LONG);
        View custom = getLayoutInflater().inflate(layout, null);
        TextView tvError = custom.findViewById(R.id.tvError);
        tvError.setText(s);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(25, 25, 25, 25);
        snackbarLayout.addView(custom, 0);
        snackbar.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    public void dialogLoading(Context context,int gravity){
         dialog = new Dialog(context, R.style.PauseDialogAnimation);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_progress);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.shadowDialog)));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progressBarLoading);
        int colorCodeDark = Color.parseColor("#FC630D");
        progressBar.setIndeterminateTintList(ColorStateList.valueOf(colorCodeDark));
        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        dialog.show();
    }

}