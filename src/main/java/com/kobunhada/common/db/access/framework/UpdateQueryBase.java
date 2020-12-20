//------------------------------------------------------------------------------
// kobunhada's common libs.
// UpdateQueryBase.java
//
// Copyright(c) 2005-2006 Ippei/Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/05/01	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.db.access.framework;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;

import com.kobunhada.common.db.sql.PreparedStatementEx;
import com.kobunhada.common.debug.SystemOut;


/**
 * 更新クエリーベースクラス.
 *
 * @author kobunhada
 * @version 1.00
 */
public abstract class UpdateQueryBase extends QueryBase {

    /**
     * データ操作を実行する.<br>
     * sql文により、insert,update,deleteの操作を行う.<br>
     *
     * @param paramList バインド変数リスト
     * @param conn コネクションオブジェクト
     * @return int 処理件数
     * @throws java.sql.SQLException SQL例外
     */
    protected int execute(ArrayList<ParameterElementBase> paramList,Connection conn)
    throws SQLException {

        PreparedStatementEx ptsmt = null;
        int execCount = 0;
        try {
            //SQL文取得
            String sql = getSql();
            //PreparedStatement取得
            ptsmt = new PreparedStatementEx(conn.prepareStatement(sql));
            //SQLを実行し、戻り値を返す
            execCount = this.executeUpdate(ptsmt,paramList);
            return execCount;
        }
        catch(SQLException ex) {
            SystemOut.StackTrace(ex);
            throw ex;
        }
        catch(Exception ex3) {
            SystemOut.StackTrace(ex3);
            return -1;
        }
        finally {
            try {
                if(ptsmt !=null) {
                    ptsmt.close();
                }
            }
            catch(SQLException ex2) {
                SystemOut.StackTrace(ex2);
            }
        }

    }

    /**
     * SQLを実行する.
     *
     * @param ptsmt 拡張プリペアドステートメントオブジェクト
     * @param paramList バインド変数リスト
     * @return int 実行件数
     * @throws java.sql.SQLException SQL例外
     */
    private int executeUpdate(PreparedStatementEx ptsmt,
                                 ArrayList<ParameterElementBase> paramList)
    throws SQLException {
        //実行件数
        int execCount = 0;
        //パラメタが0件の場合
        if(paramList.size() == 0) {
            //SQLを実行し、実行件数を取得する
            execCount = ptsmt.executeUpdate();
        }
        //パラメタが1件以上の場合
        else {
            //パラメタリストの件数分ループ
            for(int i = 0; i < paramList.size(); i++) {
                //パラメタを1件取り出す
                ParameterElementBase params
                                = (ParameterElementBase)paramList.get(i);
                //パラメタ設定
                this.setParameters(ptsmt, params);
                //SQLを実行し、実行件数を取得する
                 execCount = ptsmt.executeUpdate();
            }
        }
        return execCount;
    }

    /**
     * SQL文を取得する.<br>
     * ※当メソッドの実装は、各サブクラスにて行う.
     *
     * @return sql文
     */
 	protected abstract String getSql();

}

