//------------------------------------------------------------------------------
// kobunhada's common libs.
// StopWatch.java
//
// Copyright(c) 2005-2008 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2008/04/14	kobunhada	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.date;
import java.util.Date;

/**
 * 時間測定クラス.
 * 
 * @author kobunhada
 * @version 1.00
 */
public class StopWatch {
    
    /** 開始ミリ秒. */
    private long start = 0;
    
    /**
     * コンストラクタ.
     */
    public StopWatch() {
    }
    
    /**
     * 時間計測を開始する.
     */
    public void start() {
        Date day = new Date();
        start = day.getTime();
    }
    
    /**
     * 経過秒数を取得する.<br>
     * startメソッド実行時から経過した秒数を取得する.startメソッドが未実行の場合
     * は、0を返す.
     * 
     * @return 経過秒数
     */
    public long getElapsedTimeSec() {
        return getElapsedTimeMilliSec() / 1000;    
    }
    
    /**
     * 経過ミリ秒数を取得する.<br>
     * startメソッド実行時から経過したミリ秒数を取得する.startメソッドが未実行の
     * 場合は、0を返す.
     * 
     * @return 経過ミリ秒数
     */
    public long getElapsedTimeMilliSec() {
        
        if(start == 0) {
            return 0;
        }
        
        Date day = new Date();
        long end = day.getTime();
    
        return end - start;
    }
}