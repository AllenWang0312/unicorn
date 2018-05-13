package edu.tjrac.swant.gank.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yckj.baselib.common.adapter.FragmentsPagerAdapter;
import com.yckj.baselib.common.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 上午 11:24
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class GankHomeFragment extends BaseFragment {

    @BindView(R.id.tab) TabLayout mTab;
    @BindView(R.id.vp) ViewPager mVp;
    Unbinder unbinder;

    private FragmentsPagerAdapter adapter;
    private String[] tags = {"all", "Android", "iOS", "休息视频",  "拓展资源", "前端", "瞎推荐", "App"};
    private GankHistroyFragment histroy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gank_home, container, false);
        unbinder = ButterKnife.bind(this, v);

        adapter = new FragmentsPagerAdapter(getChildFragmentManager());
        for (String tag : tags) {

            adapter.addFragment(new GankFragment(tag), tag);

        }
        histroy = new GankHistroyFragment();
        adapter.addFragment(histroy, "历史");
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);
        return v;
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
