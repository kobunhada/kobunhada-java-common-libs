//------------------------------------------------------------------------------
// KITASOFT API
// DOSCommandUtil.java
//
// Copyright(c) 2005-2007 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2007/04/01	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.kobunhada.common.debug.SystemOut;


/**
 * DOSコマンドユーティリティクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class DOSCommandUtil  {
    
    /**
     * コンストラクタ.
     */
    private DOSCommandUtil() {
    }

    /**
     * DOSコマンドの FTYPE を実行する.<br>
     * ※ファイル拡張子の関連付けで利用するファイル・タイプを表示または変更する
     * コマンドである.
     * 
     * @param ftype ファイルタイプ
     * @return アプリケーションファイル名(絶対パス付)
     */
    public static String execFType(String ftype) {
        // FTYPEコマンドを実行
        String str = execDOSCommand("ftype", ftype);
        //----------------------------------------
        // 実行結果から絶対パス付のアプリケーション名を取得
        //----------------------------------------
        
        //
        int startIndex = str.indexOf("=");
        // ".exe"の出現インデックスを取得
        int endIndex = str.toLowerCase().indexOf(".exe");

        str = StringUtil.replace(str, "\"", "");

        // FTYPEコマンド実行の出力結果から絶対パス付のアプリケーション名を取得        
        str = str.substring(startIndex + 1, endIndex + 4);

        // 戻り値を返す
        return str;
    }
    
    /**
     * DOSコマンドの ASSOC を実行する.<br>
     * ※ファイル拡張子に対する関連付けを表示または変更するコマンドである.
     * 
     * @param extension 拡張子
     * @return ファイルタイプ
     */
    public static String execAssoc(String extension) {
        // ASSOCコマンドを実行
        String str = execDOSCommand("assoc", extension);
        // ASSOCコマンド実行の出力結果からファイルタイプを取得
        str = str.substring(extension.length() + 1);
        // 戻り値を返す
        return str;
    }
    
    /**
     * DOSコマンドを実行する.
     * 
     * @param command DOSコマンド名
     * @param param DOSコマンドに引き渡すパラメタ
     * @return 実行結果出力文字列
     */
    public static String execDOSCommand(String command, String param) {

        // 戻り値を初期化
        String rtn = "";
        
        try {
            // DOSコマンドを実行
            Process process
                = Runtime.getRuntime().exec("cmd.exe /c " + command + " " + param);
            // 実行後の出力結果を取得
            InputStream is = process.getInputStream();
            // バッファリーダ
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // 出力結果格納
            String line = "";
            // 出力結果を1行づつ読込
            while ((line = br.readLine()) != null) {
                rtn += line;
            }
            // バッファリーダを閉じる                        
            br.close();
            // インプットストリームを閉じる
            is.close();
            
        }
        catch (Throwable th) {
            SystemOut.StackTrace(th);
        }
        // 戻り値
        return rtn;
    }


}