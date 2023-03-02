package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    /*private fun replaceFragment(articleListFragment: ArticleListFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager
        val bookFragment: Fragment = BestSellerBooksFragment()
        val articleFragment: Fragment = ArticleListFragment()
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId){
                R.id.bestsellers -> fragment = bookFragment
                R.id.articles -> fragment = articleFragment
            }
            //replaceFragment(fragment)
            fragmentManager.beginTransaction().replace(R.id.article_frame_layout, fragment).commit()
            true
        }
        bottomNavView.selectedItemId = R.id.bestsellers
    }
}