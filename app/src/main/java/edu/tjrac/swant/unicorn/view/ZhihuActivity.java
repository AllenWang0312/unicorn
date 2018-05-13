package edu.tjrac.swant.unicorn.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.yckj.baselib.common.adapter.FragmentsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.douban.view.DoubanMovieFragment;
import edu.tjrac.swant.gank.view.GankHomeFragment;
import edu.tjrac.swant.kaiyan.view.KaiyanFragment;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.unicorn.SharedStartActivity;
import edu.tjrac.swant.zhihu.view.ZhihuDailyFragment;

public class ZhihuActivity extends SharedStartActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.bnb) BottomNavigationBar mBnb;
    @BindView(R.id.vp_zhihu_main) ViewPager vp;

    FragmentsPagerAdapter adapter;

    private String[] titles = {"知乎日报", "豆瓣电影", "Gank", "开眼视频"};
    private int[] icons = {R.drawable.ic_public_white_24dp, R.drawable.ic_explore_white_24dp,
            R.drawable.ic_chat_white_24dp, R.drawable.ic_account_box_white_24dp
    };

    ZhihuDailyFragment zhihu;
    DoubanMovieFragment douban;
    GankHomeFragment gank;
    KaiyanFragment kaiyan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_v2);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        adapter = new FragmentsPagerAdapter(getSupportFragmentManager());
        zhihu = new ZhihuDailyFragment();
        douban = new DoubanMovieFragment();
        gank = new GankHomeFragment();
        kaiyan = new KaiyanFragment();

        adapter.addFragment(zhihu, "zhihu");
        adapter.addFragment(douban, "douban");
        adapter.addFragment(gank, "gank");
        adapter.addFragment(kaiyan, "kaiyan");
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(adapter);


        mBnb.setTabSelectedListener(this);
//        BadgeItem badgeItem = new BadgeItem().setBackgroundColor(Color.RED).setText("99");
        mBnb.setMode(BottomNavigationBar.MODE_FIXED);
        mBnb.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBnb.setBarBackgroundColor(R.color.colorPrimary);
        mBnb.addItem(new BottomNavigationItem(icons[0], titles[0]))
                .addItem(new BottomNavigationItem(icons[1], titles[1]))
                .addItem(new BottomNavigationItem(icons[2], titles[2]))
                .addItem(new BottomNavigationItem(icons[3], titles[3])
//                        .setActiveColorResource(R.color.white).setInActiveColorResource(R.color.white)
                )

                .initialise(); //所有的设置需在调用该方法前完成
        mBnb.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        mBnb.setFirstSelectedPosition(0);
    }

    private FragmentManager manager;


    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public static void start(Activity context) {
        context.startActivity(new Intent(context,ZhihuActivity.class));
    }
}
