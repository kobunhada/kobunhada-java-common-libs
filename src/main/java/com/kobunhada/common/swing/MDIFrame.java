//------------------------------------------------------------------------------
// KITASOFT API
// MDIFrame.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/13	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.kobunhada.common.debug.SystemOut;


/**
 * MDIフレーム基底クラス.<br>
 *
 * @author Ippei.Kitajima
 * @version 1.00
 */
public abstract class MDIFrame extends JFrameEx
{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/** デスクトップペイン. */
    private JDesktopPane desktop = new JDesktopPane();
    /** メニューバー. */
    protected JMenuBar menuInstance = new JMenuBar();
    /** . */
	private int xincrement = 20;
    private int yincrement = 30;
    /** . */
	private int xnew = xincrement;
    private int ynew = yincrement;

    /**
     * コンストラクタ.
     */
    public MDIFrame() {
        // スーパークラスのコンストラクタ
        super();
        // メニューバーの設定
        menuInstance.setFont(this.getFont());
        // desktopの設定
        desktop.setDragMode(
                    JDesktopPane.OUTLINE_DRAG_MODE);   /* ドラッグ中は輪郭表示 */
        // メニューバーの追加
        this.setJMenuBar(menuInstance);
        // コンテントペインにdesktopを設定
        this.setContentPane(desktop);
        // メインウインドウのサイズ設定
        this.setSize(500,450);
        // Look&Feelの設定(OS標準)
        this.setLookAndFeel(this.getDefaultLookAndFeel());

        // ユーザーがウィンドウを閉じようとしたときに行なう処理(アプリ終了)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // メニューバーにメニューを登録.
        this.registMenu();

    }

    /**
     * Look&Feelを変更する.
     *
     * @param LookAndFeelClassName Look&Feelクラス名文字列
     */
    public void changeLookAndFeel(String LookAndFeelClassName) {

    	try {
			UIManager.setLookAndFeel(LookAndFeelClassName);
		}
    	catch (Exception e) {
			SystemOut.StackTrace(e);
		}
        SwingUtilities.updateComponentTreeUI(this);


    }

    /**
     * 現在開いているChildFrameのdoMDIFrameUpdateEvent()イベントを実行する.
     */
	public void doMDIFrameUpdateEvent() {
		// 内部フレームの配列を取得する
		JInternalFrame[] iframes = desktop.getAllFrames();
		if (iframes.length == 0) {
			return;
        }

        for(int i = 0; i < iframes.length; i++) {
			((ChildFrame)iframes[i]).doMDIFrameUpdateEvent();
        }
	}

    /**
     * メニューバーを取得する.
     *
     * @return メニューバーインスタンス
     */
    protected JMenuBar getMenuInstance() {
        return this.menuInstance;
    }

    /**
     * デスクトップペインを取得する.
     *
     * @return デスクトップペイン
     */
    protected JDesktopPane getDesktop() {
        return this.desktop;
    }

    /**
     * ウィンドウ(W)メニュー内のウィンドウ選択用メニューを選択した時の処理.
     *
     * @param e アクションイベントオブジェクト
     */
    private void selectChildWindow(ActionEvent e) {

        //ウィンドウ(W)メニューを取得
        JMenuWindow windowMenu = (JMenuWindow)this.menuInstance.getMenu(
                                                    getWindowMenuPosition());
        //ウィンドウ(W)メニュー選択メニューの選択状態を全て「非選択」にする
        windowMenu.clearWindowMenuSelected();
        //選択したメニューインスタンスを取得
        JCheckBoxMenuItem menu = (JCheckBoxMenuItem)e.getSource();
        //選択したメニューのタイトルを取得
        String menuTitle = menu.getText();

        try {
            // 現在開いている子ウィンドウを全て取得する.
            JInternalFrame[] frames = this.desktop.getAllFrames();

            for(int i = 0; i < frames.length; i++) {
                //子ウィンドウのタイトルとメニューのタイトルが同じ場合
                if(frames[i].getTitle().equals(menuTitle)) {
                    //子ウィンドウを選択状態にする
                    frames[i].setSelected(true);
                    //子ウィンドウが最小化状態の場合.
                    if(frames[i].isIcon()) {
                        //子ウィンドウを最大化する
                        frames[i].setMaximum(true);
                    }

                    //選択したフレームに対応するメニューを「選択」状態に設定
                    menu.setSelected(true);

                }

            }
        }
        catch(Exception ex) {
            SystemOut.StackTrace(ex);
        }

    }

    /**
     * ウィンドウ(W)メニューから閉じたウィンドウオブジェクトの選択用メニューを
     * 削除する.
     *
     * @param e 内部フレームイベント
     */
    protected void deleteWindowMenu(InternalFrameEvent e) {
        //子ウィンドウタイトル取得
        String frameTitle = ((ChildFrame)e.getSource()).getTitle();
        //ウィンドウ(W)メニューインスタンスを取得
        JMenuWindow windowMenu
            = (JMenuWindow)this.menuInstance.getMenu(getWindowMenuPosition());
        //ウィンドウ(W)メニューの総メニュー数を取得
        int menuCount = windowMenu.getItemCount();
        // ※セパレータもメニューの１項目として数えられてしまう。
        for(int i = windowMenu.getDefaultMenuCount(); i < menuCount; i++) {
             JMenuItem menuItem
               = this.menuInstance.getMenu(getWindowMenuPosition()).getItem(i);

                if(menuItem.getText().equals(frameTitle)) {
                    this.menuInstance.getMenu(getWindowMenuPosition()).remove(menuItem);
                    break;
                }
        }
    }

    /**
     * 全ての子ウィンドウを最小化する.
     */
    private void minAllWindow() {
        JInternalFrame[] frames = this.desktop.getAllFrames();
        try {
            for(int i = 0; i < frames.length; i++) {
                frames[i].setIcon(true);
            }

        }
        catch(PropertyVetoException pe) {
            SystemOut.StackTrace(pe);
        }
    }

    /**
     * 全ての子ウィンドウを閉じる.
     */
    private void closeAllWindow() {
        JInternalFrame[] frames = this.desktop.getAllFrames();
        try {
            for(int i = 0; i < frames.length; i++) {
                ((ChildFrame)frames[i]).dispose();
            }

            JMenuWindow windowMenu
                    = (JMenuWindow)menuInstance.getMenu(getWindowMenuPosition());

            int menuCount= windowMenu.getItemCount();

            for(int i = windowMenu.getDefaultMenuCount(); i < menuCount; i++) {
                //メニューアイテムを取得
                JMenuItem menuItem
                        = windowMenu.getItem(windowMenu.getDefaultMenuCount());
                //メニューを削除
                windowMenu.remove(menuItem);
            }
        }
        catch(Exception e) {
            SystemOut.StackTrace(e);
        }

    }
    /**
     * 子ウィンドウを縦に表示する.
     */
    private void showVerticalWindow() {
        JInternalFrame[] frames = this.desktop.getAllFrames();

		// 内部フレームの配列を取得する
		JInternalFrame[] iframes = desktop.getAllFrames();
		if (iframes.length == 0) {
			return;
        }

        // アイコン化されていないウィンドウ数を取得する
        int targetCount = 0;
        for(int j = 0; j < iframes.length; j++) {
            if(!frames[j].isIcon()) {
                targetCount++;
            }
        }
        // 子ウィンドウの縦サイズを取得
        int tate = desktop.getHeight() / targetCount;
        // 子ウィンドウの横サイズを取得
        int yoko = desktop.getWidth();
        int execCount = 0;
        for(int i = 0; i < iframes.length; i++) {
            if(!frames[i].isIcon()) {
                iframes[i].setSize(yoko,tate);
                iframes[i].setLocation(0,tate * execCount++);
            }
        }
    }

    /**
     * 子ウィンドウを横に表示する.
     */
    private void showHorizontalWindow() {
        JInternalFrame[] frames = this.desktop.getAllFrames();

		// 内部フレームの配列を取得する
		JInternalFrame[] iframes = desktop.getAllFrames();
		if (iframes.length == 0) {
			return;
        }

        // アイコン化されていないウィンドウ数を取得する
        int targetCount = 0;
        for(int j = 0; j < iframes.length; j++) {
            if(!frames[j].isIcon()) {
                targetCount++;
            }
        }
        // 子ウィンドウの縦サイズを取得
        int tate = desktop.getHeight();
        // 子ウィンドウの横サイズを取得
        int yoko = desktop.getWidth() / targetCount;
        int execCount = 0;
        for(int i = 0; i < iframes.length; i++) {
            if(!frames[i].isIcon()) {
                iframes[i].setSize(yoko,tate);
                iframes[i].setLocation(yoko * execCount++,0);
            }
        }
    }

    /**
     * ウィンドウ(W)メニューに子ウィンドウを選択するメニューを追加する.
     *
     * @param desktop 仮想デスクトップオブジェクト
     * @param frame MDI子ウィンドウクラスオブジェクト
     */
    private void setWindowMenu(JDesktopPane desktop,
                                 ChildFrame frame) {

        // ウィンドウ(W)メニューを取得
        JMenuWindow windowMenu
              = (JMenuWindow)this.menuInstance.getMenu(getWindowMenuPosition());
        // 子ウィンドウ選択メニューの選択状態を「非選択」に設定
        windowMenu.clearWindowMenuSelected();
        // メニュータイトルを設定する.
        JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(frame.getTitle());
        // ウィンドウ(W)メニュー内の対応するメニューを「選択」状態に設定
        menuItem.setSelected(true);
        // ウィンドウ選択メニューリスナを生成
        WindowMenuListener listener = new WindowMenuListener();
        //ウィンドウ選択メニューリスナを追加
        menuItem.addActionListener(listener);
        // ウィンドウ(W)メニューにメニューを追加する.
        this.menuInstance.getMenu(getWindowMenuPosition()).add(menuItem);

    }

    /**
     * 子ウィンドウを登録する.
     *
     * @param frame MDI子ウィンドウクラスオブジェクト
     */
    protected void registChildFrame(ChildFrame frame) {

        //子ウィンドウクローズ時のイベントリスナー
        ChildFrameInternalListener internalListener
                                        = new ChildFrameInternalListener();
        //子ウィンドウにウィンドウクローズ時のイベントリスナーを追加する
        frame.addInternalFrameListener(internalListener);
        //子ウィンドウをデスクトップに追加する
        this.desktop.add(frame);
        //子ウィンドウを表示する
        frame.setVisible(true);
        //子ウィンドウをアクティブにする
        try {
            frame.setSelected(true);
        }
        catch (PropertyVetoException e) {
            SystemOut.StackTrace(e);
        }
        //子ウィンドウを ウィンドウ(W)メニューに追加する
        setWindowMenu(desktop, frame);

    }

    /**
     * サブクラスで実装したcreateMenu内のメニューと、ウィンドウ(W)、ヘルプ(H)
     * メニューをメニューバーに追加する.
     */
    private void registMenu() {
        //ファイル(F)メニューを追加
        this.menuInstance.add(createFileMenu());

        //サブクラスで登録したメニューリストを取得
        ArrayList<JMenu> menuList = createExtendMenu();
        if(menuList != null) {
            //サブクラスで登録したメニューリストを追加
            for(int i = 0; i < menuList.size(); i++) {
                this.menuInstance.add((JMenu)menuList.get(i));
            }
        }
        // ウィンドウ(W)メニューを追加
        this.menuInstance.add(createWindowMenu());
        // ヘルプ(H)メニューを追加
        this.menuInstance.add(createHelpMenu());

    }

    /**
     * ファイル(F)メニューを生成する.
     *
     * @return ファイル(F)メニュー
     */
    private JMenu createFileMenu() {

        //ファイルメニューの設定
        JMenu fileMenu = new JMenu("ファイル(F)");
        fileMenu.setFont(this.getFont());
        fileMenu.setMnemonic(KeyEvent.VK_F);

        //サブクラスで追加したメニュー
        fileMenu = addFileMenu(fileMenu);
        //セパレータを追加
        fileMenu.addSeparator();
        //終了メニューを追加
        fileMenu.add(createExit());

        return fileMenu;
    }

    /**
     * ウィンドウ(W)メニューを作成する.
     *
     * @return ウィンドウメニューインスタンス
     */
    private JMenu createWindowMenu() {
        //デフォルトメニュー数
        int windowMenuItemCount = 0;

        //ウィンドウ(W)メニューの設定
        JMenuWindow windowMenu = new JMenuWindow("ウインドウ(W)");
        //フォントを設定
        windowMenu.setFont(this.getFont());
        //キーイベントを設定
        windowMenu.setMnemonic(KeyEvent.VK_W);
        //ウィンドウ(W)メニューにメニューを追加する.
        //縦に表示(O)を追加
        windowMenu.add(createTateHyouji());
        windowMenuItemCount++;
        //横に表示
        windowMenu.add(createYokoHyouji());
        windowMenuItemCount++;
        //重ねて表示
        windowMenu.add(createKasaneHyouji());
        windowMenuItemCount++;
        //並べて表示
        windowMenu.add(createNarabeHyouji());
        windowMenuItemCount++;
        //全て最小化
        windowMenu.add(createAllMinWindow());
        windowMenuItemCount++;
        //全て閉じる
        windowMenu.add(createAllCloseWindow());
        windowMenuItemCount++;
        // セパレータを追加
        windowMenu.addSeparator();
        windowMenuItemCount++;

        //デフォルトメニュー数を設定
        windowMenu.setDefaultMenuCount(windowMenuItemCount);

        return windowMenu;
    }

    /**
     * 終了(X)メニューを生成する.
     *
     * @return 終了メニューアイテム
     */
    private JMenuItem createExit() {
        JMenuItem menuItem = new JMenuItem("終了(X)");
        menuItem.setFont(this.getFont());
        menuItem.setMnemonic(KeyEvent.VK_X);
        menuItem.setAccelerator(
             KeyStroke.getKeyStroke(KeyEvent.VK_X,        /* Xキー    */
                                    InputEvent.CTRL_MASK) /* CTRLキー */
        );

        menuItem.addActionListener(new ExitMenuListener());
        return menuItem;
    }

    /**
     * 縦に表示(ウィンドウ(W)メニュー).
     *
     * @return 縦に表示メニューアイテム
     */
    private JMenuItem createTateHyouji() {
        JMenuItem menuItem = new JMenuItem("縦に表示(O)");
        menuItem.setFont(this.getFont());
        menuItem.setMnemonic(KeyEvent.VK_O);          /* Oキー    */
        menuItem.setAccelerator(
             KeyStroke.getKeyStroke(KeyEvent.VK_O,        /* Oキー    */
                                    InputEvent.CTRL_MASK) /* CTRLキー */
                                    );

        menuItem.addActionListener(new VerticalMenuListener());
        return menuItem;

    }

    /**
     * 横に表示 (ウィンドウ(W)メニュー).
     *
     * @return 横に表示メニューアイテム
     */
    private JMenuItem createYokoHyouji() {
        JMenuItem menuItem = new JMenuItem("横に表示(V)");
        menuItem.setFont(this.getFont());
        menuItem.setMnemonic(KeyEvent.VK_V);          /* Vキー    */
        menuItem.setAccelerator(
             KeyStroke.getKeyStroke(KeyEvent.VK_V,        /* Vキー    */
                                    InputEvent.CTRL_MASK) /* CTRLキー */
                                    );
        menuItem.addActionListener(new HorizontalMenuListener());
        return menuItem;
    }

    /**
     * 重ねて表示 (ウィンドウ(W)メニュー).
     *
     * @return 重ねて表示メニューアイテム
     */
    private JMenuItem createKasaneHyouji() {
        JMenuItem menuItem = new JMenuItem("重ねて表示(C)");
        menuItem.setFont(this.getFont());
        menuItem.setMnemonic(KeyEvent.VK_C);          /* Cキー    */
        menuItem.setAccelerator(
             KeyStroke.getKeyStroke(KeyEvent.VK_C,        /* Cキー    */
                                    InputEvent.CTRL_MASK) /* CTRLキー */
                                    );
        menuItem.addActionListener(new CascadeMenuListener());

        return menuItem;
    }

    /**
     * 並べて表示 (ウィンドウ(W)メニュー).
     *
     * @return 並べて表示メニューアイテム
     */
    private JMenuItem createNarabeHyouji() {
        JMenuItem menuItem = new JMenuItem("並べて表示(T)");
        menuItem.setFont(this.getFont());
        menuItem.setMnemonic(KeyEvent.VK_T);          /* Tキー    */
        menuItem.setAccelerator(
             KeyStroke.getKeyStroke(KeyEvent.VK_T,        /* Tキー    */
                                    InputEvent.CTRL_MASK) /* CTRLキー */
                                    );

        return menuItem;
    }

    /**
     * 全て最小化メニュー (ウィンドウ(W)メニュー).
     *
     * @return 全て最小化メニューアイテム
     */
    private JMenuItem createAllMinWindow() {
        JMenuItem menuItem = new JMenuItem("全て最小化(N)");
        menuItem.setFont(this.getFont());
        menuItem.setMnemonic(KeyEvent.VK_N);          /* Tキー    */
        menuItem.setAccelerator(
             KeyStroke.getKeyStroke(KeyEvent.VK_N,        /* Tキー    */
                                    InputEvent.CTRL_MASK) /* CTRLキー */
                                    );
        menuItem.addActionListener(new AllMinMenuListener());

        return menuItem;
    }
    /**
     * 全て閉じる (ウィンドウ(W)メニュー).
     *
     * @return 全て閉じるメニューアイテム
     */
    private JMenuItem createAllCloseWindow() {
        JMenuItem menuItem = new JMenuItem("全て閉じる(K)");
        menuItem.setFont(this.getFont());
        menuItem.setMnemonic(KeyEvent.VK_K);          /* Kキー    */
        menuItem.setAccelerator(
             KeyStroke.getKeyStroke(KeyEvent.VK_K,        /* Kキー    */
                                    InputEvent.CTRL_MASK) /* CTRLキー */
        );
        menuItem.addActionListener(new AllCloseMenuListener());

        return menuItem;
    }

	/**
     * 重ねて表示 (ウィンドウ(W)メニュー).
     *
     * @return 重ねて表示メニューアイテム
     */
	private void cascadeWindow() {
		// 内部フレームの配列を取得する
		JInternalFrame[] iframes = desktop.getAllFrames();
        // 内部フレームが1つも存在しない場合
		if (iframes.length == 0) {
            // 処理を抜ける
			return;
        }

		// 現在のxnewとynewの値を保存しておく
		int savexnew = xnew, saveynew = ynew;
		xnew = xincrement; ynew = yincrement;
		// 逆順に表示する
		for (int i = iframes.length -1; i >= 0; i--) {
			if (!iframes[i].isIcon()) {
				iframes[i].setSize(200,200);
				iframes[i].setLocation(xnew, ynew);
				updateLocation(iframes[i]);
			}
		}
		// xnewとynewの値を復元する
		xnew = savexnew; ynew = saveynew;
	}

	/**
     * 子ウィンドウの表示位置を更新する.<br>
     * ※当メソッドはcascadeWindowメソッド内から呼ばれる事を前提として作成されて
     * いる
     *
     * @param frame 表示位置更新対象の子ウィンドウ
     */
	private void updateLocation(JInternalFrame frame) {
		// デスクトップの内部境界矩形を求める
		Insets is = desktop.getInsets();
		// デスクトップの内部幅を求める
		int width = desktop.getWidth() - is.left - is.right;
		// デスクトップの内部高を求める
		int height = desktop.getHeight() - is.top - is.bottom;

		// 溢れれば、初期位置に戻す
		if (frame.getX() + 200 + xincrement > width ||
			frame.getY() + 200 + yincrement > height) {
			xnew = xincrement; ynew = yincrement;
		}
		else {
			xnew += xincrement; ynew += yincrement;
		}
	}


    /**
     * ヘルプ(H)メニューを生成する.
     *
     * @return ヘルプメニュー
     */
    private JMenu createHelpMenu() {
        // ヘルプ(H)メニューの設定
        JMenu helpMenu = new JMenu("ヘルプ(H)");
        helpMenu.setFont(this.getFont());
        helpMenu.setMnemonic(KeyEvent.VK_H);
        // サブクラスで追加したメニュー
        addHelpMenu(helpMenu);
        //
        helpMenu.add(createAboutVersionMenu());
        return helpMenu;
    }

	/**
     * バージョン情報のメニューを生成する (ヘルプ(H)メニュー).
     *
     * @return バージョン情報メニューアイテム
     */
    private JMenuItem createAboutVersionMenu() {
        JMenuItem menuItem = new JMenuItem("バージョン情報(A)");
        menuItem.setFont(this.getFont());
        menuItem.setMnemonic(KeyEvent.VK_A);          /* Aキー    */
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_A,        /* Aキー    */
                                       InputEvent.CTRL_MASK) /* CTRLキー */
        );
        menuItem.addActionListener(new AboutMenuListener());
        return menuItem;
    }

    /**
     * アプリケーション終了時に実行するメソッド.
     */
    private void exitApplication() {
        System.exit(0);
    }

    /**
     * ファイル(F)メニューのメニューバー内位置を取得する.<br>
     * ファイル(F)メニューは、常に左から1番目に設定されます.
     *
     * @return ウィンドウメニュー位置インデックス
     */
    protected int getFileMenuPosition() {
        return 0;
    }

    /**
     * ウィンドウ(W)メニューのメニューバー内位置を取得する.<br>
     * ウィンドウメニューは、常に右から２番目に設定されます.
     *
     * @return ウィンドウメニュー位置インデックス
     */
    protected int getWindowMenuPosition() {
        return menuInstance.getMenuCount() - 2;
    }

    /**
     * ヘルプ(H)メニューの表示位置を取得する.<br>
     * ヘルプメニューは、常に一番右に設定されます.
     *
     * @return ヘルプメニュー位置インデックス
     */
    protected int getHelpMenuPosition() {
        return menuInstance.getMenuCount() - 1;
    }


//------------------------------------------------------------------------------
// abstractメソッド
//------------------------------------------------------------------------------
    /**
     * ファイル(F)メニューにメニューを追加する.
     * ※追加するメニューについてはサブクラスで実装する事.
     *
     * @param fileMenu ファイル(F)メニュー
     * @return ファイル(F)メニュー
     */
    protected abstract JMenu addFileMenu(JMenu fileMenu);

    /**
     * ヘルプ(H)メニューにメニューを追加する.
     * ※追加するメニューについてはサブクラスで実装する事.
     *
     * @param helpMenu ヘルプ(H)メニュー
     * @return ヘルプ(H)メニュー
     */
    protected abstract JMenu addHelpMenu(JMenu helpMenu);

    /**
     * バージョン情報のフレームを取得する.<br>
     * ※取得するバージョン情報フレームについてはサブクラスで実装する事.
     *
     * @return ダイアログボックス
     */
    protected abstract JDialog getAboutFrame();

    /**
     * メニューを生成する.<br>
     * ※生成するメニューについてはサブクラスで実装する事.
     *
     * @return ArrayList メニューリスト
     */
    protected abstract ArrayList<JMenu> createExtendMenu();


//------------------------------------------------------------------------------
// イベントリスナークラス
//------------------------------------------------------------------------------
    /**
     * ファイル(F)メニューで終了(X)メニューを選択したときのアクションリスナー
     * クラス.
     *
     * @version 1.00
     * @author Ippei.Kitajima
     */
    private class ExitMenuListener implements ActionListener {

        /**
         * アクションイベント処理実行メソッド.
         *
         * @param e アクションイベント
         */
        public void actionPerformed(ActionEvent e) {
            try {
                //
                exitApplication();
            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
    }


    /**
     * ウィンドウ(W)メニューでウィンドウを選択した時のアクションリスナークラス.
     *
     * @author Ippei.Kitajima
     * @version 1.00
     */
    private class WindowMenuListener implements ActionListener {

        /**
         * アクションイベント処理実行メソッド.
         *
         * @param e アクションイベント
         */
        public void actionPerformed(ActionEvent e) {
            try {
                selectChildWindow(e);
            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
    }

    /**
     * 子ウィンドウを閉じた時のインターナルリスナークラス.
     *
     * @author Ippei.Kitajima
     * @version 1.00
     */
    protected class ChildFrameInternalListener extends InternalFrameAdapter {

        /**
         * 子ウィンドウが閉じた時の処理を実行する.
         *
         * @param e インターナルフレームイベント.
         */
        public void internalFrameClosing(InternalFrameEvent e) {
            deleteWindowMenu(e);
        }
    }

    /**
     * バージョン情報メニューを選択した時のアクションリスナークラス.
     *
     * @author Ippei.Kitajima
     * @version 1.00
     */
    protected class AboutMenuListener implements ActionListener {

        /**
         * アクションイベント処理を実行する.
         *
         * @param e アクションイベント
         */
        public void actionPerformed(ActionEvent e) {
            try {

                getAboutFrame().setVisible(true);
            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
    }

    /**
     * 全て最小化()を選択した時のアクションリスナークラス.
     *
     * @author Ippei.Kitajima
     * @version 1.00
     */
    protected class AllMinMenuListener implements ActionListener {

        /**
         * アクションイベント処理実行メソッド.
         *
         * @param e アクションイベント
         */
        public void actionPerformed(ActionEvent e) {
            try {
                minAllWindow();

            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
    }

    /**
     * 重ねて表示()を選択した時のアクションリスナークラス.
     *
     * @author Ippei.Kitajima
     * @version 1.00
     */
    protected class CascadeMenuListener implements ActionListener {
        /**
         * アクションイベント処理実行メソッド.
         *
         * @param e アクションイベント
         */
        public void actionPerformed(ActionEvent e) {
            try {
                cascadeWindow();

            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
    }

    /**
     * 全て閉じるメニュー選択時のアクションリスナークラス.
     *
     * @author Ippei.Kitajima
     * @version 1.00
     */
    protected class AllCloseMenuListener implements ActionListener {
        /**
         * アクションイベント処理実行メソッド.
         *
         * @param e アクションイベント
         */
        public void actionPerformed(ActionEvent e) {
            try {
                closeAllWindow();
            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
    }

    /**
     * 縦に表示メニュー選択時のアクションリスナークラス.
     *
     * @author Ippei.Kitajima
     * @version 1.00
     */
    protected class VerticalMenuListener implements ActionListener {
        /**
         * アクションイベント処理実行メソッド.
         *
         * @param e アクションイベント
         */
        public void actionPerformed(ActionEvent e) {
            try {
                showVerticalWindow();

            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
    }

    /**
     * 横に表示メニュー選択時のアクションリスナークラス.
     *
     * @author Ippei.Kitajima
     * @version 1.00
     */
    protected class HorizontalMenuListener implements ActionListener {
        /**
         * アクションイベント処理実行メソッド.
         *
         * @param e アクションイベント
         */
        public void actionPerformed(ActionEvent e) {
            try {
                showHorizontalWindow();

            }
            catch(Exception ex) {
                SystemOut.StackTrace(ex);
            }
        }
    }

}