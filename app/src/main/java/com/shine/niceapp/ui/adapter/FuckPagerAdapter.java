package com.shine.niceapp.ui.adapter;


import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shine.niceapp.R;

import java.util.ArrayList;
import java.util.List;

public class FuckPagerAdapter extends PagerAdapter {

    public FuckPagerAdapter() {
        holders = new ArrayList<>();
    }

    List<CardBannerHolder> holders;

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if(holders.size() > position) {
            CardBannerHolder holder = holders.get(position);
            if(holder != null) {
                container.addView(holder.getView(), position);
                return holder.getView();
            }
        }
        CardBannerHolder holder = new CardBannerHolder(container);
        container.addView(holder.getView(), position);
        return holder.getView();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView(holders.get(position).getView());
    }

    static class CardBannerHolder {
        View view;

        public CardBannerHolder(ViewGroup group) {
            view = LayoutInflater.from(group.getContext()).inflate(R.layout.fragment_card, group, false);
            view.setLayoutParams(new ViewGroup.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        public View getView() {
            return view;
        }
    }
}
