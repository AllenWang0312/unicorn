package edu.tjrac.swant.douban.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.ImageView
import com.yckj.baselib.common.base.BaseMVPFragment
import edu.tjrac.swant.douban.adapter.DoubanMoviesAdapter
import edu.tjrac.swant.douban.contract.DoubanMovieContract
import edu.tjrac.swant.douban.doubane.DoubanCommingMovieBean
import edu.tjrac.swant.douban.doubane.DoubanIsShowingBean
import edu.tjrac.swant.douban.doubane.MovieData
import edu.tjrac.swant.douban.presenterImpl.DoubanMoviePresenter
import edu.tjrac.swant.unicorn.R
import edu.tjrac.swant.unicorn.view.SearchActivity
import edu.tjrac.swant.zhihu.zhihu.SubjectsBean
import kotlinx.android.synthetic.main.fragment_douban_movie.*
import java.util.*

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 上午 9:42
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

class DoubanMovieFragment : BaseMVPFragment<DoubanMoviePresenter>(), DoubanMovieContract.View {

    //    @BindView(R.id.recycler) RecyclerView recycler;
    //    @BindView(R.id.recycler2) RecyclerView recycler2;
    //    @BindView(R.id.recycler3) RecyclerView recycler3;
    //    @BindView(R.id.recycler4) RecyclerView recycler4;

    //    @BindView(R.id.cl) CoordinatorLayout cl;
    //    @BindView(R.id.nsv) NestedScrollView nsv;

    //    @BindView(R.id.swiper) SwipeRefreshLayout mSwiper;

    internal var adapter: DoubanMoviesAdapter?=null
    internal var adapter2: DoubanMoviesAdapter?=null
    internal var adapter3: DoubanMoviesAdapter?=null
    internal var adapter4: DoubanMoviesAdapter?=null

    internal var isShowing = ArrayList<MovieData>()
    internal var comming = ArrayList<MovieData>()
    internal var top250 = ArrayList<MovieData>()
    internal var mb = ArrayList<MovieData>()
    //@BindView(R.id.iv_iv_head) ImageView iv_head;

    //    Unbinder unbinder;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_douban_movie, container, false)


        adapter = DoubanMoviesAdapter(isShowing)
        adapter2 = DoubanMoviesAdapter(comming)
        adapter3 = DoubanMoviesAdapter(top250)
        adapter4 = DoubanMoviesAdapter(mb)

        recycler.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false))
        recycler2.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false))
        recycler3.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false))
        recycler4.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false))

        recycler.setAdapter(adapter)
        recycler2.setAdapter(adapter2)
        recycler3.setAdapter(adapter3)
        recycler4.setAdapter(adapter4)

        adapter!!.setOnItemClickListener { adapter, view, position ->
            //                DoubanMovieDetialActivity.start(getActivity(), (SubjectsBean) movies.get(position));
            val imageView = view.findViewById<ImageView>(R.id.image)
            DoubanMovieDetialActivity.startWithSharedView(activity!!, imageView, isShowing[position] as SubjectsBean)
        }

        presenter = DoubanMoviePresenter(this)

        presenter.getIsShowingMovies()
        presenter.getCommingMovies()

        //        mSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        //            @Override
        //            public void onRefresh() {
        //                isShowing.clear();
        //                comming.clear();
        //                presenter.getIsShowingMovies();
        //                presenter.getCommingMovies();
        //            }
        //        });

        setHasOptionsMenu(true)
        cl.setOnHierarchyChangeListener(object : ViewGroup.OnHierarchyChangeListener {
            override fun onChildViewAdded(parent: View, child: View) {

            }

            override fun onChildViewRemoved(parent: View, child: View) {

            }
        })
        iv_head.setOnClickListener(View.OnClickListener {
            //                CoordinglayoutTestActivity.start(getActivity());
        })
        return v
    }

    override fun onBack() {

    }

    override fun onGetIsShowingSuccess(bean: DoubanIsShowingBean) {
        isShowing.addAll(bean.subjects)
        adapter!!.notifyDataSetChanged()

    }

    override fun showProgress() {
        super.showProgress()
        //        mSwiper.setRefreshing(true);
    }

    override fun dismissProgress() {
        super.dismissProgress()
        //        if (mSwiper.isRefreshing()) {
        //            mSwiper.setRefreshing(false);
        //        }
    }

    override fun onGetCommingSuccess(bean: DoubanCommingMovieBean) {
        comming.addAll(bean.subjects)
        adapter2!!.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.douban_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.seach -> SearchActivity.start(activity, 2)
        }//            case R.id.top250:
        //                LeaderboardActivity.start(getActivity(), "top250");
        //                break;
        //
        //            case R.id.us_box:
        //                LeaderboardActivity.start(getActivity(), "us_box");
        //                break;
        return super.onOptionsItemSelected(item)
    }
}
