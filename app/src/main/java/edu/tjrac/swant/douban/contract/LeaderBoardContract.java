package edu.tjrac.swant.douban.contract;

import com.yckj.baselib.common.base.BasePresenter;
import com.yckj.baselib.common.base.BaseView;

import edu.tjrac.swant.douban.doubane.DoubanTop250;
import edu.tjrac.swant.douban.doubane.DoubanUSBox;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 下午 1:13
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface LeaderBoardContract {

    public interface Presenter extends BasePresenter{
        void getTop250Data(int start,int count);
        void getUSBoxData();

    }
    public interface View extends BaseView{
        void onGetTop250Success(DoubanTop250 top);
        void onGetUSBoxSuccess(DoubanUSBox box);

    }
}
