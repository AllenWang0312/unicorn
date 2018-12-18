package edu.tjrac.swant.netimage.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar

import java.util.ArrayList

import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.netimage.adapter.DetialAdapter
import kotlinx.android.synthetic.main.activity_mzitu_ablum_detial.*

class MzituAblumDetialActivity : AppCompatActivity() {

    internal var id: Int = 0

    internal var detials = ArrayList<String>()
    internal var mDetialAdapter: DetialAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getIntExtra("id", 0)

        setContentView(R.layout.activity_mzitu_ablum_detial)

        rv.setLayoutManager(GridLayoutManager(this@MzituAblumDetialActivity, 2))
        mDetialAdapter = DetialAdapter(detials)
        rv.setAdapter(mDetialAdapter)
        initData()
    }

    private fun initData() {
        //        App.getUnicornApi().getMzituAblumDetial(id).enqueue(new Callback<MzituAblumDetial>() {
        //            @Override
        //            public void onResponse(Call<MzituAblumDetial> call, Response<MzituAblumDetial> response) {
        //                detials.addAll(response.body().getData());
        //                mDetialAdapter.notifyDataSetChanged();
        //            }
        //
        //            @Override
        //            public void onFailure(Call<MzituAblumDetial> call, Throwable t) {
        //
        //            }
        //        });
    }

    companion object {

        fun start(context: Context, id: Int) {
            context.startActivity(Intent(context, MzituAblumDetialActivity::class.java)
                    .putExtra("id", id))
        }
    }
}
