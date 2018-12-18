package edu.tjrac.swant.filesystem.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import com.yckj.baselib.util.StringUtils
import com.yckj.baselib.util.T
import edu.tjrac.swant.filesystem.MediaUtil
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_select_dir.*

class SelectDirActivity : AppCompatActivity() {

    internal var paths: List<String>?=null
    internal var sp: SharedPreferences?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_dir)

        sp = getSharedPreferences("settings", Context.MODE_PRIVATE)

        media_type.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> paths = MediaUtil.getMediaDirs(this@SelectDirActivity, MediaUtil.MediaType.image)
                    1 -> paths = MediaUtil.getMediaDirs(this@SelectDirActivity, MediaUtil.MediaType.video)
                    2 -> paths = MediaUtil.getMediaDirs(this@SelectDirActivity, MediaUtil.MediaType.music)
                }
                sp_paths.setAdapter(PathsAdapter())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

        sp_paths.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                et_selector.setText(paths!![position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

        val key = intent.getStringExtra("key")

        sure.setOnClickListener(View.OnClickListener {
            val et = et_selector.getText().toString()
            if (!StringUtils.isEmpty(et)) {
                if (sp!!.edit().putString(key, et).commit()) {
                    T.show(this@SelectDirActivity, "设置成功!")
                    finish()
                }
            }
        })

    }

    internal inner class PathsAdapter : BaseAdapter() {

        override fun getCount(): Int {
            return paths!!.size
        }

        override fun getItem(position: Int): String {
            return paths!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
            val text = TextView(this@SelectDirActivity)
            text.text = paths!![position]
            return text
        }
    }
}
