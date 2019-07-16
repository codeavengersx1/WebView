package com.codeavengers.webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener
{
    @BindView(R.id.webview_activity_main)
    WebView webView;

    @BindView(R.id.toolbar_activity_main)
    Toolbar toolbar;

    @BindView(R.id.swipe_refresh_activity_main)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Attach ButterKnife to this Activity
        * */
        ButterKnife.bind(this);

        /*
        * Tell this Activity that "toolbar" is your ActionBar #Janmabhar
        * */
        setSupportActionBar(toolbar);

        /*
         * This is how to listen to SwipeRefreshLayout
         * */
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE);

        /*
        * This is how you get Default Settings of WebView
        * */
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);

        /*
        * Tell WebView to load a WebPage
        * */
        webView.loadUrl("https://www.google.com/");

        /*
        * If you don't set a WebViewClient to a WebView, it will
        * always open WebPages in your default Web Browser.
        * */
        WebViewClient webViewClient = new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                /*
                 * This is how you find the WebPage Title
                 * */
                String pageTitle = view.getTitle();
                toolbar.setTitle(pageTitle);

                /*
                * Trigger SwipeRefreshLayout
                * */
                swipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                String pageTitle = view.getTitle();
                toolbar.setTitle(pageTitle);
                toolbar.setSubtitle(url);

                /*
                * Hide SwipeRefreshLayout
                * */
                swipeRefreshLayout.setRefreshing(false);
            }
        };

        webView.setWebViewClient(webViewClient);
    }

    @Override
    public void onBackPressed()
    {
        if (webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh()
    {
        webView.reload();
    }
}
