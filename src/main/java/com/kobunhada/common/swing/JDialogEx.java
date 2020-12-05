//------------------------------------------------------------------------------
// KITASOFT API
// JDialogEx.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/13	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.kobunhada.common.debug.SystemOut;


/**
 * JDialog拡張クラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class JDialogEx extends JDialog implements SwingSymbols
{
    /**
	 * シリアルIDバージョン.
	 */
	private static final long serialVersionUID = -7533112189955301198L;

	/**
     * コンストラクタ.
     * 
     * @param modal モーダルモード
     */
    public JDialogEx(boolean modal) {
        this(null, modal);
    }

    /**
     * コンストラクタ.
     * 
     * @param frame 親フレーム
     * @param modal モーダルモード
     */
    public JDialogEx(Frame frame, boolean modal) {
        super(frame, modal);
        //フォントの設定
        this.setFont(new Font("MS UI Gothic", 0, 12));
        //レイアウトをフリーに設定
        this.getContentPane().setLayout(null);
        // ルック&フィールの設定
        this.setLookAndFeel(getDefaultLookAndFeel());
        //フレームを閉じるときに実行される処理(フレーム解放)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // リサイズ不可
        this.setResizable(false);


    }

    /**
     * 画面の表示位置を中央に設定する.
     * 
     * @param frame 親フレーム
     */
    public void setLocationCenter(ChildFrame frame) {
        // 親画面のサイズ取得
        Dimension parent = frame.getSize();
        // 自画面のサイズ取得
        Dimension child = this.getSize();
        // 画面の表示位置を中央に設定
        setLocationCenter(parent, child);
    
    }
    
    /**
     * 画面の表示位置を中央に設定する.
     * 
     * @param frame 親フレーム
     */
    public void setLocationCenter(Frame frame) {
    
        // 親画面のサイズ取得
        Dimension parent = frame.getSize();
        // 自画面のサイズ取得
        Dimension child = this.getSize();
        // 画面の表示位置を中央に設定
        setLocationCenter(parent, child);
    
    }
    
    /**
     * 画面の表示位置を中央に設定する.
     * 
     * @param parent 親画面サイズ
     * @param child 子画面サイズ
     */
    private void setLocationCenter(Dimension parent, Dimension child) {
        // 横開始位置を取得
        int xnew = (int)(parent.getWidth() - child.getWidth()) / 2;
        // 縦開始位置を取得
        int ynew = (int)(parent.getHeight() - child.getHeight()) / 2;
        // 表示位置を設定
        this.setLocation(xnew, ynew);    
    }

    /**
     * ルック＆フィールを設定する.
     * 
     * @param lafName ルック＆フィール
     */
    public void setLookAndFeel(String lafName) {

        try{
            //ルック&フィールを変更する.
            UIManager.setLookAndFeel(lafName);
            //フレーム内コントロールのルック&フィールを変更する.
            SwingUtilities.updateComponentTreeUI(this);
        }catch(Exception ex){
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