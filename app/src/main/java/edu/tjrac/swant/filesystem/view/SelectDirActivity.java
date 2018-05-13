package edu.tjrac.swant.filesystem.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.yckj.baselib.util.StringUtils;
import com.yckj.baselib.util.T;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.filesystem.MediaUtil;

public class SelectDirActivity extends AppCompatActivity {


    @BindView(R.id.media_type) Spinner mMediaType;
    @BindView(R.id.sp_paths) Spinner mSpPaths;

    List<String> paths;

    @BindView(R.id.bt_selecter) Button mBtSelecter;
    @BindView(R.id.et_selector) EditText mEtSelector;
    @BindView(R.id.sure) Button mSure;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dir);

        sp =  getSharedPreferences("settings",MODE_PRIVATE);

        ButterKnife.bind(this);
        mMediaType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        paths = MediaUtil.getMediaDirs(SelectDirActivity.this, MediaUtil.MediaType.image);
                        break;
                    case 1:
                        paths = MediaUtil.getMediaDirs(SelectDirActivity.this, MediaUtil.MediaType.video);
                        break;
                    case 2:
                        paths = MediaUtil.getMediaDirs(SelectDirActivity.this, MediaUtil.MediaType.music);
                        break;
                }
                mSpPaths.setAdapter(new PathsAdapter());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpPaths.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mEtSelector.setText(paths.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String key = getIntent().getStringExtra("key");

        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et=mEtSelector.getText().toString();
                if(!StringUtils.isEmpty(et)){
                   if( sp.edit().putString(key,et).commit()){
                       T.show(SelectDirActivity.this,"设置成功!");
                       finish();
                   }
                }
            }
        });

    }

    class PathsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return paths.size();
        }

        @Override
        public String getItem(int position) {
            return paths.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView text = new TextView(SelectDirActivity.this);
            text.setText(paths.get(position));
            return text;
        }
    }
}
