package edu.tjrac.swant.netimage.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.netimage.bean.MMjpgHotList;
import edu.tjrac.swant.netimage.adapter.MMjpgTagAblumListAdapter;

/**
 * Created by wpc on 2018/2/11 0011.
 */

public class MMjpgMainFragment extends Fragment {

    @BindView(R.id.sp) Spinner mSp;
    @BindView(R.id.rv) RecyclerView mRv;
@BindView(R.id.swiper) SwipeRefreshLayout swiper;

    MMjpgTagAblumListAdapter adapter;
    ArrayList<MMjpgHotList.DataBean> ablums = new ArrayList<>();
    Unbinder unbinder;

    String home = "http://www.mmjpg.com/";

    String[] values;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mmjpg_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        values = getActivity().getResources().getStringArray(R.array.mmjpg_tags_values);
        mRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                initAblums();
            }
        });
        adapter = new MMjpgTagAblumListAdapter(ablums);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                initAblums();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mRv.setAdapter(adapter);

        mSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              page=1;
                path = values[mSp.getSelectedItemPosition()];
                initAblums();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

    String path = "";
    int page = 1;

    private void initAblums() {
        if (page == 1) {
            ablums.clear();
        }
        String url = home + page;
//        App.getUnicornApi().getMMjpgAblumList(path, page).enqueue(new Callback<MMjpgHotList>() {
//            @Override
//            public void onResponse(Call<MMjpgHotList> call, Response<MMjpgHotList> response) {
//
//                MMjpgHotList result = response.body();
//                MMjpgHotList.InfoBean info = result.getInfo();
//
//                if (result.getCode() == 200) {
//                    ablums.addAll(response.body().getData());
//                    adapter.notifyDataSetChanged();
//                    if (info.getPage().equals(info.getMaxPage())) {
//                        adapter.loadMoreEnd();
//                    } else {
//                        adapter.loadMoreComplete();
//                    }
//                } else {
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MMjpgHotList> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
