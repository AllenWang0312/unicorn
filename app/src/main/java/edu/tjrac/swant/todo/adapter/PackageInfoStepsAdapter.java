package edu.tjrac.swant.todo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.qqmusic.PackageSearchResult;
import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/4/27.
 */

public class PackageInfoStepsAdapter extends BaseQuickAdapter<PackageSearchResult.ResultBeanXX.DataBean.ListBeanX, BaseViewHolder> {
    public PackageInfoStepsAdapter(@Nullable List<PackageSearchResult.ResultBeanXX.DataBean.ListBeanX> data) {
        super(R.layout.item_package_info_steps, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PackageSearchResult.ResultBeanXX.DataBean.ListBeanX item) {
        helper.setText(R.id.status, item.getStatus()).setText(R.id.time, item.getTime());
    }
}
