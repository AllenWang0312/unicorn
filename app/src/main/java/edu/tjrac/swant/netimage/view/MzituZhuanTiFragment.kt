package edu.tjrac.swant.netimage.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chad.library.adapter.base.BaseQuickAdapter

import java.util.ArrayList

import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.netimage.bean.MzituZhuanTiAblum
import edu.tjrac.swant.netimage.adapter.MzituZhuanTiAdapter
import kotlinx.android.synthetic.main.fragment_mzitu_zhuangti.*

/**
 * Created by wpc on 2018/2/11 0011.
 */

class MzituZhuanTiFragment : Fragment() {


    internal var zhuantilist = ArrayList<MzituZhuanTiAblum.DataBean>()
    internal var adapter: MzituZhuanTiAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mzitu_zhuangti, container, false)
        rv.setLayoutManager(GridLayoutManager(activity, 3))
        adapter = MzituZhuanTiAdapter(zhuantilist)
        adapter!!.setOnItemClickListener { adapter, view, position ->
            val path = zhuantilist[position].path
            val chars = path.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            //                T.show(getActivity(),chars[4]);
            MzituTagAblumsActivity.start(activity!!, chars[4])
        }
        rv.setAdapter(adapter)
        initPages()
        return view
    }

    private fun initPages() {

        //        App.getUnicornApi().getMzituZhuanTi().enqueue(new Callback<MzituZhuanTiAblum>() {
        //            @Override
        //            public void onResponse(Call<MzituZhuanTiAblum> call, Response<MzituZhuanTiAblum> response) {
        //                zhuantilist.addAll(response.body().getData());
        //                adapter.notifyDataSetChanged();
        //            }
        //
        //            @Override
        //            public void onFailure(Call<MzituZhuanTiAblum> call, Throwable t) {
        //
        //            }
        //        });

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
