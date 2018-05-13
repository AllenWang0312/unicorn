package edu.tjrac.swant.zhihu.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.zhihu.zhihu.StoriesBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/16 0016 上午 9:56
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class ZhihuDailyRecycAdapter extends BaseMultiItemQuickAdapter<StoriesBean, BaseViewHolder> {

    public ZhihuDailyRecycAdapter(List<StoriesBean> data) {
        super(data);
        addItemType(0, R.layout.item_zhihu_daily);
        addItemType(1, R.layout.item_zhihu_story_title);
    }


    @Override
    protected void convert(BaseViewHolder helper, StoriesBean item) {
        if (item.getType() == 1) {
            helper.setText(R.id.iv_title, item.getTitle());
        } else {
            helper.setText(R.id.title, item.getTitle());
            if (item.getImages() != null && !item.getImages().isEmpty()) {
                Glide.with(mContext).load(item.getImages().get(0)).into(
                        (ImageView) helper.getView(R.id.iv)
                );
            }
        }
    }
}
