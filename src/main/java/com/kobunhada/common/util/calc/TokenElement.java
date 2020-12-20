//------------------------------------------------------------------------------
// kobunhada's common libs.
// TokenElement.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.util.calc;

/**
 * トークン基底クラス.
 * 
 * @author kobunhada
 * @version 1.00
 */
public abstract class TokenElement 
{

    /**
     * デフォルトコンストラクタ.
     */
    public TokenElement(){
    }
    
    /**
     * 値を取得する.
     * 
     * @return 値
     */
    public abstract String getValue();

    /**
     * 優先度を取得する.
     * 
     * @return 優先度
     */
    public abstract int getPriority();

}