package edu.tjrac.swant.todo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;

public class ToDoViewerActivity extends AppCompatActivity {


    @BindView(R.id.recyler) RecyclerView mRecyler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_viewer);
        ButterKnife.bind(this);

    }
}
