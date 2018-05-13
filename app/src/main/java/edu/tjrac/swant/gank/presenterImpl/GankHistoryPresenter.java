package edu.tjrac.swant.gank.presenterImpl;

import android.util.Log;

import com.yckj.baselib.common.base.RxPresenter;

import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.gank.Gank.HistroyDateBean;
import edu.tjrac.swant.gank.Gank.HistroyDayBean;
import edu.tjrac.swant.gank.contract.GankHistroyContact;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 下午 12:26
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class GankHistoryPresenter extends RxPresenter implements GankHistroyContact.Presenter {

    private GankHistroyContact.View mView;

    public GankHistoryPresenter(GankHistroyContact.View view) {
        mView = view;
    }

    @Override
    public void getHistoryDateList() {
        Subscription subscription = Net.getInstance().getGankService()
                .getHistoryDateBean()
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HistroyDateBean>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        mView.showProgress();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HistroyDateBean histroyDateBean) {
                        Log.i("history date",histroyDateBean.getResults().toString());
                        mView.onGetHistoryDateListSuccess(histroyDateBean);
                        mView.dismissProgress();
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getHistoryDataBean(String date) {
        Subscription subscription = Net.getInstance().getGankService()
                .getHistroyDayBean(date)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HistroyDayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HistroyDayBean histroyDayBean) {
                        Log.i("histroyDayBean",histroyDayBean.getResults().toString());
                        mView.onGetHistoryData(histroyDayBean);
                    }
                });
        addSubscribe(subscription);
    }
}
