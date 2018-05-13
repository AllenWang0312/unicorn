package edu.tjrac.swant.todo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yckj.baselib.common.base.BaseActivity;
import com.yckj.baselib.util.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.qqmusic.PackageSearchResult;
import edu.tjrac.swant.todo.adapter.PackageInfoStepsAdapter;
import edu.tjrac.swant.unicorn.R;

public class PackageResultActivity extends BaseActivity {


    PackageSearchResult result;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler) RecyclerView mRecycler;
    @BindView(R.id.fab) FloatingActionButton mFab;


    ArrayList<PackageSearchResult.ResultBeanXX.DataBean.ListBeanX> steps = new ArrayList<>();
    PackageInfoStepsAdapter adapter;


    TextView status, exp_name, exp_phone, exp_site, number, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_result);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("快递详情");
        String bean = getIntent().getStringExtra("json");
        if (!StringUtils.isEmpty(bean)) {
            result = new Gson().fromJson(bean, PackageSearchResult.class);
        }
        Log.i("json", bean);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View head = LayoutInflater.from(mContext).inflate(R.layout.package_info, null);
        status = head.findViewById(R.id.status);
        exp_name = head.findViewById(R.id.exp_name);
        exp_phone = head.findViewById(R.id.exp_phone);
        exp_site = head.findViewById(R.id.exp_site);
        number = head.findViewById(R.id.number);
        type = head.findViewById(R.id.type);

        if (result != null) {
            if (result.getResult().getData() != null) {
                if (result.getResult().getData().getList() != null)
                    steps.addAll(result.getResult().getData().getList());
                initHead(result.getResult().getData());
            }
        }

        adapter = new PackageInfoStepsAdapter(steps);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));

        adapter.addHeaderView(head);
        mRecycler.setAdapter(adapter);

    }

    private void initHead(PackageSearchResult.ResultBeanXX.DataBean data) {
        String s="";
        switch (data.getDeliverystatus()){
            case  "1":
                s="在途中";
                break;
            case  "2":
                s="正在派件";
                break;
            case  "3":
                s="已签收";
                break;
            case  "4":
                s="派送失败";
                break;
        }
        status.setText(s);
        exp_name.setText(data.getExpName());
        ;
        exp_phone.setText(data.getExpPhone());
        exp_site.setText(data.getExpSite());

        number.setText(data.getNumber());
        type.setText(data.getType());
    }

    public static void start(Context context, String s) {
        context.startActivity(new Intent(context, PackageResultActivity.class)
                .putExtra("json", s));
    }
}
