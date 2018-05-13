package edu.tjrac.swant.kaiyan.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yckj.baselib.common.base.BaseMVPFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.kaiyan.kaiyan.KaiYanHomeBean;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.kaiyan.adapter.KaiyanHomeRecycAdapter;
import edu.tjrac.swant.kaiyan.contract.KaiyanFragmentContract;
import edu.tjrac.swant.kaiyan.presenterImpl.KaiyanFragmentPresenter;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/22 0022 下午 1:34
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class KaiyanFragment extends BaseMVPFragment<KaiyanFragmentPresenter> implements KaiyanFragmentContract.View {

    @BindView(R.id.recycler) RecyclerView mRecycler;
    @BindView(R.id.swiper) SwipeRefreshLayout mSwiper;
    Unbinder unbinder;
    ArrayList<KaiYanHomeBean.ItemListBean> results = new ArrayList<>();
    KaiyanHomeRecycAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.refreshable_recycler, container, false);
        unbinder = ButterKnife.bind(this, v);
        presenter = new KaiyanFragmentPresenter(this);

        adapter = new KaiyanHomeRecycAdapter(results);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(adapter);

        presenter.getKaiyanHomeData();

        return v;
    }

    @Override
    public void onBack() {

    }

    @Override
    public void onGetHomeDataSuccess(KaiYanHomeBean bean) {

        results.addAll(bean.getItemList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
