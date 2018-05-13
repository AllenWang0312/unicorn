package edu.tjrac.swant.douyin.module.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yckj.baselib.common.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/21 0021 下午 1:42
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.ll_live_title) LinearLayout mLlLiveTitle;
    @BindView(R.id.iv_camera) ImageView mIvCamera;
    @BindView(R.id.cb_open_hide_title) CheckBox mCbOpenHideTitle;
    @BindView(R.id.tv_title_left) TextView mTvTitleLeft;
    @BindView(R.id.iv_split) ImageView mIvSplit;
    @BindView(R.id.tv_title_right) TextView mTvTitleRight;
    @BindView(R.id.iv_search) ImageView mIvSearch;
    @BindView(R.id.recycler) RecyclerView mRecycler;
    @BindView(R.id.swiper) SwipeRefreshLayout mSwiper;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_douyin_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onBack() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
