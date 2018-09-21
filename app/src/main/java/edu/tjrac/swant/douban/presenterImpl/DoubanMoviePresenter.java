package edu.tjrac.swant.douban.presenterImpl;

import com.yckj.baselib.common.base.RxPresenter;

import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.douban.doubane.DoubanCommingMovieBean;
import edu.tjrac.swant.douban.doubane.DoubanIsShowingBean;
import edu.tjrac.swant.douban.contract.DoubanMovieContract;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 上午 9:46
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class DoubanMoviePresenter extends RxPresenter implements DoubanMovieContract.Prensenter {
    DoubanMovieContract.View mView;

    public DoubanMoviePresenter(DoubanMovieContract.View view) {
        mView = view;
    }

    @Override
    public void getIsShowingMovies() {
        Subscription subscription = Net.Companion.getInstance().getDouBanService()
                .getIsShowingMovies()
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanIsShowingBean>() {

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
                        mView.showToast(e.getMessage());
                        mView.dismissProgress();
                    }

                    @Override
                    public void onNext(DoubanIsShowingBean doubanIsShowingBean) {
                        mView.dismissProgress();
                        mView.onGetIsShowingSuccess(doubanIsShowingBean);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getCommingMovies() {
        Subscription subscription = Net.Companion.getInstance().getDouBanService()
                .getCommingMovies()
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanCommingMovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(DoubanCommingMovieBean bean) {
                        mView.onGetCommingSuccess(bean);
                    }
                });
        addSubscribe(subscription);
    }
}
