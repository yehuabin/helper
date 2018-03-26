package com.yhb.taobaohelper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.yhb.taobaohelper.model.LogModel;
import com.yhb.taobaohelper.utils.BmobUtil;

public class CookieActivity extends BaseActivity {
    WebView webView;
    private static final String TAG = "CookieActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cookie;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                String cookies = CookieManager.getInstance().getCookie(url);
                Log.d(TAG, "1onPageFinished: " + cookies);
                Log.d(TAG, "2onPageFinished: " + url);
                super.onPageFinished(view, url);
            }
        });
       // CookieManager.getInstance().removeAllCookie();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.3427.400 QQBrowser/9.6.12513.400");
        webView.loadUrl("https://login.taobao.com/member/login.jhtml?style=mini&newMini2=true&css_style=alimama&from=alimama&redirectURL=http://pub.alimama.com/common/code/getAuctionCode.json?auctionid=556095016857&adzoneid=148716480&siteid=39748344&scenes=1&full_redirect=true&disableQuickLogin=true");
        Button btn_login=mViewHolder.get(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CookieManager.getInstance().removeAllCookie();
                // webView.loadUrl("https://login.taobao.com/member/login.jhtml?style=mini&newMini2=true&css_style=alimama&from=alimama&redirectURL=http://pub.alimama.com/common/code/getAuctionCode.json?auctionid=556095016857&adzoneid=148716480&siteid=39748344&scenes=1&full_redirect=true&disableQuickLogin=true");
            }
        });
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://pub.alimama.com/promo/search/index.htm?q=https://item.taobao.com/item.htm?id=562143515994");
            }
        });

        Button btn_setting = (Button) findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cookie = getCookie("http://pub.alimama.com", "cookie2");
                if (cookie==null||cookie.length()==0){
                    toastLong("cookie为空");
                    return;
                }
                String _tb_token_ = getCookie("http://pub.alimama.com", "_tb_token_");
                if (_tb_token_==null||_tb_token_.length()==0){
                    toastLong("_tb_token_为空");
                    return;
                }
                CookieManager cookieManager = CookieManager.getInstance();
                String cookies = cookieManager.getCookie("http://pub.alimama.com");
                TokenHelper.saveCookie(cookie,_tb_token_,cookies);
                LogModel logModel=new LogModel();
                logModel.setCreator("admin");
                logModel.setModule("cookie");
                logModel.setAction("更新cookie");
                logModel.setRemark("cookie:"+cookie);
                BmobUtil.saveLog(logModel);
                finish();
            }
        });
    }




    public String getCookie(String siteName, String CookieName) {
        String CookieValue = null;

        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(siteName);
        if (cookies != null) {
            String[] temp = cookies.split(";");
            for (String ar1 : temp) {
                if (ar1.contains(CookieName)) {
                    String[] temp1 = ar1.split("=");
                    CookieValue = temp1[1];
                }
            }
        }
        return CookieValue;
    }
}
