//------------------------------------------------------------------------------
// KITASOFT API
// FileUtil.java
//
// Copyright(c) 2005-2009 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/12/24	IPPEI.KITAJIMA	新規作成
// ver 1.1  2008/02/21  IPPEI.KITAJIMA  以下の2メソッドを追加
//                       loadTextFile(String), writeTextFile(String, ArrayList)
//------------------------------------------------------------------------------
package com.kobunhada.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.kobunhada.common.io.FileManager;

/**
 * ファイルユーティリティクラス.
 *
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class FileUtil {

	/** エンコードタイプ(SHIFT_JIS). */
	public static final String ENCODETYPE_SHIFT_JIS = "Shift_JIS";
	/** エンコードタイプ(EUC_JP). */
	public static final String ENCODETYPE_EUC_JP = "EUC_JP";
	/** エンコードタイプ(UTF_8). */
	public static final String ENCODETYPE_UTF8 = "UTF-8";

	/** エンコードタイプ(OSのデフォルトエンコードタイプ). */
	private static String defaultEncodetype = null;


	static {
		// 実行環境でのデフォルトエンコードタイプを取得
		defaultEncodetype = System.getProperty("file.encoding");
	}

	/**
     * プライベートコンストラクタ.
     */
    private FileUtil() {
    }

    /**
     * デフォルトエンコードタイプを取得する.
     *
     * @return デフォルトエンコードタイプ
     */
    public static String getDefaultEncodeType() {
		return defaultEncodetype;
	}

    /**
     * BufferdReaderインスタンスを取得する.
     * <pre>
     * 指定したファイルが存在しない場合はFileNotFoundException例外が発生する。
     * エンコードタイプは、当クラスを実行するOSのデフォルトエンコードタイプを使用する。
     * </pre>
     *
     * @param fileName パス付ファイル名
     * @return BufferdReaderインスタンス
     * @throws UnsupportedEncodingException 無効なエンコードタイプ・・・OSデフォルトのエンコードタイプ使用の為、当メソッドでは発生することは有り得ない。
     * @throws FileNotFoundException ファイル無効時例外
     */
    public static BufferedReader getBufferedReader(String fileName)
    throws UnsupportedEncodingException, FileNotFoundException {
    	return getBufferedReader(fileName, getDefaultEncodeType());
    }

    /**
     * BufferdReaderインスタンスを取得する.
     * <pre>
     * 指定したファイルが存在しない場合は、FileNotFoundException例外が発生する。
     * 指定したエンコードタイプが無効である場合は、UnsupportedEncodingException例外が発生する。
     * </pre>
     * @param fileName パス付ファイル名
     * @param encodeType エンコードタイプ
     * @return BufferdReaderインスタンス
     * @throws UnsupportedEncodingException 無効なエンコードタイプ
     * @throws FileNotFoundException ファイル無効時例外
     */
    public static BufferedReader getBufferedReader(String fileName, String encodeType)
    throws UnsupportedEncodingException, FileNotFoundException {
    	return new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encodeType));
    }

    /**
     * PrintWriterインスタンスを取得する.
     * <pre>
     * 指定したファイルへの書込が不可能である場合は、FileNotFoundException例外が発生する。
     * エンコードタイプは、当クラスを実行するOSのデフォルトエンコードタイプを使用する。
     * </pre>
     * @param fileName パス付ファイル名
     * @return PrintWriterインスタンス
     * @throws UnsupportedEncodingException 無効なエンコードタイプ・・・OSデフォルトのエンコードタイプ使用の為、当メソッドでは発生することは有り得ない。
     * @throws FileNotFoundException ファイル無効時例外
     */
    public static PrintWriter getPrintWriter(String fileName) throws UnsupportedEncodingException, FileNotFoundException {
    	return getPrintWriter(fileName, getDefaultEncodeType());
    }

	/**
     * PrintWriterインスタンスを取得する.
     * <pre>
     * 指定したファイルへの書込が不可能である場合は、FileNotFoundException例外が発生する。
     * 指定したエンコードタイプが無効である場合は、UnsupportedEncodingException例外が発生する。
     * </pre>
     * @param fileName パス付ファイル名
     * @param encodeType エンコードタイプ
     * @return PrintWriterインスタンス
     * @throws UnsupportedEncodingException 無効なエンコードタイプ
     * @throws FileNotFoundException ファイル無効時例外
     */
    public static PrintWriter getPrintWriter(String fileName, String encodeType) throws UnsupportedEncodingException, FileNotFoundException {
    	 return new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), encodeType)));
    }


    /**
     * カレントディレクトリを取得する.
     *
     * @return カレントディレクトリ
     */
    public static String getCurrentPath() {

        File fileCurrent = new File(".");
        String path = fileCurrent.getAbsolutePath();
        return path.substring(0, path.length() -1);
    }

    /**
     * ディレクトリを削除する.
     * <pre>
     * 対象ディレクトリ配下にディレクトリ、ファイルが存在する場合はまとめて削除
     * する。
     * 対象ディレクトリが存在しない場合は何もしない。
     * 対象ディレクトリ内のファイルが他のアプリケーションで使用中の場合は、それ以外
     * を削除する。
     * </pre>
     * @param directory 対象ディレクトリ名
     */
    public static void deleteDirectory(String directory) {
    	deleteDirectory(new File(directory));
    }

    /**
     * ディレクトリを削除する.
     * <pre>
     * 対象ディレクトリ配下にディレクトリ、ファイルが存在する場合はまとめて削除
     * する。
     * 対象ディレクトリが存在しない場合は何もしない。
     * 対象ディレクトリ内のファイルが他のアプリケーションで使用中の場合は、それ以外
     * を削除する。
     * </pre>
     * @param directory 対象ディレクトリ
     */
    public static void deleteDirectory(File directory) {

    	// 直下のファイルディレクトリ一覧を取得
    	File[] files = directory.listFiles();

    	// nullの場合
    	if(files == null) {
    		// 処理を抜ける
    		return;
    	}

    	//
    	for(int i = 0; i < files.length; i++) {

    		// ファイルタイプがディレクトリの場合
    		if(files[i].isDirectory()) {
    			// ディレクトリ削除メソッドを再帰呼出
    			deleteDirectory(files[i]);
    		}
    		// ファイル、または空のディレクトリを削除
    		files[i].delete();
    	}
    	// 対象ディレクトリ自身を削除
    	directory.delete();
    }

    /**
     * テキストファイルへ文字列を出力する.
     *
     * @param fileName パス付ファイル名
     * @param list テキストファイル内容リスト
     * @throws Exception 予期しない例外
     */
    public static void writeTextFile(String fileName, ArrayList<String> list)
    throws Exception {

        // ファイルマネージャ
        FileManager fileManager = new FileManager();

        try {
            //----------------------------------------
            // 初期処理
            //----------------------------------------
            //バッファライタ取得
            BufferedWriter writer = fileManager.getBufferedWriter(fileName);

            for(int i = 0; i < list.size(); i++) {
                String line = (String)list.get(i);
                //バッファに追加
                writer.write(line + StringUtil.getChangingLine());
                //バッファの内容を設定ファイルへ反映
                writer.flush();
            }
        }
        //----------------------------------------
        //例外処理
        //----------------------------------------
        catch(Exception ex) {
            throw ex;
        }
        //----------------------------------------
        //終了処理
        //----------------------------------------
        finally {
            //バッファをクローズする
            fileManager.closeWriter();
        }
    }

    /**
     * テキストファイルの内容を行を要素に格納したArrayListインスタンスを取得する.
     * <pre>
     * 1要素に1行分の文字列が格納される為、大容量ファイルを取得する場合は、メモリ
     * 不足となる危険性がある。
     * その場合は、getBufferdReader()メソッドにて読込む必要がある。
     * </pre>
     * @param fileName パス付ファイル名
     * @return テキストファイル内容リスト
     */
    public static ArrayList<String> loadTextFile(String fileName) {

        ArrayList<String> list = new ArrayList<String>();
        // 入力ファイル取得
        FileManager fileManager = new FileManager();
        // バッファリーダ
        BufferedReader reader = fileManager.getBufferedReader(fileName);

        try {
            while(reader.ready()) {
                //設定ファイルから1行読み込む
                list.add(StringUtil.trim(reader.readLine()));
            }
        }
        //----------------------------------------
        // 例外
        //----------------------------------------
        catch(Exception ex) {
            return null;
        }
        //----------------------------------------
        // 終了処理
        //----------------------------------------
        finally {
             fileManager.closeReader();
        }
        return list;
    }

    /**
     * 指定したパスに存在するファイルの一覧を取得する.
     *
     * @param path パス
     * @return ファイル一覧
     */
    public static ArrayList<File> getFiles(String path) {
        return getFiles(path, "");
    }

    /**
     * 指定パス内のファイルの一覧を取得する.
     *
     * @param path 指定パス
     * @param extention 拡張子(複数の場合はカンマ区切り)
     * @return jarファイル一覧
     */
    public static ArrayList<File> getFiles(String path, String extention) {

        ArrayList<File> list = new ArrayList<File>();

        File dir = new File(path);
        File[] files = dir.listFiles();

        String[] extentions = extention.split(",");

        if(StringUtil.isEmptyString(extention)) {
            for(int i = 0; i < files.length; i++) {
                list.add(files[i]);
            }
        }
        else {
            for(int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                for(int j = 0; j < extentions.length; j++) {
                    if(extentions[j].equals(
                                   fileName.substring(fileName.lastIndexOf(".")))) {
                        list.add(files[i]);
                    }
                }
            }
        }
        return list;
    }
}