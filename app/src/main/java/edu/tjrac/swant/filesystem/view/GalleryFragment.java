package edu.tjrac.swant.filesystem.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yckj.baselib.common.base.BaseFragment;
import com.yckj.baselib.common.dialog.EditTextDialog;
import com.yckj.baselib.util.FileUtils;
import com.yckj.baselib.util.L;
import com.yckj.baselib.util.OpenFileHelper;
import com.yckj.baselib.util.StringUtils;
import com.yckj.baselib.util.T;
import com.yckj.baselib.util.UiUtils;
import com.yckj.baselib.util.uncom.CameraUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.tjrac.swant.filesystem.MediaUtil;
import edu.tjrac.swant.filesystem.RecycItemFilter;
import edu.tjrac.swant.filesystem.adapter.ClipBoardRecycAdapter;
import edu.tjrac.swant.filesystem.adapter.GalleryContentAdapter;
import edu.tjrac.swant.media.view.MusicPlayerActivity;
import edu.tjrac.swant.opengl.view.OpenGLActivity;
import edu.tjrac.swant.unicorn.Config;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.unicorn.view.LottieViewerActivity;

/**
 * Created by wpc on 2018/1/12 0012.
 */

public class GalleryFragment extends BaseFragment implements View.OnClickListener {

    private int[] types = {R.drawable.ic_view_headline_white_24dp,
            R.drawable.ic_view_list_white_24dp,
            R.drawable.ic_view_module_white_24dp
    };


    RecycItemFilter<File> filter;


    @BindView(R.id.fab_left) FloatingActionButton fab_left;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.fab_add) FloatingActionButton fab_add;

//    @BindView(R.id.et_search) EditText et_search;
//    @BindView(R.id.iv_search) ImageView iv_search;

    @BindView(R.id.rv_paths) RecyclerView pathRecyc;
    @BindView(R.id.rv_gallery_content) RecyclerView mRecyclerView;

    GalleryContentAdapter adapter;
    //    ArrayList<GalleryContentAdapter.GalleryInfo> infos = new ArrayList<>();
    ArrayList<File> gallerys = new ArrayList<>();

    @BindView(R.id.tv_clipfile_size) TextView tv_clipsize;

    @BindView(R.id.swiper) SwipeRefreshLayout swiper;

    String quick_res;
    String rootptah;
    ArrayList<String> paths;
    private Unbinder mUnbinder;


    HashMap<String, Long> cut_paths = new HashMap<>();
    HashMap<String, Long> copy_paths = new HashMap<>();

    PopupWindow mPopupWindow;
    RecyclerView mPopRecycler;

    ClipBoardRecycAdapter clipAdapter;
//    @BindView(R.id.adView)
//     AdView mAdView;

    private GalleryContentAdapter.HasItemSelectedCallback mItemSelectedCallback = new GalleryContentAdapter.HasItemSelectedCallback() {
        @Override
        public void onItemSelected() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cut.setVisible(true);
                    copy.setVisible(true);
                    delete.setVisible(true);
                    paste.setVisible(true);
                    rename.setVisible(true);
                    search.setVisible(false);
                }
            });
        }

        @Override
        public void onNothingSelected() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cut.setVisible(false);
                    copy.setVisible(false);
                    delete.setVisible(false);
                    paste.setVisible(false);
                    rename.setVisible(false);
                    search.setVisible(true);
                }
            });

        }
    };

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);


        rootptah = FileUtils.getSDcardPath();
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);


        mUnbinder = ButterKnife.bind(this, view);

//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                    swiper.setRefreshing(false);
                }
            }
        });
        adapter = new GalleryContentAdapter(cut_paths, copy_paths);

        filter = new RecycItemFilter<File>(mRecyclerView, adapter.getData()) {
            @Override
            public boolean equal(File data, String filter) {
                return data.getName().contains(filter);
            }
        };

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, RecyclerView.HORIZONTAL, false));
            Log.i("info", "landscape");
        } else if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            Log.i("info", "portrait");
        }
        adapter.bindToRecyclerView(mRecyclerView);
        adapter.bindToPathRecyc(pathRecyc);
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter ad, View view, int position) {
                adapter.switchCheckState(null);
                return false;
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter ad, View view, int position) {
                if (adapter.showCheckBox) {
                    adapter.switchItem(position);
                } else {
                    File file = adapter.getItem(position);
                    if (file.isDirectory()) {
                        backable = true;
                        adapter.path_histroy.push(file);
                        adapter.cd_dir(file);
                    } else {
                        if (".h264".contains(StringUtils.getEndString(file.getName()))) {
//                            startActivity(new Intent(getActivity(), H264Activity.class)
//                            .putExtra("path",file.getAbsoluteFile()));

                        } else if (OpenGLActivity.res_type.contains(StringUtils.getEndString(file.getName()))) {
                            OpenGLActivity.start(getActivity(), file.getAbsolutePath());
                        } else if (GalleryAlbumActivity.res_type.contains(StringUtils.getEndString(file.getName()))) {
                            GalleryAlbumActivity.start(getActivity(),
                                    (adapter.getItem(position)).getParent(), position
                            );
                        } else if (LottieViewerActivity.res_type.contains(StringUtils.getEndString(file.getName()))) {
                            LottieViewerActivity.start(getActivity(), file.getAbsolutePath());
                        } else if (MusicPlayerActivity.res_type.contains(StringUtils.getEndString(file.getName()))) {
                            MusicPlayerActivity.start(getActivity(),
                                    file.getAbsolutePath()
                            );
                        }
//                        else if(MediaPlayerActivity.){
//
//                        }

                        else {
                            File file1 = adapter.getItem(position);
                            Log.i("open file", file1.getAbsolutePath());
                            OpenFileHelper.openFile(getActivity(), file1);
                        }
                    }
                }
            }
        });
        adapter.setSelectedCallback(mItemSelectedCallback);
//       mRecyclerView.setLayoutManager(new );
        mRecyclerView.setAdapter(adapter);
        fab_left.setImageResource(types[2]);
        fab_left.setOnClickListener(this);
        fab_add.setOnClickListener(this);
        fab.setOnClickListener(this);
//        iv_search.setImeOptions(EditorInfo.IME_ACTION_SEND);
//        et_search.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (KeyEvent.KEYCODE_SEARCH == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
//                    //处理事件
//                    adapter.setFailter(et_search.getText().toString());
//                    return true;
//                }
//                return false;
//            }
//        });
        initGalleryInfos(MediaUtil.MediaType.image);
        super.onViewCreated(view, savedInstanceState);

    }


    void showAddFilePopMenu(MediaUtil.MediaType type, final String dirPath) {
        if (StringUtils.isEmpty(dirPath)) {
            T.show(getActivity(), "当前路径不可用");
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            switch (type) {
                case file:
                    builder.setTitle("新建");
                    builder.setItems(new String[]{"文件夹", "文件"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    showCreateFileDialog(dirPath, true);
                                    break;
                                case 1:
                                    showCreateFileDialog(dirPath, false);
                                    break;
                            }

                        }
                    });
                    break;

                case image:
                    builder.setTitle("新建");
                    builder.setItems(new String[]{"文件夹", "图片:系统相机", "图片:网络资源"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    showCreateFileDialog(dirPath, true);
                                    break;
                                case 1:

                                    final EditTextDialog log = new EditTextDialog(getActivity(), "文件名", "请输入文件名");
                                    log.setPositive(new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String name = log.getEditText().getText().toString();
                                            if (StringUtils.isEmpty(name)) {
                                                name = System.currentTimeMillis() + ".jpg";

                                            }
                                            if (!name.endsWith(".jpg")) {
                                                name += ".jpg";
                                            }
                                            File file = new File(dirPath, name);
                                            if (!file.exists()) {
                                                try {
                                                    file.createNewFile();
                                                    CameraUtil.takePhotoWithSystemApp(getActivity(), Uri.fromFile(file));
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    });
                                    log.show(getFragmentManager(), "create file");
                                    break;
                            }
                        }
                    });
                    break;
            }
            builder.create().show();
        }

    }


    private void showCreateFileDialog(final String dirPath, final boolean isDir) {
        final EditTextDialog dir = new EditTextDialog(getActivity(), "新建文件夹", "请输入文件名");
        dir.setPositive(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = dir.getEditText().getText().toString();
                if (StringUtils.isEmpty(name)) {
                    T.show(getActivity(), "请输入文件名");
                } else {
                    File file = new File(dirPath, name);
                    if (file.exists()) {
                        T.show(getActivity(), isDir ? "文件夹已存在" : "文件已存在");
                    } else {
                        if (isDir) {
                            file.mkdir();
                        } else {
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                }
            }
        });
        dir.show(getFragmentManager(), "create file");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    void showPopView() {
        if (mPopupWindow == null) {

            mPopRecycler = (RecyclerView) LayoutInflater.from(getActivity()).inflate(R.layout.recyclerview, null);
            getClipboardData();
            View empty = LayoutInflater.from(getActivity()).inflate(R.layout.empty, null);

            clipAdapter = new ClipBoardRecycAdapter(clipboardData);
            clipAdapter.bindToRecyclerView(mPopRecycler);
            clipAdapter.setEmptyView(empty);
//            mPopRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            mPopRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            clipAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter ad, View view, int position) {
//                    clipboardData.remove(position);
                    clipAdapter.remove(position);
                    clipAdapter.notifyItemRemoved(position);
                    adapter.notifyDataSetChanged();
                    notifyClipBoardCountChanged();
                }
            });
            clipAdapter.bindToPathsType(cut_paths, copy_paths);
            mPopRecycler.setAdapter(clipAdapter);
            mPopupWindow = new PopupWindow(mPopRecycler, 600, 400);
//            mPopupWindow.setAnimationStyle(R.style.popup_window_anim);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
            mPopupWindow.setFocusable(true);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.update();

        } else {
            getClipboardData();
            clipAdapter.notifyDataSetChanged();
        }
        mPopupWindow.showAsDropDown(fab);
    }

    ArrayList<String> clipboardData = new ArrayList<>();

    @SuppressLint("NewApi")
    private void getClipboardData() {
        clipboardData.clear();
        if (cut_paths == null || copy_paths == null) {

        } else {
//            ArrayList<Long> keys = new ArrayList<>();
//            keys.addAll(cut_paths.values());
//            keys.addAll(copy_paths.values());
//
//            keys.sort(new Comparator<Long>() {
//                @Override
//                public int compare(Long o1, Long o2) {
//                    return o1 > o2 ? 1 : -1;
//                }
//            });
//
//            for (String value : keys) {
//                if (value != null) {
//                    clipboardData.add(value);
//                }
//            }
            clipboardData.addAll(cut_paths.keySet());
            clipboardData.addAll(copy_paths.keySet());

        }
    }

    MediaUtil.MediaType type;

    private void initGalleryInfos(MediaUtil.MediaType type) {
        this.type = type;
//        infos.clear();
        String typeString = "";
        gallerys.clear();
        Uri uri = null;
        switch (type) {
            case file:
                File file = new File(FileUtils.getSDcardPath());
                adapter.path_histroy.clear();
                adapter.path_histroy.push(file);
                adapter.setDatas("本地文件", file);
                adapter.notifyDataSetChanged();
                return;
            case image:
                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                typeString = "图片";
                break;
            case video:
                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                typeString = "视屏";
                break;
            case music:
                uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                typeString = "音乐";
                break;
        }
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return; // 没有图片
        }
        while (cursor.moveToNext()) {
            int index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String path = cursor.getString(index); // 文件地址
            File file = new File(path);
            if (file.exists()) {
//                    infos.add(new GalleryContentAdapter.GalleryInfo(file.getName(), file.getAbsolutePath()));
                if (gallerys.contains(file.getParentFile())) {
                    continue;
                } else {
                    gallerys.add(file.getParentFile());
                }
                adapter.setDatas(typeString, gallerys);
                adapter.path_histroy.push(adapter.getPaths(gallerys));
                adapter.notifyDataSetChanged();
            }
        }
//        getActivity().bindService(
//                new Intent(getActivity(), FileWifiShareServer.class)
//                ,connection,BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            L.i(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            L.i(TAG, "onServiceDisconnected");
        }
    };
    Menu menu;
    MenuItem copy, cut, paste, delete, rename, search;

    SearchView searchview;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.gallery, menu);
        this.menu = menu;
        copy = menu.findItem(R.id.copy);
        cut = menu.findItem(R.id.cut);
        paste = menu.findItem(R.id.paste);
        delete = menu.findItem(R.id.delete);
        rename = menu.findItem(R.id.rename_files);
        search = menu.findItem(R.id.search);
        searchview = (SearchView) search.getActionView();
        searchview.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.setFailter(newText, 0);
                filter.find(newText);

                return false;
            }
        });
        searchview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    filter.skipToNext();
                }
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        HashMap<String, Long> paths = adapter.getSelectedFilesPath();

        switch (item.getItemId()) {
            case R.id.sort:
                String[] sortType = getActivity().getResources().getStringArray(R.array.sorttype);
                String[] oradition = getActivity().getResources().getStringArray(R.array.sortoradition);


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.sort_mode, null);
                builder.setTitle("选择排序方式");

                RadioGroup left = view.findViewById(R.id.rb_left),
                        right = view.findViewById(R.id.rb_right);
                inflateItem(Config.SP.sortType, left, sortType);
                inflateItem(Config.SP.sortOrdition, right, oradition);


                builder.setView(view);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sp = getActivity().getSharedPreferences(Config.SP.GallerySetting, Context.MODE_PRIVATE);

                        adapter.sort(sp.getInt(Config.SP.sortType, 0),
                                sp.getInt(Config.SP.sortType, 0));

                    }
                });
                builder.create().show();
                break;
            case R.id.cut:
                cut_paths.putAll(paths);
                adapter.unSelectAll();
                notifyClipBoardCountChanged();
                break;
            case R.id.copy:
                copy_paths.putAll(paths);
                adapter.unSelectAll();
                notifyClipBoardCountChanged();
                break;
            case R.id.paste:
                new AsyPasteThread().execute();
//                copy_paths.put()
                break;
            case R.id.delete:
                Set<String> selectFiles = adapter.getSelectedFilesPath().keySet();
                for (String file : selectFiles) {
                    File f = new File(file);
                    if (f.exists()) {
                        f.delete();
                        int position = adapter.remove(file);
                        adapter.notifyItemRemoved(position);
                        Log.i("file is delete", f.getAbsolutePath());
                    }
                }
                break;
            case R.id.rename_files:
                break;
            case R.id.filesystem:
                initGalleryInfos(MediaUtil.MediaType.file);
                break;
            case R.id.image:
                initGalleryInfos(MediaUtil.MediaType.image);
                break;
            case R.id.video:
                initGalleryInfos(MediaUtil.MediaType.video);
                break;
            case R.id.music:
                initGalleryInfos(MediaUtil.MediaType.music);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void inflateItem(String key, RadioGroup left, String[] sortType) {
        for (int i = 0; i < sortType.length; i++) {
            RadioButton button = new RadioButton(getActivity());
            button.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setPadding(20, 10, 20, 10);
            button.setText(sortType[i]);
            int finalI = i;
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        getActivity().getSharedPreferences(Config.SP.GallerySetting, Context.MODE_PRIVATE)
                                .edit().putInt(key, finalI).commit();
                    }
                }
            });
            left.addView(button);
        }
    }

    private void notifyClipBoardCountChanged() {
        int i = cut_paths.size() + copy_paths.size();
        if (i == 0) {
            tv_clipsize.setVisibility(View.GONE);
        } else {
            tv_clipsize.setVisibility(View.VISIBLE);
            tv_clipsize.setText(i + "");
        }

    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }


    @Override
    public void onBack() {
        backable = adapter.back();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.fab:
                showPopView();
                break;


            case R.id.fab_left:
                UiUtils.showPopmenu(getActivity(), fab_left, true,
                        R.menu.layouttype, new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.action_headline:
                                        adapter.setLayoutType(0);
                                        fab_left.setImageResource(types[0]);
//                                        init
                                        break;
                                    case R.id.action_list:
                                        adapter.setLayoutType(1);
                                        fab_left.setImageResource(types[1]);
                                        break;
                                    case R.id.action_module:
                                        adapter.setLayoutType(2);
                                        fab_left.setImageResource(types[2]);
                                        break;

                                }
                                return false;
                            }
                        });
                break;
            case R.id.fab_add:
                switch (type) {
                    case file:
                        showAddFilePopMenu(MediaUtil.MediaType.file, adapter.getCurrentPath());
                        break;
                    case image:
                        showAddFilePopMenu(MediaUtil.MediaType.image, adapter.getCurrentPath());

                        break;
                    case music:

                        break;
                    case video:

                        break;
                }
                break;
        }
    }


    class AsyPasteThread extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            File file = new File(adapter.getCurrentPath());
            Set<String> cut_keys = cut_paths.keySet();
            Set<String> copy_keys = copy_paths.keySet();
            for (String item : cut_keys) {

                File file1 = new File(item);
                file1.renameTo(new File(adapter.getCurrentPath(), file1.getName()));
            }
            for (String item : copy_keys) {
                File file1 = new File(item);
                if (file1.isDirectory()) {
                    FileUtils.copyFolder(item, adapter.getCurrentPath());
                } else {
                    FileUtils.copyFile(item, adapter.getCurrentPath());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String string) {

            super.onPostExecute(string);
        }
    }


}
