package edu.tjrac.swant.zhihu.presenterimpl;

import com.yckj.baselib.common.base.RxPresenter;

import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.zhihu.zhihu.BeforeDataBean;
import edu.tjrac.swant.zhihu.zhihu.ZhihuDailyBean;
import edu.tjrac.swant.zhihu.contract.ZhihuDailyContract;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/16 0016 上午 9:19
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class ZhihuDailyPresenter extends RxPresenter implements ZhihuDailyContract.Presenter {

    private ZhihuDailyContract.View mView;

    public ZhihuDailyPresenter(ZhihuDailyContract.View view) {
        mView = view;
    }

    @Override
    public void loadDailyData() {
        Subscription subscription = Net.getInstance()
                .getZhihuService()
                .requestZhihuDaily()
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuDailyBean>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        mView.showProgress();
                    }

                    @Override
                    public void onCompleted() {
                        mView.dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissProgress();
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(ZhihuDailyBean zhihuDailyBean) {
                        mView.getDailySuccess(zhihuDailyBean);
                    }
                });
        addSubscribe(subscription);

    }

    @Override
    public void loadBeforeData(String date) {
        Subscription subscription = Net.getInstance()
                .getZhihuService()
                .requestBeforeData(date)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BeforeDataBean>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        mView.showProgress();
                    }

                    @Override
                    public void onCompleted() {
                        mView.dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissProgress();
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(BeforeDataBean zhihuDailyBean) {
                        mView.getBeforeDataSuccess(zhihuDailyBean);
                    }
                });
        addSubscribe(subscription);
    }


}
