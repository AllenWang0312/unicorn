package edu.tjrac.swant.douyin.module.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yckj.baselib.common.adapter.FragmentsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.douyin.widget.DouYinTabLayout;
import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/3/2 0002.
 */

public class MineFragment extends Fragment {

    @BindView(R.id.tl) DouYinTabLayout mTl;
    @BindView(R.id.vp) ViewPager mVp;
    FragmentsPagerAdapter adapter;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.douyin_fragment_mine, container, false);

        unbinder = ButterKnife.bind(this, view);
        adapter=new FragmentsPagerAdapter(getFragmentManager());
        adapter.addFragment(new VideoGridListFragment(),"作品");
        adapter.addFragment(new VideoGridListFragment(),"喜欢");
        adapter.addFragment(new VideoGridListFragment(),"故事");
        mVp.setAdapter(adapter);
        mTl.setUpWithViewPager(mVp);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
