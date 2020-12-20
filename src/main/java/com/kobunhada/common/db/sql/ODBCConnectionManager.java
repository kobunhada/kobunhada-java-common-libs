//------------------------------------------------------------------------------
// kobunhada's common libs.
// ODBCConnectionManager.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.db.sql;

/**
 * ODBC用コネクション管理クラス.
 * 
 * @author kobunhada
 * @version 1.00
 */
public abstract class ODBCConnectionManager extends ConnectionManager {
    
    /**
     * ドライバ文字列を取得する.
     * 
     * @return String ドライバ文字列
     */
    protected String getDriver() {
        return "sun.jdbc.odbc.JdbcOdbcDriver";
    }

    /**
     * URL文字列を取得する.
     * 
     * @return URL文字列
     */
    protected String getURL() {
        return "jdbc:odbc:" + getDataSourceName();
        
    }

    /**
     * データソース文字列を取得する.
     * ※当メソッドの実装は、各サブクラスにて行う.
     * 
     * @return データソース文字列
     */
    protected abstract String getDataSourceName();
}