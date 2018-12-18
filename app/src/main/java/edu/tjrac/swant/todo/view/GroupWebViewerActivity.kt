package edu.tjrac.swant.todo.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.yckj.baselib.common.adapter.FragmentsPagerAdapter
import com.yckj.baselib.common.base.BaseActivity
import com.yckj.baselib.common.base.BaseFragment
import com.yckj.baselib.common.base.BaseWebViewFragment
import edu.tjrac.swant.todo.adapter.GroupWebViewPageTransation
import edu.tjrac.swant.todo.bean.WebInfo
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_group_web_viewer.*
import java.util.*

class GroupWebViewerActivity : BaseActivity() {


    internal var adapter: FragmentsPagerAdapter?=null
    internal var infos: List<WebInfo>?=null
    internal var current: BaseFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_group_web_viewer);
        infos = intent.getParcelableArrayListExtra<WebInfo>("infos")

        adapter = FragmentsPagerAdapter(supportFragmentManager)
        for (item in infos!!) {
            adapter!!.addFragment(BaseWebViewFragment(item.url, R.layout.activity_service_text), item.title)
        }
        viewpager.setOffscreenPageLimit(infos!!.size)

        viewpager.setAdapter(adapter)
        viewpager.setPageMargin(40)
        viewpager.setPageTransformer(true, GroupWebViewPageTransation())
        viewpager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                current = adapter!!.getItem(position) as BaseFragment
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

    }

    override fun onBackPressed() {
        if (current!!.isBackable) {
            current!!.onBack()
            return
        }
        super.onBackPressed()
    }

    companion object {


        fun start(context: Context, infos: ArrayList<WebInfo>) {
            context.startActivity(Intent(context, GroupWebViewerActivity::class.java)
                    .putExtra("infos", infos)
                    .addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT or Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }
}
