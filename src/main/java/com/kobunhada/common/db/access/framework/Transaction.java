//------------------------------------------------------------------------------
// kobunhada's common libs.
// TransactionQueryBase.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.db.access.framework;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;

import com.kobunhada.common.debug.SystemOut;


/**
 * クエリートランザクションクラス.
 *
 * @author kobunhada
 * @version 1.00
 */
public abstract class Transaction {

    /** タスクリスト. */
	private ArrayList<TransactionQueryBase> taskList = new ArrayList<TransactionQueryBase>();
    /** タスク用パラメタリスト. */
	private ArrayList<ArrayList<ParameterElementBase>> paramsList= new ArrayList<ArrayList<ParameterElementBase>>();

    /**
     * クエリとパラメタをセットする.
     * クエリにパラメタが1件存在する場合はこちらを使用する事.<br>
     *
     * @param task クエリーオブジェクト
     * @param param タスクにセットするパラメタ
     */
	public void setTask(TransactionQueryBase task, ParameterElementBase param)
    {
        ArrayList<ParameterElementBase> paramList = new ArrayList<ParameterElementBase>();
        paramList.add(param);
        setTask(task, paramList);
    }

    /**
     * タスクをセットする.<br>
     * クエリにパラメタが存在しない場合はこちらを使用する事.<br>
     *
     * @param task クエリーオブジェクト
     */
    public void setTask(TransactionQueryBase task) {
        setTask(task, new ArrayList<ParameterElementBase>());
    }

    /**
     * タスクをセットする.<br>
     * クエリにパラメタが複数件存在する場合はこちらを使用する事.<br>
     *
     * @param task クエリーオブジェクト
     * @param paramList タスクにセットするパラメタリスト
     */
	public void setTask(TransactionQueryBase task, ArrayList<ParameterElementBase> paramList) {
        taskList.add(task);
        paramsList.add(paramList);
    }

    /**
     * クエリとパラメタをクリアする.
     */
    public void clearTask() {
        taskList = new ArrayList<TransactionQueryBase>();
        paramsList = new ArrayList<ArrayList<ParameterElementBase>>();
    }

    /**
     * タスクを実行する.
     *
     * @throws java.sql.SQLException SQL例外
     */
	public void executeTask() throws SQLException {
        //コネクション取得
        Connection conn = getConnection();

        //自動コミットをOFFにする
        conn.setAutoCommit(false);

        try {
            //トランザクションを実行する
            for(int i = 0; i < taskList.size(); i++) {
                //n件目のパラメタリストを取り出す
                ArrayList<ParameterElementBase> paramList = paramsList.get(i);
                //n件目のタスクを実行する
                ((TransactionQueryBase)taskList.get(i)).execute(
                        paramList,
                        conn
                    );
            }
            //コミットを実行する.
            conn.commit();
        }
        catch(SQLException se) {
            //ロールバックを実行する.
            conn.rollback();
            throw se;
        }
        catch(Exception ex) {
            SystemOut.StackTrace(ex);
        }
        finally {
            conn.close();
            clearTask();
        }
	}

    /**
     * コネクションを取得する.<br>
     * ※当メソッドの実装は、各サブクラスにて行う.
     *
     * @return コネクションオブジェクト
     */
    protected abstract Connection getConnection();


}

