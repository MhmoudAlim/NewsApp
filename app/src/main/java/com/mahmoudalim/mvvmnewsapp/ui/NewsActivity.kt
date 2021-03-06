package com.mahmoudalim.mvvmnewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mahmoudalim.mvvmnewsapp.R
import com.mahmoudalim.mvvmnewsapp.databinding.ActivityNewsBinding
import com.mahmoudalim.mvvmnewsapp.db.ArticleDatabase
import com.mahmoudalim.mvvmnewsapp.repository.NewsRepository
import com.mahmoudalim.mvvmnewsapp.slideDown
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel : NewsViewModel
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NewsApp)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        binding.bottomNavigationView
            .setupWithNavController(newsNavHost.findNavController())

    }
    override fun onPause() {
        super.onPause()
        Toasty.Config.reset();
    }
}
