package edu.tjrac.swant.zhihu.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yckj.baselib.util.TimeUtils;

import java.util.List;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.zhihu.zhihu.Comments;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/16 0016 下午 3:07
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class CommentsRecycAdapter extends BaseQuickAdapter<Comments.CommentsBean, BaseViewHolder> {

    public CommentsRecycAdapter(@Nullable List<Comments.CommentsBean> data) {
        super(R.layout.item_comments, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comments.CommentsBean item) {
        helper.setText(R.id.name, item.getAuthor());
        Glide.with(mContext).load(item.getAvatar()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.content, item.getContent());
        helper.setText(R.id.time, TimeUtils.getTimeWithFormat((long) item.getTime(), "MM月dd日"));
        helper.setText(R.id.tv_like, item.getLikes()+"");
        LinearLayout ll = helper.getView(R.id.ll_rep);
        if (item.getReply_to() != null) {
            ll.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_rep_content, item.getReply_to().getContent());
            helper.setText(R.id.tv_rep_to, "@"+item.getReply_to().getAuthor());
        } else {
            ll.setVisibility(View.GONE);
        }
    }
}
