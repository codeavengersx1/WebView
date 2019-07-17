package com.codeavengers.webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener
{
    /*
    * TODO: Task
    *
    * 1. Add a menu item "Find in Page"
    * 2. Whenever user clicks on "Find in Page", an EditText should appear.
    * 3. Get the Text of EditText and pass it to...
    * 4. ...to webView.findAllAsync(...);
    * 5. Set a Find Listener.
    * */

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

        // webView.findAllAsync("string");
        /*webView.setFindListener(new WebView.FindListener()
        {
            @Override
            public void onFindResultReceived(int i, int i1, boolean b)
            {

            }
        });*/

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        /*
         * You have to write this code this way only #Janmabhar
         * */
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_on_activity_with_menu,menu);

        return true;
    }

    /*
     * This method will be used to set Click Listeners on MenuItems
     *
     * "MenuItem" means the item on which user has clicked
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int menuItemId = item.getItemId();

        if (menuItemId == R.id.menu_load_desktop_site)
        {
            /*
            * Code to Load WebPage as a Desktop Site
            * */
            final String desktopUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";
            final String mobileUserAgent = "Mozilla/5.0 (Linux; Android 9; ONEPLUS A6000) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.62 Mobile Safari/537.36";

            WebSettings defaultSettings = webView.getSettings();
            String userAgentSetWebView = defaultSettings.getUserAgentString();
            if (userAgentSetWebView.equals(desktopUserAgent))
            {
                /*
                * This means WebView is on Desktop Mode
                * */
                defaultSettings.setUserAgentString(mobileUserAgent);

                /*
                * Change the MenuItem Title
                * */
                item.setTitle("Load Desktop Site");
            }
            else
            {
                /*
                * This means WebView is on Mobile Mode
                * */
                defaultSettings.setUserAgentString(desktopUserAgent);

                /*
                * There is a bug. WebView should zoom to Birds Eye View.
                * */
                defaultSettings.setUseWideViewPort(true);
                webView.setInitialScale(1);

                /*
                 * Change the MenuItem Title
                 * */
                item.setTitle("Load Mobile Site");
            }

            /*
            * Reload WebPage
            * */
            webView.reload();

            return true;
        }
        else if (menuItemId == R.id.menu_exit_app)
        {
            Toast.makeText(this, "User Clicked on Exit App", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}
