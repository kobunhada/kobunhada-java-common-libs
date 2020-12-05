//------------------------------------------------------------------------------
// KITASOFT API
// JTextFieldJPN.java
//
// Copyright(c) 2005-2007 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0.0	2007/02/25	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.im.InputSubset;

import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * 日本語テキスト入力フィールドクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class JTextFieldJPN extends JTextField  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * デフォルトコンストラクタ.
     */
    public JTextFieldJPN() {
        this(null, null, 0);
    }

    /**
     * 引数付コンストラクタ.
     *
     * @param text 初期値
     */
    public JTextFieldJPN(String text) {
        this(null, text, 0);
    }

    /**
     * 引数付コンストラクタ.
     *
     * @param columns 幅
     */ 
    public JTextFieldJPN(int columns) {
        this(null, null, columns);
    }

    /**
     * 引数付コンストラクタ.
     *
     * @param text 初期値
     * @param columns 幅
     */
    public JTextFieldJPN(String text, int columns) {
        this(null, text, columns);
    }

    /**
     * 引数付コンストラクタ.
     *
     * @param doc 使用するテキストストレージ
     * @param text 初期値
     * @param columns 幅
     */
    public JTextFieldJPN(Document doc, String text, int columns) {
        super(doc, text, columns);
        this.addFocusListener(new JPNFocusAdapter());
    }
    
    /**
     * フォーカスリスナークラス.
     * 
     * @author Ippei.Kitajima
     * @version 1.00
     */
    class JPNFocusAdapter extends FocusAdapter {
        
        /**
         * フォーカス取得時イベント.
         * 
         * @param event フォーカスイベント
         */
        public void focusGained(FocusEvent event){
            event.getComponent().getInputContext().setCharacterSubsets(
                            new Character.Subset[] { InputSubset.KANJI });
        }
        
        /**
         * フォーカス喪失時イベント.
         * 
         * @param event フォーカスイベント
         */
        public void focusLost(FocusEvent event){
            event.getComponent().getInputContext().setCharacterSubsets(null);
        }
        
    }
}