package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.board.BoardFragment
import com.example.myapplication.list.ListFragment
import com.example.myapplication.login.LoginDiaglogFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var listFragment : ListFragment
    lateinit var boardFragment : BoardFragment

    lateinit var loginDialogFragment : LoginDiaglogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.supportActionBar?.hide()

        listFragment = ListFragment()
        boardFragment = BoardFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, listFragment)
            .commit()

        System.out.println("Fragment Committed")

        val bottomNavView : BottomNavigationView = findViewById(R.id.bottom_navView)

        bottomNavView.run{
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.list -> {
                        println("LIST FRAG SELECTED")
                        selectFragment(listFragment)
                    }
                    R.id.board -> {
                        println("Board FRAG SELECTED")
                        selectFragment(boardFragment)
                    }
                    R.id.login -> {
                        println("Board FRAG SELECTED")
                        loginDialogFragment = LoginDiaglogFragment()
                        loginDialogFragment.show(supportFragmentManager, "dialog_event")

                    }
                }
                true
            }
        }
    }
    fun selectFragment(frag : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, frag).commit()
    }
}