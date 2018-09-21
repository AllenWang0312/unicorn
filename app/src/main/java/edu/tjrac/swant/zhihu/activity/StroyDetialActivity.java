package edu.tjrac.swant.zhihu.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.yckj.baselib.common.base.BaseActivity;
import com.yckj.baselib.util.T;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.zhihu.adapter.CommentsRecycAdapter;
import edu.tjrac.swant.zhihu.zhihu.Comments;
import edu.tjrac.swant.zhihu.zhihu.StoriesBean;
import edu.tjrac.swant.zhihu.zhihu.StroyDetialBean;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StroyDetialActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.webview) WebView mWebview;


//    @BindView(R.id.iv_title) ImageView mImageView;
//    @BindView(R.id.tv_like) TextView like;
//    @BindView(R.id.tv_comment) TextView comment;


//    @BindView(R.id.iv_close)ImageView ivClose;

//    @BindView(R.id.tv_comment_title) TextView mTvCommentTitle;
//    @BindView(R.id.rv_comments) RecyclerView mRvComments;
//    @BindView(R.id.et_comment) EditText mEtComment;
//    @BindView(R.id.cv_comments) CardView mCvComments;

    int id;
    String title_image;

    ArrayList<Comments.CommentsBean> comments = new ArrayList<>();
    CommentsRecycAdapter adapter;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra("id", 0);
        title_image = getIntent().getStringExtra("title_image");

        setContentView(R.layout.activity_stroy_detial);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setTitle(getIntent().getStringExtra("title"));

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.getSettings().setDisplayZoomControls(false);
        mWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //取消滚动条白边效果
//                        mWebview.setWebChromeClient(webChromeClient);
//                        mWebview.setWebViewClient(webViewClient);
        mWebview.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebview.getSettings().setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebview.getSettings().setMixedContentMode(mWebview.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);  //注意安卓5.0以上的权限
        }

//        if (!StringUtils.isEmpty(title_image)) {
//            Glide.with(StroyDetialActivity.this).load(title_image).into(mImageView);
//        }

        Net.Companion.getInstance()
                .getZhihuService()
                .requestStroyDetial(id)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StroyDetialBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        T.show(mContext,e.getMessage());
                    }

                    @Override
                    public void onNext(StroyDetialBean stroyDetialBean) {
                        setTitle(stroyDetialBean.getTitle());
//                        mWebview.loadDataWithBaseURL(stroyDetialBean.getShare_url(), stroyDetialBean.getBody(), "text/html", "UTF-8", null);
                        mWebview.loadData(stroyDetialBean.getBody(), "text/html", "utf-8");
                    }
                });
//        ivClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCvComments.animate().translationY(0).setDuration(1000).start();
//            }
//        });
//
//        adapter = new CommentsRecycAdapter(comments);
//        mRvComments.setLayoutManager(new LinearLayoutManager(StroyDetialActivity.this));
//
//        mRvComments.setAdapter(adapter);
//
//        comment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCvComments.animate().translationY(-(UiUtils.Dp2Px(StroyDetialActivity.this,300))).setDuration(1000).start();
//                Net.getInstance().getZhihuService().requestStoryComments(id)
//                        .unsubscribeOn(Schedulers.io())
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<Comments>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(Comments com) {
//                                comments.addAll(com.getComments());
//                                adapter.notifyDataSetChanged();
//                            }
//                        });
//                Net.getInstance().getZhihuService().requestStoryShortComments(id)
//                        .unsubscribeOn(Schedulers.io())
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<Comments>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(Comments com) {
//                                comments.addAll(com.getComments());
//                                adapter.notifyDataSetChanged();
//                            }
//                        });
//            }
//        });
    }

    public static void start(Context context, StoriesBean bean) {
        Intent i = new Intent(context, StroyDetialActivity.class);
        i.putExtra("title", bean.getTitle());
        i.putExtra("id", bean.getId());
        i.putExtra("title_image", bean.getImages() == null ? "" : bean.getImages().size() > 0 ? bean.getImages().get(0) : "");
        context.startActivity(i);
    }
}
