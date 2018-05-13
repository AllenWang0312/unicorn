package edu.tjrac.swant.gank.contract;

import com.yckj.baselib.common.base.BasePresenter;
import com.yckj.baselib.common.base.BaseView;

import edu.tjrac.swant.gank.Gank.CategoryListBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 上午 11:10
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface GankContract {

    public interface Presenter extends BasePresenter{
        void getCategoryList(String tag, int count,int page);
    }

    public interface View extends BaseView{
        void onGetCategorySuccess(CategoryListBean bean);
    }
}
