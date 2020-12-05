//------------------------------------------------------------------------------
// KITASOFT API
// Config.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/01	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.config;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 設定ファイルクラス.
 *
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class Config  {

    /** 設定カテゴリリスト. */
    private HashMap<String, Properties> propertiesList = new HashMap<String, Properties>();
    /** 設定インデックスリスト */
    private ArrayList<String> indexList = new ArrayList<String>();
    /** 設定ファイル名. */
    private String name = "";
    /** 設定ファイルパス. */
    private String path = "";

    /**
     * デフォルトコンストラクタ.
     */
    public Config() {
    }

    /**
     * 設定ファイル名を取得する.
     *
     * @return 設定ファイル名
     */
    public String getName() {
        return name;
    }

    /**
     * 設定ファイル名を設定する.
     *
     * @param name 設定ファイル名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 設定ファイルパスを取得する.
     *
     * @return 設定ファイルパス
     */
    public String getPath() {
        return path;
    }

    /**
     * 設定ファイルパスを設定する.
     *
     * @param path 設定ファイルパス
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 設定ファイル名をパス付で取得する.
     *
     * @return 設定ファイル名（パス付）
     */
    public String getNameWithPath() {

        //path文字列の最後が "/"の場合
        if("/".equals(path.substring(path.length()-1,path.length()))) {
            return path + name;

        //path文字列の最後が "/"ではない場合
        } else {
            return path + "/" + name;

        }

    }

    /**
     * 属性カテゴリインスタンスを取得する.<br>
     * 引数:keyで指定された属性カテゴリのインスタンスを取得する.属性カテゴリが
     * 存在しない場合は null を返す.
     *
     * @param key 属性カテゴリ名
     * @return 属性カテゴリインスタンス
     */
    public Properties getProperties(String key) {
        //設定カテゴリインスタンスを取得
        Object obj = propertiesList.get(key);
        //存在しない場合はnullを返す
        if(obj==null) {
            return null;
        }

        return (Properties)obj;
    }

    /**
     * 設定カテゴリインスタンスを設定する.<br>
     * ※設定ファイルへ反映させる場合は、当メソッド実行後に write メソッドを実行
     * する事.
     *
     * @param properties 設定カテゴリインスタンス
     * @throws Exception 予期しない例外
     */
    public void setProperties(Properties properties) throws Exception {

        //引数がPropertiesインスタンスではない場合は例外を発生させる
        if(!properties.getClass().getName().equals(
                                new Properties().getClass().getName())) {

            throw new Exception();

        }

        //設定カテゴリインスタンスを設定
        propertiesList.put(properties.getName(), properties);
        //インデックスを記録
        indexList.add(properties.getName());
    }

    /**
     * 設定カテゴリ以外の情報を追加する.<br>
     * ※設定カテゴリ以外の情報とは、コメント、空行情報を想定している.
     *
     * @param line 追加する行文字列
     */
    void setNoPropertiesInfo(String line) {
        //インデックスを記録
        indexList.add(line);
    }

    /**
     * 設定カテゴリインスタンスを削除する.<br>
     * ※設定ファイルへ反映させる場合は、当メソッド実行後に write メソッドを実行
     * する事.
     *
     * @param key キー
     */
    public void removeProperties(String key) {

        //設定カテゴリインスタンスを削除
        propertiesList.remove(key);
        //インデックスを削除
        for(int i = 0; i < indexList.size(); i++) {
            String tmpKey = (String)indexList.get(i);
            if(tmpKey.equals(key)) {
                indexList.remove(i);
                break;
            }
        }
    }

    /**
     * 設定カテゴリリストを取得する.
     *
     * @return 設定カテゴリリスト
     */
    public HashMap<String, Properties> getPropoertiesList() {
        return propertiesList;
    }

    /**
     * 設定インデックスリストを取得する.
     *
     * @return 設定インデックスリスト
     */
    public ArrayList<String> getIndexList() {
        return indexList;
    }

}