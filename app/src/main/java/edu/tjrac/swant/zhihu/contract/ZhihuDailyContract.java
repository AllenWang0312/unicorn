package edu.tjrac.swant.zhihu.contract;

import com.yckj.baselib.common.base.BasePresenter;
import com.yckj.baselib.common.base.BaseView;

import edu.tjrac.swant.zhihu.zhihu.BeforeDataBean;
import edu.tjrac.swant.zhihu.zhihu.ZhihuDailyBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/16 0016 上午 9:01
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface ZhihuDailyContract {

    interface View extends BaseView {
        void getDailySuccess(ZhihuDailyBean data);
        void getBeforeDataSuccess(BeforeDataBean data);
    }

    interface Presenter  extends BasePresenter{
        void loadDailyData();
        void loadBeforeData(String date);
    }

}
