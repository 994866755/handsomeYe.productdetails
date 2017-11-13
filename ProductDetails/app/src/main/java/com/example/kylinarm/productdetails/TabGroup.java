package com.example.kylinarm.productdetails;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kylinARM on 2017/11/13.
 */

public class TabGroup {

    private Context context;
    private List<TabLayout> tabLayoutList;
    private TabChangeLinstener tabChangeLinstener;
    // 记录选中的Tab，默认为0
    private int mTab = 0;

    public TabGroup(Context context){
        this.context = context;
        tabLayoutList = new ArrayList<>();
    }

    public void addTabLayout(TabLayout tabLayout){
        tabLayoutList.add(tabLayout);
    }

    public void addTitiles(String[] titles){

        if (tabLayoutList == null || tabLayoutList.size() < 1){
            return;
        }

        for (int i = 0; i < tabLayoutList.size(); i++) {
            for (int j = 0; j < titles.length; j++) {
                tabLayoutList.get(i).addTab(tabLayoutList.get(i).newTab().setText(titles[j]));
            }
        }

    }

    public void setTabChangeLinstener(TabChangeLinstener tabChangeLinstener) {
        this.tabChangeLinstener = tabChangeLinstener;
    }

    public void tabGroupListener(){
        if (tabLayoutList == null || tabLayoutList.size() < 1){
            return;
        }

        tabLayoutList.get(0).setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTab = tab.getPosition();
                for (int i = 1; i < tabLayoutList.size(); i++) {
                    tabLayoutList.get(i).getTabAt(mTab).select();
                }
                if (tabChangeLinstener != null) {
                    tabChangeLinstener.tabChange(tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 1; i < tabLayoutList.size(); i++) {
            tabLayoutList.get(i).setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (mTab != tab.getPosition()) {
                        mTab = tab.getPosition();
                        tabLayoutList.get(0).getTabAt(mTab).select();
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }

    }

}
