package com.test.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewAction()
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
}