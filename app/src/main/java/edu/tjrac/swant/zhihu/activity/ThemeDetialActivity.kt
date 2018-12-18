package edu.tjrac.swant.zhihu.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import edu.tjrac.swant.unicorn.Net
import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.zhihu.adapter.ZhihuDailyRecycAdapter
import edu.tjrac.swant.zhihu.zhihu.StoriesBean
import edu.tjrac.swant.zhihu.zhihu.ThemeDetialBean
import kotlinx.android.synthetic.main.refreshable_recycler.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class ThemeDetialActivity : AppCompatActivity() {

    internal var id: Int = 0
    internal var page = 1
    internal var bean: ThemeDetialBean? = null
    internal var stories = ArrayList<StoriesBean>()
    internal var adapter: ZhihuDailyRecycAdapter?=null
    internal var imageHead: ImageView?=null
    internal var subs: TextView?=null
    internal var ll_zb: LinearLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_detial)
        id = intent.getIntExtra("id", 0)
        adapter = ZhihuDailyRecycAdapter(stories)
        recycler.setLayoutManager(LinearLayoutManager(this@ThemeDetialActivity))

        val head = LayoutInflater.from(this@ThemeDetialActivity)
                .inflate(R.layout.head_theme_detial, null)
        imageHead = head.findViewById<ImageView>(R.id.iv_head)
        subs = head.findViewById<TextView>(R.id.tv_subs)
        ll_zb = head.findViewById<LinearLayout>(R.id.ll_zb)


        adapter!!.addHeaderView(head)
        adapter!!.setEnableLoadMore(true)
        adapter!!.setOnLoadMoreListener {
            page++
            requestData(id, page)
        }

    }


    internal fun requestData(id: Int, page: Int) {
        Net.instance.zhihuService.requestThemeData(id, page)
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ThemeDetialBean>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onNext(b: ThemeDetialBean) {
                        stories.addAll(b.stories)
                        adapter!!.notifyDataSetChanged()
                        if (bean == null) {
                            Glide.with(this@ThemeDetialActivity).load(b.image).into(imageHead!!)
                            subs!!.text = b.description
                            for (item in b.editors) {
                                val imageView = ImageView(this@ThemeDetialActivity)
                                imageView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
                                Glide.with(this@ThemeDetialActivity).load(item.avatar).into(imageView)
                                ll_zb!!.addView(imageView)
                            }
                        }
                        bean = b
                    }
                })
    }

    companion object {

        fun start(context: Context, id: Int) {
            val i = Intent(context, ThemeDetialActivity::class.java)
            i.putExtra("id", id)
            context.startActivity(i)
        }
    }
}
