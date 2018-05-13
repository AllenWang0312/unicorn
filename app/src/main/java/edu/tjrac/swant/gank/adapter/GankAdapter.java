package edu.tjrac.swant.gank.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.gank.Gank.CategoryListBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 上午 11:36
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class GankAdapter extends BaseQuickAdapter<CategoryListBean.ResultsBean, BaseViewHolder> {
    public GankAdapter(@Nullable List<CategoryListBean.ResultsBean> data) {
        super(R.layout.item_gank_category, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryListBean.ResultsBean item) {
        helper.setText(R.id.tv_des, item.getDesc());
        helper.setText(R.id.tv_time, item.getWho() + "发布于" + item.getPublishedAt());
    }
}
