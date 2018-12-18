package edu.tjrac.swant.gank.view

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yckj.baselib.common.base.BaseMVPFragment
import com.yckj.baselib.common.base.BaseWebViewActivity
import edu.tjrac.swant.gank.Gank.HistroyDateBean
import edu.tjrac.swant.gank.Gank.HistroyDayBean
import edu.tjrac.swant.gank.adapter.HistroyAdapter
import edu.tjrac.swant.gank.contract.GankHistroyContact
import edu.tjrac.swant.gank.presenterImpl.GankHistoryPresenter
import edu.tjrac.swant.unicorn.ApiUtils
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.refreshable_recycler.*
import java.util.*

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 上午 11:35
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

class GankHistroyFragment : BaseMVPFragment<GankHistoryPresenter>(), GankHistroyContact.View {


    internal var index = 0
    internal var dates: List<String> = ArrayList()

    internal var results = ArrayList<HistroyDayBean.ResultsBean>()

    internal var adapter: HistroyAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.refreshable_recycler, container, false)
        presenter = GankHistoryPresenter(this)
        adapter = HistroyAdapter(results)
        adapter!!.setEnableLoadMore(true)
        adapter!!.setOnLoadMoreListener {
            index++
            presenter.getHistoryDataBean(dates[index])
        }
        adapter!!.setOnItemClickListener { adapter, view, position ->
            if (results[position].itemType == 0) {
                BaseWebViewActivity.startWithContent(activity, results[position].title, results[position].content)
            }
        }
        recycler.setLayoutManager(LinearLayoutManager(activity))
        recycler.setAdapter(adapter)

        swiper.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            index = 0
            presenter.getHistoryDataBean(dates[index])
        })
        presenter.getHistoryDateList()
        return v
    }

    override fun onBack() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onGetHistoryDateListSuccess(bean: HistroyDateBean) {
        dates = bean.results
        Log.i("HistoryDateListSuccess", bean.results.toString())
        results.add(HistroyDayBean.ResultsBean(dates[index], 1))
        presenter.getHistoryDataBean(ApiUtils.formatGankDate(dates[index]))
    }

    override fun onGetHistoryData(bean: HistroyDayBean) {
        results.addAll(bean.results)
        adapter!!.notifyDataSetChanged()
        if (swiper.isRefreshing()) {
            swiper.setRefreshing(false)
        }
    }
}
