package com.example.guest.popularmovies.ui.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.guest.popularmovies.mvp.model.reviews.Review;
import com.example.guest.popularmovies.ui.ReviewFragment;

import java.util.List;

/**
 * Created by l1maginaire on 4/7/18.
 */

public class ReviewsPagerAdapter extends FragmentStatePagerAdapter {
    private List<Review> reviewsList;

    public ReviewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<Review> reviewsList2) {
        this.reviewsList = reviewsList2;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putString("DATA", reviewsList.get(position).getContent());
        ReviewFragment fragment = new ReviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return (reviewsList == null || reviewsList.size() < 1) ? 0 : reviewsList.size();
    }
}