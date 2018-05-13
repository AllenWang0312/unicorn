package edu.tjrac.swant.common.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.yckj.baselib.common.base.BaseActivity;

import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_adv) ImageView mIvAdv;
    @BindView(R.id.tv_skip) TextView mTvSkip;
    @BindView(R.id.animation_view) LottieAnimationView lottie;
    int seconds = 5;
    Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);


//        lottie.playAnimation();
//
//        LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.animation_view);
//        animationView.setAnimation("helloworld.json");
//        animationView.loop(true);
//        animationView.playAnimation();

//        mTimer = new Timer();
//        JSONObject json = new JSONObject(response.body().string());
//        LottieComposition.fromJson(getResources(), json, new LottieComposition.OnCompositionLoadedListener() {
//            @Override
//            public void onCompositionLoaded(LottieComposition composition) {
//                setComposition(composition);
//            }
//        });

//        mTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                mTvSkip.post(
//                        new Runnable() {
//                            @Override
//                            public void run() {
//                                mTvSkip.setText("跳过" + "(" + seconds + ")");
//                            }
//                        }
//                );
//                seconds--;
//                if (seconds == 0) {
//                    finish();
//                    ZhihuActivity.start(mContext);
//                }
//            }
//        }, 1000, 1000);

    }
}
