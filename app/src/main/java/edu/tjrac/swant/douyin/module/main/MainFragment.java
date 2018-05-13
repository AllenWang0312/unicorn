package edu.tjrac.swant.douyin.module.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yckj.baselib.common.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.douyin.module.main.care.CareFragment;
import edu.tjrac.swant.douyin.module.main.home.HomeFragment;
import edu.tjrac.swant.douyin.module.main.mine.MineFragment;
import edu.tjrac.swant.douyin.module.main.notice.NoticeFragment;
import edu.tjrac.swant.unicorn.R;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/21 0021 下午 2:12
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.content) FrameLayout mContent;

    @BindView(R.id.rb_home) RadioButton mRbHome;
    @BindView(R.id.rb_care) RadioButton mRbCare;
    @BindView(R.id.iv_add) ImageView mIvAdd;

    @BindView(R.id.rb_notice) RadioButton mRbNotice;
    @BindView(R.id.rb_mine) RadioButton mRbMine;
    @BindView(R.id.rg) RadioGroup mRg;
    Unbinder unbinder;

    HomeFragment home;
    CareFragment care;
    NoticeFragment notice;
    MineFragment mine;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_douyin_main, container, false);
        unbinder = ButterKnife.bind(this, v);
        selectFragment(0);

        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_home) {
                    selectFragment(0);
                } else if (checkedId == R.id.rb_care) {
                    selectFragment(1);
                } else if (checkedId == R.id.rb_notice) {
                    selectFragment(2);
                } else if (checkedId == R.id.rb_mine) {
                    selectFragment(3);
                }
            }
        });
        return v;
    }

    void selectFragment(int position) {
        if (position == 0) {
            if (home == null) {
                home = new HomeFragment();
            }
            getChildFragmentManager().beginTransaction().replace(R.id.content, home).commit();
        } else if (position == 1) {
            if (care == null) {
                care = new CareFragment();
            }
            getChildFragmentManager().beginTransaction().replace(R.id.content, care).commit();
        } else if (position == 2) {
            if (notice == null) {
                notice = new NoticeFragment();
            }
            getChildFragmentManager().beginTransaction().replace(R.id.content, notice).commit();
        } else if (position == 3) {
            if (mine == null) {
                mine = new MineFragment();
            }
            getChildFragmentManager().beginTransaction().replace(R.id.content, mine).commit();
        }
    }
    @Override
    public void onBack() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
