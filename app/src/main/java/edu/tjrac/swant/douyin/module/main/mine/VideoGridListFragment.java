package edu.tjrac.swant.douyin.module.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/3/2 0002.
 */

public class VideoGridListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.recycler,container,false);
        return view;
    }
}
