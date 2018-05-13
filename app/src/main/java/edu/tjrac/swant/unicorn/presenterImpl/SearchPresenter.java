package edu.tjrac.swant.unicorn.presenterImpl;

import com.yckj.baselib.common.base.RxPresenter;

import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.douban.doubane.DoubanBookSearchResult;
import edu.tjrac.swant.douban.doubane.DoubanMovieSearchResultBean;
import edu.tjrac.swant.qqmusic.qqmucsic.QQMusicSearchResultBean;
import edu.tjrac.swant.unicorn.contract.SearchActivityContract;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 下午 4:45
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class SearchPresenter extends RxPresenter implements SearchActivityContract.Presenter {

    private SearchActivityContract.View mView;

    public SearchPresenter(SearchActivityContract.View view) {
        mView = view;
    }

    @Override
    public void doSearchMovie(String tag, String search) {
        Subscription subscription = Net.getInstance().getDouBanService()
                .searchMovie(search, tag)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanMovieSearchResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DoubanMovieSearchResultBean doubanMovieSearchResultBean) {
                        mView.onSearchMovieResult(doubanMovieSearchResultBean);
                    }
                });

        addSubscribe(subscription);
    }

    @Override
    public void doSearchBook(String tag, String search, int start, int count) {
        Subscription subscription = Net.getInstance().getDouBanService()
                .searchBook(search, tag, start, count)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanBookSearchResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DoubanBookSearchResult doubanBookSearchResult) {
                        mView.onSearchBookResult(doubanBookSearchResult);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void doSearchMusic(String search, int page, int count) {
        Subscription subscription = Net.getInstance().getQQmusicApi()
                .searchMusic(0, count, 1, 1, 0, "json"
                        , "GB231", "utf-8", 0, "jqminiframe.json"
                        , 0, page, 0, "sizer.newclient.next_song"
                        , search).unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QQMusicSearchResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(QQMusicSearchResultBean qqMusicSearchResultBean) {
                        mView.onSearchMusicResult(qqMusicSearchResultBean);
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void getQQMusicKey(String qqid) {
        Subscription subscription = Net.getInstance()
                .getQQmusicKeyApi()
                .getQQmusicKey(3, qqid, "jsonp", "GB2312"
                        , "GB2312", 0, "yqq", 0)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        mView.onGetQQMusicKeySuccess(s);
                    }
                });
        addSubscribe(subscription);
    }
}
