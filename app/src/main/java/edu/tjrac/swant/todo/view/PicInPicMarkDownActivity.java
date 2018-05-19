package edu.tjrac.swant.todo.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.tjrac.swant.unicorn.R;

@TargetApi(Build.VERSION_CODES.O)
public class PicInPicMarkDownActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_in_pic_mark_down);
//        enterPictureInPictureMode();

    }
}
