package edu.tjrac.swant.gank.presenterImpl;

import android.util.Log;

import com.yckj.baselib.common.base.RxPresenter;

import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.gank.Gank.CategoryListBean;
import edu.tjrac.swant.gank.contract.GankContract;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 上午 11:15
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class GankPresenter extends RxPresenter implements GankContract.Presenter {

    private GankContract.View mView;

    public GankPresenter(GankContract.View view) {
        mView = view;
    }

    @Override
    public void getCategoryList(String tag, int count, int page) {
        Subscription subscription= Net.getInstance().getGankService()
                .getCategoryList(tag, count, page)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryListBean>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CategoryListBean categoryListBean) {
                        Log.i("GankApi:getCategoryList", categoryListBean.getResults().toString());
                        mView.onGetCategorySuccess(categoryListBean);
                    }
                });
        addSubscribe(subscription);
    }
}
