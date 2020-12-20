//------------------------------------------------------------------------------
// kobunhada's common libs.
// TokenDivideElement.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.util.calc;

import java.math.BigDecimal;

/**
 * 除算演算子トークンクラス
 * 
 * @version 1.00
 * @author kobunhada
 */
class TokenDivideElement extends TokenOperationElement 
{
    /**
     * コンストラクタ.
     */
    public TokenDivideElement(){
        super();
    }
    
    /**
     * 除算演算子を取得する.
     * 
     * @return 除算演算子
     */
    public String getValue(){
        return "/";
    }
    
    /**
     * 演算順序の優先度を返す.
     * 
     * @return 優先度
     */
    public int getPriority(){
        return 3;
    }
    
    /**
     * 除算を実行する.
     * 
     * @param p1 値1
     * @param p2 値2
     * @return 除算結果
     */
    protected BigDecimal calc(BigDecimal p1, BigDecimal p2){
        return p1.divide(p2, BigDecimal.ROUND_HALF_UP);
    }

}