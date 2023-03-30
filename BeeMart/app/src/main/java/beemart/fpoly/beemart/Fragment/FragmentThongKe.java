package beemart.fpoly.beemart.Fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import beemart.fpoly.beemart.DAO.ThongKeDao;
import beemart.fpoly.beemart.DTO.ThongKe;
import beemart.fpoly.beemart.DTO.ThongKeDoanhThu;
import beemart.fpoly.beemart.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentThongKe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentThongKe extends Fragment {
    private BarChart barChar;
    private ThongKeDao thongKeDao;
    private List<ThongKeDoanhThu> list;
    int mYear, mMonth, mDay;
    private EditText edTuNgay,edDenNgay;
    private Button btnTuNgay,btnDenNgay;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    String str_date ="27/08/2010";
    String end_date ="02/09/2010";
    private Button btnXacNhanThongKe;
    public FragmentThongKe() {
        // Required empty public constructor
    }

    public static FragmentThongKe newInstance() {
        FragmentThongKe fragment = new FragmentThongKe();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        thongKeDao = new ThongKeDao(getContext());
        list = thongKeDao.getTop("30/11/2022","30/11/2022");
        Log.d("zzzzz", "List thống kê " + list.size());





        ArrayList<BarEntry> listBar = new ArrayList<>();
        ArrayList<ThongKe> listThongKe = new ArrayList<>();
        ArrayList<String> timeColumn = new ArrayList<>();
        listThongKe.add(new ThongKe("10/10", 300000));
        listThongKe.add(new ThongKe("11/10", 400000));
        listThongKe.add(new ThongKe("12/10", 500000));
        listThongKe.add(new ThongKe("13/10", 600000));
        listThongKe.add(new ThongKe("14/10", 700000));
        listThongKe.add(new ThongKe("15/10", 800000));
        for (int i = 0; i < listThongKe.size(); i++) {
            String ngay = listThongKe.get(i).getNgay();
            double doanhthu = listThongKe.get(i).getDoanhThu();
            listBar.add(new BarEntry(i, (float) doanhthu));
            timeColumn.add(ngay);
        }
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                mYear=c.get(Calendar.YEAR);
                mMonth=c.get(Calendar.MONTH);
                mDay=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d=new DatePickerDialog(getActivity(),
                        0,mDateNSX,mYear,mMonth,mDay);
                d.show();

            }
        });

        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                mYear=c.get(Calendar.YEAR);
                mMonth=c.get(Calendar.MONTH);
                mDay=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d=new DatePickerDialog(getActivity(),
                        0,mDateHSD,mYear,mMonth,mDay);
                d.show();
            }
        });

        BarDataSet barDataSet = new BarDataSet(listBar, "Doanh thu");
        barDataSet.setColors(Color.WHITE);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        BarData barData = new BarData(barDataSet);
        barChar.setFitBars(true);
        barChar.setData(barData);
        barChar.getDescription().setText("Thống kê");
        barChar.animateY(2000);
        XAxis xAxis = barChar.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(timeColumn));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(timeColumn.size());
        btnXacNhanThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListDay();
            }
        });


    }
    private void getListDay(){
        List<Date> dates = new ArrayList<Date>();
        DateFormat formatter ;
        formatter = new SimpleDateFormat("dd/MM");
        Date  startDate = null;
        try {
            startDate = (Date)formatter.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date  endDate = null;
        try {
            endDate = (Date)formatter.parse(end_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long interval = 24*1000 * 60 * 60; // 1 hour in millis
        long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
        long curTime = startDate.getTime();
        while (curTime <= endTime) {
            dates.add(new Date(curTime));
            curTime += interval;
        }
        for(int i=0;i<dates.size();i++){
            Date lDate =(Date)dates.get(i);
            String ds = formatter.format(lDate);
            Log.d("zzzzz", "List ngày " + ds);
        }
    }

    private void initView(View view) {
        barChar = view.findViewById(R.id.barChar);
        edTuNgay = view.findViewById(R.id.edTuNgay);
        edDenNgay = view.findViewById(R.id.edDenNgay);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnXacNhanThongKe = view.findViewById(R.id.btnXacNhanThongKe);
    }
    DatePickerDialog.OnDateSetListener mDateNSX=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear=year;
            mMonth=monthOfYear;
            mDay=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(mYear,mMonth,mDay);
            edTuNgay.setText(sdf.format(c.getTime()));
            str_date = sdf.format(c.getTime());
        }
    };

    DatePickerDialog.OnDateSetListener mDateHSD=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear=year;
            mMonth=monthOfYear;
            mDay=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(mYear,mMonth,mDay);
            edDenNgay.setText(sdf.format(c.getTime()));
            end_date = sdf.format(c.getTime());
        }
    };
}