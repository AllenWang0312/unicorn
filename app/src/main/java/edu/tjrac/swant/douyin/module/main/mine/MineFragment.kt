package edu.tjrac.swant.douyin.module.main.mine

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yckj.baselib.common.adapter.FragmentsPagerAdapter

import edu.tjrac.swant.douyin.widget.DouYinTabLayout
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.douyin_fragment_mine.*

/**
 * Created by wpc on 2018/3/2 0002.
 */

class MineFragment : Fragment() {

    internal var adapter: FragmentsPagerAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.douyin_fragment_mine, container, false)

        adapter = FragmentsPagerAdapter(fragmentManager)
        adapter!!.addFragment(VideoGridListFragment(), "作品")
        adapter!!.addFragment(VideoGridListFragment(), "喜欢")
        adapter!!.addFragment(VideoGridListFragment(), "故事")
        vp.setAdapter(adapter)
        tl.setUpWithViewPager(vp)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
