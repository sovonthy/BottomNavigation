package com.test.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView



class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewAction()
        intNavigationDrawer()

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

    private fun initViewAction(){
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

    private fun intNavigationDrawer(){

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
                    true
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
}