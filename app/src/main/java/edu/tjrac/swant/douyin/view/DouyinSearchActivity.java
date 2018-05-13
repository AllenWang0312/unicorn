package edu.tjrac.swant.douyin.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yckj.baselib.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;

public class DouyinSearchActivity extends BaseActivity {

    @BindView(R.id.iv_close) ImageView mIvClose;
    @BindView(R.id.tv_search) TextView mTvSearch;

    @BindView(R.id.recycler) RecyclerView mRecycler;
    @BindView(R.id.swiper) SwipeRefreshLayout mSwiper;

    View headview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douyin_search);
        ButterKnife.bind(this);
        headview = LayoutInflater.from(mContext).inflate(R.layout.douyin_search_head, null);


    }

}
