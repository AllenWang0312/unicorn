package edu.tjrac.swant.zhihu.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yckj.baselib.common.base.BaseMVPFragment;
import com.yckj.baselib.util.TimeUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.zhihu.activity.DailyReportActivity;
import edu.tjrac.swant.zhihu.activity.StroyDetialActivity;
import edu.tjrac.swant.zhihu.adapter.ZhihuDailyRecycAdapter;
import edu.tjrac.swant.zhihu.contract.ZhihuDailyContract;
import edu.tjrac.swant.zhihu.presenterimpl.ZhihuDailyPresenter;
import edu.tjrac.swant.zhihu.zhihu.BeforeDataBean;
import edu.tjrac.swant.zhihu.zhihu.StoriesBean;
import edu.tjrac.swant.zhihu.zhihu.ZhihuDailyBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/15 0015 下午 5:22
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class ZhihuDailyFragment extends BaseMVPFragment<ZhihuDailyPresenter> implements ZhihuDailyContract.View {


    @BindView(R.id.swiper) SwipeRefreshLayout swiper;
    @BindView(R.id.recycler) RecyclerView recycler;
    ZhihuDailyRecycAdapter adapter;
    ArrayList<StoriesBean> stories;
    Unbinder unbinder;


    @Override
    public void onResume() {
        super.onResume();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new ZhihuDailyPresenter(this);
        stories = new ArrayList<>();

        View v = inflater.inflate(R.layout.refreshable_recycler, container, false);
        unbinder = ButterKnife.bind(this, v);

        adapter = new ZhihuDailyRecycAdapter(stories);
        recycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StroyDetialActivity.start(getActivity(), stories.get(position));
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        presenter.loadDailyData();
        presenter.loadBeforeData( TimeUtils.getTimeWithFormat("yyyyMMdd"));

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                stories.clear();
                presenter.loadDailyData();
            }
        });

        setHasOptionsMenu(true);
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
    public void getDailySuccess(ZhihuDailyBean data) {
        stories.add(new StoriesBean(1,"热门.."));
        stories.addAll(data.getTop_stories());
        stories.add(new StoriesBean(1,"今日.."));
        stories.addAll(data.getStories());

        adapter.notifyDataSetChanged();
    }

    @Override
    public void getBeforeDataSuccess(BeforeDataBean data) {
        stories.add(new StoriesBean(1,"before stories"));
        stories.addAll(data.getStories());

        adapter.notifyDataSetChanged();
    }

    @Override
    public void dismissProgress() {
        super.dismissProgress();
        if(swiper.isRefreshing()){
            swiper.setRefreshing(false);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.zhihu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.option_themes:
                DailyReportActivity.start(getActivity());
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
