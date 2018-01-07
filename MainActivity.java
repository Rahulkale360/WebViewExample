package com.example.rahul.webviewtest;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1=findViewById(R.id.wview);
        Button yt=findViewById(R.id.yt);
        Button fb=findViewById(R.id.fb);
        Button ggl=findViewById(R.id.ggl);
        Button srch=findViewById(R.id.srch);
        Button web=findViewById(R.id.web);
        v1.getSettings().setJavaScriptEnabled(true);
        v1.getSettings().setBuiltInZoomControls(true);
        final ProgressDialog p=new ProgressDialog(this);
        v1.addJavascriptInterface(this,"login_interface");
        v1.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                p.setTitle("Message");
                p.setMessage("Please Wait Webpage is loading");
                p.show();

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                p.dismiss();
            }
        });
    }
    @JavascriptInterface
    public void getData(String Name, String upass)
    {
        Toast.makeText(this,"UserName :"+Name+" PassWord :"+upass,Toast.LENGTH_LONG).show();
    }
    public void load(View v)
    {
        switch (v.getId())
        {
            case R.id.srch :
                EditText e=findViewById(R.id.et1);
                v1.loadUrl(e.getText().toString());
                break;
            case R.id.ggl: v1.loadUrl("http://www.google.com");
                break;
            case R.id.fb: v1.loadUrl("http://www.facebook.com");
                break;
            case R.id.yt: v1.loadUrl("http://www.youtube.com");
                break;
            case R.id.web:
                v1.loadUrl("file:///android_asset/login.html");
                break;


        }
    }
}
