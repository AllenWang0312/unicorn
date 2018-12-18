package edu.tjrac.swant.netimage.view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_net_image.*
import kotlinx.android.synthetic.main.app_bar_net_image.*

class NetImageActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //    @BindView(R.id.toolbar) Toolbar toolbar;
    //    @BindView(R.id.drawer_layout) DrawerLayout drawer_layout;

    internal var mmjpg: MMjpgMainFragment? = null
    internal var mMzituZhuanTi: MzituZhuanTiFragment? = null
    internal var mManager: android.support.v4.app.FragmentManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mManager = supportFragmentManager
        setContentView(R.layout.activity_net_image)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        mManager!!.beginTransaction().replace(R.id.fl_content, getMmjpg()).commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.net_image, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    internal var tag = 0

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_mmjpg) {
            if (tag != 0) {
                mManager!!.beginTransaction().replace(R.id.fl_content, getMmjpg()).commit()
                tag = 0
            }
            // Handle the camera action
        } else if (id == R.id.nav_mzitu_zhuanti) {
            if (tag != 1) {
                mManager!!.beginTransaction().replace(R.id.fl_content, mzituZhuanTi).commit()
                tag = 1
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    fun getMmjpg(): MMjpgMainFragment {
        if (mmjpg == null) {
            mmjpg = MMjpgMainFragment()
        }

        return mmjpg!!
    }

    val mzituZhuanTi: Fragment
        get() {
            if (mMzituZhuanTi == null) {
                mMzituZhuanTi = MzituZhuanTiFragment()
            }
            return mMzituZhuanTi!!
        }
}
