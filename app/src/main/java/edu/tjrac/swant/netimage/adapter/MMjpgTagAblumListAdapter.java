package edu.tjrac.swant.netimage.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.netimage.bean.MMjpgHotList;

/**
 * Created by wpc on 2018/2/11 0011.
 */

public class MMjpgTagAblumListAdapter extends BaseQuickAdapter<MMjpgHotList.DataBean, BaseViewHolder> {
    public MMjpgTagAblumListAdapter(@Nullable ArrayList<MMjpgHotList.DataBean> data) {
        super(R.layout.image_ablum, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MMjpgHotList.DataBean item) {
        Glide.with(mContext).load(item.getCover()).into((ImageView) helper.getView(R.id.iv_cover));
        helper.setText(R.id.tv_name,item.getTitle());
    }
}
