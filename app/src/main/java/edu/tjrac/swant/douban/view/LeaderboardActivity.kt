package edu.tjrac.swant.douban.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.yckj.baselib.common.base.BaseMVPActivity
import edu.tjrac.swant.douban.adapter.DoubanMoviesAdapter
import edu.tjrac.swant.douban.contract.LeaderBoardContract
import edu.tjrac.swant.douban.doubane.DoubanTop250
import edu.tjrac.swant.douban.doubane.DoubanUSBox
import edu.tjrac.swant.douban.doubane.MovieData
import edu.tjrac.swant.douban.presenterImpl.LeaderBoardPresenter
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.android.synthetic.main.refreshable_recycler.*
import java.util.*

class LeaderboardActivity : BaseMVPActivity<LeaderBoardPresenter>(), LeaderBoardContract.View {

    //    @BindView(R.id.toolbar) Toolbar toolbar;
    //    @BindView(R.id.recycler) RecyclerView recycler;
    //    @BindView(R.id.swiper) SwipeRefreshLayout swiper;

    internal var pageSize = 25
    internal var movies = ArrayList<MovieData>()

    internal var adapter: DoubanMoviesAdapter? = null
    internal var tag: String = ""

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        presenter = LeaderBoardPresenter(this)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        //        getActionBar().setDisplayHomeAsUpEnabled(true);
        tag = intent.getStringExtra("tag")

        adapter = DoubanMoviesAdapter(movies)
        adapter!!.setOnItemClickListener { adapter, view, position -> DoubanMovieDetialActivity.start(mContext, movies[position].movieData) }
        recycler.setLayoutManager(LinearLayoutManager(mContext))

        if (tag == "top250") {
            title = "top250"
            adapter!!.setEnableLoadMore(true)
            adapter!!.setOnLoadMoreListener { presenter!!.getTop250Data(movies.size, pageSize) }
        } else {
            title = "美榜"
        }

        recycler.setAdapter(adapter)
        if (tag == "top250") {
            presenter!!.getTop250Data(movies.size, pageSize)
        } else {
            presenter!!.getUSBoxData()
        }

        swiper.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            if (tag == "top250") {
                movies.clear()
                presenter!!.getTop250Data(movies.size, pageSize)
            } else {
                movies.clear()
                presenter!!.getUSBoxData()
            }
        })
    }

    override fun onGetTop250Success(top: DoubanTop250) {
        if (top.count > 0) {
            movies.addAll(top.subjects)
            adapter!!.loadMoreComplete()
            adapter!!.notifyDataSetChanged()
        } else {
            adapter!!.loadMoreEnd()
        }

    }

    override fun onGetUSBoxSuccess(box: DoubanUSBox) {
        movies.addAll(box.subjects)
        adapter!!.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {

        fun start(context: Context, tag: String) {
            val i = Intent(context, LeaderboardActivity::class.java)
            i.putExtra("tag", tag)
            context.startActivity(i)
        }
    }
}
