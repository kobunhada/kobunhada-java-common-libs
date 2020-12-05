//------------------------------------------------------------------------------
// KITASOFT API
// IKElementBase.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/01	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common;

/**
 * 要素ベースクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public abstract class IKElementBase {
    
    /**
     * キーを取得する.
     * ※当メソッドは各サブクラスで実装すること.
     * 
     * @return IKKeyElementBase 要素
     */
    public abstract IKKeyElementBase getKey();
         
    /**
     * 要素をコピーする.<br>
     * ※当メソッドは各サブクラスで実装すること.
     * 
     * @param myObject コピー対象オブジェクト
     */
	public abstract void copy(IKElementBase myObject);
	 
}
 
