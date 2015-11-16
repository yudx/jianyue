// Copyright 2015 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.yiming.jianyue.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yiming.jianyue.R;


/**
 * This Activity is used as a fallback when there is no browser installed that supports
 * Chrome Custom Tabs
 */
public class WebviewActivity extends AppCompatActivity {
    private static final String TAG = "WebviewActivity";
    public static final String EXTRA_URL = "url";
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        
        
        appBarLayout = (AppBarLayout) findViewById(R.id.view_appbar);
        String url = getIntent().getStringExtra(EXTRA_URL);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(false);
        setTitle(url);
        webView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        CookieSyncManager.getInstance().stopSync();
        super.onResume();
    }

    @Override
    protected void onPause() {
        CookieSyncManager.getInstance().sync();
        super.onPause();
    }
    
}
