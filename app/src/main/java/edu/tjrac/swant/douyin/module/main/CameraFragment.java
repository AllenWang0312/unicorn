package edu.tjrac.swant.douyin.module.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yckj.baselib.common.base.BaseFragment;

import edu.tjrac.swant.unicorn.R;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/21 0021 下午 2:18
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class CameraFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_douyin_camera,container,false);

        return v;
    }

    @Override
    public void onBack() {

    }
}
