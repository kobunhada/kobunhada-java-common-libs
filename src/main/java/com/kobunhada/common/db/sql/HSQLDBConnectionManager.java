//------------------------------------------------------------------------------
// KITASOFT API
// HSQLDBConnectionManager.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0		2006/05/01	IPPEI.KITAJIMA	新規作成
// ver 1.0.1	2008/12/23	IPPEI.KITAJIMA	データベースモードを選択できる様に処理を修正
//------------------------------------------------------------------------------
package com.kobunhada.common.db.sql;

/**
 * HSQL用コネクション管理クラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class HSQLDBConnectionManager extends ConnectionManager {
    
    /** データソース名. */
    private String dataSourceName = "";
    /** データベース接続モード. */
    private String dbMode = "";
    
	/**
	 * サーバモード.<br> 
     * 通常のデータベースと同じように、データベースサーバを立ち上げ、 クライアントからアクセスします。
	 */
	public static final String DBMODE_SERVER = "server mode"; 
    
	/**
	 * Webサーバーモード.<br>
     * HTTP経由でサーバにアクセスします。
     */
    public static final String DBMODE_WEBSERVER = "web server mode"; 
	
     /**
      * In-Memory モード.<br>
      * データを一切保存せず、メモリ上だけで動作します。
      */
    public static final String DBMODE_INMEMORY = "In-Memory mode";
    
     /**
      * スタンドアロンモード.<br>
      *サーバとクライアントが一体となって動きます。 
      */
    public static final String DBMODE_STANDALONE = "stand alone mode";

    /**
     * デフォルトコンストラクタ.
     */
    public HSQLDBConnectionManager() {
    	super();
    	// デフォルトデータベースモードをスタンドアローンモードに設定
    	dbMode = DBMODE_STANDALONE;
    }

    /**
     * データソース名を設定する.
     * 
     * @param dataSourceName データソース名
     */
    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    /**
     * データベースモードを取得する.
     * 
     * @return データベースモード
     */
	public String getDBMode() {
		return dbMode;
	}
	
	/**
	 * データベースモードを設定する.
	 * 
	 * @param dbMode データベースモード
	 */
	public void setDBMode(String dbMode) {
		this.dbMode = dbMode;
	}

    /**
     * ドライバ名を取得する. 
     * 
     * @return ドライバ名
     */
    protected String getDriver() {
        return "org.hsqldb.jdbcDriver";
    }
    
    /**
     * URLを取得する.
     * 
     * @return URL
     */
    protected String getURL() {
        return getDBModeString() + dataSourceName + ";shutdown=true";
        
    }
    
    /**
     * データベース接続モード接頭文字列を取得する.
     * 
     * @return データベース接続モード接頭文字列
     */
    private String getDBModeString() {
    	
    	if(dbMode.equals(DBMODE_SERVER)) {
    		return "jdbc:hsqldb:hsql:";
    	}
    	else if(dbMode.equals(DBMODE_WEBSERVER)) {
    		return "jdbc:hsqldb:http://";
    	}
    	else if(dbMode.equals(DBMODE_INMEMORY)) {
    		return "jdbc:hsqldb:mem:";
    	}
    	else if(dbMode.equals(DBMODE_STANDALONE)) {
    		return "jdbc:hsqldb:file:";
    	}
    	else {
    		return "";
    	}
    	
    }

}