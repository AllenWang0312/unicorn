package edu.tjrac.swant.douyin.module.main

import android.os.Bundle
import android.support.v4.view.ViewPager

import com.yckj.baselib.common.adapter.FragmentsPagerAdapter

import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.unicorn.SharedStartActivity
import kotlinx.android.synthetic.main.activity_douyin_main.*

class DouYinActivity : SharedStartActivity() {

    internal var adapter: FragmentsPagerAdapter?=null
    internal var camera: CameraFragment?=null
    internal var main: MainFragment?=null
    internal var userinfo: UserInfoFragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_douyin_main)
        adapter = FragmentsPagerAdapter(supportFragmentManager)
        camera = CameraFragment()
        main = MainFragment()
        userinfo = UserInfoFragment()

        adapter!!.addFragment(camera, "camera")
        adapter!!.addFragment(main, "main")
        adapter!!.addFragment(userinfo, "userinfo")

        vp.setAdapter(adapter)
        vp.setCurrentItem(1)
        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {

                } else if (position == 1) {

                } else if (position == 2) {

                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }


}
