package com.example.twossreensofkinopoisk.ui.theme

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.example.twossreensofkinopoisk.R
import com.example.twossreensofkinopoisk.ui.MainVM
import com.example.twossreensofkinopoisk.ui.detailed_information.DetailedInformationFragment
import com.example.twossreensofkinopoisk.ui.list_of_movies.ListOfMoviesFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ListOfMoviesFragment>(R.id.fragmentContainerView, tag = ListOfMoviesFragment::class.java.canonicalName)
            }
        }

        viewModel.selectedItem.observe(this, Observer { item ->
            if(item == null){
                hideFragmentDetailedInformation()
            }else{
                showFragmentDetailedInformation()
            }
        })
    }

    fun showFragmentDetailedInformation(){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<DetailedInformationFragment>(R.id.fragmentContainerView, tag = DetailedInformationFragment::class.java.canonicalName)
        }
    }
    fun hideFragmentDetailedInformation(){
        val fragment = supportFragmentManager.findFragmentByTag(DetailedInformationFragment::class.java.canonicalName)

        fragment?.let{
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                remove(it)
            }
        }
    }
}

