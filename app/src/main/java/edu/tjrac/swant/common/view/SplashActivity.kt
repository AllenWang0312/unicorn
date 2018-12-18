package edu.tjrac.swant.common.view

import android.os.Bundle
import com.yckj.baselib.common.base.BaseActivity
import edu.tjrac.swant.unicorn.R
import java.util.*

class SplashActivity : BaseActivity() {

    //    @BindView(R.id.iv_adv) ImageView iv_adv;
    //    @BindView(R.id.tv_skip) TextView tv_skip;
    //    @BindView(R.id.animation_view) LottieAnimationView animation_view;
    internal var seconds = 5
    internal var mTimer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


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
        //                tv_skip.post(
        //                        new Runnable() {
        //                            @Override
        //                            public void run() {
        //                                tv_skip.setText("跳过" + "(" + seconds + ")");
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
