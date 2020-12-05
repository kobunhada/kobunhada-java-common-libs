//------------------------------------------------------------------------------
// KITASOFT API
// JFrameEx.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/07	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.kobunhada.common.debug.SystemOut;


/**
 * JFrame拡張クラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class JFrameEx extends JFrame implements SwingSymbols {

    /** 生成シリアルバージョンID. */
	private static final long serialVersionUID = -2896216865357786328L;

	/**
     * コンストラクタ.
     */
    public JFrameEx() {
        super();
        this.setFont(new Font("MS UI Gothic", 0, 12));
    }
    
    /**
     * ルック＆フィールを設定する.<br>
     * 当メソッドは、各コントロールの初期化後にコールする事。初期化前にコールした
     * 場合、設定が反映されない場合がある.
     * 
     * @param lafName ルック＆フィール
     */
    public void setLookAndFeel(String lafName){

        try {
            //ルック&フィールを変更する.
            UIManager.setLookAndFeel(lafName);
            //フレーム内コントロールのルック&フィールを変更する.
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch(Exception ex) {
            SystemOut.StackTrace(ex);
        }
    }

    /**
     * デフォルトルック&フィールを取得する.
     * 
     * @return デフォルトルック&フィール
     */
    protected String getDefaultLookAndFeel() {
        return UIManager.getSystemLookAndFeelClassName();
    }

}