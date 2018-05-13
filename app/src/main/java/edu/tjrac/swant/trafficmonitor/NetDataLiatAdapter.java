package edu.tjrac.swant.trafficmonitor;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/2/6 0006.
 */

public class NetDataLiatAdapter extends BaseQuickAdapter<NetDataWatcherActivity.AppNetDataInfo,BaseViewHolder> {
    public NetDataLiatAdapter(@Nullable List<NetDataWatcherActivity.AppNetDataInfo> data) {
        super(R.layout.item_app_data_info,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NetDataWatcherActivity.AppNetDataInfo item) {

        helper.setImageDrawable(R.id.iv_icon,item.getAppIcon());
        helper.setText(R.id.tv_name,item.getAppName());
        helper.setText(R.id.tv_packageName,item.getPackageName());
        helper.setText(R.id.tv_netdata,item.getRx()+"_"+item.getTx());
    }
}
