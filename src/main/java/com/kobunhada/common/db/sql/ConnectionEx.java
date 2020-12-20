//------------------------------------------------------------------------------
// ConnectionEx.java
//
// Copyright(c) 2005-2006 IppeiKitajima. All Right Reserved
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
 * 拡張コネクションクラス.
 * 
 * @version 1.00
 * @author kobunhada
 */
public class ConnectionEx 
{
    /** コネクション */
    private Connection conn = null;

    /**
     * コンストラクタ.
     */
    public ConnectionEx(String url) {
        getConnection(url);
    }

    /**
     * Connectionを取得する.
     * 
     * @return Connection コネクションインスタンス
     */
    public Connection getConnection(String url) {
        if(conn ==null) {
            try {
                // ドライバクラスをロード
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                // データベースへ接続
                conn = DriverManager.getConnection(url);
            }
            catch(Exception e) {
                SystemOut.StackTrace(e);
            }
        }

        return conn;
    }
    /**
     * Connectionをクローズする.
     * 
     * @throws java.sql.SQLException
     */
    public void close() throws SQLException {
        conn.close();
        conn = null;
    }
    public void setAutoCommit(boolean flg) throws SQLException {
        conn.setAutoCommit(flg);
    }

    public void commit() throws SQLException {
        conn.commit();
    }

    public void rollback()throws SQLException {
        conn.rollback();
    }

}
    
    