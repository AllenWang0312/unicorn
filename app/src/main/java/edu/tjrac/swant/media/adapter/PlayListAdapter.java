package edu.tjrac.swant.media.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.qqmusic.lrchelper.MusicInfo;
import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/2/6 0006.
 */

public class PlayListAdapter extends BaseQuickAdapter<MusicInfo, BaseViewHolder> {

    public PlayListAdapter(@Nullable List<MusicInfo> data) {
        super(R.layout.item_play_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MusicInfo item) {
        helper.setText(R.id.tv_name, item.getName());
    }
}
