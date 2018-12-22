package com.example.hubert.gameoflife;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hubert.gameoflife.education.EduFragment;
import com.example.hubert.gameoflife.girlboyfriend.GirlboyfriendFragment;
import com.example.hubert.gameoflife.house.HomeFragment;
import com.example.hubert.gameoflife.profile.MainFragment;
import com.example.hubert.gameoflife.shop.ShopFragment;

class CustomPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 5;
    // --Commented out by Inspection (12/8/2018 12:30 AM):private final Context context;

    public CustomPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        //this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return EduFragment.newInstance();
            case 2:
                return ShopFragment.newInstance();
            case 3:
                return GirlboyfriendFragment.newInstance();
            case 4:
                return HomeFragment.newInstance();
            default:
                return MainFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return "Edu";
            case 2:
                return "Shop";
            case 3:
                return "Love";
            case 4:
                return "Home";
            default:
                return "Main";
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
