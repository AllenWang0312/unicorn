package edu.tjrac.swant.douban.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.yckj.baselib.common.base.BaseActivity
import com.yckj.baselib.common.base.BaseWebViewActivity
import com.yckj.baselib.util.StringUtils
import edu.tjrac.swant.douban.doubane.DoubanMovieDetialBean
import edu.tjrac.swant.unicorn.Net
import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.zhihu.adapter.CharacterRecycAdapter
import edu.tjrac.swant.zhihu.zhihu.Character
import edu.tjrac.swant.zhihu.zhihu.SubjectsBean
import kotlinx.android.synthetic.main.activity_douban_movie_detial.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class DoubanMovieDetialActivity : BaseActivity() {

    internal var bean: SubjectsBean? = null
    internal var characters = ArrayList<Character>()
    internal var characterAdapter: CharacterRecycAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_douban_movie_detial)

        bean = intent.getParcelableExtra<SubjectsBean>("subjects")
        if (bean != null) {
            Glide.with(mContext).load(bean!!.images).into(iv)
            Glide.with(mContext).load(bean!!.images)
                    //                    .apply(new RequestOptions().transform(new BlurTransformation(14,3) ))
                    //                    .bitmapTransform(new BlurTransformation(this, 14, 3))
                    .into(bg)
            tv_name.setText(bean!!.title)
            disc.setText(bean!!.year + "/" + StringUtils.join(bean!!.genres, "/"))
            if (bean!!.title == bean!!.original_title) {
                original_name.setVisibility(View.GONE)
            } else {
                original_name.setText("原名: " + bean!!.original_title)
            }
            tv_online_time.setText("上映时间: " + bean!!.year)
            if (bean!!.rating != null) {
                rating.setText(bean!!.rating.average.toString() + "")
                ratingbar.setMax(100)
                ratingbar.setProgress(java.lang.Float.floatToIntBits(bean!!.rating.average * 10))

            }

        }
        characterAdapter = CharacterRecycAdapter(characters)
        artists.setLayoutManager(LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, true))
        artists.setAdapter(characterAdapter)
        characterAdapter!!.setOnItemClickListener { adapter, view, position ->
            //                CharacterDetialActivity.start(mContext, characters.get(position)));
            BaseWebViewActivity.start(mContext, characters[position].name, "https:/movie.douban.com/celebrity/" + characters[position].id + "/mobile")
        }
        Net.instance.douBanService.getMovieDetialInfo(bean!!.id)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<DoubanMovieDetialBean>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onNext(bean: DoubanMovieDetialBean) {

                        characters.addAll(bean.directors)
                        characters.addAll(bean.casts)
                        //                        characters.addAll(bean.ge)
                        characterAdapter!!.notifyDataSetChanged()
                        discrabtion.setText(bean.summary)
                        rating_count.setText(bean.ratings_count.toString() + "人")

                        wish_count.setText(bean.wish_count.toString() + "人想看")
                        collect_count.setText(bean.collect_count.toString() + "人看过")
                        //                        Glide.with(mContext).load(bean.getImages().getLarge()).into(iv);
                        all_comments.setText("全部短评" + bean.reviews_count + "个")
                    }
                })

    }

    companion object {


        fun start(context: Context, bean: SubjectsBean) {
            val intent = Intent(context, DoubanMovieDetialActivity::class.java)
            intent.putExtra("subjects", bean)
            context.startActivity(intent)
        }

        fun startWithSharedView(context: Activity, view: View, bean: SubjectsBean) {
            val intent = Intent(context, DoubanMovieDetialActivity::class.java)
            intent.putExtra("subjects", bean)
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, view, "movie_icon")
            context.startActivity(intent, optionsCompat.toBundle())
        }
    }
}
