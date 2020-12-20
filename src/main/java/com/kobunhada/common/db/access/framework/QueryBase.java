//------------------------------------------------------------------------------
// kobunhada's common libs.
// ParameterElementBase.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.db.access.framework;

import java.sql.SQLException;

import com.kobunhada.common.db.sql.PreparedStatementEx;


/**
 * クエリーベースクラス.
 * 
 * @author kobunhada
 * @version 1.00
 */
public abstract class QueryBase 
{

    /**
     * パラメタを設定する.<br>
     * ※当メソッドの実装は、各サブクラスにて行う.
     * 
     * @param ptsmt プリペアドステートメントオブジェクト
     * @param parameters パラメタ
     * @return 拡張プリペアドステートメントオブジェクト
     * @throws java.sql.SQLException SQL例外
     */
	protected abstract PreparedStatementEx setParameters(
                                            PreparedStatementEx ptsmt,
                                            ParameterElementBase parameters) 
    throws SQLException;

}