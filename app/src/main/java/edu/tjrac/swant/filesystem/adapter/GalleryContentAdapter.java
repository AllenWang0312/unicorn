package edu.tjrac.swant.filesystem.adapter;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yckj.baselib.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.tjrac.swant.filesystem.FileSystemHelper;
import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/1/4.
 */

public class GalleryContentAdapter extends BaseQuickAdapter<File, BaseViewHolder> {

    private GalleryContentAdapter instance;
    public boolean showCheckBox = false;
    //    Set<Integer> checkedItemIndex = new HashSet<>();
    File dir;

    HashMap<String, Long> checkedItemIndex = new HashMap<>();

    HashMap<String, Long> cut_paths;
    HashMap<String, Long> copy_paths;

    RecyclerView pathRecyc;


//    @Override
//    public void bindToRecyclerView(RecyclerView recyclerView) {
//        super.bindToRecyclerView(recyclerView);
//    }

    ArrayList<String> paths = new ArrayList<>();
    int failterMode = 1;//1模糊匹配 0精准
    String mFailter;

    PathRecycAdapter pathRecycAdapter = new PathRecycAdapter(paths);

    public void bindToPathRecyc(RecyclerView Recyc) {
        pathRecyc = Recyc;
        pathRecyc.setLayoutManager(new LinearLayoutManager(mContext, OrientationHelper.HORIZONTAL, false));
        pathRecyc.setAdapter(pathRecycAdapter);
    }

    public GalleryContentAdapter(HashMap<String, Long> cut_paths, HashMap<String, Long> copy_paths) {
        super(R.layout.item_gallery);
        this.cut_paths = cut_paths;
        this.copy_paths = copy_paths;
    }

    public File getCurrentDir() {
        if (dir != null) {
            return dir;
        }
        return mData.get(0).getParentFile();
    }

    public String getCurrentDirPath() {
        if (dir != null) {
            return dir.getAbsolutePath();
        }
        return mData.get(0).getParent();
    }

    public void setDatas(String rootName, Object object) {
        if (object instanceof File) {
            this.setDatas(rootName, (File) object);
        } else if (object instanceof String) {
            this.setDatas(rootName, (String) object);
        }
    }

    public void setDatas(String rootName, File dirfile) {
        dir = dirfile;
        this.setDatas(rootName, dirfile.listFiles());
    }

    public int remove(String path) {
        for (int i = 0; i < paths.size(); i++) {
            if (path.equals(paths.get(i))) {
                paths.remove(path);
                return i;
            }
        }
        return -1;
    }

    public void setDatas(@Nullable String rootName, @Nullable File[] data) {
        List<File> files = new ArrayList<>();
        for (File file : data) {
            files.add(file);
        }
        this.setDatas(rootName, files);
    }

    public void setDatas(@Nullable String rootName, @Nullable List<File> data) {
//        this.rootName = rootName;
        dir=null;
        mData = data;
//        path_histroy.clear();
//        path_histroy.push(data);
//        path_histroy.addFirst(getPaths(data));
        notifyDataSetChanged();
        if (rootName != null) {
            paths.clear();
            paths.add(rootName);
            pathRecycAdapter.notifyDataSetChanged();
        }
    }

    public void setDatas(String rootName, @Nullable String p) {

        dir=null;

        path_histroy.clear();
        path_histroy.push(p);

        mData = getFiles(p);
        notifyDataSetChanged();
        if (rootName != null) {
            paths.clear();
            paths.add(rootName);
            pathRecycAdapter.notifyDataSetChanged();
        }

    }

    public String getPaths(List<File> data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i).getAbsoluteFile());
            if (i != data.size()) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    static String currentPath;

    public void cd_dir(File file) {
        currentPath = file.getAbsolutePath();
        checkedItemIndex.clear();
        mData.clear();
        File[] files = file.listFiles();
        for (File file1 : files) {
            mData.add(file1);
        }
        paths.add(file.getName());
        pathRecycAdapter.notifyDataSetChanged();
        notifyDataSetChanged();
    }

//    public ArrayList<File> getSelectedFiles() {
//        ArrayList<File> list = new ArrayList<>();
//        for (Integer i : checkedItemIndex) {
//            list.add(mData.get(i));
//        }
//        return list;
//    }

    public HashMap<String, Long> getSelectedFilesPath() {
        Log.i("getSelectedFilesPath", checkedItemIndex.size() + "");
        return checkedItemIndex;
    }

    public void switchCheckState(@Nullable Boolean b) {
        if (b == null) {
            showCheckBox = !showCheckBox;

        } else {
            showCheckBox = b;
        }
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, File item) {
        FileSystemHelper.loadFileIconToImageView(item, (ImageView) helper.getView(R.id.iv), mContext);
//        if(type==1){
//            UiUtils.loadFileIconToImageView(item,(ImageView) helper.getView(R.id.iv),mContext);
//        }else {
//            if (item.isDirectory()) {
//                File[] childs = item.listFiles();
//                Glide.with(mContext).load(childs[0].getAbsolutePath()).into((ImageView) helper.getView(R.id.iv));

//            } else {
//                Glide.with(mContext).load(item.getAbsolutePath()).into((ImageView) helper.getView(R.id.iv));

//            }
//        }
        if (item.isDirectory()) {
            helper.setText(R.id.tv_title, item.getName() + "(" + item.list().length + ")");
        } else {
            helper.setText(R.id.tv_title, item.getName());
        }
        helper.setVisible(R.id.cb_select, showCheckBox);
        if (showCheckBox) {
            helper.setChecked(R.id.cb_select, checkedItemIndex.keySet().contains(item.getAbsolutePath()));
        }
        if (cut_paths.keySet().contains(item.getAbsolutePath())) {
            helper.setAlpha(R.id.fl_root, 0.5f);
        } else {
            helper.setAlpha(R.id.fl_root, 1f);
        }
        if (!StringUtils.isEmpty(mFailter)) {
            if (failterMode == 1) {
                int count = 0;
                Pattern p = Pattern.compile(item.getName());
                Matcher m = p.matcher(mFailter);

                if (m.find()) {
                    helper.setAlpha(R.id.fl_root, 1f);
                } else {
                    helper.setAlpha(R.id.fl_root, 0.3f);
                }
            } else if (failterMode == 0) {
                if (item.getName().toLowerCase().contains(mFailter)) {
                    helper.setAlpha(R.id.fl_root, 1f);
                } else {
                    helper.setAlpha(R.id.fl_root, 0.3f);
                }
            }
        }


//        helper.addOnClickListener(R.id.iv);

    }

    public void select(int position) {
        checkedItemIndex.put(getItem(position).getAbsolutePath(), System.currentTimeMillis());
        if (showCheckBox) {
            notifyItemChanged(position);
        } else {
            showCheckBox = !showCheckBox;
            notifyDataSetChanged();
        }
    }

    public void switchItem(int position) {
        String path = getItem(position).getAbsolutePath();
        if (checkedItemIndex.keySet().contains(path)) {
            checkedItemIndex.remove(path);
        } else {
            checkedItemIndex.put(path, System.currentTimeMillis());
        }

        notifyItemChanged(position);

        if (selected != null) {
            if (checkedItemIndex.size() > 0) {
                if (checkedItemIndex.size() == 1) selected.onItemSelected();
            } else {
                selected.onNothingSelected();
            }
        }

    }


    public LinkedList<Object> path_histroy = new LinkedList<>();

    public boolean back() {
        showCheckBox = false;
        checkedItemIndex.clear();

        if (path_histroy != null && path_histroy.size() > 0) {
            path_histroy.pop();
            setDatas(null, path_histroy.getFirst());
            paths.remove(paths.size() - 1);
            pathRecycAdapter.notifyDataSetChanged();
        }
        return path_histroy.size() > 1;
    }

    public void setSelectedCallback(HasItemSelectedCallback selected) {
        this.selected = selected;
    }

    private HasItemSelectedCallback selected;

    public void unSelectAll() {
        checkedItemIndex.clear();
        notifyDataSetChanged();
    }

    int type = 2;

    public void setLayoutType(int type) {
        this.type = type;
        switch (type) {
            case 0:
                mLayoutResId = R.layout.item_gallery_headline;
                getRecyclerView().setLayoutManager(new LinearLayoutManager(mContext));
//                GalleryContentAdapter.this=new GalleryContentAdapter(cut_paths,copy_paths);

                break;
            case 1:
                mLayoutResId = R.layout.item_gallery_list;
                getRecyclerView().setLayoutManager(new LinearLayoutManager(mContext));
                break;
            case 2:
                mLayoutResId = R.layout.item_gallery;
                getRecyclerView().setLayoutManager(new GridLayoutManager(mContext, 3));
                break;
        }
        notifyDataSetChanged();
//        notifyAll();
//        notify();
    }

    public String getCurrentPath() {
        return currentPath;
    }


    public void setFailter(String failter, int failterMode) {
        mFailter = failter;
        this.failterMode = failterMode;
        notifyDataSetChanged();

    }

    //
    int sortType, sortOrdition;

    @TargetApi(Build.VERSION_CODES.N)
    public void sort(int sortType, int sortOrdition) {
        this.sortType = sortType;
        this.sortOrdition = sortOrdition;
        getData().sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (sortType == 0) {
//                    return o1.getName()-o2.getName();
                    return 1;
                } else if (sortType == 1) {

                    return -1;
                } else {
                    if (sortOrdition == 0) {
                        return o1.lastModified() > o2.lastModified() ? -1 : 1;
                    } else {
                        return o1.lastModified() > o2.lastModified() ? 1 : -1;
                    }

                }

            }
        });
    }

    public boolean hasDirPath() {
        return false;

    }


    public interface HasItemSelectedCallback {
        void onItemSelected();

        void onNothingSelected();
    }


    private static List<File> getFiles(String paths) {
        List<File> files = new ArrayList<>();
        if (paths.contains(";")) {
            currentPath = "";
            String[] path = paths.split(";");
            for (String item : path) {
                File f = new File(item);
                if (f.exists()) {
                    files.add(f);
                }

            }
        } else {
            files.add(new File(paths));
        }
        return files;
    }
}
