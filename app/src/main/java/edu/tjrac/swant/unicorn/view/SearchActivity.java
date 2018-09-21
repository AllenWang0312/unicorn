package edu.tjrac.swant.unicorn.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yckj.baselib.common.base.BaseMVPActivity;
import com.yckj.baselib.util.L;
import com.yckj.baselib.util.T;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.douban.doubane.DoubanBookSearchResult;
import edu.tjrac.swant.douban.doubane.DoubanMovieSearchResultBean;
import edu.tjrac.swant.media.view.MusicPlayerActivity;
import edu.tjrac.swant.qqmusic.QQMusicApi;
import edu.tjrac.swant.qqmusic.lrchelper.MusicInfo;
import edu.tjrac.swant.qqmusic.qqmucsic.QQMusicKey;
import edu.tjrac.swant.qqmusic.qqmucsic.QQMusicSearchResultBean;
import edu.tjrac.swant.unicorn.ApiUtils;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.unicorn.adapter.SearchResultAdapter;
import edu.tjrac.swant.unicorn.contract.SearchActivityContract;
import edu.tjrac.swant.unicorn.presenterImpl.SearchPresenter;

public class SearchActivity extends BaseMVPActivity<SearchPresenter> implements SearchActivityContract.View {

    //index 0 电影 1 图书
    int typeIndex;

    @BindView(R.id.sp_types) Spinner mSpTypes;
    @BindView(R.id.et_search) EditText mEtSearch;
    @BindView(R.id.iv_search) ImageView mIvSearch;
    @BindView(R.id.recycler) RecyclerView mRecycler;

    SearchResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeIndex = getIntent().getIntExtra("type", 0);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setPresenter(new SearchPresenter(this));


        mEtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                    doSearch();
                }
                return false;
            }
        });

        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearch();
            }
        });

        adapter = new SearchResultAdapter(results);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));

        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (typeIndex == 0) {
                    getPresenter().doSearchBook("", search, results.size(), count);
                } else if (typeIndex == 2) {
                    getPresenter().doSearchMusic(search, page, count);
                }
            }
        });
        mRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (results.get(position).getItemType()) {
                    case 0:

                        break;

                    case 1:

                        break;
                    case 2:
//                        id: str[20] img: str[22] lrc:str[0]
                        QQMusicSearchResultBean.DataBean.SongBean.ListBean bean = (QQMusicSearchResultBean.DataBean.SongBean.ListBean) results.get(position);
                        String[] infos = bean.getF().split("|");
//                       infos[20];
                        ArrayList<MusicInfo> infos1 = new ArrayList<>();
                        infos1.add(new MusicInfo(
                                QQMusicApi.getPlayUrl(infos[20], key.getKey()),
//                                QQMusicApi.playUrl.replace("{ID}", infos[20]).replace("{Key}",key.getKey() ),
                                QQMusicApi.getLrcPath(infos[0])
                        ));
                        MusicPlayerActivity.start(mContext,
                                infos1
                        );


                        break;
                }
            }
        });
        mSpTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeIndex = position;
                if (typeIndex == 0) {
                    adapter.setEnableLoadMore(true);
                } else if (typeIndex == 1) {
                    adapter.setEnableLoadMore(false);
                } else if (typeIndex == 2) {
                    adapter.setEnableLoadMore(true);
                    if (key == null) {
                        getPresenter().getQQMusicKey("1007991483");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpTypes.setSelection(typeIndex);


    }

    ArrayList<MultiItemEntity> results = new ArrayList<>();
    int count = 15;
    //    ArrayList<DoubanMovieSearchResultBean.SubjectsBean> movies = new ArrayList<>();
    String search;

    int page = 1;

    private void doSearch() {
        search = mEtSearch.getText().toString().trim();
        results.clear();
        if (typeIndex == 0) {
            getPresenter().doSearchBook("", search, results.size(), count);
        } else if (typeIndex == 1) {
            getPresenter().doSearchMovie("", search);
        } else if (typeIndex == 2) {
            getPresenter().doSearchMusic(search, page, count);
        }
    }


    @Override
    public void onSearchBookResult(DoubanBookSearchResult result) {
        if (result.getCount() > 0) {
            results.addAll(result.getBooks());
            adapter.loadMoreComplete();
            adapter.notifyDataSetChanged();
        } else {
            adapter.loadMoreEnd();
        }

    }

    @Override
    public void onSearchMovieResult(DoubanMovieSearchResultBean result) {
        results.addAll(result.getSubjects());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSearchMusicResult(QQMusicSearchResultBean result) {
        QQMusicSearchResultBean.DataBean.SongBean bean = result.getData().getSong();
        if (bean.getList().size() > 0) {
            results.addAll(bean.getList());
            adapter.loadMoreComplete();
            L.i("song curnum", bean.getCurnum() + "");
            adapter.notifyDataSetChanged();
        } else {
            adapter.loadMoreEnd();
        }

    }

    QQMusicKey key;

    @Override
    public void onGetQQMusicKeySuccess(String keyString) {
        key = ApiUtils.formatKeyResut(keyString);
        T.show(mContext, "获取key成功:" + key.getKey());
    }

    public static void start(Context activity, int type) {
        activity.startActivity(new Intent(activity, SearchActivity.class).putExtra("type", type));
    }
}
