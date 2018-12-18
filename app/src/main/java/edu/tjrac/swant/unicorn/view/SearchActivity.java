package edu.tjrac.swant.unicorn.view;

//public class SearchActivity extends BaseMVPActivity<SearchPresenter> implements SearchActivityContract.View {
//
//    //index 0 电影 1 图书
//    int typeIndex;
//
//
//    SearchResultAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        typeIndex = getIntent().getIntExtra("type", 0);
//        setContentView(R.layout.activity_search);
//        setPresenter(new SearchPresenter(this));
//        et_search.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
//                    doSearch();
//                }
//                return false;
//            }
//        });
//
//        iv_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                doSearch();
//            }
//        });
//
//        adapter = new SearchResultAdapter(results);
//        recycler.setLayoutManager(new LinearLayoutManager(mContext));
//
//        adapter.setEnableLoadMore(true);
//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                if (typeIndex == 0) {
//                    getPresenter().doSearchBook("", search, results.size(), count);
//                } else if (typeIndex == 2) {
//                    getPresenter().doSearchMusic(search, page, count);
//                }
//            }
//        });
//        recycler.setAdapter(adapter);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                switch (results.get(position).getItemType()) {
//                    case 0:
//
//                        break;
//
//                    case 1:
//
//                        break;
//                    case 2:
////                        id: str[20] img: str[22] lrc:str[0]
//                        QQMusicSearchResultBean.DataBean.SongBean.ListBean bean = (QQMusicSearchResultBean.DataBean.SongBean.ListBean) results.get(position);
//                        String[] infos = bean.getF().split("|");
////                       infos[20];
//                        ArrayList<MusicInfo> infos1 = new ArrayList<>();
//                        infos1.add(new MusicInfo(
//                                QQMusicApi.getPlayUrl(infos[20], key.getKey()),
////                                QQMusicApi.playUrl.replace("{ID}", infos[20]).replace("{Key}",key.getKey() ),
//                                QQMusicApi.getLrcPath(infos[0])
//                        ));
//                        MusicPlayerActivity.Companion.start(mContext,
//                                infos1
//                        );
//
//
//                        break;
//                }
//            }
//        });
//        sp_types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                typeIndex = position;
//                if (typeIndex == 0) {
//                    adapter.setEnableLoadMore(true);
//                } else if (typeIndex == 1) {
//                    adapter.setEnableLoadMore(false);
//                } else if (typeIndex == 2) {
//                    adapter.setEnableLoadMore(true);
//                    if (key == null) {
//                        getPresenter().getQQMusicKey("1007991483");
//                    }
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        sp_types.setSelection(typeIndex);
//
//
//    }
//
//    ArrayList<MultiItemEntity> results = new ArrayList<>();
//    int count = 15;
//    //    ArrayList<DoubanMovieSearchResultBean.SubjectsBean> movies = new ArrayList<>();
//    String search;
//
//    int page = 1;
//
//    private void doSearch() {
//        search = et_search.getText().toString().trim();
//        results.clear();
//        if (typeIndex == 0) {
//            getPresenter().doSearchBook("", search, results.size(), count);
//        } else if (typeIndex == 1) {
//            getPresenter().doSearchMovie("", search);
//        } else if (typeIndex == 2) {
//            getPresenter().doSearchMusic(search, page, count);
//        }
//    }
//
//
//    @Override
//    public void onSearchBookResult(DoubanBookSearchResult result) {
//        if (result.getCount() > 0) {
//            results.addAll(result.getBooks());
//            adapter.loadMoreComplete();
//            adapter.notifyDataSetChanged();
//        } else {
//            adapter.loadMoreEnd();
//        }
//
//    }
//
//    @Override
//    public void onSearchMovieResult(DoubanMovieSearchResultBean result) {
//        results.addAll(result.getSubjects());
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onSearchMusicResult(QQMusicSearchResultBean result) {
//        QQMusicSearchResultBean.DataBean.SongBean bean = result.getData().getSong();
//        if (bean.getList().size() > 0) {
//            results.addAll(bean.getList());
//            adapter.loadMoreComplete();
//            L.i("song curnum", bean.getCurnum() + "");
//            adapter.notifyDataSetChanged();
//        } else {
//            adapter.loadMoreEnd();
//        }
//
//    }
//
//    QQMusicKey key;
//
//    @Override
//    public void onGetQQMusicKeySuccess(String keyString) {
//        key = ApiUtils.formatKeyResut(keyString);
//        T.show(mContext, "获取key成功:" + key.getKey());
//    }
//
//    public static void start(Context activity, int type) {
//        activity.startActivity(new Intent(activity, SearchActivity.class).putExtra("type", type));
//    }
//}
