package edu.tjrac.swant.unicorn.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.Menu
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.yckj.baselib.common.adapter.FragmentsPagerAdapter
import edu.tjrac.swant.douban.view.DoubanMovieFragment
import edu.tjrac.swant.gank.view.GankHomeFragment
import edu.tjrac.swant.kaiyan.view.KaiyanFragment
import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.unicorn.SharedStartActivity
import edu.tjrac.swant.zhihu.view.ZhihuDailyFragment
import kotlinx.android.synthetic.main.activity_zhihu_v2.*

class ZhihuActivity : SharedStartActivity(), BottomNavigationBar.OnTabSelectedListener {

    internal var adapter: FragmentsPagerAdapter?=null
    private val titles = arrayOf("知乎日报", "豆瓣电影", "Gank", "开眼视频")
    private val icons = intArrayOf(R.drawable.ic_public_white_24dp, R.drawable.ic_explore_white_24dp, R.drawable.ic_chat_white_24dp, R.drawable.ic_account_box_white_24dp)
    internal var zhihu: ZhihuDailyFragment?=null
    internal var douban: DoubanMovieFragment?=null
    internal var gank: GankHomeFragment?=null
    internal var kaiyan: KaiyanFragment?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zhihu_v2)
//        setSupportActionBar(toolbar)

        adapter = FragmentsPagerAdapter(supportFragmentManager)
        zhihu = ZhihuDailyFragment()
        douban = DoubanMovieFragment()
        gank = GankHomeFragment()
        kaiyan = KaiyanFragment()

        adapter!!.addFragment(zhihu, "zhihu")
        adapter!!.addFragment(douban, "douban")
        adapter!!.addFragment(gank, "gank")
        adapter!!.addFragment(kaiyan, "kaiyan")
        vp_zhihu_main!!.offscreenPageLimit = 4
        vp_zhihu_main!!.adapter = adapter


        bnb!!.setTabSelectedListener(this)
        //        BadgeItem badgeItem = new BadgeItem().setBackgroundColor(Color.RED).setText("99");
        bnb!!.setMode(BottomNavigationBar.MODE_FIXED)
        bnb!!.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        bnb!!.setBarBackgroundColor(R.color.colorPrimary)
        bnb!!.addItem(BottomNavigationItem(icons[0], titles[0]))
                .addItem(BottomNavigationItem(icons[1], titles[1]))
                .addItem(BottomNavigationItem(icons[2], titles[2]))
                .addItem(BottomNavigationItem(icons[3], titles[3])
                        //                        .setActiveColorResource(R.color.white).setInActiveColorResource(R.color.white)
                )

                .initialise() //所有的设置需在调用该方法前完成
        bnb!!.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                vp_zhihu_main!!.currentItem = position
            }

            override fun onTabUnselected(position: Int) {

            }

            override fun onTabReselected(position: Int) {

            }
        })

        bnb!!.setFirstSelectedPosition(0)
    }

    private val manager: FragmentManager? = null


    override fun onTabSelected(position: Int) {

    }

    override fun onTabUnselected(position: Int) {

    }

    override fun onTabReselected(position: Int) {

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    companion object {

        fun start(context: Activity) {
            context.startActivity(Intent(context, ZhihuActivity::class.java))
        }
    }
}
