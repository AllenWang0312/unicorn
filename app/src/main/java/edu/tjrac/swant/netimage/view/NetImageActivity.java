package edu.tjrac.swant.netimage.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;

public class NetImageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.fl_content) FrameLayout fl;

    MMjpgMainFragment mmjpg;
    MzituZhuanTiFragment mMzituZhuanTi;

    android.support.v4.app.FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mManager = getSupportFragmentManager();
        setContentView(R.layout.activity_net_image);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mManager.beginTransaction().replace(R.id.fl_content, getMmjpg()).commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.net_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    int tag = 0;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mmjpg) {
            if (tag != 0) {
                mManager.beginTransaction().replace(R.id.fl_content, getMmjpg()).commit();
                tag = 0;
            }
            // Handle the camera action
        } else if (id == R.id.nav_mzitu_zhuanti) {
            if (tag != 1) {
                mManager.beginTransaction().replace(R.id.fl_content, getMzituZhuanTi()).commit();
                tag = 1;
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public MMjpgMainFragment getMmjpg() {
        if (mmjpg == null) {
            mmjpg = new MMjpgMainFragment();
        }

        return mmjpg;
    }

    public Fragment getMzituZhuanTi() {
        if (mMzituZhuanTi == null) {
            mMzituZhuanTi = new MzituZhuanTiFragment();
        }
        return mMzituZhuanTi;
    }
}
