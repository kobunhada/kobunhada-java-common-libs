//------------------------------------------------------------------------------
// TokenNumberElement.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.util.calc;

import java.math.BigDecimal;

/**
 * 数値トークン要素クラス.
 * 
 * @author kobunhada
 * @version 1.00
 */
class TokenNumberElement extends TokenElement 
{
    private String value = "";

    /**
     * コンストラクタ.
     */
    public TokenNumberElement() {
        super();
    }
    
    /**
     * 引数付コンストラクタ.
     * 
     * @param value 値
     */
    public TokenNumberElement(String value) {
        this.setValue(value);
    }
    
    /**
     * 値を設定する.
     * 
     * @param value 値
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * 値を取得する.
     * 
     * @return 値
     */
    public String getValue() {
        return value;
    }
    
    /**
     * BigDecimal型で値を取得する.
     * 
     * @return 値
     */
    public BigDecimal getBigDecimalValue() {
        return getBigDecimalInstance(value);
    }

    /**
     * 優先度を取得する.
     * 
     * @return 優先度
     */
    public int getPriority() {
        return 0;
    }
    
    /**
     * 文字列をBigDecimal型に変換して取得する.
     * 
     * @param str 対象文字列
     * @return BigDecimal型に変換された値
     */
    private static BigDecimal getBigDecimalInstance(String str) {
        BigDecimal dec = new BigDecimal("0.0000000000");
        return dec.add(new BigDecimal(str));
    }
    


}