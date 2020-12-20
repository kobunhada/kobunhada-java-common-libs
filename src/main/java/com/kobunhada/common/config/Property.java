//------------------------------------------------------------------------------
// kobunhada's common libs.
// ParameterElementBase.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/05	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.config;

/**
 * 属性クラス.
 * 
 * @author kobunhada
 * @version 1.00
 */
public class Property 
{   
    /** 属性名. */
    private String name = "";
    /** 属性値. */
    private String value = "";

    /**
     * デフォルトコンストラクタ.
     */
    public Property() {
    }
    
    /**
     * コンストラクタ.
     * 
     * @param name 属性名
     * @param value 属性値
     */
    public Property(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    /**
     * コピーコンストラクタ.
     * 
     * @param property コピー元オブジェクト
     */
    public Property(Property property) {
        this(property.name, property.value);
    }
    
    /**
     * 属性名を取得する.
     * 
     * @return 属性名
     */
    public String getName() {
        return name;
    }
    
    /**
     * 属性名を設定する.
     * 
     * @param name 属性名
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 属性値を取得する.
     * 
     * @return 属性値
     */
    public String getValue() {
        return value;
    }
    
    /**
     * 属性値を設定する.
     * 
     * @param value 属性値
     */
    public void setValue(String value) {
        this.value = value;
    }
    
}