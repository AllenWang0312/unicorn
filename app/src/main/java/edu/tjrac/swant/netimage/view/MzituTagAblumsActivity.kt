package edu.tjrac.swant.netimage.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import edu.tjrac.swant.netimage.adapter.MzituTagAblumAdapter
import edu.tjrac.swant.netimage.bean.MzituTagAblum
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_mzitu_tag_ablums.*
import java.util.*

class MzituTagAblumsActivity : AppCompatActivity() {

    internal var adapter: MzituTagAblumAdapter? = null
    internal var tags = ArrayList<MzituTagAblum.DataBean>()
    internal var tag: String = ""
    internal var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tag = intent.getStringExtra("tag")
        setContentView(R.layout.activity_mzitu_tag_ablums)

        rv.setLayoutManager(GridLayoutManager(this@MzituTagAblumsActivity, 2))
        adapter = MzituTagAblumAdapter(tags)

        adapter!!.setOnLoadMoreListener {
            page++
            initDatas()
        }
        adapter!!.setOnItemClickListener { adapter, view, position ->
            val chars = tags[position].path.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            MzituAblumDetialActivity.start(this@MzituTagAblumsActivity, Integer.valueOf(chars[3])!!)
        }
        swiper.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            page = 1
            initDatas()
        })
        rv.setAdapter(adapter)

        initDatas()

    }

    private fun initDatas() {
        swiper.setRefreshing(true)
        if (page == 1) {
            tags.clear()
        }
        //        App.getUnicornApi().getMzituTag(tag, page).enqueue(new Callback<MzituTagAblum>() {
        //            @Override
        //            public void onResponse(Call<MzituTagAblum> call, Response<MzituTagAblum> response) {
        //                tags.addAll(response.body().getData());
        //                adapter.notifyDataSetChanged();
        //                adapter.loadMoreComplete();
        //                swiper.setRefreshing(false);
        //            }
        //
        //            @Override
        //            public void onFailure(Call<MzituTagAblum> call, Throwable t) {
        //                adapter.loadMoreEnd();
        //                swiper.setRefreshing(false);
        //            }
        //        });
    }

    companion object {


        fun start(context: Context, tag: String) {
            context.startActivity(Intent(context, MzituTagAblumsActivity::class.java).putExtra("tag", tag))
        }
    }
}
