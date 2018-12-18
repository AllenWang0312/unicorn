package edu.tjrac.swant.filesystem.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.yckj.baselib.util.L
import com.yckj.baselib.util.StringUtils
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_image_editor.*

class ImageEditorActivity : AppCompatActivity() {

    internal var TAG = "ImageEditorActivity"

    //    @BindView(R.id.iv_iv_object) ImageView iv_object;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_editor)

        val url = intent.getStringExtra("url")
        L.i("imageEditor", url)


        if (StringUtils.isEmpty(url)) {

        } else {
            L.i(TAG, url)
            Glide.with(this@ImageEditorActivity).load(url).into(iv_object)
        }
    }
}
