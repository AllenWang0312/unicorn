package edu.tjrac.swant.gank.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yckj.baselib.common.base.BaseMVPFragment;
import com.yckj.baselib.common.base.BaseWebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.ApiUtils;
import edu.tjrac.swant.gank.Gank.HistroyDateBean;
import edu.tjrac.swant.gank.Gank.HistroyDayBean;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.gank.adapter.HistroyAdapter;
import edu.tjrac.swant.gank.contract.GankHistroyContact;
import edu.tjrac.swant.gank.presenterImpl.GankHistoryPresenter;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 上午 11:35
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class GankHistroyFragment extends BaseMVPFragment<GankHistoryPresenter> implements GankHistroyContact.View {

    @BindView(R.id.recycler) RecyclerView mRecycler;
    @BindView(R.id.swiper) SwipeRefreshLayout mSwiper;

    int index = 0;
    List<String> dates = new ArrayList<>();

    ArrayList<HistroyDayBean.ResultsBean> results = new ArrayList<>();

    HistroyAdapter adapter;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.refreshable_recycler, container, false);
        unbinder = ButterKnife.bind(this, v);
        presenter = new GankHistoryPresenter(this);
        adapter = new HistroyAdapter(results);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                index++;
                presenter.getHistoryDataBean(dates.get(index));
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(results.get(position).getItemType()==0){
                    BaseWebViewActivity.startWithContent(getActivity(), results.get(position).getTitle(), results.get(position).getContent());
                }
            }
        });
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(adapter);

        mSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                index = 0;
                presenter.getHistoryDataBean(dates.get(index));
            }
        });
        presenter.getHistoryDateList();
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
    public void onGetHistoryDateListSuccess(HistroyDateBean bean) {
        dates = bean.getResults();
        Log.i("HistoryDateListSuccess", bean.getResults().toString());
        results.add(new HistroyDayBean.ResultsBean(dates.get(index), 1));
        presenter.getHistoryDataBean(ApiUtils.formatGankDate(dates.get(index)));
    }

    @Override
    public void onGetHistoryData(HistroyDayBean bean) {
        results.addAll(bean.getResults());
        adapter.notifyDataSetChanged();
        if (mSwiper.isRefreshing()) {
            mSwiper.setRefreshing(false);
        }
    }
}
