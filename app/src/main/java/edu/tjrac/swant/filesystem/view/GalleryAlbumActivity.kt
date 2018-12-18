package edu.tjrac.swant.filesystem.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import com.yckj.baselib.common.base.BaseBarActivity
import com.yckj.baselib.util.StringUtils
import com.yckj.baselib.util.T
import edu.tjrac.swant.filesystem.adapter.CheckPhotoPagerAdapter
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_gallery_album.*
import java.io.File
import java.util.*

class GalleryAlbumActivity : BaseBarActivity() {

    private var adapter: CheckPhotoPagerAdapter? = null
    private val list = ArrayList<String>()

    internal var dirPath: String?=null
    internal var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_album)
        setToolbar(findViewById<View>(R.id.toolbar))
        dirPath = intent.getStringExtra("dirPath")
        index = intent.getIntExtra("index", 0)
        Log.i("dirPath", dirPath)
        if (!StringUtils.isEmpty(dirPath)) {
            val file = File(dirPath)
            val childs = file.list()
            for (item in childs) {
                val end = StringUtils.getEndString(item)
                if (!StringUtils.isEmpty(end)) {
                    if (res_type.contains(end)) {
                        list.add(file.absolutePath + "/" + item)
                    }
                }

            }
        }
        adapter = CheckPhotoPagerAdapter(this@GalleryAlbumActivity, list)
        viewpager.setAdapter(adapter)
        viewpager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                tv_position.setText("(" + (position + 1) + "/" + list.size + ")"
                        + "\n"
                        + list[position])
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        viewpager.setCurrentItem(index)
    }

    override fun setToolbar(view: View) {
        super.setToolbar(view)
        enableBackIcon()
        setRightText("删除") {
            AlertDialog.Builder(this@GalleryAlbumActivity)
                    .setTitle("notice").setMessage("make sure to delete the file")
                    .setPositiveButton("yes") { dialog, which ->
                        val position = viewpager.getCurrentItem()
                        list.removeAt(position)
                        adapter!!.remove(position)

                        val delete = File(list[position])
                        if (delete.exists()) {
                            val success = delete.delete()
                            if (success) {


                                T.show(this@GalleryAlbumActivity, "文件删除成功")

                            } else {
                                T.show(this@GalleryAlbumActivity, "文件删除失败")
                            }
                        } else {

                        }
                        adapter!!.notifyDataSetChanged()
                    }.create().show()
        }
        setToolbarBackgroud(resources.getColor(R.color.translate))
    }

    companion object {

        internal val res_type = ".jpeg.jpg.gif.png"

        fun start(context: Context, dirPath: String, position: Int) {
            context.startActivity(Intent(context, GalleryAlbumActivity::class.java)
                    .putExtra("dirPath", dirPath)
                    .putExtra("index", position)
            )
        }
    }
}
