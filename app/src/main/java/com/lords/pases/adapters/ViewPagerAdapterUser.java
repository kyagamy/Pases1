package com.lords.pases.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lords.pases.fragments.FragmentAddSoli;
import com.lords.pases.fragments.FragmentListSolicitudes;
import com.lords.pases.fragments.Fragment_Settings;
import com.lords.pases.fragments.ScannerFragment;

public class ViewPagerAdapterUser   extends FragmentStatePagerAdapter {
    public ViewPagerAdapterUser(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 2:
                return Fragment_Settings.newInstance();
            case 1:
               return FragmentAddSoli.newInstance();
            case 0:
               return new FragmentListSolicitudes();
            case 3:
                return new ScannerFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
