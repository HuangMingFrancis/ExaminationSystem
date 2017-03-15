package com.example.francis.examinationsystem.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.francis.examinationsystem.R;
import com.example.francis.examinationsystem.view.adapter.GuidePageAdapter;

import java.util.ArrayList;

/**
 * 引导页
 * Created by Francis on 2017-3-15.
 */

public class GuideActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private ArrayList<View> pageViews;
    private ViewGroup main, group;
    private ImageView imageView;
    private ImageView[] imageViews;

    private TextView tv_Start;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        pageViews.add(inflater.inflate(R.layout.guide_page1, null));
        pageViews.add(inflater.inflate(R.layout.guide_page2, null));
        pageViews.add(inflater.inflate(R.layout.guide_page3, null));

        pageViews.get(2).findViewById(R.id.tv_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,LoginActivity.class));
            }
        });

        imageViews = new ImageView[pageViews.size()];

        main = (ViewGroup) inflater.inflate(R.layout.activity_guide, null);

        // group是R.layou.main中的负责包裹小圆点的LinearLayout.
        group = (ViewGroup) main.findViewById(R.id.viewGroup);

        viewPager = (ViewPager) main.findViewById(R.id.guidePages);

        for (int i = 0; i < pageViews.size(); i++) {
            imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            imageView.setPadding(20, 0, 20, 0);
            imageViews[i] = imageView;
            if (i == 0) {
                // 默认选中第一张图片
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.page_indicator);
            }
            group.addView(imageViews[i]);
        }

        setContentView(main);

        viewPager.setAdapter(new GuidePageAdapter(pageViews));
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());

    }

    /** 指引页面改监听器 */
    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            if (arg0 <= imageViews.length - 1) {
                for (int i = 0; i < imageViews.length; i++) {
                    imageViews[arg0].setBackgroundResource(R.drawable.page_indicator_focused);
                    if (arg0 != i) {
                        imageViews[i].setBackgroundResource(R.drawable.page_indicator);
                    }
                }
            }
            if (arg0 == imageViews.length - 1) {
            }
        }
    }
}
