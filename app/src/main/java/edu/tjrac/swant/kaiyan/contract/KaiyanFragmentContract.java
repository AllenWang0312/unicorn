package edu.tjrac.swant.kaiyan.contract;

import edu.tjrac.swant.kaiyan.kaiyan.KaiYanHomeBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/22 0022 下午 1:34
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface KaiyanFragmentContract  {
    public interface Presenter{
       void getKaiyanHomeData();
    }
    public interface View{
        void onGetHomeDataSuccess(KaiYanHomeBean bean);
    }
}
