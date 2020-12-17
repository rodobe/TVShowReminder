package com.tooldev.test.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.tooldev.test.R
import com.tooldev.test.databinding.FragmentWebviewArticlesBinding
import com.tooldev.test.view.activities.ArticlesHomeActivity
import com.tooldev.test.view.viewModels.WebViewArticlesViewModel

class WebViewArticlesFragment: Fragment() {

    companion object{
        fun getInstance(url: String): WebViewArticlesFragment{
            val args = Bundle()
            args.putString("url", url)
            val webViewArticlesFragment = WebViewArticlesFragment()
            webViewArticlesFragment.arguments = args
            return webViewArticlesFragment
        }
    }

    private val viewModel: WebViewArticlesViewModel by viewModels()
    private lateinit var binding: FragmentWebviewArticlesBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewArticlesBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding. lifecycleOwner= viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        setAppBar()
        setWebViewSettings()
        loadUrl()
        setWebViewClient()
    }

    private fun setAppBar(){
        (activity as ArticlesHomeActivity).setSupportActionBar(binding.toolbarArticlesWeb)
        (activity as ArticlesHomeActivity).actionBar?.setDisplayShowTitleEnabled(false)
        (activity as ArticlesHomeActivity).actionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as ArticlesHomeActivity).actionBar?.title = null
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebViewSettings(){
        val settings = binding.webviewArticle.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.allowContentAccess  = true
    }

    private fun loadUrl(){
        binding.webviewArticle.loadUrl(getUrl()!!)
    }

    private fun setWebViewClient(){
        binding.webviewArticle.webViewClient = object: WebViewClient(){

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.webviewArticle.visibility = View.VISIBLE
                binding.progressbarWebviewarticles.visibility = View.GONE
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                Toast.makeText(context, "Ocurrió un error", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun getUrl(): String? {
        if (this.arguments != null){
            return this.arguments?.getString("url", "")
        } else{
            return ""
        }
    }



}