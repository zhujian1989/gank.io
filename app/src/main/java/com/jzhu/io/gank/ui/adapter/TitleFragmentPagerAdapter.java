package com.jzhu.io.gank.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.jzhu.io.gank.R;

import java.util.List;

/**
 * Created by jzhu on 2016/8/31.
 */
public class TitleFragmentPagerAdapter extends FragmentPagerAdapter {

    /**
     * The m fragment list.
     */
    private List<Fragment> mFragmentList = null;

    private Context mContext;

    private LayoutInflater mInflater;

    private static final int[] TITLES = {
            R.string.tab_fl,
            R.string.tab_android,
            R.string.tab_ios
    };

    private static final int[] ICONS = {
            R.drawable.selector_tab_fl,
            R.drawable.selector_tab_android,
            R.drawable.selector_tab_ios
    };

    /**
     * titles是给TabLayout设置title用的
     *
     * @param fragmentManager
     * @param fragmentList
     */
    public TitleFragmentPagerAdapter(FragmentManager fragmentManager,
                                     List<Fragment> fragmentList, Context context) {
        super(fragmentManager);
        mFragmentList = fragmentList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * 描述：获取数量.
     *
     * @return the count
     * @see android.support.v4.view.PagerAdapter#getCount()
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * 描述：获取索引位置的Fragment.
     *
     * @param position the position
     * @return the item
     * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
     */
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if (position < mFragmentList.size()) {
            fragment = mFragmentList.get(position);
        }
        else {
            fragment = mFragmentList.get(0);
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mContext.getResources().getString(TITLES[position]);
    }

    public View getTabView(int postion) {
        View view = mInflater.inflate(R.layout.tab_with_icon, null);
        TextView tv = (TextView) view.findViewById(R.id.title);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        tv.setText(mContext.getString(TITLES[postion]));
        imageView.setImageResource(ICONS[postion]);
        return view;
    }

}
