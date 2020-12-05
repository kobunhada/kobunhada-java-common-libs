//------------------------------------------------------------------------------
// KITASOFT API
// JTextAreaJPN.java
//
// Copyright(c) 2005-2007 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0.0	2007/02/25	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.im.InputSubset;

import javax.swing.JTextArea;
import javax.swing.text.Document;

/**
 * 日本語テキストエリアクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class JTextAreaJPN extends JTextArea implements KeyListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Shiftキー押下中判断フラグ. */
    private boolean isShiftKeyPressed = false;

    /**
     * Constructs a new TextArea.  A default model is set, the initial string
     * is null, and rows/columns are set to 0.
     */
    public JTextAreaJPN() {
        this(null, null, 0, 0);
    }

    /**
     * Constructs a new TextArea with the specified text displayed.
     * A default model is created and rows/columns are set to 0.
     *
     * @param text the text to be displayed, or null
     */
    public JTextAreaJPN(String text) {
        this(null, text, 0, 0);
    }

    /**
     * Constructs a new empty TextArea with the specified number of
     * rows and columns.  A default model is created, and the initial
     * string is null.
     *
     * @param rows the number of rows >= 0
     * @param columns the number of columns >= 0
     * @exception IllegalArgumentException if the rows or columns
     *  arguments are negative.
     */
    public JTextAreaJPN(int rows, int columns) {
        this(null, null, rows, columns);
    }

    /**
     * Constructs a new TextArea with the specified text and number
     * of rows and columns.  A default model is created.
     *
     * @param text the text to be displayed, or null
     * @param rows the number of rows >= 0
     * @param columns the number of columns >= 0
     * @exception IllegalArgumentException if the rows or columns
     *  arguments are negative.
     */
    public JTextAreaJPN(String text, int rows, int columns) {
        this(null, text, rows, columns);
    }

    /**
     * Constructs a new JTextArea with the given document model, and defaults
     * for all of the other arguments (null, 0, 0).
     *
     * @param doc  the model to use
     */
    public JTextAreaJPN(Document doc) {
        this(doc, null, 0, 0);
    }

    /**
     * Constructs a new JTextArea with the specified number of rows
     * and columns, and the given model.  All of the constructors
     * feed through this constructor.
     *
     * @param doc the model to use, or create a default one if null
     * @param text the text to be displayed, null if none
     * @param rows the number of rows >= 0
     * @param columns the number of columns >= 0
     * @exception IllegalArgumentException if the rows or columns
     *  arguments are negative.
     */
    public JTextAreaJPN(Document doc, String text, int rows, int columns) {
        super(doc, text, rows, columns);
        this.addFocusListener(new JPNFocusAdapter());
        this.addKeyListener(this);
    }

    /**
     * TABキーを押した場合に処理するメソッド.
     * ※当メソッドの実装は、サブクラスにて行う.
     */
    protected void tabKeyPressed() {
        this.transferFocus();
    }
    
    /**
     * SHIFT + TABキーを押した場合に処理するメソッド.
     */
    protected void tabKeyPressedWithShift() {
        isShiftKeyPressed = false;
        this.transferFocusBackward();
    }

    /**
     * キーを押した際に発生するイベント.
     * 
     * @param e キーイベント
     */
    public void keyTyped(KeyEvent e) {
//        SystemOut.println("keyTyped:" + e.getKeyCode());
    }
    
    /**
     * キーを押し続けた際に発生するイベント.
     * 
     * @param e キーイベント
     */
    public void keyPressed(KeyEvent e) {
//        SystemOut.println("keyPressed:" + e.getKeyCode());
        // キーがTABキーの場合
        if(e.getKeyCode() == KeyEvent.VK_TAB) {
            if(isShiftKeyPressed) {
                tabKeyPressedWithShift();            
            }
            else {
                tabKeyPressed(); 
            }        
            // キーコードを0にセット
            e.setKeyCode(0);

        }
        // Shiftキーの場合
        else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            // Shiftキー押下フラグをfalseに設定
            isShiftKeyPressed = true;
        }
    }

    /**
     * キー押下時イベント.
     * 
     * @param e キーイベント
     */
    public void keyReleased(KeyEvent e) {
//        SystemOut.println("keyReleased:" + e.getKeyCode());

        // Shiftキーの場合
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            // Shiftキー押下フラグをfalseに設定
            isShiftKeyPressed = false;
        }

    }

    /**
     * フォーカスイベントリスナークラス.
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
            // IMEの設定(日本語入力On)
            event.getComponent().getInputContext().setCharacterSubsets(
                            new Character.Subset[] { InputSubset.KANJI });
            // ShiftKey押下フラグの初期化
            isShiftKeyPressed = false;
        }
        
        /**
         * フォーカス喪失時イベント.
         * 
         * @param event フォーカスイベント
         */
        public void focusLost(FocusEvent event){
            // IMEの設定(日本語入力Off)
            event.getComponent().getInputContext().setCharacterSubsets(null);
            // ShiftKey押下フラグの初期化
            isShiftKeyPressed = false;
        }
        
    }

}