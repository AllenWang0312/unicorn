package edu.tjrac.swant.gank.view

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yckj.baselib.common.adapter.FragmentsPagerAdapter
import com.yckj.baselib.common.base.BaseFragment

import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.fragment_gank_home.*

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/20 0020 上午 11:24
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

class GankHomeFragment : BaseFragment() {

    private var adapter: FragmentsPagerAdapter? = null
    private val tags = arrayOf("all", "Android", "iOS", "休息视频", "拓展资源", "前端", "瞎推荐", "App")
    private var histroy: GankHistroyFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_gank_home, container, false)

        adapter = FragmentsPagerAdapter(childFragmentManager)
        for (tag in tags) {

            adapter!!.addFragment(GankFragment(tag), tag)

        }
        histroy = GankHistroyFragment()
        adapter!!.addFragment(histroy, "历史")
        vp.setAdapter(adapter)
        tab.setupWithViewPager(vp)
        return v
    }

    override fun onBack() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
