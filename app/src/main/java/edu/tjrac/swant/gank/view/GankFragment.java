package edu.tjrac.swant.gank.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yckj.baselib.common.base.BaseMVPFragment;
import com.yckj.baselib.common.base.BaseWebViewFragment;
import com.yckj.baselib.common.base.FragmentActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.gank.Gank.CategoryListBean;
import edu.tjrac.swant.gank.adapter.GankAdapter;
import edu.tjrac.swant.gank.contract.GankContract;
import edu.tjrac.swant.gank.presenterImpl.GankPresenter;
import edu.tjrac.swant.unicorn.R;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 上午 11:09
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

@SuppressLint("ValidFragment")
public class GankFragment extends BaseMVPFragment<GankPresenter> implements GankContract.View {


    String tag;
    @BindView(R.id.recycler) RecyclerView mRecycler;
    @BindView(R.id.swiper) SwipeRefreshLayout mSwiper;
    ArrayList<CategoryListBean.ResultsBean> results = new ArrayList<>();

    private GankAdapter adapter;
    Unbinder unbinder;

    public GankFragment(String tag) {
        super();
        this.tag = tag;
    }

    int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.refreshable_recycler, container, false);
        unbinder = ButterKnife.bind(this, v);
        presenter = new GankPresenter(this);

        adapter = new GankAdapter(results);

        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                presenter.getCategoryList(tag, 10, page);
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                BaseWebViewActivity.start(getActivity(), results.get(position).getDesc(), results.get(position).getUrl());
                FragmentActivity.start(getActivity(),new BaseWebViewFragment(results.get(position).getUrl()));
            }
        });
        mRecycler.setAdapter(adapter);
        presenter.getCategoryList(tag, 10, page);

        mSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getCategoryList(tag, 10, page);
            }
        });
        return v;
    }

    @Override
    public void onBack() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onGetCategorySuccess(CategoryListBean bean) {
        if (page == 1) {
            results.clear();
        }
        if (bean.getCount() > 0) {
            results.addAll(bean.getResults());
            adapter.loadMoreComplete();
            adapter.notifyDataSetChanged();
        } else {
            adapter.loadMoreEnd();
        }
        if (bean.isError()) {
            adapter.loadMoreFail();
        }

        if (mSwiper.isRefreshing()) {
            mSwiper.setRefreshing(false);
        }
    }
}
