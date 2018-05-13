package edu.tjrac.swant.douban.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yckj.baselib.common.base.BaseMVPActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.douban.adapter.DoubanMoviesAdapter;
import edu.tjrac.swant.douban.doubane.DoubanTop250;
import edu.tjrac.swant.douban.doubane.DoubanUSBox;
import edu.tjrac.swant.douban.doubane.MovieData;
import edu.tjrac.swant.douban.contract.LeaderBoardContract;
import edu.tjrac.swant.douban.presenterImpl.LeaderBoardPresenter;

public class LeaderboardActivity extends BaseMVPActivity<LeaderBoardPresenter> implements LeaderBoardContract.View {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler) RecyclerView mRecycler;
    @BindView(R.id.swiper) SwipeRefreshLayout mSwiper;

    int pageSize = 25;
    ArrayList<MovieData> movies = new ArrayList<>();

    DoubanMoviesAdapter adapter;
    String tag;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        ButterKnife.bind(this);

        presenter = new LeaderBoardPresenter(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        tag = getIntent().getStringExtra("tag");

        adapter = new DoubanMoviesAdapter(movies);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DoubanMovieDetialActivity.start(mContext, movies.get(position).getMovieData());
            }
        });
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));

        if (tag.equals("top250")) {
            setTitle("top250");
            adapter.setEnableLoadMore(true);
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    presenter.getTop250Data(movies.size(), pageSize);
                }
            });
        } else {
            setTitle("美榜");
        }

        mRecycler.setAdapter(adapter);
        if (tag.equals("top250")) {
            presenter.getTop250Data(movies.size(), pageSize);
        } else {
            presenter.getUSBoxData();
        }

        mSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (tag.equals("top250")) {
                    movies.clear();
                    presenter.getTop250Data(movies.size(), pageSize);
                } else {
                    movies.clear();
                    presenter.getUSBoxData();
                }

            }
        });
    }

    public static void start(Context context, String tag) {
        Intent i = new Intent(context, LeaderboardActivity.class);
        i.putExtra("tag", tag);
        context.startActivity(i);
    }

    @Override
    public void onGetTop250Success(DoubanTop250 top) {
        if (top.getCount() > 0) {
            movies.addAll(top.getSubjects());
            adapter.loadMoreComplete();
            adapter.notifyDataSetChanged();
        } else {
            adapter.loadMoreEnd();
        }

    }

    @Override
    public void onGetUSBoxSuccess(DoubanUSBox box) {
        movies.addAll(box.getSubjects());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
