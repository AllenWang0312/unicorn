package edu.tjrac.swant.douban.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yckj.baselib.common.base.BaseMVPFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.douban.adapter.DoubanMoviesAdapter;
import edu.tjrac.swant.douban.contract.DoubanMovieContract;
import edu.tjrac.swant.douban.doubane.DoubanCommingMovieBean;
import edu.tjrac.swant.douban.doubane.DoubanIsShowingBean;
import edu.tjrac.swant.douban.doubane.MovieData;
import edu.tjrac.swant.douban.presenterImpl.DoubanMoviePresenter;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.unicorn.view.SearchActivity;
import edu.tjrac.swant.zhihu.zhihu.SubjectsBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 上午 9:42
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class DoubanMovieFragment extends BaseMVPFragment<DoubanMoviePresenter> implements DoubanMovieContract.View {

    @BindView(R.id.recycler) RecyclerView mRecycler;
    @BindView(R.id.recycler2) RecyclerView mRecycler2;
    @BindView(R.id.recycler3) RecyclerView mRecycler3;
    @BindView(R.id.recycler4) RecyclerView mRecycler4;

    @BindView(R.id.cl) CoordinatorLayout cl;
    @BindView(R.id.nsv) NestedScrollView nsv;

//    @BindView(R.id.swiper) SwipeRefreshLayout mSwiper;

    DoubanMoviesAdapter adapter,adapter2,adapter3,adapter4;

    ArrayList<MovieData> isShowing = new ArrayList<>();
    ArrayList<MovieData> comming = new ArrayList<>();
    ArrayList<MovieData> top250 = new ArrayList<>();
    ArrayList<MovieData> mb = new ArrayList<>();
@BindView(R.id.iv_head) ImageView head;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_douban_movie, container, false);

        unbinder = ButterKnife.bind(this, v);

        adapter = new DoubanMoviesAdapter(isShowing);
        adapter2=new DoubanMoviesAdapter(comming);
        adapter3 = new DoubanMoviesAdapter(top250);
        adapter4=new DoubanMoviesAdapter(mb);

        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mRecycler2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mRecycler3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mRecycler4.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        mRecycler.setAdapter(adapter);
        mRecycler2.setAdapter(adapter2);
        mRecycler3.setAdapter(adapter3);
        mRecycler4.setAdapter(adapter4);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                DoubanMovieDetialActivity.start(getActivity(), (SubjectsBean) movies.get(position));
                ImageView imageView = view.findViewById(R.id.image);
                DoubanMovieDetialActivity.startWithSharedView(getActivity(), imageView, (SubjectsBean) isShowing.get(position));
            }
        });

        presenter = new DoubanMoviePresenter(this);

        presenter.getIsShowingMovies();
        presenter.getCommingMovies();

//        mSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                isShowing.clear();
//                comming.clear();
//                presenter.getIsShowingMovies();
//                presenter.getCommingMovies();
//            }
//        });

        setHasOptionsMenu(true);
        cl.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {

            }

            @Override
            public void onChildViewRemoved(View parent, View child) {

            }
        });
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CoordinglayoutTestActivity.start(getActivity());
            }
        });
        return v;
    }

    @Override
    public void onBack() {

    }

    @Override
    public void onGetIsShowingSuccess(DoubanIsShowingBean bean) {
        isShowing.addAll(bean.getSubjects());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showProgress() {
        super.showProgress();
//        mSwiper.setRefreshing(true);
    }

    @Override
    public void dismissProgress() {
        super.dismissProgress();
//        if (mSwiper.isRefreshing()) {
//            mSwiper.setRefreshing(false);
//        }
    }

    @Override
    public void onGetCommingSuccess(DoubanCommingMovieBean bean) {
        comming.addAll(bean.getSubjects());
        adapter2.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.douban_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.seach:
                SearchActivity.start(getActivity(),2);
                break;
//            case R.id.top250:
//                LeaderboardActivity.start(getActivity(), "top250");
//                break;
//
//            case R.id.us_box:
//                LeaderboardActivity.start(getActivity(), "us_box");
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
