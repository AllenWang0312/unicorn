package edu.tjrac.swant.zhihu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.zhihu.adapter.DailyReportRecycAdapter;
import edu.tjrac.swant.zhihu.zhihu.DailyReportBean;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DailyReportActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler) RecyclerView mRecycler;

    ArrayList<DailyReportBean.OthersBean> themes = new ArrayList<>();
    DailyReportRecycAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_report);
        ButterKnife.bind(this);

        adapter = new DailyReportRecycAdapter(themes);
        mRecycler.setLayoutManager(new LinearLayoutManager(DailyReportActivity.this));
        mRecycler.setAdapter(adapter);
adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }
});
        Net.getInstance().getZhihuService().requestDailyReportThemes()
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DailyReportBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DailyReportBean dailyReportBean) {
                        themes.addAll(dailyReportBean.getOthers());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context,DailyReportActivity.class));
    }
}
