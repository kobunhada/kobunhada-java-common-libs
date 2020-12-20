//------------------------------------------------------------------------------
// kobunhada's common libs.
// SystemOut.java
//
// Copyright(c) 2005-2009 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2009/06/20	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.debug;

/**
 * 標準出力制御クラス.
 * 
 * @author kobunhada
 * @version 1.0.0
 */
public class SystemOut {
	
	/** 標準出力制御フラグ. */
	private static final boolean OUTPUT_FLG = true;
	
	/**
	 * プライベートコンストラクタ.
	 */
	private SystemOut() {
	}
	
	/**
	 * 標準出力メソッド.
	 * 標準出力制御フラグがTrueの場合のみ引数objをPrint.out.printInメソッドで
	 * 標準出力する.
	 * 
	 * @param obj 出力対象メソッド
	 */
	public static void println(Object obj) {
		if(OUTPUT_FLG) {
			System.out.println(obj);
		}
	}
	
	/**
	 * スタックトレース出力メソッド.
	 * 標準出力制御フラグが true の場合のみ、引数 thのprintStackTrace()メソッド
	 * を実行する.
	 * 
	 * @param th 例外オブジェクト
	 */
	public static void StackTrace(Throwable th) {
		if(OUTPUT_FLG) {
			th.printStackTrace();
		}
	}
	
}
