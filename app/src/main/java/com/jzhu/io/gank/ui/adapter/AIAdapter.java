package com.jzhu.io.gank.ui.adapter;

import android.support.v7.widget.RecyclerView;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import com.jzhu.io.baselibrary.utils.DateUtil;
import com.jzhu.io.baselibrary.utils.ObjectUtils;
import com.jzhu.io.baselibrary.utils.glide.ImageLoadConfig;
import com.jzhu.io.baselibrary.utils.glide.ImageLoader;
import com.jzhu.io.data.entities.GankAndroidAndiOSEntities;
import com.jzhu.io.gank.R;
import com.jzhu.io.gank.common.Constant;

/**
 * Created by zhujian on 2017/3/26.
 */

public class AIAdapter extends BGARecyclerViewAdapter<GankAndroidAndiOSEntities> {

    public AIAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.rv_ai_item);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, GankAndroidAndiOSEntities model) {
        helper.getConvertView().setTag(model);
        if (!ObjectUtils.isListEmpty(model.getImages())) {
            String imageUrl = model.getImages().get(0);
            ImageLoader.loadStringRes(helper.getImageView(R.id.image), formatThumbnailUrl(imageUrl), ImageLoadConfig.parseBuilder(ImageLoader.defConfig).setAnimResId(R.anim.fade_in).build(), null);
        }
        helper.getTextView(R.id.title).setText(model.getDesc());
        helper.getTextView(R.id.name).setText(model.getWho());
        helper.getTextView(R.id.date).setText(DateUtil.format(model.getPublishedAt(), DateUtil.FORMAT_SHORT_CN));
    }

    private String formatThumbnailUrl(String url) {
        StringBuilder sb = new StringBuilder();
        sb.append(url).append(Constant.THUMBNAIL_END_URL_100);
        return sb.toString();
    }
}
