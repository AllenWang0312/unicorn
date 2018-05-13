package edu.tjrac.swant.douban.presenterImpl;

import com.yckj.baselib.common.base.RxPresenter;

import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.douban.doubane.DoubanTop250;
import edu.tjrac.swant.douban.doubane.DoubanUSBox;
import edu.tjrac.swant.douban.contract.LeaderBoardContract;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 下午 1:15
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class LeaderBoardPresenter extends RxPresenter implements LeaderBoardContract.Presenter {
    private LeaderBoardContract.View mView;

    public LeaderBoardPresenter(LeaderBoardContract.View view) {
        mView = view;
    }

    @Override
    public void getTop250Data(int start,int count) {
        Subscription subscription = Net.getInstance().getDouBanService()
                .getTop250(start,count)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanTop250>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DoubanTop250 doubanTop250) {
                        mView.onGetTop250Success(doubanTop250);

                    }
                });
        addSubscribe(subscription);

    }

    @Override
    public void getUSBoxData() {
        Subscription subscription = Net.getInstance().getDouBanService()
                .getUSTop()
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanUSBox>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DoubanUSBox doubanUSBox) {
                        mView.onGetUSBoxSuccess(doubanUSBox);
                    }
                });
        addSubscribe(subscription);
    }
}
