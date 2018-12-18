package edu.tjrac.swant.todo.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import com.yckj.baselib.common.base.BaseActivity
import com.yckj.baselib.util.StringUtils
import edu.tjrac.swant.todo.adapter.TodoListAdapter
import edu.tjrac.swant.todo.bean.Todo
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_to_do_main.*
import java.util.*

class ToDoMainActivity : BaseActivity(), View.OnClickListener {

    internal var target: Todo? = null
    internal var todolist = ArrayList<Todo>()//树形list
    internal var formatList = ArrayList<Todo>()//排序后

    internal var todoAdapter: TodoListAdapter?=null

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_main)
        //        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //        mDrawerLayout.addDrawerListener(toggle);
        //        toggle.syncState();

        //        mFlCreate.setOnClickListener(this);

        todoAdapter = TodoListAdapter(formatList)
        todoAdapter!!.setOnItemClickListener { adapter, view, position ->
            formatList[position].switchExpend()
            formatList.clear()
            formatList.addAll(formatList(todolist))
            todoAdapter!!.notifyDataSetChanged()
        }
        todoAdapter!!.setOnItemChildClickListener { adapter, view, position ->
            //                if (view.getId() == R.id.options_one) {
            //
            //
            //                }
            //                else
            if (view.id == R.id.options_two) {
                addTag(formatList[position])
            } else if (view.id == R.id.options_three) {
                target = formatList[position]
                addTodo()
            } else if (view.id == R.id.cb_done) {
                formatList[position].switchState()
                todoAdapter!!.notifyItemChanged(position)
            }
        }
        recycle_main.setLayoutManager(LinearLayoutManager(mContext))
        recycle_main.setAdapter(todoAdapter)

        fab_add.setOnClickListener(this)
    }

    internal var icon: ImageView?=null
    internal var title: EditText?=null
    internal var rv_icons: RecyclerView?=null

    override fun onClick(v: View) {
        when (v.id) {
        //            case R.id.fl_create:
        //
        //
        //                break;
            R.id.fab_add -> {
                target = null
                addTodo()
            }
        }
    }

    private var dialog: AlertDialog? = null

    fun addTodo() {
        if (dialog == null) {
            val builder = AlertDialog.Builder(mContext)
            builder.setTitle(mContext.resources.getString(R.string.create_new_list))
            val view = LayoutInflater.from(mContext).inflate(R.layout.dialog_create_new_orderlist, null)
            icon = view.findViewById<ImageView>(R.id.iv_icon)
            title = view.findViewById<EditText>(R.id.et_title)
            rv_icons = view.findViewById<RecyclerView>(R.id.recycle_icon)

            builder.setView(view)
            builder.setPositiveButton("创建") { dialog, which ->
                val str = title!!.text.toString().trim { it <= ' ' }
                if (!StringUtils.isEmpty(str)) {
                    val todo = Todo(str)
                    if (target == null) {
                        todolist.add(todo)
                    } else {
                        target!!.addChild(todo)
                    }
                    formatList.clear()
                    formatList.addAll(formatList(todolist))
                    todoAdapter!!.notifyDataSetChanged()
                }
            }
            dialog = builder.create()
        } else {

        }
        dialog!!.show()
    }

    fun addTag(todo: Todo) {

    }

    fun formatList(list: ArrayList<Todo>): ArrayList<Todo> {
        val formatList = ArrayList<Todo>()
        for (item in list) {
            formatList.addAll(item.format())
        }
        return formatList
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MenuInflater(mContext).inflate(R.menu.options_todos, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, ToDoMainActivity::class.java))
        }
    }
}
