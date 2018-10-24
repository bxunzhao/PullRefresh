package com.shine.niceapp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.extras.viewpager.PullToRefreshViewViewPager;
import com.ichongliang.hpull.library.PullToRefreshBase;
import com.shine.niceapp.R;
import com.shine.niceapp.ui.adapter.FuckPagerAdapter;

public class PagerActivity extends Activity {

    private PullToRefreshViewViewPager refreshViewViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        ViewPager viewPager = findViewById(R.id.viewpager);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewPager.getLayoutParams();
        params.leftMargin = 100;
        params.rightMargin = 100;
        viewPager.setLayoutParams(params);

        viewPager.setClipChildren(false);
        viewPager.setPageMargin(20);
        viewPager.setAdapter(new FuckPagerAdapter());

        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        refreshViewViewPager = findViewById(R.id.refreshPager);
        refreshViewViewPager.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<LinearLayout>() {
            @Override
            public void onRefresh(PullToRefreshBase<LinearLayout> refreshView) {

            }
        });
    }


    static class ZoomOutPageTransformer implements ViewPager.PageTransformer {

        private float MIN_SCALE = 0.95f;
        private float MIN_ALPHA = 0.9f;

        @Override
        public void transformPage(@NonNull View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                view.setAlpha(MIN_ALPHA);
                view.setScaleX(MIN_SCALE);
                view.setScaleY(MIN_SCALE);
            } else if (position <= 1)
            //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor);
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;

                view.setPadding(0, 0, 0, 0);
                view.setScaleY(scaleFactor);
                view.setScaleX(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(MIN_ALPHA);
                view.setScaleX(MIN_SCALE);
                view.setScaleY(MIN_SCALE);
            }
            view.invalidate();
        }
    }
}
