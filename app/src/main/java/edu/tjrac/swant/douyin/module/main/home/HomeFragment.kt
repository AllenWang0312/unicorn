package edu.tjrac.swant.douyin.module.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yckj.baselib.common.base.BaseFragment

import edu.tjrac.swant.unicorn.R

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/21 0021 下午 1:42
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_douyin_home, container, false)

        return view
    }

    override fun onBack() {

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}
