//------------------------------------------------------------------------------
// KITASOFT API
// ShowBrowserWindows.java
//
// Copyright(c) 2005-2007 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2007/04/01	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.web;

import com.kobunhada.common.util.SystemUtil;

/**
 * Windows用ブラウザ起動クラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class ShowBrowserWindows implements ShowBrowser {
    
    private String browser = "";
    
    /**
     * コンストラクタ.
     */
    public ShowBrowserWindows() {
        setDefaultBrowser();
    }
    
    /**
     * デフォルトブラウザアプリケーション実行ファイルを設定する.
     */
    public void setDefaultBrowser() {
        this.browser = SystemUtil.getWebBrowserApplicationPath();
    }
    
    /**
     * ブラウザアプリケーション実行ファイルを設定する.
     * 
     * @param browser ブラウザアプリケーション実行ファイル
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }
    
    /**
     * 指定したURLでブラウザを起動する.
     * 
     * @param url URL
     * @throws Throwable 予期しない例外
     */
    public void execute(String url) throws Throwable {
        
        // Webブラウザを起動する        
        Runtime.getRuntime().exec(browser + " " + url);
    }

}