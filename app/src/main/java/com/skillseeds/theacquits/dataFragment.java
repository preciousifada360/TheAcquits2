package com.skillseeds.theacquits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

class dataFragment extends Fragment {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.s_o_s,container,false);


       viewPager=(ViewPager)view.findViewById(R.id.viewpage);
       viewPager.setAdapter(new sliderAdapter(getChildFragmentManager()));
       tabLayout=(TabLayout)view.findViewById(R.id.sliding_tabs);
       tabLayout.post(new Runnable(){
           @Override
           public void run(){
               tabLayout.setupWithViewPager(viewPager);
           }
       });



       return view;
    }
        private class sliderAdapter extends FragmentPagerAdapter{


            final String tabs[]={"tab1","tab2"};
            public sliderAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                return new contentFragment();
            }

            @Override
            public int getCount() {
                return 2;
            }
            @Override
            public CharSequence getPageTitle(int position){
                return tabs [position];
            }
        }

}
