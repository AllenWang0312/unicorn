package edu.tjrac.swant.douyin.module.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.yckj.baselib.common.adapter.FragmentsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.unicorn.SharedStartActivity;

public class DouYinActivity extends SharedStartActivity {

    @BindView(R.id.vp) ViewPager mContent;

    FragmentsPagerAdapter adapter;

    CameraFragment camera;
    MainFragment main;
    UserInfoFragment userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douyin_main);
        ButterKnife.bind(this);
        adapter = new FragmentsPagerAdapter(getSupportFragmentManager());
        camera = new CameraFragment();
        main = new MainFragment();
        userinfo = new UserInfoFragment();

        adapter.addFragment(camera, "camera");
        adapter.addFragment(main, "main");
        adapter.addFragment(userinfo, "userinfo");

        mContent.setAdapter(adapter);
        mContent.setCurrentItem(1);
        mContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {

                } else if (position == 1) {

                } else if (position == 2) {

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
