package com.jzhu.io.gank.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jzhu.io.gank.R;

/**
 * Created by jzhu on 2016/10/14.
 * exp
 * <com.jzhu.io.gank.ui.widget.TitleBar
 * android:id="@+id/title_bar"
 * style="@style/MatchMatch"
 * app:title_bar_title="@string/flight_info_title"
 * app:title_bar_left_subtext="@string/flight_info_ok"
 * app:title_bar_left_icon="@drawable/return_icon"
 * app:title_bar_right_icon="@drawable/return_icon"
 * app:title_bar_right_subtext="@string/flight_info_ok"
 * />
 */
public class TitleBar extends RelativeLayout {

    @BindView(R.id.left_icon)
    ImageView leftIcon;

    @BindView(R.id.left_bullet)
    TextView leftBullet;

    @BindView(R.id.left_layout)
    RelativeLayout leftLayout;

    @BindView(R.id.left_subtext)
    TextView leftSubtext;

    @BindView(R.id.right_icon)
    ImageView rightIcon;

    @BindView(R.id.right_bullet)
    TextView rightBullet;

    @BindView(R.id.right_layout)
    RelativeLayout rightLayout;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.right_subtext)
    TextView rightSubtext;

    public TitleBar(Context context) {
        super(context);
        initViews(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        View content = View.inflate(context, R.layout.title_bar_layout, this);
        ButterKnife.bind(this);
        initTypedArray(context,attrs);
    }

    private void initTypedArray(Context context, AttributeSet attrs){
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.title_bar_style);
        String minTitle = array.getString(R.styleable.title_bar_style_title_bar_title);
        String leftText = array.getString(R.styleable.title_bar_style_title_bar_left_subtext);
        String rightText = array.getString(R.styleable.title_bar_style_title_bar_right_subtext);
        Drawable leftImage = array.getDrawable(R.styleable.title_bar_style_title_bar_left_icon);
        Drawable rightImage = array.getDrawable(R.styleable.title_bar_style_title_bar_right_icon);

        if (!TextUtils.isEmpty(minTitle)) {
              title.setText(minTitle);
        }

        if (!TextUtils.isEmpty(leftText)) {
            leftSubtext.setVisibility(View.VISIBLE);
            leftSubtext.setText(leftText);
        }

        if (!TextUtils.isEmpty(rightText)) {
            rightSubtext.setVisibility(View.VISIBLE);
            rightSubtext.setText(rightText);
        }

        if (null != leftImage) {
            leftLayout.setVisibility(View.VISIBLE);
            leftIcon.setVisibility(View.VISIBLE);
            leftIcon.setImageDrawable(leftImage);
        }

        if (null != rightImage) {
            rightLayout.setVisibility(View.VISIBLE);
            rightIcon.setVisibility(View.VISIBLE);
            rightIcon.setImageDrawable(rightImage);
        }

        array.recycle();

    }

    //左上角红点
    public void setLeftBullet(boolean isShow) {
        if (!isShow) {
            leftBullet.setVisibility(View.GONE);
        }
        else {
            leftBullet.setVisibility(View.VISIBLE);
        }

    }

    //右上角数字提示
    public void setBullet(int count, boolean isShow) {
        if (!isShow) {
            rightBullet.setVisibility(View.GONE);
            rightBullet.setText(null);
            return;
        }
        else {
            rightBullet.setText(String.valueOf(count));
            rightBullet.setVisibility(View.VISIBLE);
        }
    }

    public void setTitle(String str) {
        this.title.setText(str);
    }

}

