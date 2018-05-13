package edu.tjrac.swant.unicorn.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.yckj.baselib.common.base.BaseActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;

public class LottieViewerActivity extends BaseActivity {

    public static String res_type = ".json";
    @BindView(R.id.animation_view) LottieAnimationView mAnimationView;
    @BindView(R.id.iv_reload) FloatingActionButton fab;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_viewer);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra("url");

        File file = new File(url);

        String name=file.getName().replace(".json","");

//int width,height;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            LottieComposition.Factory.fromInputStream(inputStream,new OnCompositionLoadedListener(){

                @Override
                public void onCompositionLoaded(@Nullable LottieComposition composition) {
                    mAnimationView.setComposition(composition);

                    mAnimationView.setImageAssetsFolder(name);

                    fab.setClickable(true);
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mAnimationView.playAnimation();
                        }
                    });
                    mAnimationView.playAnimation();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void start(Context context, String url) {
        context.startActivity(new Intent(context, LottieViewerActivity.class)
                .putExtra("url", url));
    }
}
