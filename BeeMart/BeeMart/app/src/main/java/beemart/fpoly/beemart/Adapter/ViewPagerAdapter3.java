package beemart.fpoly.beemart.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import beemart.fpoly.beemart.Fragment.FragmentChoDuyet;
import beemart.fpoly.beemart.Fragment.FragmentDaDuyet;
import beemart.fpoly.beemart.Fragment.FragmentLichSuNap;
import beemart.fpoly.beemart.Fragment.FragmentVi;

public class ViewPagerAdapter3 extends FragmentStatePagerAdapter {

    public ViewPagerAdapter3(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentChoDuyet();

            case 1:
                return new FragmentDaDuyet();

            default:
                return new FragmentChoDuyet();
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
                title = "Chờ Duyệt";
                break;

            case 1:
                title = "Đã Duyệt";
                break;


        }
        return title;
    }
}
