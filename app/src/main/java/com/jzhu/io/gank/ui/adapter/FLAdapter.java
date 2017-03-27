package com.jzhu.io.gank.ui.adapter;

import android.support.v7.widget.RecyclerView;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import com.jzhu.io.baselibrary.utils.glide.ImageLoadConfig;
import com.jzhu.io.baselibrary.utils.glide.ImageLoader;
import com.jzhu.io.data.entities.GankFLEntities;
import com.jzhu.io.gank.R;

/**
 * Created by zhujian on 2017/3/26.
 */

public class FLAdapter extends BGARecyclerViewAdapter<GankFLEntities> {

    public FLAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.rv_fl_item);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, GankFLEntities model) {
        ImageLoader.loadStringRes(helper.getImageView(R.id.fl_image), model.getUrl(), ImageLoadConfig.parseBuilder(ImageLoader.defConfig).setAnimResId(R.anim.fade_in).build(), null);
    }
}
