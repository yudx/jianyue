package com.yiming.jianyue.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.yiming.jianyue.R;
import com.yiming.jianyue.ui.widget.GapClient;

public class ScrollingActivity extends AppCompatActivity {
    private WebView webView;
    private String url = "";
    private CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        String imgUrl = getIntent().getStringExtra("imgUrl");
        setContentView(R.layout.activity_scrolling);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarLayout.setTitle(getIntent().getStringExtra("title"));
        initWebView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        int mScreenHeight = dm.heightPixels;

        Glide.with(getApplicationContext())
                .load(imgUrl)
                .asBitmap()
                .centerCrop()
                .into(new SimpleTarget<Bitmap>(mScreenWidth, mScreenHeight) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Palette.generateAsync(resource, new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                Palette.Swatch vibrant = palette.getVibrantSwatch();
                                 /* 界面颜色UI统一性处理,看起来更Material一些 */
                                toolbarLayout.setBackgroundColor(vibrant != null ? vibrant.getRgb() : Color.WHITE);
                                toolbar.setBackgroundColor(vibrant != null ? vibrant.getRgb() : Color.WHITE);
                                toolbarLayout.setCollapsedTitleTextColor(vibrant != null ? vibrant.getTitleTextColor() : Color.WHITE);
                                // 其中状态栏、游标、底部导航栏的颜色需要加深一下，也可以不加，具体情况在代码之后说明
                                toolbarLayout.setContentScrimColor(colorBurn(vibrant != null ? vibrant.getRgb() : Color.WHITE));
                                if (android.os.Build.VERSION.SDK_INT >= 21) {
                                    Window window = getWindow();
                                    // 很明显，这两货是新API才有的。
                                    window.setStatusBarColor(colorBurn(vibrant != null ? vibrant.getRgb() : Color.TRANSPARENT));
                                    window.setNavigationBarColor(colorBurn(vibrant != null ? vibrant.getRgb() : Color.TRANSPARENT));
                                }
                            }
                        });
                        imageView.setImageBitmap(resource); // Possibly runOnUiThread()
                    }
                });
    }


    /**
     * 设置webview
     */
    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    private void initWebView() {
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebChromeClient(new GapClient(this) {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                progressBar.setProgress(newProgress);
//                if (newProgress >= 100) {
//                    progressBar.setVisibility(View.GONE);
//                } else {
//                    progressBar.setVisibility(View.VISIBLE);
//                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                // 设置当前activity的标题栏
                String displayTitle = title;
                if (displayTitle != null) {
                    if (displayTitle.length() > 15) {
                        displayTitle = displayTitle.substring(0, 15);
                    }
//                    t.setText(fixString(displayTitle, 10, true));
                    toolbarLayout.setTitle(fixString(displayTitle, 10, true));
                }
                super.onReceivedTitle(view, title);
            }
        });

        webView.setDownloadListener(new MyWebViewDownLoadListener());
        // GapViewClient viewClient = new GapViewClient(this);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
//				super.onReceivedSslError(view, handler, error);
//				handler.cancel(); // Android默认的处理方式
                handler.proceed();  // 接受所有网站的证书
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String Url) {
//				if (Url.startsWith("sms:")) {
//					// processSMS(Url);
//				} else {
//					view.loadUrl(Url);
//				}
                view.loadUrl(Url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String Url) {
                super.onPageFinished(view, Url);
//                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view,
                                      String url, android.graphics.Bitmap favicon) {
//                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
        });

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.requestFocusFromTouch();
        // appView.enablePlatformNotifications();

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webView.loadUrl(url);
    }

    public String fixString(String src, int len, boolean isAddDel) {
        if (src.length() <= len)
            return src;
        return src.substring(0, len) + (isAddDel ? "..." : "");
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent,
                                    String contentDisposition, String mimetype, long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            ScrollingActivity.this.startActivity(intent);

        }

    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *                  Android中我们一般使用它的16进制，
     *                  例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *                  red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *                  所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
