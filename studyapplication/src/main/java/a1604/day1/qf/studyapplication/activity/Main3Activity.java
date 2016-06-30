package a1604.day1.qf.studyapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import a1604.day1.qf.studyapplication.R;

public class Main3Activity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initView();
        transToHere();
    }
//接收传值，并引导web页面
    private void transToHere() {
        Intent intent = getIntent();
        String path = intent.getStringExtra("url");
        mWebView.loadUrl(path);
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webview);
    }
}
