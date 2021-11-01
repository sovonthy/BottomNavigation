package com.test.bottomnavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.test.bottomnavigation.model.Todo
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNavigation()
        initNavigationDrawer()
        getTodoList()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_bottom, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.home -> {
                val toast = Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT)
                toast.show()
                true
            }
            R.id.search -> {
                val toast = Toast.makeText(applicationContext, "Search", Toast.LENGTH_SHORT)
                toast.show()
                true
            }
            R.id.setting -> {
                val toast = Toast.makeText(applicationContext, "Setting", Toast.LENGTH_SHORT)
                toast.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initBottomNavigation(){
        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener() {
            when(it.itemId){
                R.id.home-> {
                    Toast.makeText(this,"Home", Toast.LENGTH_SHORT).show()
                }
                R.id.search-> {
                    Toast.makeText(this,"Search",Toast.LENGTH_SHORT).show()
                }
                R.id.setting-> {
                    Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show()
                }

            }
            true
        }
    }

    private fun initNavigationDrawer(){

        val actionbar = supportActionBar
        actionbar!!.title = "My Activity"
        actionbar.setDisplayHomeAsUpEnabled(true)

        drawerLayout = findViewById(R.id.drawerLayout)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    val b = true
                }
                R.id.search -> {
                    Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.setting -> {
                    Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
            // Close the drawer
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun getTodoList() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/todos")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val todos = JSONArray(response.body!!.string())
                    val aList = arrayListOf<Todo>()
                    for (i in 0 until todos.length()) {
                        val dataInner: JSONObject = todos.getJSONObject(i)
                        Log.d(">>>>>>",dataInner.getString("id"))
                        aList.add(
                            Todo(
                                dataInner.getString("userId"),
                                dataInner.getString("id"),
                                dataInner.getString("title"),
                                dataInner.getString("completed"),
                            ))
                    }
                    runOnUiThread {
                        val adapter = TodoAdapter(this@MainActivity, aList)
                        val myListView = findViewById<ListView>(R.id.listView)
                        myListView.adapter = adapter

//                        myListView.setOnItemClickListener { _, _, position, _ ->
//                            var intent: Intent = Intent(this@MainActivity, Detail::class.java)
//
//                            // Pass the values to next activity (StationActivity)
//                            intent!!.putExtra("id",aList[position].id)
//                            intent!!.putExtra("title",aList[position].title)
//
//                            startActivity(intent)
//                        }
                    }
                }
            }
        })
    }


}