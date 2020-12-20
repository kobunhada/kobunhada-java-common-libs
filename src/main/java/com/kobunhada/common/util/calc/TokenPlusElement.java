//------------------------------------------------------------------------------
// kobunhada's common libs.
// TokenPlusElement.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.util.calc;

import java.math.BigDecimal;

/**
 * 
 * 
 * @author kobunhada
 * @version 1.00
 */
class TokenPlusElement extends TokenOperationElement 
{
    /**
     * コンストラクタ.
     */
    public TokenPlusElement(){
        super();
    }
    
    /**
     * 加算記号値を取得する.
     * 
     * @return 加算記号値
     */
    public String getValue(){
        return "+";
    }
    
    /**
     * 優先度を取得する.
     * 
     * @return 優先度
     */
    public int getPriority(){
        return 2;
    }
    
    /**
     * 演算を実行する.
     * 
     * @param p1 値1
     * @param p2 値2
     * @return 演算結果
     */
    protected BigDecimal calc(BigDecimal p1, BigDecimal p2){
        return p1.add(p2);
    
    }
    
}