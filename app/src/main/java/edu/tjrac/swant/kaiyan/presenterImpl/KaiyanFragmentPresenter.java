package edu.tjrac.swant.kaiyan.presenterImpl;

import com.yckj.baselib.common.base.RxPresenter;

import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.kaiyan.kaiyan.KaiYanHomeBean;
import edu.tjrac.swant.kaiyan.contract.KaiyanFragmentContract;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/22 0022 下午 1:36
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class KaiyanFragmentPresenter extends RxPresenter implements KaiyanFragmentContract.Presenter {

    private KaiyanFragmentContract.View mView;

    public KaiyanFragmentPresenter(KaiyanFragmentContract.View view) {
        mView = view;
    }

    @Override
    public void getKaiyanHomeData() {
        Subscription subscription = Net.Companion.getInstance().getKaiyanApi().getHomeBean()
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<KaiYanHomeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(KaiYanHomeBean kaiYanHomeBean) {
                        mView.onGetHomeDataSuccess(kaiYanHomeBean);
                    }
                });
        addSubscribe(subscription);
    }
}
