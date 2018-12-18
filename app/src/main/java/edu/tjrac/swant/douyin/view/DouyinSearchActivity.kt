package edu.tjrac.swant.douyin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View

import com.yckj.baselib.common.base.BaseActivity

import edu.tjrac.swant.unicorn.R

class DouyinSearchActivity : BaseActivity() {
    //
    //    @BindView(R.id.iv_close) ImageView mIvClose;
    //    @BindView(R.id.tv_search) TextView mTvSearch;
    //
    //    @BindView(R.id.recycler) RecyclerView mRecycler;
    //    @BindView(R.id.swiper) SwipeRefreshLayout mSwiper;

    internal var headview: View?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_douyin_search)
        //        ButterKnife.bind(this);
        headview = LayoutInflater.from(mContext).inflate(R.layout.douyin_search_head, null)


    }

}
