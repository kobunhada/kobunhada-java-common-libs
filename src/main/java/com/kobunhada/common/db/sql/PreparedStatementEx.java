//------------------------------------------------------------------------------
// KITASOFT API
// PreparedStatementEx.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0 2006/05/01	IPPEI.KITAJIMA	新規作成
// ver 1.1 2006/05/19   IPPEI.KITAJIMA  setParameter(int, InputStream, int)追加
//------------------------------------------------------------------------------
package com.kobunhada.common.db.sql;

import java.io.InputStream;

import java.math.BigDecimal;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import java.util.Calendar;

/**
 * PreparedStatement委譲クラス.
 * 
 * @author	IPPEI.KITAJIMA
 * @version	1.0
 */
public class PreparedStatementEx {
    //----------------------------------------
    // private変数
    //----------------------------------------
    /** プリペアドステートメント. */
	private PreparedStatement pstmt;
	 
    /**
     * コンストラクタ.
     * 
     * @param pstmt プリペアドステートメント
     */
	public PreparedStatementEx(PreparedStatement pstmt) {
        this.pstmt = pstmt;
	}
	
    /**
     * PreparedStatement.executeUpdate()メソッドを呼び出す.
     * 
     * @return 実行件数
     * @throws java.sql.SQLException SQL例外
     */
    public int executeUpdate() throws SQLException {    
        return pstmt.executeUpdate();
    }
    
    /**
     * PreparedStatement.executeUpdate(String)メソッドを呼び出す.
     * 
     * @param sql SQL文
     * @return 処理件数
     * @throws java.sql.SQLException SQL例外
     */
    public int executeUpdate(String sql) throws SQLException {
        return pstmt.executeUpdate(sql);
    }
    
    /**
     * PreparedStatement.executeQuery()メソッドを呼び出す.
     * 
     * @return 結果セット
     * @throws java.sql.SQLException SQL例外
     */
    public ResultSet executeQuery() throws SQLException {
        return pstmt.executeQuery();
    }

    /**
     * PreparedStatement.close()メソッドを呼び出す.
     * 
     * @throws java.sql.SQLException SQL例外
     */
    public void close() throws SQLException {
        pstmt.close();
    }
    
    /**
     * String型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, String value) throws SQLException {
        pstmt.setString(index ,value);
	}
	
    /**
     * int型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, int value) throws SQLException {
        pstmt.setInt(index, value);
	}
    
    /**
     * long型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, long value) throws SQLException {
        pstmt.setLong(index, value);
    }
	 
    /**
     * short型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, short value) throws SQLException {
        pstmt.setShort(index, value);
    }
	
    /**
     * BigDecimal型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, BigDecimal value)
    throws SQLException {
        pstmt.setBigDecimal(index, value);
    }
	
    /**
     * boolean型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, boolean value) throws SQLException {
        pstmt.setBoolean(index, value);
    }
	
    /**
     * byte型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, byte value) throws SQLException {
        pstmt.setByte(index, value);
    }
	
    /**
     * float型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, float value) throws SQLException {
        pstmt.setFloat(index, value);
    }
	
    /**
     * double型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, double value) throws SQLException {
        pstmt.setDouble(index, value);
    }
	
    /**
     * byte[]型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, byte[] value) throws SQLException {
        pstmt.setBytes(index, value);
    }
	
    /**
     * Date型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, Date value) throws SQLException {
        pstmt.setDate(index, value);
    }
	
    /**
     * Time型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, Time value) throws SQLException {
        pstmt.setTime(index, value);
    }
	
    /**
     * Timestamp型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
	public void setParameter(int index, Timestamp value)
    throws SQLException {
        pstmt.setTimestamp(index, value);
    }
    /**
     * Blob型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
    public void setParameter(int index, Blob value) throws SQLException {
        pstmt.setBlob(index, value);
    }
    
    /**
     * Ref型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
    public void setParameter(int index, Ref value) throws SQLException {
        pstmt.setRef(index, value);
    }
    
    /**
     * Clob型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
    public void setParameter(int index, Clob value) throws SQLException {
        pstmt.setClob(index, value);
    }
    
    /**
     * Array型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @throws java.sql.SQLException SQL例外
     */
    public void setParameter(int index, Array value) throws SQLException {
        pstmt.setArray(index, value);
    }
    
    /**
     * Date型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @param cal カレンダオブジェクト
     * @throws java.sql.SQLException SQL例外
     */
    public void setParameter(
                        int index,
                        java.sql.Date value,
                        Calendar cal) throws SQLException {

        pstmt.setDate(index, value, cal);
    }
    
    /**
     * Time型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @param cal カレンダオブジェクト
     * @throws java.sql.SQLException SQL例外
     */
    public void setParameter(int index,
                               java.sql.Time value,
                               Calendar cal) throws SQLException {
        pstmt.setTime(index, value,cal);
    }

    /**
     * Timestamp型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param value パラメタ値
     * @param cal カレンダオブジェクト
     * @throws java.sql.SQLException SQL例外
     */
    public void setParameter(int index,
                              java.sql.Timestamp value,
                              Calendar cal) throws SQLException {
        pstmt.setTimestamp(index, value,cal);
    }
    
    /**
     * InputStream型のパラメタをセットする.
     * 
     * @param index インデックス
     * @param inputStream インプットストリーム
     * @param length 文字列長
     * @throws java.sql.SQLException SQL例外
     */
    public void setParameter(int index,
                                InputStream inputStream,
                                int length) throws SQLException {
                
        pstmt.setBinaryStream(index,inputStream,length);            
    }

    /**
     * PreparedStatement型のインスタンスを取得する.
     * 
     * @return PreparedStatement プリペアドステートメントオブジェクト
     * @throws java.sql.SQLException SQL例外
     */
	public PreparedStatement getPreparedStatement() throws SQLException {
		return this.pstmt;
	}
	 
}
 
