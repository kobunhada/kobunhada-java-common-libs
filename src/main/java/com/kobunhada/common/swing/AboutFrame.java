//------------------------------------------------------------------------------
// KITASOFT API
// AboutFrame.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/13	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.kobunhada.common.debug.SystemOut;


/**
 * バージョン情報ダイアログボックスクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public abstract class AboutFrame extends JDialogEx
{
    //----------------------------------------
    // クラス変数(コントロール)
    //----------------------------------------

    /**
	 * 
	 */
	private static final long serialVersionUID = -8583784559292540594L;
	/** イメージ表示用ラベル. */
    private JLabel lblImage = new JLabel();
    /** アプリケーション名表示ラベル. */
    private JLabel lblAppName = new JLabel();
    /** 著作権情報表示用ラベル. */
    private JLabel lblCopyRight = new JLabel();
    /** OKボタン. */
    private JButton cmdOK = new JButton();

    /**
     * コンストラクタ.
     * 
     * @param frame フレーム
     * @param modal モーダルモード
     */
    public AboutFrame(Frame frame, boolean modal) {
        // 親クラスのコンストラクタを実行
        super(frame, modal);
        // 初期化処理
        try {
            init();
        }
        catch(Exception e) {
            SystemOut.StackTrace(e);
        }
    }
    
    /**
     * 初期処理.
     * 
     * @throws Exception 予期しない例外
     */
    private void init() throws Exception {

        // フレーム初期化
        initFrame();
        // コントロール初期化
        initControl();
        // コントロールを画面に追加
        addContentPain();

    }

    /**
     * 画面の初期化.
     */
    private void initFrame() {

        // サイズ設定
        this.setSize(new Dimension(400, 212));
        // デフォルトフレームタイトル設定
        this.setTitle(getAppricationName() + " - " + "バージョン情報");
        // 表示位置設定（デスクトップ中央）
        Rectangle screen = this.getGraphicsConfiguration().getBounds();
        this.setLocation(screen.x + screen.width/2  - this.getSize().width/2,
                          screen.y + screen.height/2 - this.getSize().height/2);
        // OKボタンをデフォルトボタンとして設定
        this.rootPane.setDefaultButton(this.cmdOK);
        
        
        
    }

    /**
     * 画面内コントロール初期化.
     */
    private void initControl() {

        // イメージコントロール関連初期化
        initImageControl();
        // アプリケーション名コントロール関連初期化
        initAppNameControl();
        // コピーライトコントロール関連初期化
        initCopyRightControl();

        // OKボタンコントロール関連初期化
        initOKControl();    
    }
    
    /**
     * イメージコントロール関連初期化.
     */
    private void initImageControl() {

        lblImage = new JLabel(new ImageIcon(getImageFilePath()));
        lblImage.setBounds(new Rectangle(10, 10, 370, 105));
        lblImage.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        lblImage.setFont(new Font("MS UI Gothic", 0, 12));
    }
    /**
     * アプリケーション名コントロール関連初期化.
     */
    private void initAppNameControl() {
        lblAppName.setText(getAppName());
        lblAppName.setBounds(new Rectangle(10, 50, 370, 35));
        lblAppName.setFont(new Font("ＭＳ Ｐゴシック", 1, 26));
        lblAppName.setForeground(new Color(82, 82, 255));
        lblAppName.setHorizontalAlignment(SwingConstants.CENTER);
        lblAppName.setHorizontalTextPosition(SwingConstants.CENTER);
    }
    /**
     * コピーライトコントロール関連初期化.
     */
    private void initCopyRightControl() {
        lblCopyRight.setText(getCopyRight());
        lblCopyRight.setBounds(new Rectangle(10, 115, 370, 20));
        lblCopyRight.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCopyRight.setHorizontalAlignment(SwingConstants.CENTER);
    }
    /**
     * OKボタンコントロール関連初期化.
     */
    private void initOKControl() {
        cmdOK.setText("OK");
        cmdOK.setBounds(new Rectangle(300, 140, 75, 25));
        cmdOK.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    cmdOK_actionPerformed(e);
                    }
                }
        );
        cmdOK.setDefaultCapable(true);
    }
    
    /**
     * アプリケーション名のフォントを設定する.
     * 
     * @param name フォント名
     * @param style フォントスタイル
     * @param size フォントサイズ
     */
    public void setApplicationNameFont(String name, int style, int size) {
        setApplicationNameFont(new Font(name, style, size));
    }
    
    /**
     * アプリケーション名のフォントを設定する.
     * 
     * @param font フォントオブジェクト
     */
    public void setApplicationNameFont(Font font) {
        lblAppName.setFont(font);    
    }
    
    /**
     * アプリケーション名のフォントサイズを設定する.
     * 
     * @param size フォントサイズ
     */
    public void setApplicationNameFontSize(int size) {
        setApplicationNameFont(this.lblAppName.getFont().getFontName(),
                               this.lblAppName.getFont().getStyle(),
                               size);
    }
    
    /**
     * アプリケーション名のフォントスタイルを設定する.
     * 
     * @param style フォントスタイル
     */
    public void setApplicationNameFontStyle(int style) {
        setApplicationNameFont(this.lblAppName.getFont().getFontName(),
                               style,
                               this.lblAppName.getFont().getSize());
    }

    /**
     * アプリケーション名のフォント名を設定する.
     * 
     * @param name フォント名
     */
    public void setApplicationNameFontName(String name) {
        setApplicationNameFont(name,
                               this.lblAppName.getFont().getStyle(),
                               this.lblAppName.getFont().getSize());
    }

    /**
     * コントロールを画面に追加.
     */
    private void addContentPain() {
        this.getContentPane().add(cmdOK, null);
        this.getContentPane().add(lblCopyRight, null);
        this.getContentPane().add(lblAppName, BorderLayout.CENTER);
        this.getContentPane().add(lblImage, BorderLayout.CENTER);
    }
    
    /**
     * OKボタンクリック時イベント.
     * 
     * @param e アクションイベント
     */
    private void cmdOK_actionPerformed(ActionEvent e){
        this.dispose();
    }
    
    /**
     * アプリケーション名を取得します.<br>
     * ※デフォルトでは、""を返します。よって、任意の文字列を取得したい場合は、
     * サブクラスにて当メソッドをオーバライドして下さい.
     * 
     * @return アプリケーション名
     */
    protected String getAppricationName() {
        return "";
    }

    /**
     * イメージファイルパスを取得する.<br>
     * ※当メソッドの実装は、各サブクラスにて行う.
     * 
     * @return イメージファイルパス
     */
    protected abstract String getImageFilePath();

    /**
     * アプリケーション名を取得する.<br>
     * ※当メソッドの実装は、各サブクラスにて行う.
     * 
     * @return アプリケーション名
     */
    protected abstract String getAppName();
    
    /**
     * コピーライトを取得する.<br>
     * ※当メソッドの実装は、各サブクラスにて行う.
     * 
     * @return コピーライト
     */
    protected abstract String getCopyRight();
    
}