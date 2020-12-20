//------------------------------------------------------------------------------
// kobunhada's common libs.
// ConfigManager.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0  2006/06/05  kobunhada  新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

import com.kobunhada.common.debug.SystemOut;
import com.kobunhada.common.io.FileManager;
import com.kobunhada.common.util.StringUtil;


/**
 * 設定ファイル管理クラス.<p>
 * 1.機能概要<br>
 * 設定ファイルにアクセスし、内容の取得、編集する機能を提供する.<p>
 *
 * 2.実行例<p>
 * 2.1.設定ファイルの内容取得例を以下に示す.<br>
 *
 * <pre>
 * 設定ファイル内容(c:/temp/abc.properties)
 * ----------------------------------------------------------------------------
 * [Category1]
 * property1 = value1
 * property2 = value2
 *
 * [Category2]
 * property3 = value3
 * property4 = value4
 * ----------------------------------------------------------------------------
 *
 * ソース
 * ----------------------------------------------------------------------------
 *        //設定ファイルマネージャ
 *        ConfigManager configManager = new ConfigManager();
 *        //設定ファイルインスタンスを生成する.
 *        Config config = configManager.createConfig("c:/temp/abc.properties");
 *
 *        //----------------------------------------
 *        //ロード内容を標準出力
 *        //----------------------------------------
 *
 *        //設定ファイル存在パスを出力
 *        SystemOut.println("設定ファイルパス:" + config.getPath());
 *        //設定ファイル名を出力
 *        SystemOut.println("設定ファイル名:" + config.getName());
 *        //設定ファイル内の属性カテゴリキーリストを取得
 *        Iterator iteConfig = config.getPropoertiesList().keySet().iterator();
 *
 *        while(iteConfig.hasNext()) {
 *            //属性カテゴリを取得
 *            kitasoft.common.config.Properties properties
 *                                = config.getProperties((String)iteConfig.next());
 *            //属性カテゴリ名を出力
 *            SystemOut.println("属性カテゴリ名:" + properties.getName());
 *            //属性カテゴリ内の属性キーリストを取得
 *            Iterator iteProperties
 *                    = properties.getPropertyList().keySet().iterator();
 *
 *            while(iteProperties.hasNext()){
 *                //属性を取得
 *                kitasoft.common.config.Property property
 *                        = properties.getProperty((String)iteProperties.next());
 *                //属性名、属性値を標準出力
 *                SystemOut.println("属性名:" + property.getName() + " " +
 *                                   "属性値:" + property.getValue());
 *
 *            }
 *
 *        }
 * ----------------------------------------------------------------------------
 *
 * 出力結果
 * ----------------------------------------------------------------------------
 * 設定ファイルパス:c:/temp
 * 設定ファイル名:abc.properties
 * 属性カテゴリ名:Category1
 * 属性名:property2 属性値:value2
 * 属性名:property1 属性値:value1
 * 属性カテゴリ名:Category2
 * 属性名:property4 属性値:value4
 * 属性名:property3 属性値:value3
 * ----------------------------------------------------------------------------
 * </pre>
 *
 * 2.2.設定ファイルの内容編集、反映例を以下に示す.<br>
 * <pre>
 * ソース
 * ----------------------------------------------------------------------------
 *         //設定ファイルインスタンス
 *         Config config = new Config();
 *         //設定ファイルパス
 *         config.setPath("c:/temp");
 *         //設定ファイル名
 *         config.setName("zzz.properties");
 *
 *         //----------------------------------------
 *         //属性カテゴリ1
 *         //----------------------------------------
 *
 *         //属性カテゴリインスタンス
 *         Properties properties1 = new Properties();
 *         //属性カテゴリインスタンス名
 *         properties1.setName("Category1");
 *         //属性インスタンス
 *         Property property1 = new Property();
 *         //属性名
 *         property1.setName("property1");
 *         //属性値
 *         property1.setValue("value1");
 *         //属性インスタンスを属性カテゴリインスタンスへ追加
 *         properties1.setProperty(property1);
 *
 *         //属性カテゴリインスタンスを設定ファイルインスタンスへ追加
 *         try {
 *             config.setProperties(properties1);
 *         } catch(Exception ex){
 *             ex.printStackTrace();
 *         }
 *
 *         //設定ファイル管理
 *         ConfigManager manager = new ConfigManager();
 *         //設定ファイルを書き込み
 *         manager.write(config.getNameWithPath(), config);
 *
 * ----------------------------------------------------------------------------
 *
 * 反映ファイル内容(c:/temp/zzz.properties)
 * ----------------------------------------------------------------------------
 * [Category1]
 * property1=value1
 * ----------------------------------------------------------------------------
 * </pre>
 *
 * @author kobunhada
 * @version 1.00
 */
public class ConfigManager
{

    /** デフォルトファイル名. */
    private static final String DEFAULT_FILENAME = "config.properties";
    /** ファイルマネージャ. */
    private FileManager fileUtil = new FileManager();

    /**
     * デフォルトコンストラクタ.
     */
    public ConfigManager() {
    }

    /**
     * 設定ファイルを読み込み、設定ファイルインスタンスを生成する.<br>
     * 設定ファイルの読込に失敗した場合は nullを返す.<br>
     * カレントディレクトリに存在する config.properties ファイルを読み込む.
     *
     * @return 設定ファイルインスタンス
     */
    public Config createConfigInstance() {
        return createConfigInstance(DEFAULT_FILENAME);
    }

    /**
     * 設定ファイルを読み込み、設定ファイルインスタンスを生成する.<br>
     * 設定ファイルの読込に失敗した場合は nullを返す.
     *
     * @param fileName パス付ファイル名
     * @return 設定ファイルインスタンス
     */
    public Config createConfigInstance(String fileName) {

        //----------------------------------------
        // 設定ファイルインスタンスを生成する.
        //----------------------------------------

        // ¥を/へ変換する
        String replacedFileName = StringUtil.replace(fileName, "¥¥", "/");

        //設定ファイル
        Config config = new Config();
        //設定ファイルを読み込み
        BufferedReader reader =  fileUtil.getBufferedReader(replacedFileName);
        //設定ファイル名を設定
        config.setName(StringUtil.getFileName(replacedFileName));
        //設定ファイル存在パスを設定
        config.setPath(StringUtil.getFilePath(replacedFileName));

        //----------------------------------------
        // 設定ファイルを1行づつ解析し、設定ファイルインスタンスへ反映する
        //----------------------------------------
        try{

            //設定カテゴリ
            Properties properties = null;
            //設定ファイル内容1行分
            String line = "";
            //コメント等を保持するリスト
            ArrayList<String> commentList = new ArrayList<String>();

            //----------------------------------------
            // 1行目〜最終行  解析、反映処理
            //----------------------------------------
            while(reader.ready()) {

                //設定ファイルから1行読み込む
                line = StringUtil.trim(reader.readLine());

                // 読み込んだ行が属性カテゴリの場合
                if(isProperties(line)) {

                    if(properties != null) {
                        //前の属性カテゴリインスタンスを保存する
                        config.setProperties(properties);

                    }
                    //コメントの追加
                    config = setCommentsToConfig(commentList, config);
                    //コメントリストの初期化
                    commentList = new ArrayList<String>();

                    //属性カテゴリインスタンスを生成する
                    properties = createPropertiesInstance(line);

                // 読み込んだ行が属性の場合
                } else if(isProperty(line)) {
                    //コメントの追加
                    properties = setCommentsToProperties(commentList, properties);
                    commentList = new ArrayList<String>();
                    //属性インスタンスを生成する.
                    Property property = createPropertyInstance(line);
                    //属性を属性カテゴリへ設定する
                    properties.setProperty(property);

                // 読み込んだ行が上記以外の場合
                } else {
                    //コメント等を追加
                    commentList.add(line);
                    // 次の行へ
                    continue;
                }

            }

            //コメントの追加
            config = setCommentsToConfig(commentList, config);
            //設定ファイルインスタンスに属性カテゴリを設定
            config.setProperties(properties);

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
        finally{
            try {
                 fileUtil.closeReader();
            } catch(Exception ex2) {
                SystemOut.StackTrace(ex2);
            }
        }

        return config;

    }

    /**
     * 設定ファイルを保存する.<br>
     * 設定ファイルをカレントディレクトリへファイル名 config.properties で保存
     * する.
     *
     * @param config 設定ファイルインスタンス
     */
    public void write(Config config) {
        //デフォルト設定ファイル名を指定して設定ファイルを保存
        write(DEFAULT_FILENAME, config);
    }

    /**
     * 設定ファイルを保存する.<br>
     * 設定ファイルをカレントディレクトリへ保存する.
     *
     * @param fileName パス付設定ファイル名
     * @param config 設定ファイルインスタンス
     */
    public void write(String fileName, Config config) {

        try {
            //----------------------------------------
            // 初期処理
            //----------------------------------------
            //バッファライタ取得
            BufferedWriter writer = fileUtil.getBufferedWriter(fileName);

            //----------------------------------------
            //Configインスタンスの内容を設定ファイルへ書込
            //----------------------------------------
            //Configインスタンスの内容をバッファへ追加
            writeConfig(writer, config);
            //バッファの内容を設定ファイルへ反映
            writer.flush();

        }
        //----------------------------------------
        //例外処理
        //----------------------------------------
        catch(Exception ex) {
            //例外内容を出力
            SystemOut.StackTrace(ex);
        }
        //----------------------------------------
        //終了処理
        //----------------------------------------
        finally {
            //バッファをクローズする
            fileUtil.closeWriter();
        }
    }

    /**
     * 属性カテゴリをバッファへ書き込む.<br>
     *
     * @param writer 書込みバッファ
     * @param config 設定ファイルインスタンス
     * @throws java.io.IOException 入出力例外
     */
	private void writeConfig(BufferedWriter writer, Config config)
    throws IOException {

        //Configインデックスリスト取得
        ArrayList<String> cList = config.getIndexList();

        //Configインデックスリスト要素数分ループ
        for(int i = 0; i < cList.size(); i++) {
            //1行読込
            String cLine = (String)cList.get(i);

            //----------------------------------------
            //属性カテゴリとコメントをバッファに追加
            //----------------------------------------
            Properties prop = writeCategory(writer, config, cLine);
            //取得した行が属性カテゴリではない場合
            if(prop == null) {
                //次の行へ
                continue;
            }

            //----------------------------------------
            //属性値とコメントをバッファに追加
            //----------------------------------------
            writeProperty(writer, prop);
        }
	}

    /**
     * 属性カテゴリとコメントを書込バッファへ追加する.
     *
     * @param writer 書込みバッファ
     * @param config 設定ファイルインスタンス
     * @param cLine 書込対象行
     * @return 属性カテゴリインスタンス
     * @throws java.io.IOException 入出力例外
     */
    private Properties writeCategory(BufferedWriter writer,
                                         Config config,
                                         String cLine)
    throws IOException {

        //属性カテゴリインスタンスを取得
        Properties prop = config.getProperties(cLine);
        //属性カテゴリインスタンスが取得できなかった場合(コメントとみなす)
        if(prop == null) {
            //属性カテゴリのコメントをバッファへ追加
            writeLine(writer, cLine);
            //次の行へ
            return null;
        }

        //属性カテゴリをバッファへ追加
        writeLine(writer, "[" + prop.getName() + "]" );

        return prop;
    }

    /**
     * 属性値とコメントをバッファへ追加する.
     *
     * @param writer 書込バッファ
     * @param prop 属性カテゴリインスタンス
     * @throws java.io.IOException 入出力例外
     */
	private void writeProperty(BufferedWriter writer,
                                 Properties prop)
    throws IOException {

        //Propertiesインデックスリスト
        ArrayList<String> pList = prop.getIndexList();

	    //Propertiesインデックスリスト要素分ループ
	    for(int j = 0; j < pList.size(); j++) {
	        //１行読込
	        String pLine = (String)pList.get(j);
	        //属性インスタンス取得
	        Property property = prop.getProperty(pLine);
	        //属性インスタンスが取得できた場合
	        if(property != null) {
	            //属性をバッファへ追加
	            writeLine(writer,
                    property.getName() + "=" + property.getValue());
	        }
	        //属性インスタンスが取得できなかった場合
	        else {
	            //コメント出力
	            writeLine(writer, pLine);
	        }
	    }
	}

    /**
     * バッファへ1行追加する.
     *
     * @param writer 書込バッファ
     * @param line 追加する文字列
     * @throws java.io.IOException 入出力例外
     */
    private void writeLine(BufferedWriter writer, String line)
    throws IOException {
        //バッファに追加
        writer.write(line);
        //バッファに新しい行を追加
        writer.newLine();
    }

    /**
     * 属性カテゴリインスタンスを生成する.<br>
     * 引数:strより、属性カテゴリ名を抽出し、属性カテゴリインスタンスへ設定する.
     * <br>引数:str は [CategoryName] の形式を想定する.
     *
     * @param str 対象文字列
     * @return 属性カテゴリインスタンス
     */
    private Properties createPropertiesInstance(String str) {
        //Properties生成
        Properties properties = new Properties();
        //属性カテゴリ名を設定する.
        properties.setName(StringUtil.trim(str.substring(1,str.length()-1)));
        //値を返す
        return properties;

    }

    /**
     * 属性インスタンスを生成する.<br>
     * 引数:strより、属性名、属性値を抽出し、属性インスタンスへ設定する.<br>
     * 引数:strは、name = value の形式を想定する.<br>
     *
     * @param str 対象文字列
     * @return 属性インスタンス
     */
    private Property createPropertyInstance(String str) {
            //属性インスタンス
            Property property = new Property();
            // "="の文字列位置を取得
            int equalIndex = str.indexOf("=");
            //nameをセット
            property.setName(str.substring(0,equalIndex).trim());
            //valueをセット
            property.setValue(str.substring(equalIndex + 1).trim());
            //値を返す
            return property;

    }

    /**
     * 文字列が属性カテゴリであるかどうかを判定する.<br>
     * 判定基準は以下の通りとする.<br>
     * 1.文字列の先頭が"["である<br>
     * 2.文字列の最後が"]"である<br>
     * 3."["と"]"の間に全半角スペース以外に1文字以上の文字列が存在する<br>
     * 以上の条件と一致する場合は 属性カテゴリと判断し true を、一致しない場合
     * は falseを返す.
     *
     * @param str 対象文字列
     * @return true:属性である、false:属性ではない
     */
    private boolean isProperties(String str) {

        String category = StringUtil.trim(str);

        if(StringUtil.isEmptyString(category)) {
            return false;
        }

        //----------------------------------------
        // 条件1.文字列の先頭が"["かどうかを判定
        //----------------------------------------

        //文字列の先頭が"["ではない場合 falseを返す
        if(!str.substring(0,1).equals("[")) {
            return false;
        }

        //----------------------------------------
        // 条件2.文字列の最後が"]"かどうかを判定
        //----------------------------------------
        if(!str.substring(str.length()-1,str.length()).equals("]")) {
            return false;
        }

        //----------------------------------------
        // 条件3."["と"]"の間に全半角スペース以外に1文字以上の文字列が存在するかどうかを判定
        //----------------------------------------
        String categoryName = StringUtil.trim(str.substring(1,str.length()-1));

        if(StringUtil.isEmptyString(categoryName)) {
            return false;
        }

        return true;
    }

    /**
     * 文字列が属性であるかどうかを判定する.<br>
     * 判定基準は以下の通りとする.<br>
     * 1.文字列内に "=" が含まれる<br>
     * 2."="の前にスペース以外の文字列が存在する<br>
     * 以上の条件と一致する場合は 属性と判断し true を、一致しない場合は false
     * を返す.
     *
     * @param str 対象文字列
     * @return true:属性である、false:属性ではない
     */
    private boolean isProperty(String str) {

        //----------------------------------------
        // 条件1.文字列内に "=" が含まれるかどうかを判定
        //----------------------------------------

        //"="の出現位置を取得する
        int equalIndex = str.indexOf("=");
        // "="が存在しない場合は false を返す.
        if(equalIndex < 0 ) {
            return false;
        }

        //----------------------------------------
        // 条件2."="の前にスペース以外の文字列が存在するかどうかを判定
        //----------------------------------------

        //nameを取得
        String name = StringUtil.trim(str.substring(0,equalIndex));

        //nameが存在しない場合は falseを返す
        if(StringUtil.isEmptyString(name)) {
            return false;
        }

        return true;

    }

    /**
     * コメントをConfigインスタンスへ追加する.
     *
     * @param comments コメントリスト
     * @param config Configインスタンス
     */
    private Config setCommentsToConfig(ArrayList<String> comments, Config config) {

        for(int i = 0; i < comments.size(); i++) {
            //コメントを1行取得
            String line = (String)comments.get(i);
            //コメントを追加
            config.setNoPropertiesInfo(line);

        }
        return config;
    }

    /**
     * コメントをPropertiesインスタンスへ追加する.
     *
     * @param comments コメントリスト
     * @param properties Propertiesインスタンス
     */
    private Properties setCommentsToProperties(ArrayList<String> comments,
                                                Properties properties) {

        for(int i = 0; i < comments.size(); i++) {
            //コメントを1行取得
            String line = (String)comments.get(i);
            if(line == null) {
                line = "";
            }
            //コメントを追加
            properties.setNoProperty(line);

        }

        return properties;

    }

}