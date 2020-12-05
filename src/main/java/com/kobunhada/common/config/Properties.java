//------------------------------------------------------------------------------
// KITASOFT API
// Properties.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0  2006/06/05  IPPEI.KITAJIMA  新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.config;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 属性カテゴリクラス.
 *
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class Properties {
    /** 属性リスト. */
    private HashMap<String, Property> propertyList = new HashMap<String, Property>();
    /** インデックスリスト. */
    private ArrayList<String> indexList = new ArrayList<String>();
    /** 属性カテゴリ名. */
    private String name = "";

    /**
     * デフォルトコンストラクタ.
     */
    public Properties() {
    }

    /**
     * コンストラクタ.
     *
     * @param name 属性カテゴリ名
     */
    public Properties(String name) {
        this.name = name;
    }

    /**
     * 属性カテゴリ名を取得する.
     *
     * @return 属性カテゴリ名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 属性カテゴリ名を設定する.
     *
     * @param name 属性カテゴリ名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 属性インスタンスを取得する.
     *
     * @param key 属性名
     * @return 属性インスタンス
     */
    public Property getProperty(String key) {
        return (Property)propertyList.get(key);
    }

    /**
     * 属性以外の行を追加する.
     *
     * @param line 追加する行
     */
    void setNoProperty(String line) {
        indexList.add(line);
    }

    /**
     * 属性インスタンスを設定する.
     *
     * @param value 属性インスタンス
     */
    public void setProperty(Property value) {
        //属性リストへ追加
        propertyList.put(value.getName(),value);
        //属性インデックスへ追加
        indexList.add(value.getName());
    }

    /**
     * 属性インスタンスを削除する.
     *
     * @param key 属性名
     */
    public void removeProperty(String key) {
        //属性リストから削除
        propertyList.remove(key);
        //属性インデックスから削除
        for(int i = 0; i < indexList.size(); i++) {
            String tmpKey = (String)indexList.get(i);
            if(tmpKey.equals(key)) {
                indexList.remove(i);
                break;
            }
        }
    }

    /**
     * 属性リストを取得する.
     *
     * @return 属性リスト
     */
    public HashMap<String, Property> getPropertyList() {
        return propertyList;
    }

    /**
     * 属性インデックスリストを取得する.
     *
     * @return 属性インデックスリスト
     */
    public ArrayList<String> getIndexList() {
        return indexList;
    }

}