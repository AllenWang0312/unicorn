package edu.tjrac.swant.zhihu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.zhihu.adapter.ZhihuDailyRecycAdapter;
import edu.tjrac.swant.zhihu.zhihu.StoriesBean;
import edu.tjrac.swant.zhihu.zhihu.ThemeDetialBean;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ThemeDetialActivity extends AppCompatActivity {

    int id;
    int page = 1;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler) RecyclerView mRecycler;
    @BindView(R.id.swiper) SwipeRefreshLayout mSwiper;

    ThemeDetialBean bean;
    ArrayList<StoriesBean> stories = new ArrayList<>();

    ZhihuDailyRecycAdapter adapter;

    ImageView imageHead;
    TextView subs;
    LinearLayout ll_zb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_detial);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 0);
        adapter = new ZhihuDailyRecycAdapter(stories);
        mRecycler.setLayoutManager(new LinearLayoutManager(ThemeDetialActivity.this));

        View head = LayoutInflater.from(ThemeDetialActivity.this)
                .inflate(R.layout.head_theme_detial, null);
        imageHead = head.findViewById(R.id.iv_head);
        subs = head.findViewById(R.id.tv_subs);
        ll_zb = head.findViewById(R.id.ll_zb);


        adapter.addHeaderView(head);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                requestData(id,page);
            }
        });

    }

    public static void start(Context context, int id) {
        Intent i = new Intent(context, ThemeDetialActivity.class);
        i.putExtra("id", id);
        context.startActivity(i);
    }


     void requestData(int id,int page){
         Net.getInstance().getZhihuService().requestThemeData(id, page)
                 .unsubscribeOn(Schedulers.io())
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Subscriber<ThemeDetialBean>() {
                     @Override
                     public void onCompleted() {

                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onNext(ThemeDetialBean b) {
                         stories.addAll(b.getStories());
                         adapter.notifyDataSetChanged();
                         if (bean == null) {
                             Glide.with(ThemeDetialActivity.this).load(b.getImage()).into(imageHead);
                             subs.setText(b.getDescription());
                             for (ThemeDetialBean.EditorsBean item : b.getEditors()) {
                                 ImageView imageView = new ImageView(ThemeDetialActivity.this);
                                 imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                                 Glide.with(ThemeDetialActivity.this).load(item.getAvatar()).into(imageView);
                                 ll_zb.addView(imageView);
                             }
                         }
                         bean = b;
                     }
                 });
     }
}
