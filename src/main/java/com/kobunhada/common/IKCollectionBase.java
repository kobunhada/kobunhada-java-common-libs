//------------------------------------------------------------------------------
// KITASOFT API
// IKCollectionBase.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/01	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common;

import java.util.ArrayList;

/**
 * コレクションベースクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public abstract class IKCollectionBase {

    /** ElementList. */
    private ArrayList<IKElementBase> elementList = new ArrayList<IKElementBase>();
	
    /**
     * 要素のリストをArrayList型で取得する.
     * 
     * @return ArrayList 要素リスト
     */
	public ArrayList<IKElementBase> getElementList() {
		return this.elementList;
	}
	
    /**
     * 要素のリストをArrayList型で設定する.
     * 
     * @param elementList 要素リスト
     */
	public void setElementList(ArrayList<IKElementBase> elementList) {

        //要素リストを設定する
        this.elementList = new ArrayList<IKElementBase>(elementList);
 
    }
	
    /**
     * 要素を追加する.
     * 
     * @param element 要素オブジェクト
     */
	public void addElement(IKElementBase element) {

        //要素を追加する
        elementList.add(element);

	}
	/**
     * 要素を取得する.<br>
     * 引数 key と一致する要素を取得する。一致する要素がない場合は nullを
     * 返す.
     * 
     * @param key 要素を識別する為のIKKeyElementBaseオブジェクト
     * @return 要素
     */
	public IKElementBase getElement(IKKeyElementBase key) {

        for(int i = 0; i < elementList.size(); i++){
            IKElementBase element = (IKElementBase)elementList.get(i);

            if (isEqualKey(key, element.getKey()) ){
                return element;
            }
        }
        
        return null;
        
	}
	/**
     * キーに対応する要素を削除する.
     * 
     * @param key キー
     */
	public void removeElement(IKKeyElementBase key) {

        for(int i = 0; i < elementList.size(); i++){
            IKElementBase element = (IKElementBase)elementList.get(i);

            if (isEqualKey(key, element.getKey()) ){
                elementList.remove(i);
            }
        }
	}
	
    /**
     * 要素のリストを初期化する.
     */
	public void clear() {

        elementList = new ArrayList<IKElementBase>();
	}
	
    /**
     * 2つのキーを比較する.<br>
     * ※当メソッドは getElement メソッドで使用する事が前提である.<br>
     * ※当メソッドは、サブクラスで実装する事.
     * 
     * @param aKey 比較対象1番目のIKKeyElementBaseオブジェクト
     * @param bKey 比較対象2番目のIKKeyElementBaseオブジェクト
     * @return true:一致  false:不一致
     */
    protected abstract boolean isEqualKey(IKKeyElementBase aKey,
                                               IKKeyElementBase bKey);


}
 
