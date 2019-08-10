package com.mojang.minecraftpe.codeBuilder;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.mojang.minecraftpe.MainActivity;

class CodeBuilderChromeClient extends WebChromeClient {
    
    public CodeBuilderView mView;
    public CodeBuilderChromeClient(CodeBuilderView view) {
        mView = view;
    }

    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        MainActivity.mInstance.runOnUiThread(new Runnable() {
            public void run() {
                mView._injectApi();
            }
        });
    }
}
