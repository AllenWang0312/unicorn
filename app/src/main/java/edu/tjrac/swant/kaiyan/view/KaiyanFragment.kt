package edu.tjrac.swant.kaiyan.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yckj.baselib.common.base.BaseMVPFragment
import edu.tjrac.swant.kaiyan.adapter.KaiyanHomeRecycAdapter
import edu.tjrac.swant.kaiyan.contract.KaiyanFragmentContract
import edu.tjrac.swant.kaiyan.kaiyan.KaiYanHomeBean
import edu.tjrac.swant.kaiyan.presenterImpl.KaiyanFragmentPresenter
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.refreshable_recycler.*
import java.util.*

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/22 0022 下午 1:34
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

class KaiyanFragment : BaseMVPFragment<KaiyanFragmentPresenter>(), KaiyanFragmentContract.View {

    internal var results = ArrayList<KaiYanHomeBean.ItemListBean>()
    internal var adapter: KaiyanHomeRecycAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.refreshable_recycler, container, false)
        presenter = KaiyanFragmentPresenter(this)

        adapter = KaiyanHomeRecycAdapter(results)
        recycler.setLayoutManager(LinearLayoutManager(activity))
        recycler.setAdapter(adapter)

        presenter.getKaiyanHomeData()

        return v
    }

    override fun onBack() {

    }

    override fun onGetHomeDataSuccess(bean: KaiYanHomeBean) {

        results.addAll(bean.itemList)
        adapter!!.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
