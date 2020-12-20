//------------------------------------------------------------------------------
// kobunhada's common libs.
// FileManager.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0  2006/06/05  kobunhada  新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.kobunhada.common.debug.SystemOut;


/**
 * テキストファイルIOマネージャクラス.
 * 
 * @author kobunhada
 * @version 1.00
 */
public class FileManager {

    //----------------------------------------
    //クラス定数
    //----------------------------------------
    /**  JISコード（ISO-2022-JP）. */
    public static final String ENCODE_TYPE_ISO_2022_JP = "ISO-2022-JP";
    /**  JISコード（ISO-2022-JP）. */
    public static final String ENCODE_TYPE_ISO2022JP = "ISO2022JP";
    /** SJIS シフトJISコード（Shift_JIS）. */
    public static final String ENCODE_TYPE_SHIFT_JIS ="Shift_JIS";
    /** EUC_JP 日本語EUCコード（EUC-JP）. */
    public static final String ENCODE_TYPE_EUC_JP = "EUC-JP";
    /**  Unicode（UTF-8）. */
    public static final String ENCODE_TYPE_UTF_8 = "UTF-8";
    /**  Unicode（UTF-16）. */
    public static final String ENCODE_TYPE_UTF_16 = "UTF-16";
    /** 文字コードの自動判定（Readerクラスのみ利用可）. */
    public static final String ENCODE_TYPE_JIS_AUTO_DITECT = "JISAutoDetect"; 

    //----------------------------------------
    //クラス変数
    //----------------------------------------
    /** ファイルインプットストリーム. */
    private FileInputStream fis = null;
    /** インプットストリームリーダ. */
    private InputStreamReader isr = null;
    /** バッファリーダ. */
    private BufferedReader br = null;
    /** ファイル. */
    private File file = null;
    /** ファイルアウトプットストリーム. */
    private FileOutputStream fos = null;
    /** アウトプットストリームライタ. */
    private OutputStreamWriter osw = null;
    /** バッファライタ. */
    private BufferedWriter out = null;
    /** デフォルトエンコードタイプ. */
    private String defaultEncodeType = null;
    
    /**
     * デフォルトコンストラクタ.
     */
    public FileManager() {
        //デフォルトエンコードタイプ付コンストラクタ
        this(ENCODE_TYPE_SHIFT_JIS);
    }

    /**
     * デフォルトエンコードタイプ付コンストラクタ.
     * 
     * @param encodeType エンコードタイプ文字列
     */
    public FileManager(String encodeType) {
        defaultEncodeType = encodeType;
    }

    /**
     * BufferdReaderインスタンスを取得する.
     * 
     * @param fileName ファイル名（パス含む）
     * @return バッファリーダ
     */
    public BufferedReader getBufferedReader(String fileName) {
        return getBufferedReader(fileName, defaultEncodeType);
    }
    
    /**
     * BufferdReaderインスタンスを取得する.
     * 
     * @param fileName ファイル名（パス含む）
     * @param encodeType エンコードタイプ
     * @return バッファリーダ
     */
    public BufferedReader getBufferedReader(String fileName, String encodeType)
    {

        try {  
            //FileInputStream生成
            fis = new FileInputStream(fileName);
            //InputStreamReaderを生成
            isr = new InputStreamReader(fis, encodeType);
            //BufferedReaderを生成
            br =  new BufferedReader(isr);
            
        }
        catch ( Exception e ) {
            //ストリームを閉じる
            this.closeReader();
            //エラー出力
            SystemOut.StackTrace(e);
        }

        //戻り値を返す
        return br;

    }
    /**
     * ファイル読込に使用した各オブジェクトをクローズする.
     */
    public void closeReader() {
        try {
            br.close();
            isr.close();
            fis.close();
        }
        catch(Exception ex) {
            SystemOut.StackTrace(ex);
        }

    }
    /**
     * ファイル書込みに使用したオブジェクトをクローズする.
     */
    public void closeWriter() {

        try {
            //ストリームを閉じる
            fos.close();
            osw.close();
            out.close();
        }
        catch(Exception ex) {
            SystemOut.StackTrace(ex);
        }
    }

    /**
     * BufferdWriterインスタンスを取得する.
     * 
     * @param fileName 出力先ファイル名（パス含む）
     * @return BufferdWriterインスタンス
     */
    public BufferedWriter getBufferedWriter(String fileName) { 
        return getBufferedWriter(fileName, defaultEncodeType);
    }


    /**
     * BufferdWriterインスタンスを取得する.
     * 
     * @param fileName 出力先ファイル名（パス含む）
     * @param encodeType エンコードタイプ
     * @return BufferdWriterインスタンス
     */
    public BufferedWriter getBufferedWriter(String fileName, String encodeType)
    {  

        try {  
            //書き込むファイルのインスタンスを生成
            file = new File( fileName );
            //FileOutputStreamを生成
            fos = new FileOutputStream(file);
            //OutputStreamWriterを生成
            osw = new OutputStreamWriter ( fos, encodeType );
            //BufferdWriterを生成
            out = new BufferedWriter( osw );

        }
         catch ( Exception e ) {
            SystemOut.StackTrace(e);
        }
     
        return out;

    }  
    
    /**
     * 指定したファイルを削除する.
     * 
     * @param fileName 削除対象ファイル名(パス付)
     * @return true:成功、false:失敗
     */
    public boolean deleteFile(String fileName) {
        file = new File(fileName);    
        return file.delete();
    }

}