package edu.tjrac.swant.douban.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import edu.tjrac.swant.unicorn.R;

public class CoordinglayoutTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinglayout_test);
    }

    public static void start(FragmentActivity activity) {
        activity.startActivity(new Intent(activity, CoordinglayoutTestActivity.class));
    }
}
