//------------------------------------------------------------------------------
// KITASOFT API
// SearchQueryBase.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/01	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.db.access.framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kobunhada.common.db.sql.PreparedStatementEx;
import com.kobunhada.common.debug.SystemOut;


/**
 * 検索クエリーベースクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public abstract class SearchQueryBase extends QueryBase {
    /**
     * データを取得する.
     * 
     * @return DataCollectionBase データ取得結果 
     * @throws java.sql.SQLException SQL例外
     */
    public DataCollectionBase getData() throws SQLException {
        
        return getData(null);
    }

    /**
     * データを取得する.
     * 
     * @param parameters バインド変数コレクション
     * @return DataCollectionBase データ取得結果
     * @throws java.sql.SQLException SQL例外
     */
	public DataCollectionBase getData(ParameterElementBase parameters)
    throws SQLException {
    
        Connection conn = null;
        PreparedStatementEx ptsmt = null;
        ResultSet result = null;
        try {
            //コネクション取得
            conn = this.getConnection();
            //ReadOnry設定
            conn.setReadOnly(true);
            //sqlを取得する.
            String sql = this.getSql(parameters);
            //
            ptsmt = new PreparedStatementEx(conn.prepareStatement(sql));
            if(parameters != null) {
                ptsmt = this.setParameters(ptsmt, parameters);
            }
            //SQLを実行し、ResultSetを取得する
            result = ptsmt.executeQuery();
            //DataCollection作成し、戻り値を返す
            return this.createElementCollection(result);
        }
        catch(SQLException ex) {
            throw ex;
        }
        finally {
            try {
                if(result != null) {
                    result.close();
                }
                if(ptsmt != null) {
                    ptsmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            }
            catch(SQLException ex2) {
                SystemOut.StackTrace(ex2);
            }
        }
    }

    /**
     * SQL文を取得する.<br>
     * 
     * @param parameters バインド変数コレクション
     * @return sql文
     */
    private String getSql(ParameterElementBase parameters) {
        
        String sql = "";
        
        if(parameters == null) {
            sql = createSQLBaseString() + " " + createSQLSortString();
        }
        else {
            sql = createSQLBaseString() + " "
                  + createSQLWhereString(parameters) + " "
                  + createSQLSortString();
        }

        return sql;
    }
    
    /**
     * SQL文(基本部分)を生成する.
     * ※当メソッドの実装は、各サブクラスにて行う.
     * 
     * @return SQL文(基本部分)
     */
    protected abstract String createSQLBaseString();
    
    /**
     * SQL文(Order By句)を生成する.
     * ※当メソッドの実装は、各サブクラスにて行う.
     * 
     * @return SQL文(Order By句)
     */
    protected abstract String createSQLSortString();
    
    /**
     * SQL文(Where句)を生成する.
     * ※当メソッドの実装は、各サブクラスにて行う.
     * 
     * @param parameters バインド変数コレクション
     * @return SQL文(Where句)
     */
    protected abstract String createSQLWhereString(ParameterElementBase parameters);

    /**
     * データコレクションを作成する.<br>
     * ※当メソッドの実装は、各サブクラスにて行う.
     * 
     * @param result ResultSet オブジェクト
     * @return DataCollection データコレクション
     * @throws java.sql.SQLException SQL例外
     */
	protected abstract DataCollectionBase createElementCollection(
                                        ResultSet result) throws SQLException;

    /**
     * コネクションを取得する.<br>
     * ※当メソッドの実装は、各サブクラスにて行う.
     * 
     * @return Connectionオブジェクト 
     */
    protected abstract Connection getConnection();
    

}
 

