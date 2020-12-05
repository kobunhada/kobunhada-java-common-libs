//------------------------------------------------------------------------------
// KITASOFT API
// ChildFrame.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/13	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import com.kobunhada.common.debug.SystemOut;


/**
 * MDI子ウィンドウクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class ChildFrame extends JInternalFrame implements SwingSymbols {

    //----------------------------------------
    // インスタンス定数
    //----------------------------------------

    /** 生成シリアルバージョンID. */
	private static final long serialVersionUID = 4024520526869948635L;
	/** デフォルトウィンドウタイトル. */
    private static final String    DEFAULT_WINDOW_TITLE = "";
    /** デフォルトウィンドウサイズ. */
    private static final Dimension DEFAULT_WINDOW_SIZE 
                                                    = new Dimension(100,100);
    /** デフォルトウィンドウ初期表示位置. */
    private static final Point DEFAULT_WINDOW_LOCATION = new Point(0,0);
    /** ウィンドウメニュー. */
    private JMenuWindow parentWindowMenu = null;
    /** 親MDIフレーム. */
    private MDIFrame parentFrame = null;
    
    //----------------------------------------
    // クラス変数
    //----------------------------------------

    /** ウィンドウ生成回数. */
    private static int createCount = 0;


//------------------------------------------------------------------------------
// コンストラクタ
//------------------------------------------------------------------------------

    /**
     * デフォルトコンストラクタ.
     */
    public ChildFrame() {
        this(null, DEFAULT_WINDOW_TITLE,DEFAULT_WINDOW_SIZE,DEFAULT_WINDOW_LOCATION);
    }
    
    /**
     * コンストラクタ.
     * 
     * @param frame 親フレーム
     */
    public ChildFrame(MDIFrame frame) {
        this(null, DEFAULT_WINDOW_TITLE,DEFAULT_WINDOW_SIZE,DEFAULT_WINDOW_LOCATION);
        parentFrame = frame;
    }
    
    /**
     * コンストラクタ.
     * 
     * @param windowSize ウィンドウサイズ
     */
    public ChildFrame(Dimension windowSize) {
        this(null, DEFAULT_WINDOW_TITLE,windowSize, DEFAULT_WINDOW_LOCATION);
    }
    /**
     * コンストラクタ.
     * 
     * @param windowTitle ウィンドウタイトル
     */
    public ChildFrame(String windowTitle) {
        this(null, windowTitle, DEFAULT_WINDOW_SIZE, DEFAULT_WINDOW_LOCATION);
    }
    /**
     * コンストラクタ.
     * 
     * @param windowLocation ウィンドウ位置
     */
    public ChildFrame(Point windowLocation) {
        this(null, DEFAULT_WINDOW_TITLE, DEFAULT_WINDOW_SIZE, windowLocation);
    }
    /**
     * コンストラクタ.
     * 
     * @param frame 親フレーム
     * @param parentWindowMenu 親画面のウィンドウ(W)メニューインスタンス
     */
    public ChildFrame(MDIFrame frame, JMenuWindow parentWindowMenu) {
        this(parentWindowMenu,
              DEFAULT_WINDOW_TITLE,DEFAULT_WINDOW_SIZE, 
              DEFAULT_WINDOW_LOCATION);

        parentFrame = frame;

    }
    /**
     * コンストラクタ.
     * 
     * @param windowTitle ウィンドウタイトル
     * @param windowSize ウィンドウサイズ
     */
    public ChildFrame(String windowTitle,Dimension windowSize) {
        this(null, windowTitle, windowSize, DEFAULT_WINDOW_LOCATION);
    }
    /**
     * コンストラクタ.
     * 
     * @param windowTitle ウィンドウタイトル
     * @param windowLocation ウィンドウ位置
     */
    public ChildFrame(String windowTitle,Point windowLocation) {
        this(null, windowTitle, DEFAULT_WINDOW_SIZE, windowLocation);
    }
    /**
     * デフォルトコンストラクタ.
     * 
     * @param parentWindowMenu 親画面のウィンドウ(W)メニューインスタンス
     */
    public ChildFrame(JMenuWindow parentWindowMenu) {
        this(parentWindowMenu, DEFAULT_WINDOW_TITLE, DEFAULT_WINDOW_SIZE, 
              DEFAULT_WINDOW_LOCATION);
    }
    /**
     * コンストラクタ.
     * 
     * @param parentWindowMenu 親画面のウィンドウ(W)メニューインスタンス
     * @param windowSize ウィンドウサイズ
     */
    public ChildFrame(JMenuWindow parentWindowMenu, Dimension windowSize) {
        this(parentWindowMenu, DEFAULT_WINDOW_TITLE, windowSize,
              DEFAULT_WINDOW_LOCATION);
    }
    /**
     * コンストラクタ.
     * 
     * @param parentWindowMenu 親画面のウィンドウ(W)メニューインスタンス
     * @param windowTitle ウィンドウタイトル
     */
    public ChildFrame(JMenuWindow parentWindowMenu, String windowTitle) {
        this(parentWindowMenu, windowTitle, DEFAULT_WINDOW_SIZE, 
              DEFAULT_WINDOW_LOCATION);
    }
    /**
     * コンストラクタ.
     * 
     * @param parentWindowMenu 親画面のウィンドウ(W)メニューインスタンス
     * @param windowLocation ウィンドウ位置
     */
    public ChildFrame(JMenuWindow parentWindowMenu, Point windowLocation) {
        this(parentWindowMenu, DEFAULT_WINDOW_TITLE, DEFAULT_WINDOW_SIZE,
              windowLocation);
    }
    /**
     * コンストラクタ.
     * 
     * @param parentWindowMenu 親画面のウィンドウ(W)メニューインスタンス
     * @param windowTitle ウィンドウタイトル
     * @param windowSize ウィンドウサイズ
     */
    public ChildFrame(JMenuWindow parentWindowMenu,
                       String windowTitle,
                       Dimension windowSize) {
        this(parentWindowMenu, windowTitle, windowSize,
            DEFAULT_WINDOW_LOCATION);
    }

    /**
     * コンストラクタ.
     * 
     * @param parentWindowMenu 親画面のウィンドウ(W)メニューインスタンス
     * @param windowTitle ウィンドウタイトル
     * @param windowLocation ウィンドウ位置
     */
    public ChildFrame(JMenuWindow parentWindowMenu,
                       String windowTitle,
                       Point windowLocation) {
        this(parentWindowMenu, windowTitle, DEFAULT_WINDOW_SIZE, 
              windowLocation);
    }
    /**
     * コンストラクタ.
     * 
     * @param parentWindowMenu 親画面のウィンドウ(W)メニューインスタンス
     * @param windowTitle ウィンドウタイトル
     * @param windowSize ウィンドウサイズ
     * @param windowLocation ウィンドウ位置
     */
    public ChildFrame(JMenuWindow parentWindowMenu,
                       String windowTitle,
                       Dimension windowSize,
                       Point windowLocation) {

        //スーパークラスのコンストラクタ
        super(windowTitle + ++createCount,  //ウインドウタイトル
               true,         //サイズ変更可
               true,         //クローズ  可
               true,         //最大化可
               true);        //アイコン化可

        //親画面のウィンドウ(W)メニューインスタンスを保持
        this.parentWindowMenu = parentWindowMenu;
        //初期表示位置(左端)
        this.setLocation(windowLocation);
        //レイアウトをフリーに設定
        this.getContentPane().setLayout(null);
        //フレームを閉じるときに実行される処理(フレーム解放)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //ウィンドウサイズの設定
        this.setSize(windowSize);
        //内部フレームリスナーを追加
        this.addInternalFrameListener(new ChildFrameListener());

    }

//------------------------------------------------------------------------------
// メソッド
//------------------------------------------------------------------------------
    
    /**
     * 画面の表示位置を中央に設定する.
     * 
     * @param frame 親フレーム
     */
    public void setLocationCenter(MDIFrame frame) {
        // 親画面のサイズ取得
        Dimension parent = frame.getDesktop().getSize();
        // 自画面のサイズ取得
        Dimension child = this.getSize();
        // 横開始位置を取得
        int xnew = (int)(parent.getWidth() - child.getWidth()) / 2;
        // 縦開始位置を取得
        int ynew = (int)(parent.getHeight() - child.getHeight()) / 2;
        // 表示位置を設定
        this.setLocation(xnew, ynew);    
    }
    
    /**
     * 親フレームインスタンスを取得する.
     * 
     * @return 親フレームインスタンス
     */
    protected MDIFrame getParentFrame() {
        return parentFrame;
    }
    
    /**
     * ウィンドウ(W)メニュー内の選択ウィンドウを、自ウィンドウに変更する.<br>
     * 当メソッドは、execActivated内からのみ呼び出される.
     */
    private void changeSelectedMenuWindow() {
        // ウィンドウ(W)メニュー内のメニュー数を取得 
        int menuCount = parentWindowMenu.getItemCount();
        
        // ウィンドウ(W)メニューのウィンドウ選択状態を変更
        for(int i = parentWindowMenu.getDefaultMenuCount(); i < menuCount; i++)
        {
            //メニューアイテムを取得
            JCheckBoxMenuItem menuItem
                       = (JCheckBoxMenuItem)parentWindowMenu.getItem(i);
            // 対応するメニューである場合は、選択状態を「選択」にする
            if(this.getTitle().equals(menuItem.getText())) {
                menuItem.setSelected(true);
            }
            // 上記以外は、選択状態を「非選択」にする
            else {
                menuItem.setSelected(false);
            }
        }
    }


//------------------------------------------------------------------------------
// リスナークラスより呼び出されるメソッド
//------------------------------------------------------------------------------
    /**
     * 内部フレームがアクティブになった時に呼び出される.<br>
     * ※サブクラスが、当メソッドをオーバライドする場合は、必ず最初に親クラスの
     * メソッドをコールする事.
     * 
     * @param e 内部フレームイベントオブジェクト
     */
    protected void execActivated(InternalFrameEvent e) {
        //ウィンドウ(W)メニュー内にある、ウィンドウ選択メニューの選択項目を自
        //ウィンドウに変更
        changeSelectedMenuWindow();
    }
    /**
     * 内部フレームが非アクティブになった時に呼び出される.<br>
     * ※サブクラスが、当メソッドをオーバライドする場合は、必ず最初に親クラスの
     * メソッドをコールする事.
     * 
     * @param e 内部フレームイベントオブジェクト
     */
    protected void execDeactivated(InternalFrameEvent e) {
    }

    /**
     * 内部フレームがアイコン化解除されたときに呼び出される.<br>
     * ※サブクラスが、当メソッドをオーバライドする場合は、必ず最初に親クラスの
     * メソッドをコールする事.
     * 
     * @param e 内部フレームイベントオブジェクト
     */
    protected void execDeiconified(InternalFrameEvent e) {
        
    }

    /**
     * 内部フレームがアイコン化されたときに呼び出される.<br>
     * ※サブクラスが、当メソッドをオーバライドする場合は、必ず最初に親クラスの
     * メソッドをコールする事.
     * 
     * @param e 内部フレームイベントオブジェクト
     */
    protected void execIconified(InternalFrameEvent e) {
    }

    /**
     * 内部フレームがクローズされたときに呼び出される.<br>
     * ※サブクラスが、当メソッドをオーバライドする場合は、必ず最初に親クラスの
     * メソッドをコールする事.
     * 
     * @param e 内部フレームイベントオブジェクト
     */
    protected void execClosed(InternalFrameEvent e) {            
    }

    /**
     * 内部フレームがクローズ処理中のときに呼び出される.<br>
     * ※サブクラスが、当メソッドをオーバライドする場合は、必ず最初に親クラスの
     * メソッドをコールする事.
     * 
     * @param e 内部フレームイベントオブジェクト
     */
    protected void execClosing(InternalFrameEvent e) {            
    }

    /**
     * 内部フレームがオープンされたときに呼び出される.<br>
     * ※サブクラスが、当メソッドをオーバライドする場合は、必ず最初に親クラスの
     * メソッドをコールする事.
     * 
     * @param e 内部フレームイベントオブジェクト
     */
    protected void execOpened(InternalFrameEvent e) {            
    }
    
    /**
     * MDIフレームの同名メソッドが実行された場合に呼び出される.
     */
    public void doMDIFrameUpdateEvent() {
        // 処理なし。
    }
    
//------------------------------------------------------------------------------
// インナークラス
//------------------------------------------------------------------------------
    /**
     * 内部フレームイベントを受け取るためのリスナー実装クラス.
     * 
     * @version 1.00
     * @author Ippei.Kitajima
     */
    private class ChildFrameListener implements InternalFrameListener {
        /**
         * 内部フレームがアクティブになった時に呼び出される.
         * 
         * @param e 内部フレームイベントオブジェクト
         */
        public void internalFrameActivated(InternalFrameEvent e) {
            try {
                //内部フレームイベント
                execActivated(e);
            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
        /**
         * 内部フレームが非アクティブになった時に呼び出される.
         * 
         * @param e 内部フレームイベントオブジェクト
         */
        public void internalFrameDeactivated(InternalFrameEvent e) {
            try {
                execDeactivated(e);
            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
        /**
         * 内部フレームがアイコン化解除されたときに呼び出される.
         * 
         * @param e 内部フレームイベントオブジェクト
         */
        public void internalFrameDeiconified(InternalFrameEvent e) {
            try {
                execDeiconified(e);            
            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
        /**
         * 内部フレームがアイコン化されたときに呼び出される.
         * 
         * @param e 内部フレームイベントオブジェクト
         */
        public void internalFrameIconified(InternalFrameEvent e) {
            try {
                execIconified(e);            
            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
            
        }
        /**
         * 内部フレームがクローズされたときに呼び出される.
         * 
         * @param e 内部フレームイベントオブジェクト
         */
        public void internalFrameClosed(InternalFrameEvent e) {
            try {
                execClosed(e);            
            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
        /**
         * 内部フレームがクローズ処理中のときに呼び出される.
         * 
         * @param e 内部フレームイベントオブジェクト
         */
        public void internalFrameClosing(InternalFrameEvent e) {
            try {
                execClosing(e);            
            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
        /**
         * 内部フレームがオープンされたときに呼び出される.
         * 
         * @param e 内部フレームイベントオブジェクト
         */
        public void internalFrameOpened(InternalFrameEvent e) {
            try {
                execOpened(e);            
            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
    }


}