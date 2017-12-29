package com.xialan.beautymall.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xialan.beautymall.fragmentfactory.MainFragmentFactory;

/**
 * Created by ${苗} on 2017/11/8.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = MainFragmentFactory.getFragment(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
