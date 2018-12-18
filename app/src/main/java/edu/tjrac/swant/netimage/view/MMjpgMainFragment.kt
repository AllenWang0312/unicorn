package edu.tjrac.swant.netimage.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import edu.tjrac.swant.netimage.adapter.MMjpgTagAblumListAdapter
import edu.tjrac.swant.netimage.bean.MMjpgHotList
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.fragment_mmjpg_main.*
import java.util.*

/**
 * Created by wpc on 2018/2/11 0011.
 */

class MMjpgMainFragment : Fragment() {

    internal var adapter: MMjpgTagAblumListAdapter? = null
    internal var ablums = ArrayList<MMjpgHotList.DataBean>()
    internal var home = "http://www.mmjpg.com/"

    internal var values: Array<String>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mmjpg_main, container, false)
        values = activity!!.resources.getStringArray(R.array.mmjpg_tags_values)
        rv.setLayoutManager(GridLayoutManager(activity, 2))
        swiper.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            page = 1
            initAblums()
        })
        adapter = MMjpgTagAblumListAdapter(ablums)
        adapter!!.setOnLoadMoreListener {
            page++
            initAblums()
        }
        adapter!!.setOnItemClickListener { adapter, view, position -> }
        rv.setAdapter(adapter)

        sp.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                page = 1
                path = values!![sp.getSelectedItemPosition()]
                initAblums()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })


        return view
    }

    internal var path = ""
    internal var page = 1

    private fun initAblums() {
        if (page == 1) {
            ablums.clear()
        }
        val url = home + page
        //        App.getUnicornApi().getMMjpgAblumList(path, page).enqueue(new Callback<MMjpgHotList>() {
        //            @Override
        //            public void onResponse(Call<MMjpgHotList> call, Response<MMjpgHotList> response) {
        //
        //                MMjpgHotList result = response.body();
        //                MMjpgHotList.InfoBean info = result.getInfo();
        //
        //                if (result.getCode() == 200) {
        //                    ablums.addAll(response.body().getData());
        //                    adapter.notifyDataSetChanged();
        //                    if (info.getPage().equals(info.getMaxPage())) {
        //                        adapter.loadMoreEnd();
        //                    } else {
        //                        adapter.loadMoreComplete();
        //                    }
        //                } else {
        //
        //                }
        //
        //            }
        //
        //            @Override
        //            public void onFailure(Call<MMjpgHotList> call, Throwable t) {
        //
        //            }
        //        });
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
