package edu.tjrac.swant.zhihu.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.zhihu.zhihu.DailyReportBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/16 0016 下午 4:20
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class DailyReportRecycAdapter extends BaseQuickAdapter<DailyReportBean.OthersBean, BaseViewHolder> {
    public DailyReportRecycAdapter(@Nullable List<DailyReportBean.OthersBean> data) {
        super(R.layout.item_daily_report_themes, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyReportBean.OthersBean item) {
        helper.setText(R.id.title, item.getName());
        helper.setText(R.id.desc, item.getDescription());
        Glide.with(mContext).load(item.getThumbnail()).into((ImageView) helper.getView(R.id.icon));

    }
}
