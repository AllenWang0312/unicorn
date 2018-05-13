package edu.tjrac.swant.unicorn.contract;

import com.yckj.baselib.common.base.BasePresenter;
import com.yckj.baselib.common.base.BaseView;

import edu.tjrac.swant.douban.doubane.DoubanBookSearchResult;
import edu.tjrac.swant.douban.doubane.DoubanMovieSearchResultBean;
import edu.tjrac.swant.qqmusic.qqmucsic.QQMusicSearchResultBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 下午 4:41
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface SearchActivityContract  {
    public interface Presenter extends BasePresenter{
        void doSearchMovie(String tag,String search);
        void doSearchBook(String tag,String search,int start,int count);
        void doSearchMusic(String search,int page,int count);
        void getQQMusicKey(String qqid);

    }

    public interface View extends BaseView{
        void onSearchBookResult(DoubanBookSearchResult result);
        void onSearchMovieResult(DoubanMovieSearchResultBean result);
        void onSearchMusicResult(QQMusicSearchResultBean result);

        void onGetQQMusicKeySuccess(String keyString);
    }
}
