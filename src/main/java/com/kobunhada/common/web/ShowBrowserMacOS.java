//------------------------------------------------------------------------------
// KITASOFT API
// ShowBrowserMacOS.java
//
// Copyright(c) 2005-2007 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2007/04/01	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.web;

import java.lang.reflect.Method;

/**
 * MacOS用ブラウザ起動クラス.
 *
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class ShowBrowserMacOS implements ShowBrowser {

    /**
     * コンストラクタ.
     */
    public ShowBrowserMacOS() {
    }

    /**
     * 指定したURLでブラウザを起動する.
     *
     * @param url URL
     * @throws Throwable 予期しない例外
     */
    public void execute(String url) throws Throwable {
        Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
        Method openURL = fileMgr.getDeclaredMethod("openURL",
                                            new Class[] {String.class});
        openURL.invoke(null, new Object[] {url});
    }

	public void setBrowser(String browser) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setDefaultBrowser() {
		// TODO 自動生成されたメソッド・スタブ

	}

}