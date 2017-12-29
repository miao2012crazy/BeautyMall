package com.xialan.beautymall.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xialan.beautymall.bean.ADBean;
import com.xialan.beautymall.bean.HomeBean;
import com.xialan.beautymall.utils.ImageLoaderManager;

import java.util.List;

/**
 * Created by Administrator on 2017/11/12.
 */

public class RollAdapter extends StaticPagerAdapter {
    private final List<HomeBean.DataBean.LoopListBean> list;

    public RollAdapter(List<HomeBean.DataBean.LoopListBean> mlist) {
        this.list = mlist;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        ImageLoader.getInstance().displayImage(list.get(position).getUrl(),view);
//        ImageLoaderManager.displayImage(list.get(position).getUrl(),view);
        view.setTag(list.get(position).getProduct_id());
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
