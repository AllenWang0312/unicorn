package edu.tjrac.swant.bluetooth.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yckj.baselib.util.TimeUtils;

import java.util.List;

import edu.tjrac.swant.bluetooth.bean.ScanInfo;
import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/2/2 0002.
 */

public class ScanInfoHistoryAdapter extends BaseQuickAdapter<ScanInfo, BaseViewHolder> {
    public ScanInfoHistoryAdapter(@Nullable List<ScanInfo> data) {
        super(R.layout.item_scan_info_history, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScanInfo item) {
        helper.setText(R.id.time, TimeUtils.getTimeWithFormat(item.getTagTime(), "hh:MM:ss.mm"));
        helper.setText(R.id.div_time, String.valueOf(item.getTagTime() - item.getLastTagTime()) + "ms");
        helper.setText(R.id.rssi, item.getDbm() + "dBm");
    }
}
