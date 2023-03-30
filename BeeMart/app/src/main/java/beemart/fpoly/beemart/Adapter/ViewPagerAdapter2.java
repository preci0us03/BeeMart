package beemart.fpoly.beemart.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import beemart.fpoly.beemart.Fragment.FragmentLichSuNap;
import beemart.fpoly.beemart.Fragment.FragmentVi;

public class ViewPagerAdapter2 extends FragmentStatePagerAdapter {

    public ViewPagerAdapter2(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentVi();

            case 1:
                return new FragmentLichSuNap();

            default:
                return new FragmentVi();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Nạp Tiền";
                break;

            case 1:
                title = "Lịch Sử Nạp";
                break;


        }
        return title;
    }
}
