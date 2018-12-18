package edu.tjrac.swant.zhihu.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View

import com.chad.library.adapter.base.BaseQuickAdapter

import java.util.ArrayList

import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.unicorn.Net
import edu.tjrac.swant.zhihu.adapter.DailyReportRecycAdapter
import edu.tjrac.swant.zhihu.zhihu.DailyReportBean
import kotlinx.android.synthetic.main.activity_daily_report.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DailyReportActivity : AppCompatActivity() {


    internal var themes = ArrayList<DailyReportBean.OthersBean>()
    internal var adapter: DailyReportRecycAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_report)

        adapter = DailyReportRecycAdapter(themes)
        recycler.setLayoutManager(LinearLayoutManager(this@DailyReportActivity))
        recycler.setAdapter(adapter)
        adapter!!.setOnItemChildClickListener { adapter, view, position -> }
        Net.instance.zhihuService.requestDailyReportThemes()
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<DailyReportBean>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onNext(dailyReportBean: DailyReportBean) {
                        themes.addAll(dailyReportBean.others)
                        adapter!!.notifyDataSetChanged()
                    }
                })
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, DailyReportActivity::class.java))
        }
    }
}
