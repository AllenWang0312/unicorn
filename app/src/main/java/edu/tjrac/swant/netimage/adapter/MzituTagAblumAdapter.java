package edu.tjrac.swant.netimage.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.netimage.bean.MzituTagAblum;

/**
 * Created by wpc on 2018/2/11 0011.
 */

public class MzituTagAblumAdapter extends BaseQuickAdapter<MzituTagAblum.DataBean,BaseViewHolder> {
    public MzituTagAblumAdapter( @Nullable List<MzituTagAblum.DataBean> data) {
        super(R.layout.item_ablum_tag_mzitu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MzituTagAblum.DataBean item) {
//        ImageView imageView=new ImageView(mContext);
//        imageView.setImageURI(new URi);
        Glide.with(mContext).load(item.getCover()).into((ImageView) helper.getView(R.id.iv_cover));
        helper.setText(R.id.tv_name,item.getTitle());
    }
}
