package edu.tjrac.swant.todo.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.todo.bean.Todo;
import edu.tjrac.swant.unicorn.R;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/13 0013 下午 5:13
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class TodoListAdapter extends BaseQuickAdapter<Todo, BaseViewHolder> {
    public TodoListAdapter(@Nullable List<Todo> data) {
        super(R.layout.item_todo_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Todo item) {
        TextView title = helper.getView(R.id.tv_title);
        CheckBox explan=helper.getView(R.id.cb_explan);
            explan.setChecked(item.expand);
        if (item.isFinished()) {
            title.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            title.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
        }
        helper.addOnClickListener(R.id.cb_done);
        helper.setChecked(R.id.cb_done, item.isFinished());
        helper.setText(R.id.tv_title, item.getTitle());
        TextView lv = helper.getView(R.id.tv_lv);
        lv.setLayoutParams(new LinearLayout.LayoutParams(100 + item.lv() * 100, ViewGroup.LayoutParams.WRAP_CONTENT));
        helper.addOnClickListener(R.id.options_three);


    }
}
