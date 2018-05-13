package edu.tjrac.swant.netimage.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/2/11 0011.
 */

public class DetialAdapter extends BaseQuickAdapter<String ,BaseViewHolder> {
    public DetialAdapter( @Nullable List<String> data) {
        super(R.layout.item_image, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).into((ImageView) helper.getView(R.id.iv));
    }
}
