package edu.tjrac.swant.filesystem.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yckj.baselib.common.base.BaseBarActivity;
import com.yckj.baselib.util.StringUtils;
import com.yckj.baselib.util.T;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.filesystem.adapter.CheckPhotoPagerAdapter;
import edu.tjrac.swant.unicorn.R;

public class GalleryAlbumActivity extends BaseBarActivity {

    final static String res_type = ".jpeg.jpg.gif.png";

    @BindView(R.id.viewpager) ViewPager viewpager;
    private CheckPhotoPagerAdapter adapter;
    private ArrayList<String> list = new ArrayList<>();

    @BindView(R.id.tv_position) TextView tvPosition;
    String dirPath;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_album);
        ButterKnife.bind(this);
        bindToolbar(R.id.toolbar);
        setToolbar();
        dirPath = getIntent().getStringExtra("dirPath");
        index = getIntent().getIntExtra("index", 0);
        Log.i("dirPath", dirPath);
        if (!StringUtils.isEmpty(dirPath)) {
            File file = new File(dirPath);
            String[] childs = file.list();
            for (String item : childs) {
                String end=StringUtils.getEndString(item);
                if(!StringUtils.isEmpty(end)){
                    if (res_type.contains(end)) {
                        list.add(file.getAbsolutePath() + "/" + item);
                    }
                }

            }
        }
        adapter = new CheckPhotoPagerAdapter(GalleryAlbumActivity.this, list);
        viewpager.setAdapter(adapter);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvPosition.setText("(" + (position + 1) + "/" + list.size() + ")"
                        +"\n"
                        + list.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewpager.setCurrentItem(index);
    }

    @Override
    public void setToolbar() {
        enableBackIcon();
        setRightText("删除", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new AlertDialog.Builder(GalleryAlbumActivity.this)
                       .setTitle("notice").setMessage("make sure to delete the file")
                       .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               int position = viewpager.getCurrentItem();
                               list.remove(position);
                               adapter.remove(position);

                               File delete = new File( list.get(position));
                               if(delete.exists()){
                                  boolean success= delete.delete();
                                  if(success){


                                      T.show(GalleryAlbumActivity.this,"文件删除成功");

                                  }else{
                                      T.show(GalleryAlbumActivity.this,"文件删除失败");
                                  }
                               }else {

                               }
                               adapter.notifyDataSetChanged();

                           }
                       }).create().show();

            }
        });
        setToolbarBackgroud(getResources().getColor(R.color.translate));
    }

    public static void start(Context context, String dirPath, int position) {
        context.startActivity(new Intent(context, GalleryAlbumActivity.class)
                .putExtra("dirPath", dirPath)
                .putExtra("index", position)
        );
    }
}
