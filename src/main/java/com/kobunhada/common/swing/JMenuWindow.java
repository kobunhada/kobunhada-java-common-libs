//------------------------------------------------------------------------------
// KITASOFT API
// JMenuWindow.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/07	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

/**
 * ウィンドウ(W)メニュークラス.<br>
 * 当クラスは、ウィンドウ(W)メニュー専用メニュークラスである.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class JMenuWindow extends JMenu  {

	/** 生成シリアルバージョンID. */
	private static final long serialVersionUID = -1810730451895534478L;
	/** デフォルトメニューカウント */
    private int defaultMenuCount = 0;    

    /**
     * デフォルトコンストラクタ.
     */
    public JMenuWindow() {
        super();
    }

    /**
     * コンストラクタ.
     *
     * @param s メニュータイトル
     */
    public JMenuWindow(String s) {
        super(s);
    }

    /**
     * コンストラクタ.
     * 
     * @param a アクション
     */
    public JMenuWindow(Action a) {
        super(a);
    }

    /**
     * コンストラクタ.
     *
     * @param s メニュータイトル
     * @param b メニューがティアオフできるかどうか
     */
    public JMenuWindow(String s, boolean b) {
        super(s, b);
    }

    /**
     * デフォルトメニュー数を取得する.
     * 
     * @param defaultMenuCount デフォルトメニュー数
     */
    public void setDefaultMenuCount(int defaultMenuCount) {
        this.defaultMenuCount = defaultMenuCount;
    }

    /**
     * デフォルトメニュー数を設定する.
     * 
     * @return デフォルトメニュー数
     */
    public int getDefaultMenuCount() {
        return defaultMenuCount;
    }

    /**
     * ウィンドウ(W)メニュー内の選択ウィンドウメニューアイテムを全て「非選択」
     * 状態に変更する.
     */
    public void clearWindowMenuSelected() {

        // ウィンドウ(W)メニュー内のメニュー数を取得 
        int menuCount = this.getItemCount();
    
        for(int i = this.getDefaultMenuCount(); i < menuCount; i++) {
            //メニューアイテムを取得
            ((JCheckBoxMenuItem)this.getItem(i)).setSelected(false);
        }
    
    }

}