package edu.tjrac.swant.gank.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chad.library.adapter.base.BaseQuickAdapter
import com.yckj.baselib.common.base.BaseMVPFragment
import com.yckj.baselib.common.base.BaseWebViewFragment
import com.yckj.baselib.common.base.FragmentActivity

import java.util.ArrayList

import edu.tjrac.swant.gank.Gank.CategoryListBean
import edu.tjrac.swant.gank.adapter.GankAdapter
import edu.tjrac.swant.gank.contract.GankContract
import edu.tjrac.swant.gank.presenterImpl.GankPresenter
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.refreshable_recycler.*

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 上午 11:09
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

@SuppressLint("ValidFragment")
class GankFragment(internal var tag: String) : BaseMVPFragment<GankPresenter>(), GankContract.View {
    internal var results = ArrayList<CategoryListBean.ResultsBean>()

    private var adapter: GankAdapter? = null

    internal var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.refreshable_recycler, container, false)
        presenter = GankPresenter(this)

        adapter = GankAdapter(results)

        recycler.setLayoutManager(LinearLayoutManager(activity))

        adapter!!.setEnableLoadMore(true)
        adapter!!.setOnLoadMoreListener {
            page++
            presenter.getCategoryList(tag, 10, page)
        }
        adapter!!.setOnItemClickListener { adapter, view, position ->
            //                BaseWebViewActivity.start(getActivity(), results.get(position).getDesc(), results.get(position).getUrl());
            FragmentActivity.start(activity, BaseWebViewFragment(results[position].url))
        }

        recycler.setAdapter(adapter)
        presenter.getCategoryList(tag, 10, page)

        swiper.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            page = 1
            presenter.getCategoryList(tag, 10, page)
        })
        return v
    }

    override fun onBack() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onGetCategorySuccess(bean: CategoryListBean) {
        if (page == 1) {
            results.clear()
        }
        if (bean.count > 0) {
            results.addAll(bean.results)
            adapter!!.loadMoreComplete()
            adapter!!.notifyDataSetChanged()
        } else {
            adapter!!.loadMoreEnd()
        }
        if (bean.isError) {
            adapter!!.loadMoreFail()
        }

        if (swiper.isRefreshing()) {
            swiper.setRefreshing(false)
        }
    }
}
