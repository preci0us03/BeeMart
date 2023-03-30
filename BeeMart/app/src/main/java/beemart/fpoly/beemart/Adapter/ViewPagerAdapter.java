package beemart.fpoly.beemart.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import beemart.fpoly.beemart.Fragment.FragmentChoXacNhan;
import beemart.fpoly.beemart.Fragment.FragmentDaXacNhan;
import beemart.fpoly.beemart.Fragment.FragmentDangGiao;
import beemart.fpoly.beemart.Fragment.FragmentGiaoXong;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentChoXacNhan();

            case 1:
                return new FragmentDaXacNhan();

            case 2:
                return new FragmentDangGiao();

            case 3:
                return new FragmentGiaoXong();

            default:
                return new FragmentChoXacNhan();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Chờ xác nhận";
                break;

            case 1:
                title = "Đã xác nhận";
                break;

            case 2:
                title = "Đang giao";
                break;

            case 3:
                title = "Đã giao";
                break;

        }
        return title;
    }
}
