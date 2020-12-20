//------------------------------------------------------------------------------
// kobunhada's common libs.
// SearchQueryBase.java
//
// Copyright(c) 2005-2006 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/06	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.db.access.framework;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;

/**
 * 更新クエリーベースクラス.
 *
 * @author kobunhada
 * @version 1.00
 */
public abstract class UpdateQueryBaseEx extends UpdateQueryBase
{
    /**
     * コンストラクタ.
     */
    public UpdateQueryBaseEx() {
    }

    /**
     * データ操作を実行する.<br>
     * sql文により、insert,update,deleteの操作を行う.<br>
     * ※単独で1件のSQLを実行する場合に使用する事.
     *
     * @param param パラメタ
     * @return int 処理件数
     * @throws java.sql.SQLException SQL例外
     */
    public int execute(ParameterElementBase param) throws SQLException {
        //パラメタをリストにセット
        ArrayList<ParameterElementBase> paramList = new ArrayList<ParameterElementBase>();
        paramList.add(param);
        //SQL実行
        return execute(paramList, true);

    }

    /**
     * データ操作を実行する.<br>
     * sql文により、insert,update,deleteの操作を行う.
     * ※単独でリスト件数分のSQLを実行する場合に使用する事.
     *
     * @param paramList ParameterElementBase拡張クラスを要素に持つリスト
     * @param autoCommit 自動コミットモード true:On false:Off
     * @return 処理件数
     * @throws java.sql.SQLException SQL例外
     */
    public int execute(ArrayList<ParameterElementBase> paramList, boolean autoCommit)
    throws SQLException {

        //コネクション取得
        Connection conn = this.getConnection();
        //自動コミットモードをOn
        conn.setAutoCommit(autoCommit);
        //executeメソッドを実行する
        int execCount = execute(paramList, conn);
        //コネクションクローズ
        conn.close();
        //戻り値を返す
        return execCount;

    }

    /**
     * コネクションを取得する.<br>
     * ※当メソッドの実装は、各サブクラスにて行う.
     *
     * @return コネクションインスタンス
     */
    protected abstract Connection getConnection();

}