//------------------------------------------------------------------------------
// kobunhada's common libs.
// TokenLParenthesis.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.util.calc;

/**
 * 左括弧トークンクラス.
 * 
 * @author kobunhada
 * @version 1.00
 */
class TokenLParenthesis extends TokenElement 
{
    /**
     * コンストラクタ.
     */
    public TokenLParenthesis(){
        super();
    }
    
    /**
     * 左括弧を取得する.
     * 
     * @return 左括弧
     */
    public String getValue(){
        return "(";
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
