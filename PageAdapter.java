package com.example.demo;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import androidx.fragment.app.FragmentStatePagerAdapter;

class PageAdapter extends FragmentPagerAdapter {

    private  int numberoftab;

    public PageAdapter(FragmentManager fm,int numberoftab) {
        super(fm);
        this.numberoftab=numberoftab;
    }

    @NonNull

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case  0:
                return new tab1();
            case  1:
                return new tab2();
            case  2:
                return new tab3();
            case  3:
                return new tab4();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numberoftab;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
