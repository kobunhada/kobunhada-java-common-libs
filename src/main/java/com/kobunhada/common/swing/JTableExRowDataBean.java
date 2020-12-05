//------------------------------------------------------------------------------
// KITASOFT API
// JTableExRowDataBean.java
//
// Copyright(c) 2005-2007 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0.0	2007/02/25	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

/**
 * JTableExクラス用行データ基底クラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public abstract class JTableExRowDataBean  {
    
    /**
     * デフォルトコンストラクタ.
     */
    public JTableExRowDataBean() {
    }
    
    /**
     * コピーコンストラクタ.
     * 
     * @param copyObject コピー元オブジェクト
     */
    public JTableExRowDataBean(JTableExRowDataBean copyObject) {
        copy(copyObject);
    } 

    /**
     * コピーメソッド.
     * 
     * @param copyObject コピーオブジェクト
     */
    public abstract void copy(JTableExRowDataBean copyObject);
        

}