package edu.tjrac.swant.zhihu.view

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.*
import com.yckj.baselib.common.base.BaseMVPFragment
import com.yckj.baselib.util.TimeUtils
import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.zhihu.activity.DailyReportActivity
import edu.tjrac.swant.zhihu.activity.StroyDetialActivity
import edu.tjrac.swant.zhihu.adapter.ZhihuDailyRecycAdapter
import edu.tjrac.swant.zhihu.contract.ZhihuDailyContract
import edu.tjrac.swant.zhihu.presenterimpl.ZhihuDailyPresenter
import edu.tjrac.swant.zhihu.zhihu.BeforeDataBean
import edu.tjrac.swant.zhihu.zhihu.StoriesBean
import edu.tjrac.swant.zhihu.zhihu.ZhihuDailyBean
import kotlinx.android.synthetic.main.refreshable_recycler.*
import java.util.*

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/15 0015 下午 5:22
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

class ZhihuDailyFragment : BaseMVPFragment<ZhihuDailyPresenter>(), ZhihuDailyContract.View {
    internal var adapter: ZhihuDailyRecycAdapter?=null
    internal var stories: ArrayList<StoriesBean>?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = ZhihuDailyPresenter(this)
        stories = ArrayList<StoriesBean>()

        val v = inflater.inflate(R.layout.refreshable_recycler, container, false)

        adapter = ZhihuDailyRecycAdapter(stories)
        recycler.setLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))
        recycler.setAdapter(adapter)
        adapter!!.setOnItemClickListener { adapter, view, position -> StroyDetialActivity.start(activity!!, stories!![position]) }
        adapter!!.setOnItemChildClickListener { adapter, view, position -> }

        presenter.loadDailyData()
        presenter.loadBeforeData(TimeUtils.getTimeWithFormat("yyyyMMdd"))

        swiper.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            stories!!.clear()
            presenter.loadDailyData()
        })

        setHasOptionsMenu(true)
        return v
    }

    override fun onBack() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun getDailySuccess(data: ZhihuDailyBean) {
        stories!!.add(StoriesBean(1, "热门.."))
        stories!!.addAll(data.top_stories)
        stories!!.add(StoriesBean(1, "今日.."))
        stories!!.addAll(data.stories)

        adapter!!.notifyDataSetChanged()
    }

    override fun getBeforeDataSuccess(data: BeforeDataBean) {
        stories!!.add(StoriesBean(1, "before stories"))
        stories!!.addAll(data.stories)
        adapter!!.notifyDataSetChanged()
    }

    override fun dismissProgress() {
        super.dismissProgress()
        if (swiper.isRefreshing()) {
            swiper.setRefreshing(false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.zhihu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {

            R.id.option_themes -> DailyReportActivity.start(activity!!)
        }
        return super.onOptionsItemSelected(item)
    }
}
