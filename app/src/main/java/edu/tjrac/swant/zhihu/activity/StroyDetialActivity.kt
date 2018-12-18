package edu.tjrac.swant.zhihu.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import android.webkit.WebView

import com.yckj.baselib.common.base.BaseActivity
import com.yckj.baselib.util.T

import java.util.ArrayList

import edu.tjrac.swant.unicorn.Net
import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.zhihu.adapter.CommentsRecycAdapter
import edu.tjrac.swant.zhihu.zhihu.Comments
import edu.tjrac.swant.zhihu.zhihu.StoriesBean
import edu.tjrac.swant.zhihu.zhihu.StroyDetialBean
import kotlinx.android.synthetic.main.activity_stroy_detial.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class StroyDetialActivity : BaseActivity() {

    internal var id: Int = 0
    internal var title_image: String=""
    internal var comments = ArrayList<Comments.CommentsBean>()
    internal var adapter: CommentsRecycAdapter? = null


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getIntExtra("id", 0)
        title_image = intent.getStringExtra("title_image")

        setContentView(R.layout.activity_stroy_detial)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra("title")

        webview.getSettings().setJavaScriptEnabled(true)
        webview.getSettings().setBuiltInZoomControls(true)
        webview.getSettings().setDisplayZoomControls(false)
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY) //取消滚动条白边效果
        //                        webview.setWebChromeClient(webChromeClient);
        //                        webview.setWebViewClient(webViewClient);
        webview.getSettings().setDefaultTextEncodingName("UTF-8")
        webview.getSettings().setBlockNetworkImage(false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW)  //注意安卓5.0以上的权限
        }

        //        if (!StringUtils.isEmpty(title_image)) {
        //            Glide.with(StroyDetialActivity.this).load(title_image).into(mImageView);
        //        }

        Net.instance
                .zhihuService
                .requestStroyDetial(id)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<StroyDetialBean>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {

                        T.show(mContext, e.message)
                    }

                    override fun onNext(stroyDetialBean: StroyDetialBean) {
                        title = stroyDetialBean.title
                        //                        webview.loadDataWithBaseURL(stroyDetialBean.getShare_url(), stroyDetialBean.getBody(), "text/html", "UTF-8", null);
                        webview.loadData(stroyDetialBean.body, "text/html", "utf-8")
                    }
                })
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

    companion object {

        fun start(context: Context, bean: StoriesBean) {
            val i = Intent(context, StroyDetialActivity::class.java)
            i.putExtra("title", bean.title)
            i.putExtra("id", bean.id)
            i.putExtra("title_image", if (bean.images == null) "" else if (bean.images.size > 0) bean.images[0] else "")
            context.startActivity(i)
        }
    }
}
