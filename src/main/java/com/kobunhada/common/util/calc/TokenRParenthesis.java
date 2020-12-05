//------------------------------------------------------------------------------
// KITASOFT API
// TokenRParenthesis.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/01	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.util.calc;

/**
 * 右括弧トークンクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
class TokenRParenthesis extends TokenElement 
{
    /**
     * コンストラクタ.
     */
    public TokenRParenthesis(){
        super();
    }

    /**
     * 右括弧値を取得する.
     * 
     * @return 右括弧値 
     */
    public String getValue(){
        return ")";
    }
    /**
     * 優先度を取得する.
     * 
     * @return 優先度
     */
    public int getPriority(){
        return 1;
    }

}