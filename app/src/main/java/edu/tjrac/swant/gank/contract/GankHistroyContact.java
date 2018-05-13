package edu.tjrac.swant.gank.contract;

import com.yckj.baselib.common.base.BasePresenter;
import com.yckj.baselib.common.base.BaseView;

import edu.tjrac.swant.gank.Gank.HistroyDateBean;
import edu.tjrac.swant.gank.Gank.HistroyDayBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 下午 12:16
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface GankHistroyContact {
    interface Presenter extends BasePresenter{
        void getHistoryDateList();
        void getHistoryDataBean(String date);
    }
    interface View extends BaseView{
        void onGetHistoryDateListSuccess(HistroyDateBean bean);
        void onGetHistoryData(HistroyDayBean bean);
    }
}
