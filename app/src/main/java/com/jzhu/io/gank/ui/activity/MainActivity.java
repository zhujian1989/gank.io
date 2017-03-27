package com.jzhu.io.gank.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jzhu.io.baselibrary.BaseActivity;
import com.jzhu.io.gank.R;
import com.jzhu.io.gank.ui.adapter.TitleFragmentPagerAdapter;
import com.jzhu.io.gank.ui.fragment.AndroidFragment;
import com.jzhu.io.gank.ui.fragment.FLFragment;
import com.jzhu.io.gank.ui.fragment.IosFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/main/mainActivity")
public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        initFragments();
    }

    private void initFragments() {
        List<Fragment> fragments = new ArrayList<Fragment>();
        FLFragment flFragment = new FLFragment();
        AndroidFragment AndroidFragment = new AndroidFragment();
        IosFragment IosFragment = new IosFragment();
        fragments.add(flFragment);
        fragments.add(AndroidFragment);
        fragments.add(IosFragment);

        TitleFragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getSupportFragmentManager(), fragments, this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < adapter.getCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(adapter.getTabView(i));
        }
    }


    @OnClick(R.id.about)
    public void onViewClicked() {
        ARouter.getInstance().build("/about/aboutActivity").navigation();
    }
}
