package edu.tjrac.swant.todo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.yckj.baselib.common.adapter.FragmentsPagerAdapter;
import com.yckj.baselib.common.base.BaseActivity;
import com.yckj.baselib.common.base.BaseFragment;
import com.yckj.baselib.common.base.BaseWebViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.todo.adapter.GroupWebViewPageTransation;
import edu.tjrac.swant.todo.bean.WebInfo;
import edu.tjrac.swant.unicorn.R;

public class GroupWebViewerActivity extends BaseActivity {

    @BindView(R.id.viewpager) ViewPager mViewpager;

    FragmentsPagerAdapter adapter;
    List<WebInfo> infos;
BaseFragment current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_web_viewer);
        ButterKnife.bind(this);
        infos = getIntent().getParcelableArrayListExtra("infos");

        adapter = new FragmentsPagerAdapter(getSupportFragmentManager());
        for (WebInfo item : infos) {
            adapter.addFragment(new BaseWebViewFragment(item.getUrl()
            ,R.layout.activity_service_text), item.getTitle());
        }
        mViewpager.setOffscreenPageLimit(infos.size());

        mViewpager.setAdapter(adapter);
        mViewpager.setPageMargin(40);
        mViewpager.setPageTransformer(true, new GroupWebViewPageTransation());
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current= (BaseFragment) adapter.getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    public static void start(Context context, ArrayList<WebInfo> infos) {
        context.startActivity(new Intent(context, GroupWebViewerActivity.class)
                .putExtra("infos", infos)
                .addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT | Intent.FLAG_ACTIVITY_NEW_TASK)
        );
    }

    @Override
    public void onBackPressed() {
        if(current.isBackable()){
            current.onBack();
            return;
        }
        super.onBackPressed();
    }
}
