//------------------------------------------------------------------------------
// KITASOFT API
// JTableEx.java
//
// Copyright(c) 2005-2009 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0.0	2005/11/27	IPPEI.KITAJIMA	新規作成
// ver 1.0.1    2007/02/09  IPPEI.KITAJIMA	マウスイベントを追加
// ver 1.0.2    2007/02/24  IPPEI.KITAJIMA  行操作メソッドを全面見直し
// ver 1.0.3    2007/04/05  IPPEI.KITAJIMA  列ヘッダ再設定メソッド追加
// ver 1.0.4    2009/02/10  IPPEI.KITAJIMA  doClickRightMouthButton(Point)メソッド削除
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * JTable拡張クラス.
 *
 * @author	IPPEI.KITAJIMA
 * @version 1.0.4
 */
public abstract class JTableEx extends JTable
implements MouseInputListener, KeyListener {

    /**
	 *
	 */
	private static final long serialVersionUID = 3676585944433371829L;
	//----------------------------------------
    // private変数
    //----------------------------------------
    /** TableModelオブジェクト. */
    private DefaultTableModel   defTableModel = null;
    /** テーブル内編集可能判定フラグ. */
    private boolean cellEditableFlag = true;
    /** データリスト. */
    private ArrayList<JTableExRowDataBean> rowDataList = new ArrayList<JTableExRowDataBean>();
    /** Shiftキー押下中判断フラグ. */
    private boolean isShiftKeyPressed = false;

    /**
     * デフォルトコンストラクタ.
     */
    public JTableEx() {
        super();
        // マウスイベントリスナを追加
        this.addMouseListener(this);
        // キーイベントリスナを追加
        this.addKeyListener(this);

    }

    /**
     * ヘッダ付コントラクタ.<br>
     * 引数colNamesには、String配列を指定して下さい.
     *
     * @param colNames String配列のヘッダ
     * @param row デフォルト行数
     */
    public JTableEx(String[] colNames, int row) {
        super();
        // カラムと行数指定
        this.setColNames(colNames,row);
        // マウスイベントリスナを追加
        this.addMouseListener(this);
        // キーイベントリスナを追加
        this.addKeyListener(this);
    }

    /**
     * ヘッダ付コンストラクタ.<br>
     * ※引数colNamesには、String型を格納したArrayListを指定して下さい.
     *
     * @param colNames ArrayList型のヘッダ。
     * @param row デフォルト行数
     */
    public JTableEx(ArrayList<String> colNames, int row) {
        super();
        // カラムと行数指定
        this.setColNames(colNames, row);
        // マウスイベントリスナを追加
        this.addMouseListener(this);
        // キーイベントリスナを追加
        this.addKeyListener(this);
    }

    /**
     * ヘッダ付コンストラクタ(ヘッダ付、カラム幅指定).<br>
     *
     * @param colNames ヘッダ名称の配列
     * @param row デフォルト行数
     * @param colSize 各カラム幅指定
     */
    public JTableEx(String[] colNames, int row, int[] colSize) {
        super();
        // カラムと行数指定
        this.setColNames(colNames, row);
        // カラム幅指定
        this.setColSize(colSize);
        // マウスイベントリスナを追加
        this.addMouseListener(this);
        // キーイベントリスナを追加
        this.addKeyListener(this);
    }

    /**
     * ヘッダ付コンストラクタ(ヘッダ付、カラム幅指定).<br>
     *
     * @param colNames ヘッダ名称の配列
     * @param row デフォルト行数
     * @param colSize 各カラム幅指定
     */
    public JTableEx(ArrayList<String> colNames, int row, int[] colSize) {
        super();
        // カラムと行数指定
        this.setColNames(colNames,row);
        // カラム幅指定
        this.setColSize(colSize);
        // マウスイベントリスナを追加
        this.addMouseListener(this);
        // キーイベントリスナを追加
        this.addKeyListener(this);
    }

    /**
     * ヘッダを追加する.
     *
     * @param colNames ヘッダ名称の配列
     * @param row デフォルト行数
     */
    public void setColNames(String[] colNames, int row) {
        if(defTableModel == null) {
            // デフォルトテーブルモデルのインスタンス取得
            defTableModel = new DefaultTableModel(colNames, row);
            // 取得したインスタンスをテーブルにセット
            super.setModel(defTableModel);
        }
    }

    /**
     * ヘッダを再設定する.<br>
     * 初回設定時以外は、当メソッドにてカラムの再設定を実行して下さい.
     *
     * @param colNames ヘッダ名称の配列
     * @param row デフォルト行数
     */
    public void resetColNames(String[] colNames, int row) {
        defTableModel = null;
        setColNames(colNames, row);
    }


    /**
     * ヘッダを追加する.
     *
     * @param colNames ヘッダ名称の配列
     * @param row デフォルト行数
     */
    public void setColNames(ArrayList<String> colNames, int row) {
        if(defTableModel == null) {
            // デフォルトテーブルモデルのインスタンス取得
            defTableModel = new DefaultTableModel(new Vector<String>(colNames), row);
            // 取得したインスタンスをテーブルにセット
            super.setModel(defTableModel);
        }
    }

    /**
     * ヘッダを再設定する.<br>
     * 初回設定時以外は、当メソッドにてカラムの再設定を実行して下さい.
     *
     * @param colNames ヘッダ名称の配列
     * @param row デフォルト行数
     */
    public void resetColNames(ArrayList<String> colNames, int row) {
        defTableModel = null;
        setColNames(colNames, row);
    }


    /**
     * カラム幅を設定する.
     *
     * @param colSize 各カラム幅をint型の配列で指定
     */
    public void setColSize(int[] colSize) {

        // 自動でカラムサイズを割り当てない
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // カラムモデルを取得
        TableColumnModel colModel = super.getColumnModel();
        // カラム幅をセットする
        for(int i = 0; i < colModel.getColumnCount(); i++) {
            // 対象のカラムを取得する
            TableColumn col = colModel.getColumn(i);
            // カラム幅を指定する
            col.setPreferredWidth(colSize[i]);
        }

    }

    /**
     * 行データリストを設定する.
     *
     * @param rowDataList 行データリスト
     */
    public void setRowList(List<JTableExRowDataBean> rowDataList) {

        // 項目一覧を再設定
        this.rowDataList = new ArrayList<JTableExRowDataBean>(rowDataList);
        // 行をクリア
        this.removeTableAllRows();
        // 項目一覧が0件の場合
        if(this.rowDataList == null || this.rowDataList.size() == 0) {
            // 処理を抜ける
            return;
        }
        // テーブル表示用行データリスト
        ArrayList<List<String>> tableRowList = new ArrayList<List<String>>();
        // テーブル表示用行データリストにデータを追加
        for(int i = 0; i < rowDataList.size(); i++) {
            // 行データリストから行データオブジェクトを取得
            JTableExRowDataBean rowData = rowDataList.get(i);
            // 行データオブジェクトから表示用行データを生成
            ArrayList<String> row = createTableRowData(i + 1, rowData);
            // 表示用行データリストに表示用データを追加
            tableRowList.add(row);
        }
        // テーブルにリストを追加
        this.addTableRows(tableRowList);

    }

    /**
     * データリストを取得する.
     *
     * @return データリスト
     */
    public ArrayList<JTableExRowDataBean> getRowList() {
        return this.rowDataList;
    }

    /**
     * テーブルに行を複数行追加する.
     *
     * @param list 行リスト
     */
    private void addTableRows(ArrayList<List<String>> list) {

        for(int i=0; i<list.size(); i++) {
            this.addTableRow((ArrayList<String>)list.get(i));
        }
    }

    /**
     * テーブルに行を1行追加する.
     *
     * @param row 1行分のデータ
     */
    @SuppressWarnings("unchecked")
	private void addTableRow(ArrayList<String> row) {
        defTableModel.addRow(new Vector(row));
    }

    /**
     * 指定行にデータを設定する.
     *
     * @param index 行インデックス
     * @param row 行データリスト
     */
    private void setTableRow(int index, ArrayList<String> row) {
        for(int i = 0; i < row.size(); i++) {
            this.setValueAt((String)row.get(i), index, i);
        }
    }

    /**
     * 指定行を削除する.
     *
     * @param rowIndex 行インデックス
     */
    private void removeTableRow(int rowIndex) {
        defTableModel.removeRow(rowIndex);
    }

    /**
     * テーブル内の行をすべて削除する.
     */
    private void removeTableAllRows() {
        try {
            defTableModel.setRowCount(0);
        }
        catch(Throwable th) {
            // SystemOut.StackTrace(th);
        }
//        while(this.getRowCount() > 0) {
//            defTableModel.removeRow(0);
//        }
    }

    /**
     * セルの編集可・不可を設定する.
     *
     * @param cellEditableFlag true:編集可 false:編集不可
     */
    public void setCellEditable(boolean cellEditableFlag) {
        this.cellEditableFlag = cellEditableFlag;
    }

    /**
     * セルが編集可能かどうか判断する.
     *
     * @param row 行
     * @param column 列
     * @return true:編集可 false:編集不可
     */
    public boolean isCellEditable(int row, int column) {
        // セル編集フラグがfalseの場合は全ての編集を禁止する。
        if(cellEditableFlag == true) {
            // 親クラスのisCellEditableメソッドを呼ぶ。
            return super.isCellEditable(row, column);
        }
        else {
            // row と column をチェックすれば特定のカラムのみ禁止もできる
            return(false);
        }
    }

    /**
     * Shiftキー押下フラグを設定する.
     *
     * @param flg Shiftキー押下フラグ
     */
    public void setShiftKeyPressedFlg(boolean flg) {
        this.isShiftKeyPressed = flg;
    }

    /**
     * シフトキーが押下中か否かを判定する.
     *
     * @return true:押下中である、false:それ以外
     */
    public boolean getShiftKeyPressedFlg() {
        return this.isShiftKeyPressed;
    }

    /**
     * 行を追加する.
     *
     * @param rowData 行データ
     */
    public void addRow(JTableExRowDataBean rowData) {
        // テーブル用行データを取得
        ArrayList<String> row = createTableRowData(this.rowDataList.size() + 1, rowData);
        // テーブルに行を追加
        this.addTableRow(row);
        // 項目一覧データに追加
        this.rowDataList.add(rowData);
    }

    /**
     * 行データリストを追加する.
     *
     * @param addList 行データリスト
     */
	public void addRowList(ArrayList<JTableExRowDataBean> addList) {
        for (int i = 0; i < addList.size(); i++) {
            JTableExRowDataBean rowData
                                = rowDataList.get(i);
            this.addRow(rowData);
        }
    }

    /**
     * 指定行を取得する.
     *
     * @param rowIndex 行インデックス
     * @return 指定行データオブジェクト
     */
	public JTableExRowDataBean getRow(int rowIndex) {
        return rowDataList.get(rowIndex);
    }

    /**
     * 行にデータを設定する.
     *
     * @param rowIndex 行インデックス
     * @param rowData 行データ
     */
    public void setRow(int rowIndex, JTableExRowDataBean rowData) {
        //
        this.rowDataList.set(rowIndex, rowData);
        //
        ArrayList<String> row = createTableRowData(rowIndex + 1, rowData);
        //
        setTableRow(rowIndex, row);
    }

    /**
     * 指定行を削除する.
     *
     * @param rowIndex 行インデックス
     */
	public void removeRow(int rowIndex) {
        // データリストから行を追加
        this.rowDataList.remove(rowIndex);
        // テーブルから行を削除
        removeTableRow(rowIndex);
    }

    /**
     * 指定行のデータを全て削除する.
     *
     * @param rowIndexList 行インデックス
     */
	public void removeRowList(int[] rowIndexList) {

        int removeCount = 0;
        for(int i = 0; i < this.rowDataList.size(); i++) {
            if(i == rowIndexList[removeCount] - removeCount) {
                this.removeRow(i);
                removeCount++;
                i--;
            }
        }
    }

    /**
     * クリアメソッド.
     */
    public void clear() {
        // 行データリストを初期化
        this.rowDataList = new ArrayList<JTableExRowDataBean>();
        // テーブル表示用データをクリア
        this.removeTableAllRows();
    }

    /**
     * 選択行の項目を取得する.
     *
     * @return 項目オブジェクト
     */
	public JTableExRowDataBean getSelectedRowData() {
        return getRow(this.getSelectedRow());
	}

    /**
     * 全選択行の行データリストを取得する.<br>
     * 選択行が無い場合は サイズ0のArrayListインスタンスを返す.
     *
     * @return 全選択行の行データリスト
     */
	public ArrayList<JTableExRowDataBean> getSelectedRowDataList() {
        ArrayList<JTableExRowDataBean> selectedList = new ArrayList<JTableExRowDataBean>();

        int[] selectedRows = this.getSelectedRows();

        for(int i = 0; i < selectedRows.length; i++) {
            selectedList.add(this.getRow(selectedRows[i]));
        }

        return selectedList;
    }

    /**
     * テーブル表示用行データを生成する.
     *
     * @param sequence 行番号
     * @param rowData 行データオブジェクト
     * @return テーブル表示用行データ
     */
    protected abstract ArrayList<String> createTableRowData(
                                            int sequence,
                                            JTableExRowDataBean rowData);

    /**
     * TABキーを押した場合に処理するメソッド.
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
     * 指定行を選択状態にする.
     *
     * @param rowIndex 行インデックス
     */
    protected void setSelectRow(int rowIndex) {
        // 行を選択状態に設定する
        this.setRowSelectionInterval(rowIndex, rowIndex);
    }

    /**
     * マウスクリック時イベント.
     *
     * @param e マウスイベント
     */
    public final void mouseClicked(MouseEvent e) {

        // クリック回数を取得
        int clickCount = e.getClickCount();

        // クリック回数が1回の場合
        if(clickCount == 1) {
            // 右クリック時の処理
            if(SwingUtilities.isRightMouseButton(e)) {
                doClickRightMouseButton(e);
            }
            // 中ボタンクリック時の処理
            else if(SwingUtilities.isMiddleMouseButton(e)) {
                doClickMiddleMouseButton(e);
            }
            // 左クリック時の処理
            else if(SwingUtilities.isLeftMouseButton(e)) {
                doClickLeftMouseButton(e);
            }
        }
        // クリック回数が2回の場合
        else if(clickCount == 2) {
            // 右クリック時の処理
            if(SwingUtilities.isRightMouseButton(e)) {
                doDblClickRightMouseButton(e);
            }
            // 中ボタンクリック時の処理
            else if(SwingUtilities.isMiddleMouseButton(e)) {
                doDblClickMiddleMouseButton(e);
            }
            // 左クリック時の処理
            else if(SwingUtilities.isLeftMouseButton(e)) {
                doDblClickLeftMouseButton(e);
            }
        }
    }

    /**
     * 右クリック時に実行されるメソッド.<br>
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
    protected void doClickRightMouseButton(MouseEvent e) {}

    /**
     * 中クリック時に実行されるメソッド.<br>
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
    protected void doClickMiddleMouseButton(MouseEvent e) {}

    /**
     * 左クリック時に実行されるメソッド.<br>
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
    protected void doClickLeftMouseButton(MouseEvent e) {}

    /**
     * 右ダブルクリック時に実行されるメソッド.<br>
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
    protected void doDblClickRightMouseButton(MouseEvent e) {}

    /**
     * 中ダブルクリック時に実行されるメソッド.<br>
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
    protected void doDblClickMiddleMouseButton(MouseEvent e) {}

    /**
     * 左ダブルクリック時に実行されるメソッド.<br>
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
    protected void doDblClickLeftMouseButton(MouseEvent e) {}

    /**
     * マウスボタンを押した時に発生するイベント.<br>
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
    protected void doMousePressed(MouseEvent e) {}

    /**
     * マウスボタンを放した時に発生するイベント.<br>
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
    protected void doMouseReleased(MouseEvent e) {}

    /**
     * マウスカーソルが当コンポーネントに入ってきた時に発生するイベント.<br>
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
    protected void doMouseEntered(MouseEvent e) {}

    /**
     * マウスカーソルが当コンポーネントを離れた時に発生するイベント.<br>
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
    protected void doMouseExited(MouseEvent e) {}

    /**
     * マウスをドラッグした時に発生するイベント.<br>
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
	protected void doMouseDragged(MouseEvent e) {}

    /**
     * マウスボタンを押さずに、マウスカーソルをコンポーネント上に移動する時に発生するイベント.
     * ※当メソッドの実装は、サブクラスにて行う.
     *
     * @param e マウスイベント
     */
	protected void doMouseMoved(MouseEvent e) {}

//-----------------------------------------------------------------------------
// イベントリスナメソッド
//-----------------------------------------------------------------------------

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
        // キーがTABキーの場合
        if(e.getKeyCode() == KeyEvent.VK_TAB) {
            if(isShiftKeyPressed) {
                tabKeyPressedWithShift();
            }
            else {
                tabKeyPressed();
            }
//            // キーコードを0にセット
//            e.setKeyCode(0);
        }
        // Shiftキーの場合
        else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            // Shiftキー押下フラグをfalseに設定
            isShiftKeyPressed = true;
        }
    }

    /**
     * キー押下時イベント処理.
     *
     * @param e キーイベント
     */
    public void keyReleased(KeyEvent e) {
        // Shiftキーの場合
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            // Shiftキー押下フラグをfalseに設定
            isShiftKeyPressed = false;
        }
    }

    /**
     * マウスボタンを押した時に発生するイベント.
     *
     * @param e マウスイベント
     */
    public void mousePressed(MouseEvent e) {
        doMousePressed(e);
    }

    /**
     * マウスボタンを放した時に発生するイベント.
     *
     * @param e マウスイベント
     */
    public void mouseReleased(MouseEvent e) {
        doMouseReleased(e);
    }

    /**
     * マウスカーソルが当コンポーネントに入ってきた時に発生するイベント.
     *
     * @param e マウスイベント
     */
    public void mouseEntered(MouseEvent e) {
        doMouseEntered(e);
    }

    /**
     * マウスカーソルが当コンポーネントを離れた時に発生するイベント.
     *
     * @param e マウスイベント
     */
    public void mouseExited(MouseEvent e) {
        doMouseExited(e);
    }

    /**
     * マウスをドラッグした時に発生するイベント.
     *
     * @param e マウスイベント
     */
    public void mouseDragged(MouseEvent e) {
    	doMouseDragged(e);
    }

    /**
     * マウスボタンを押さずに、マウスカーソルをコンポーネント上に移動する時に発生するイベント.
     *
     * @param e マウスイベント
     */
    public void mouseMoved(MouseEvent e) {
    	doMouseMoved(e);
    }



}