package com.lords.pases.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lords.pases.fragments.FragmentAddSoli;
import com.lords.pases.fragments.FragmentListSolicitudes;
import com.lords.pases.fragments.Fragment_Settings;
import com.lords.pases.fragments.ScannerFragment;

public class ViewPagerAdapterUser   extends FragmentStatePagerAdapter {
    private String matricula;
    public ViewPagerAdapterUser(FragmentManager fm,String matricula) {
        super(fm);
        this.matricula=matricula;
    }



    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 2:

                return Fragment_Settings.newInstance();
            case 1:
                FragmentAddSoli  f1=new FragmentAddSoli();
                f1.setMatri(matricula);
               return f1 ;
            case 0:
                FragmentListSolicitudes f0= new FragmentListSolicitudes();
                f0.setMatri(matricula);
               return f0;
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
