package com.xialan.beautymall.fragmentfactory;

import android.support.v4.app.Fragment;

import com.xialan.beautymall.base.BaseFragment;
import com.xialan.beautymall.view.ClassificationFragment;
import com.xialan.beautymall.view.home.HomeFragment;
import com.xialan.beautymall.view.ShopingCartFragment;
import com.xialan.beautymall.view.UserFragment;

import java.util.HashMap;
import java.util.Map;

/**  主工厂类
 * Created by Administrator on 2017/7/18.
 */
public class MainFragmentFactory {
    private static Map<Integer, BaseFragment> mFragments = new HashMap<>();
    public static Fragment getFragment(int position) {
        BaseFragment fragment = null;
        fragment = mFragments.get(position);  //在集合中取出来Fragment
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new ClassificationFragment();
                    break;
                case 2:
                    fragment = new ShopingCartFragment();
                    break;
                case 3:
                    fragment = new UserFragment();
                    break;
            }
        }
        return fragment;
    }
}
