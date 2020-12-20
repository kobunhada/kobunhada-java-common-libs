//------------------------------------------------------------------------------
// kobunhada's common libs.
// SystemUtil.java
//
// Copyright(c) 2005-2007 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2007/04/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.util;

/**
 * システムユーティリティクラス.
 * 
 * @author kobunhada
 * @version 1.00
 */
public class SystemUtil  {
    
    /** OS種別(Windows). */
    public static final String OS_TYPE_WINDOWS = "windows";
    /** OS種別(MacOS). */
    public static final String OS_TYPE_MACOS = "mac";
    /** OS種別(Linux). */
    public static final String OS_TYPE_LINUX = "linux";
    
    /**
     * コンストラクタ.
     */
    private SystemUtil() {
    }
    
    /**
     * 実行環境のOS名称を取得する.
     * 
     * @return OS名称
     */
    public static String getOS() {
        
        // OS名を取得する
        String osName = System.getProperty("os.name");
        
        // Windowsの場合
        if (osName.startsWith("Windows")) {
            return OS_TYPE_WINDOWS;
        }
        // Mac OSの場合
        else if (osName.startsWith("Mac OS")) {
            return OS_TYPE_MACOS;
        }
        // その他(Linux)の場合
        else {
            return OS_TYPE_LINUX;
        }
    }
    
    /**
     * Webブラウザアプリケーションのファイル名を絶対パス付で取得する.
     * 
     * @return Webブラウザアプリケーションのファイル名
     */
    public static String getWebBrowserApplicationPath() {
        return getApplicationPath(".html");
    }
    
    /**
     * 拡張子と関連付されているアプリケーションのファイル名を絶対パス付で取得
     * する.
     * 
     * @param extension 拡張子
     * @return アプリケーションのファイル名
     */
    public static String getApplicationPath(String extension) {
        
        // ASSOCコマンド実行
        String ftype = DOSCommandUtil.execAssoc(extension);
        // FTYPEコマンド実行
        String path = DOSCommandUtil.execFType(ftype);
                
        return path;
    }

}