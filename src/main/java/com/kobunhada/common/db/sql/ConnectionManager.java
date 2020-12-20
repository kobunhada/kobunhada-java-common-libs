//------------------------------------------------------------------------------
// kobunhada's common libs.
// ConnectionManager.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.db.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.kobunhada.common.debug.SystemOut;


/**
 * コネクション管理基底クラス.
 * 
 * @author kobunhada
 * @version 1.00
 */
public abstract class ConnectionManager {

    /** コネクション. */
    private Connection conn = null;
    
    public ConnectionManager() {
    	// 処理なし
    }
    
    /**
     * Connectionを取得する.
     * 
     * @return Connection コネクションインスタンス
     */
    public Connection getConnection() {
        try {
            if(conn == null) {
                // ドライバクラスをロード
                Class.forName(getDriver());
            }
            // データベースへ接続
            conn = DriverManager.getConnection(getURL());
        }
        catch(Exception e) {
            SystemOut.StackTrace(e);
        }

        return conn;
    }
    /**
     * Connectionをクローズする.
     * 
     * @throws java.sql.SQLException SQL例外
     */
    public void close() throws SQLException {
        conn.close();
        conn = null;
    }
    
    /**
     * 自動コミットモードを設定する.
     * 
     * @param flg 自動コミットモード
     * @throws java.sql.SQLException SQL例外
     */
    public void setAutoCommit(boolean flg) throws SQLException {
        conn.setAutoCommit(flg);
    }

    /**
     * コミットを実行する.
     *
     * @throws java.sql.SQLException SQL例外
     */
    public void commit() throws SQLException {
        conn.commit();
    }

    /**
     * ロールバックを実行する.
     *
     * @throws java.sql.SQLException SQL例外
     */
    public void rollback()throws SQLException {
        conn.rollback();
    }

    /**
     * ドライバを取得する.<br>
     * ※当メソッドの実装は、各サブクラスにて行う.
     *
     * @return ドライバ文字列
     */
    protected abstract String getDriver();

    /**
     * URLを取得する.<br>
     * ※当メソッドの実装は、各サブクラスにて行う.
     * 
     * @return URL文字列
     */
    protected abstract String getURL();

}