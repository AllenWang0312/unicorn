package edu.tjrac.swant.netimage.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.netimage.bean.MzituZhuanTiAblum;

/**
 * Created by wpc on 2018/2/11 0011.
 */

public class MzituZhuanTiAdapter extends BaseQuickAdapter<MzituZhuanTiAblum.DataBean,BaseViewHolder> {
    public MzituZhuanTiAdapter(@Nullable List<MzituZhuanTiAblum.DataBean> data) {
        super(R.layout.item_mzitu_zhuanti_ablum,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MzituZhuanTiAblum.DataBean item) {
        helper.setText(R.id.tv_name,item.getTitle());
        Glide.with(mContext).load(item.getCover()).into((ImageView) helper.getView(R.id.iv_cover));
    }
}
