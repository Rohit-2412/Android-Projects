package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R.id.rvTodoItems

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val items = mutableListOf<Todo>()
        for (i in 1..9) {
            items.add(Todo("Item$i", false))
        }
        todoAdapter = TodoAdapter(items)
//        todoAdapter = TodoAdapter(mutableListOf())

        findViewById<RecyclerView>(rvTodoItems).adapter = todoAdapter
        findViewById<RecyclerView>(rvTodoItems).layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btnAddTodo).setOnClickListener {
            val todoTitle =
                "⦾ ${findViewById<EditText>(R.id.etTodoTitle).text}"

            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                findViewById<EditText>(R.id.etTodoTitle).text.clear()
            }
        }

        findViewById<Button>(R.id.btnDeleteTodo).setOnClickListener {
            todoAdapter.deleteTodos()
        }

    }
}