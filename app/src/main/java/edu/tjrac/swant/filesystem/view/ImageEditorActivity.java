package edu.tjrac.swant.filesystem.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yckj.baselib.util.L;
import com.yckj.baselib.util.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;

public class ImageEditorActivity extends AppCompatActivity {

    String TAG = "ImageEditorActivity";

    @BindView(R.id.iv_object) ImageView object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_editor);
        ButterKnife.bind(this);

       String url= getIntent().getStringExtra("url");
       L.i("imageEditor",url);


        if(StringUtils.isEmpty(url)){

        }else {
            L.i(TAG,url);
            Glide.with(ImageEditorActivity.this).load(url).into(object);
        }
    }
}
