/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.handmark.pulltorefresh.extras.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ichongliang.hpull.library.PullToRefreshBase;
import com.shine.niceapp.R;
import com.shine.niceapp.ui.adapter.FuckPagerAdapter;

public class PullToRefreshViewViewPager extends PullToRefreshBase<LinearLayout> {

    public PullToRefreshViewViewPager(Context context) {
        super(context);
    }

    public PullToRefreshViewViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private ViewPager mViewPager;

    @Override
    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.HORIZONTAL;
    }

    @Override
    protected LinearLayout createRefreshableView(Context context, AttributeSet attrs) {
       LinearLayout view =  (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_view_pager, null);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new FuckPagerAdapter());
        return view;
    }

    @Override
    protected boolean isReadyForPullStart() {

        PagerAdapter adapter = mViewPager.getAdapter();
        if (null != adapter) {
            return mViewPager.getCurrentItem() == 0;
        }

        return false;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        PagerAdapter adapter = mViewPager.getAdapter();
        if (null != adapter) {
            return mViewPager.getCurrentItem() == adapter.getCount() - 1;
        }

        return false;
    }
}
