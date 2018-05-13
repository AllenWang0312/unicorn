package edu.tjrac.swant.unicorn.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.unicorn.ApiUtils;
import edu.tjrac.swant.douban.doubane.DoubanBookSearchResult;
import edu.tjrac.swant.douban.doubane.DoubanMovieSearchResultBean;
import edu.tjrac.swant.qqmusic.qqmucsic.QQMusicSearchResultBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 下午 4:57
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class SearchResultAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public SearchResultAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(0, R.layout.item_douban_book_search_result);
        addItemType(1, R.layout.item_douban_movie_search_result);
        addItemType(2, R.layout.item_qq_music_search_result);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        if (item.getItemType() == 0) {
            DoubanBookSearchResult.BooksBean book = (DoubanBookSearchResult.BooksBean) item;
            Glide.with(mContext).load(book.getImage()).into((ImageView) helper.getView(R.id.image));
            helper.setText(R.id.title, book.getTitle() + "[" + book.getAlt_title() + "]");
            helper.setText(R.id.author, book.getAuthor().toString());
        } else if (item.getItemType() == 1) {
            DoubanMovieSearchResultBean.SubjectsBean movie = (DoubanMovieSearchResultBean.SubjectsBean) item;
            Glide.with(mContext).load(movie.getImages().getMedium()).into((ImageView) helper.getView(R.id.image));
            helper.setText(R.id.title, movie.getTitle());
            helper.setText(R.id.desc, movie.getYear() + movie.getOriginal_title() + movie.getGenres().toString());
        } else if (item.getItemType() == 2) {
            QQMusicSearchResultBean.DataBean.SongBean.ListBean music = (QQMusicSearchResultBean.DataBean.SongBean.ListBean) item;

            String[] info = music.getF().split("\\|");
            Glide.with(mContext).load(ApiUtils.getQQMusicImagePath(info[22])).into((ImageView) helper.getView(R.id.image));
            helper.setText(R.id.title, music.getSongName_hilight());
            helper.setText(R.id.author, music.getSingerName_hilight());
            helper.setText(R.id.ablum, music.getAlbumName_hilight());
        }
    }
}
