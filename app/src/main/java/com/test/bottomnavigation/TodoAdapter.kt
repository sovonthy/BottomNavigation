package com.test.bottomnavigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.test.bottomnavigation.model.Todo

class TodoAdapter (context: Context, private val dataSource: ArrayList<Todo>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val todoItem = getItem(position) as Todo
        val rowView = inflater.inflate(R.layout.todo_item, parent, false)

        // Get id
        val todoId = rowView.findViewById(R.id.todoId) as TextView
        todoId.text = todoItem.id

        // Get title
        val title = rowView.findViewById(R.id.todoTitle) as TextView
        title.text = todoItem.title

        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}