package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class useractivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tab1,tab2;
    public PageAdapter pageradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useractivity);

        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        tab1=(TabItem)findViewById(R.id.Tab1);
        tab2=(TabItem)findViewById(R.id.Tab2);
        tab1=(TabItem)findViewById(R.id.Tab3);
        tab2=(TabItem)findViewById(R.id.Tab4);
        viewPager=findViewById(R.id.viewpager);

        pageradapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageradapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){

                    pageradapter.notifyDataSetChanged();
                }
                else if(tab.getPosition()==1){

                    pageradapter.notifyDataSetChanged();
                }
                else if(tab.getPosition()==2){

                    pageradapter.notifyDataSetChanged();
                }
                else if(tab.getPosition()==3){

                    pageradapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
