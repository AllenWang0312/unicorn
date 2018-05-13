package edu.tjrac.swant.gank.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.gank.Gank.HistroyDayBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 下午 12:20
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class HistroyAdapter extends BaseMultiItemQuickAdapter<HistroyDayBean.ResultsBean, BaseViewHolder> {
    public HistroyAdapter(@Nullable List<HistroyDayBean.ResultsBean> data) {
        super(data);
        addItemType(0, R.layout.item_histroy);
        addItemType(1, R.layout.item_title_histroy);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistroyDayBean.ResultsBean item) {
        if (item.getItemType() == 0) {
            helper.setText(R.id.title, item.getTitle());
            helper.setText(R.id.tv_date, item.getPublishedAt());
        } else {
            helper.setText(R.id.title, item.getTitle());
        }

    }
}
