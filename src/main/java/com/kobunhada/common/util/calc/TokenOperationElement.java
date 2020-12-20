//------------------------------------------------------------------------------
// kobunhada's common libs.
// TokenOperationElement.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.util.calc;

import java.math.BigDecimal;

/**
 * 演算要素基底クラス.
 * 
 * @author kobunhada
 * @version 1.00
 */
abstract class TokenOperationElement extends TokenElement 
{
    /**
     * コンストラクタ.
     */
    public TokenOperationElement(){
        super();
    }
    
    /**
     * 引数p1とp2を計算し、結果を返す.
     * 
     * @param p1 数値1
     * @param p2 数値2
     * @return 計算結果
     */
    public BigDecimal execute(BigDecimal p1, BigDecimal p2){
       return  new BigDecimal(
                        trimRZERO(calc(p1, p2).toString()));

    }

    /**
     * 文字列の右側の1以上の連続する"0"を取り除く.
     * 
     * @param str 対象文字列
     * @return 対象文字列から文字列の右側に存在する"0"を取り除いた文字列
     */
    private String trimRZERO(String str){
        // 文字数を取得.
        int length = str.length();
        
        //
        for (int i = length; i > 0; i--) {
            String tmp = str.substring(i-1,i);
            if(!tmp.equals("0") ){
                return str.substring(0,i);
            }
        }

        return str;
    }
    
    /**
     * 引数p1とp2を計算し、結果を返す.<br>
     * 計算内容は、サブクラスにて実装.
     * 
     * @param p1 数値文字列1
     * @param p2 数値文字列1
     * @return 計算結果文字列
     */
    protected abstract BigDecimal calc(BigDecimal p1, BigDecimal p2);
}