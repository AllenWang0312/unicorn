package edu.tjrac.swant.unicorn.view

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View

import com.airbnb.lottie.ImageAssetDelegate
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieImageAsset
import com.airbnb.lottie.OnCompositionLoadedListener
import com.yckj.baselib.common.base.BaseActivity

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_lottie_viewer.*

class LottieViewerActivity : BaseActivity() {
    internal var url: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_viewer)
        url = intent.getStringExtra("url")

        val file = File(url)


        val name = file.absolutePath.replace(".json", "")
        val fileRes = File(file.parent, name)
        //int width,height;
        try {
            val inputStream = FileInputStream(file)
            LottieComposition.Factory.fromInputStream(inputStream) { composition ->
                animation_view.setComposition(composition!!)

                //                    animation_view.setImageAssetsFolder(name);
                animation_view.setImageAssetDelegate(ImageAssetDelegate { asset ->
                    val opts = BitmapFactory.Options()
                    opts.inScaled = true
                    opts.inDensity = 160
                    BitmapFactory.decodeFile(name + File.separator + asset.fileName, opts)
                })
                //animation_view.setjso
                iv_reload.setClickable(true)
                iv_reload.setOnClickListener(View.OnClickListener { animation_view.playAnimation() })
                //                    animation_view.playAnimation();
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

    }

    companion object {

        var res_type = ".json"

        fun start(context: Context, url: String) {
            context.startActivity(Intent(context, LottieViewerActivity::class.java)
                    .putExtra("url", url))
        }
    }
}
