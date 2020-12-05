//------------------------------------------------------------------------------
// KITASOFT API
// SDIFrame.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/13	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

/**
 * SDIフレームクラス.
 *
 * @author Ippei.Kitajima
 * @version 1.00
 */
public abstract class SDIFrame extends JFrameEx
{
    /**
	 *
	 */
	private static final long serialVersionUID = 1958464028616474515L;

	/**
     * コンストラクタ.
     */
    public SDIFrame() {
        super();
        //レイアウトをフリーに設定
        this.getContentPane().setLayout(null);
        //フレームを閉じるときに実行される処理(フレーム解放)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}