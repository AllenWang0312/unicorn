package edu.tjrac.swant.douyin.module.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup

import com.yckj.baselib.common.base.BaseFragment

import edu.tjrac.swant.douyin.module.main.care.CareFragment
import edu.tjrac.swant.douyin.module.main.home.HomeFragment
import edu.tjrac.swant.douyin.module.main.mine.MineFragment
import edu.tjrac.swant.douyin.module.main.notice.NoticeFragment
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.fragment_douyin_main.*

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/21 0021 下午 2:12
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

class MainFragment : BaseFragment() {

    internal var home: HomeFragment? = null
    internal var care: CareFragment? = null
    internal var notice: NoticeFragment? = null
    internal var mine: MineFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_douyin_main, container, false)
        selectFragment(0)

        rg.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rb_home) {
                selectFragment(0)
            } else if (checkedId == R.id.rb_care) {
                selectFragment(1)
            } else if (checkedId == R.id.rb_notice) {
                selectFragment(2)
            } else if (checkedId == R.id.rb_mine) {
                selectFragment(3)
            }
        })
        return v
    }

    internal fun selectFragment(position: Int) {
        if (position == 0) {
            if (home == null) {
                home = HomeFragment()
            }
            childFragmentManager.beginTransaction().replace(R.id.content, home).commit()
        } else if (position == 1) {
            if (care == null) {
                care = CareFragment()
            }
            childFragmentManager.beginTransaction().replace(R.id.content, care).commit()
        } else if (position == 2) {
            if (notice == null) {
                notice = NoticeFragment()
            }
            childFragmentManager.beginTransaction().replace(R.id.content, notice).commit()
        } else if (position == 3) {
            if (mine == null) {
                mine = MineFragment()
            }
            childFragmentManager.beginTransaction().replace(R.id.content, mine).commit()
        }
    }

    override fun onBack() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
