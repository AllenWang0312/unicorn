package edu.tjrac.swant.bluetooth.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.yckj.baselib.common.base.BaseFragment
import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.unicorn.SharedStartActivity
import kotlinx.android.synthetic.main.app_bar_bluetooth.*

class BluetoothActivity : SharedStartActivity(), NavigationView.OnNavigationItemSelectedListener {

    //    @BindView(R.id.toolbar) Toolbar mToolbar;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    internal var contentFragment: BaseFragment?=null
    internal var mBLEFragment: BLEFragment? = null

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        val id = item.itemId
        if (id == R.id.nav_bluetooth) {
            if (mBLEFragment == null) {
                mBLEFragment = BLEFragment()
            }
            contentFragment = mBLEFragment
            supportFragmentManager.beginTransaction().replace(R.id.fl_main, mBLEFragment).commit()
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
