package edu.tjrac.swant.netimage.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.netimage.bean.MzituZhuanTiAblum;
import edu.tjrac.swant.netimage.adapter.MzituZhuanTiAdapter;

/**
 * Created by wpc on 2018/2/11 0011.
 */

public class MzituZhuanTiFragment extends Fragment {


    ArrayList<MzituZhuanTiAblum.DataBean> zhuantilist = new ArrayList<>();
    @BindView(R.id.rv) RecyclerView mRv;
    MzituZhuanTiAdapter adapter;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mzitu_zhuangti, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter = new MzituZhuanTiAdapter(zhuantilist);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String path = zhuantilist.get(position).getPath();
               String[] chars= path.split("/");
//                T.show(getActivity(),chars[4]);
                MzituTagAblumsActivity.start(getActivity(),chars[4]);
            }
        });
        mRv.setAdapter(adapter);
        initPages();
        return view;
    }

    private void initPages() {

//        App.getUnicornApi().getMzituZhuanTi().enqueue(new Callback<MzituZhuanTiAblum>() {
//            @Override
//            public void onResponse(Call<MzituZhuanTiAblum> call, Response<MzituZhuanTiAblum> response) {
//                zhuantilist.addAll(response.body().getData());
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<MzituZhuanTiAblum> call, Throwable t) {
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
