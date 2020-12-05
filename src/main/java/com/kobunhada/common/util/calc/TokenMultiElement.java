//------------------------------------------------------------------------------
// KITASOFT API
// TokenMultiElement.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/01	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.util.calc;

import java.math.BigDecimal;

/**
 * 積算演算子トークンクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
class TokenMultiElement extends TokenOperationElement 
{
    /**
     * コンストラクタ.
     */
    public TokenMultiElement(){
        super();
    }
    
    /**
     * 積算演算子を取得する.
     * 
     * @return 積算演算子
     */
    public String getValue(){
        return "*";
    }
    
    /**
     * 演算優先度を取得する.
     * 
     * @return 演算優先度
     */
    public int getPriority(){
        return 3;
    }
    
    /**
     * 積算を実行する.
     * 
     * @param p1 値1
     * @param p2 値2
     * @return 積算結果
     */
    protected BigDecimal calc(BigDecimal p1, BigDecimal p2){
        return p1.multiply(p2);
        
    }

}