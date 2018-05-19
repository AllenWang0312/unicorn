package edu.tjrac.swant.todo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yckj.baselib.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.todo.adapter.WebWorkGroupAdapter;
import edu.tjrac.swant.todo.bean.WebInfo;
import edu.tjrac.swant.unicorn.R;

public class WebWorkSpaceActivity extends BaseActivity {

    @BindView(R.id.recycler) RecyclerView mRecycler;
//    @BindView(R.id.fl_note) FrameLayout mFlNote;


    List<WebWorkGroupAdapter.WebWorkGroupInfo> groups = new ArrayList<>();
    WebWorkGroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_work_space);
        ButterKnife.bind(this);
        WebWorkGroupAdapter.WebWorkGroupInfo webs = getIntent().getParcelableExtra("groups");
        if (webs != null) {
            groups.add(webs);
        }
        mRecycler.setLayoutManager(new GridLayoutManager(WebWorkSpaceActivity.this, 3));

        adapter = new WebWorkGroupAdapter(groups);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GroupWebViewerActivity.start(mContext,groups.get(position).getInfos());
                startActivity(new Intent(mContext,PicInPicMarkDownActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT| Intent.FLAG_ACTIVITY_NEW_TASK)
                );
            }
        });
        mRecycler.setAdapter(adapter);

    }


    public static void debugStart(Context context) {
        ArrayList<WebInfo> infos = new ArrayList<>();
        infos.add(new WebInfo("https://www.baidu.com", "百度"));
        infos.add(new WebInfo("https://www.google.com", "谷歌"));
        infos.add(new WebInfo("https://www.baidu.com", "百度"));
        infos.add(new WebInfo("https://www.google.com", "谷歌"));
        infos.add(new WebInfo("https://www.baidu.com", "百度"));
        infos.add(new WebInfo("https://www.google.com", "谷歌"));
        WebWorkGroupAdapter.WebWorkGroupInfo group = new WebWorkGroupAdapter.WebWorkGroupInfo(
                "搜索器", infos
        );
        context.startActivity(new Intent(context, WebWorkSpaceActivity.class)
                .putExtra("groups", group)
        );
    }
}
