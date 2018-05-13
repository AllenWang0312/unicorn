package edu.tjrac.swant.douban.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.douban.doubane.DoubanUSBox;
import edu.tjrac.swant.douban.doubane.MovieData;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.zhihu.zhihu.SubjectsBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 上午 9:58
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class DoubanMoviesAdapter extends BaseMultiItemQuickAdapter<MovieData, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DoubanMoviesAdapter(List<MovieData> data) {
        super(data);
        //默认样式
        addItemType(0, R.layout.item_douban_movie);
        //title
        addItemType(1, R.layout.item_douban_movie_title);
        //排行榜
        addItemType(2, R.layout.item_douban_movie_board);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieData movie) {
        SubjectsBean item = movie.getMovieData();
        if (item.getType() == 1) {
            helper.setText(R.id.title, item.getTitle());
        } else {
            Glide.with(mContext).load(item.getImages().getMedium()).into((ImageView) helper.getView(R.id.image));
            helper.setText(R.id.title, item.getTitle());
//            TextView original = helper.getView(R.id.original_title);
//            if (item.getTitle().equals(item.getOriginal_title())) {
//                original.setVisibility(View.GONE);
//            } else {
//                original.setVisibility(View.VISIBLE);
//                helper.setText(R.id.original_title, item.getOriginal_title());
//            }

//            helper.setText(R.id.collect_count,  + item.getCollect_count() + "条评论");
//            helper.setText(R.id.subtype, item.getSubtype());
//            helper.setText(R.id.year, "["+item.getYear()+"]");
            helper.setText(R.id.genres, "类型:" + item.getGenres().toString());
            if (item.getType() == 2) {
                DoubanUSBox.SubjectBean sub = (DoubanUSBox.SubjectBean) movie;
                helper.setText(R.id.tv_order, sub.getRank() + "");
            }
        }
    }
}
