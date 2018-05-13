package edu.tjrac.swant.netimage.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.netimage.bean.MzituTagAblum;
import edu.tjrac.swant.netimage.adapter.MzituTagAblumAdapter;

public class MzituTagAblumsActivity extends AppCompatActivity {


    @BindView(R.id.swiper) SwipeRefreshLayout swiper;

    @BindView(R.id.rv) RecyclerView mRv;

    MzituTagAblumAdapter adapter;

    ArrayList<MzituTagAblum.DataBean> tags = new ArrayList<>();

    String tag;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tag = getIntent().getStringExtra("tag");

        setContentView(R.layout.activity_mzitu_tag_ablums);
        ButterKnife.bind(this);

        mRv.setLayoutManager(new GridLayoutManager(MzituTagAblumsActivity.this, 2));
        adapter = new MzituTagAblumAdapter(tags);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                initDatas();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String[] chars = tags.get(position).getPath().split("/");

                MzituAblumDetialActivity.start(MzituTagAblumsActivity.this, Integer.valueOf(chars[3]));
            }
        });
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initDatas();
            }
        });
        mRv.setAdapter(adapter);

        initDatas();

    }

    private void initDatas() {
        swiper.setRefreshing(true);
        if (page == 1) {
            tags.clear();
        }
//        App.getUnicornApi().getMzituTag(tag, page).enqueue(new Callback<MzituTagAblum>() {
//            @Override
//            public void onResponse(Call<MzituTagAblum> call, Response<MzituTagAblum> response) {
//                tags.addAll(response.body().getData());
//                adapter.notifyDataSetChanged();
//                adapter.loadMoreComplete();
//                swiper.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(Call<MzituTagAblum> call, Throwable t) {
//                adapter.loadMoreEnd();
//                swiper.setRefreshing(false);
//            }
//        });
    }


    public static void start(Context context, String tag) {
        context.startActivity(new Intent(context, MzituTagAblumsActivity.class).putExtra("tag", tag));
    }
}
