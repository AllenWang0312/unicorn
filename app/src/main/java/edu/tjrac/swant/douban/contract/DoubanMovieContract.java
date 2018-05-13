package edu.tjrac.swant.douban.contract;

import com.yckj.baselib.common.base.BasePresenter;
import com.yckj.baselib.common.base.BaseView;

import edu.tjrac.swant.douban.doubane.DoubanCommingMovieBean;
import edu.tjrac.swant.douban.doubane.DoubanIsShowingBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 上午 9:42
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface DoubanMovieContract {

    public interface Prensenter extends BasePresenter {
        void getIsShowingMovies();

        void getCommingMovies();

//        void getTop250Movies();

//        void getMBMovies();

    }

    public interface View extends BaseView {
        void onGetIsShowingSuccess(DoubanIsShowingBean bean);

        void onGetCommingSuccess(DoubanCommingMovieBean bean);
    }
}
