//------------------------------------------------------------------------------
// kobunhada's common libs.
// DateUtil.java
//
// Copyright(c) 2005-2011 kobunhada. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/11/16	kobunhada	新規作成
// ver 1.1	2008/04/14	kobunhada	グレゴリオ日数取得メソッド追加
// ver 1.2  2011/02/26  kobunhada  日付の差を求めるメソッド追加
//------------------------------------------------------------------------------
package com.kobunhada.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日付ユーティリティクラス.
 *
 * @author kobunhada
 * @version 1.00
 */
public class DateUtil  {

    /**  日付デフォルトフォーマット. */
    public static final String DEFAULT_FORMAT = "yyyy/MM/dd HH:mm:ss SSS";

    /**
     * コンストラクタ.
     */
    private DateUtil() {
    	// 処理なし
    }

	/**
	 * 2つの日付の差を求める。
	 *
	 * @param dateToStr    日付文字列 yyyy/MM/dd
	 * @param dateFromStr    日付文字列 yyyy/MM/dd
	 * @return    2つの日付の差
	 * @throws ParseException 日付フォーマットが不正な場合
	 */
	public static int diffDaysString(String dateToStr,String dateFromStr, String format)
	    throws Throwable {
			return diffDays(DateUtil.parge(dateToStr, format),
					DateUtil.parge(dateFromStr, format));
	}
	/**
	 * 2つの日付の差を求める。
	 *
	 * <pre>
	 * 以下の方法でdateTo - dateFromの差を求める.
	 * 1.dateTo、dateFromをミリ秒に変換し、減算を実行する.
	 * 2.減算した値を1日分のミリ秒(1000 * 60 * 60 * 24)で除算した値を返す.
	 * </pre>
	 *
	 * @param dateTo    日付 java.util.Date
	 * @param dateFrom  日付 java.util.Date
	 * @return   2つの日付の差
	 */
	public static int diffDays(java.util.Date dateTo,java.util.Date dateFrom) {
		// System.out.println(dateTo.getTime() / (1000 * 60 * 60 * 24) + "  -   " + dateFrom.getTime()/ (1000 * 60 * 60 * 24) + " = " + (int)(dateTo.getTime() - dateFrom.getTime()) / (1000 * 60 * 60 * 24));
	    return  (int)((dateTo.getTime()  / (1000 * 60 * 60 * 24)) - (dateFrom.getTime()/ (1000 * 60 * 60 * 24)));
	}

    /**
     * 日付文字列をDate型インスタンスに変換する.
     *
     * @param dateStr 変換対象日付文字列
     * @param format 日付形式
     * @return Date型インスタンス
     * @throws Throwable 予期しない例外
     */
    public static Date parge(String dateStr, String format)
    throws Throwable {
        //
        SimpleDateFormat formatter = new SimpleDateFormat();
        //
        formatter.setLenient( true );
        // 日付の出力形式を設定
        formatter.applyPattern( format );
        // 指定したフォーマットで日付を返す
        return formatter.parse( dateStr );

    }

    /**
     * 日付を文字列で取得する.
     *
     * @param date フォーマット対象日付
     * @param format フォーマット
     * @return フォーマット処理された文字列
     */
    public static String format(Date date, String format) {
        SimpleDateFormat formatter
                        = new SimpleDateFormat(format);

        return formatter.format(date);
    }

    /**
     * システム日付を取得する.
     *
     * @param format 日付フォーマット
     * @return システム日付
     */
    public static String getSystemDateTime(String format) {
        //
        Date date = getSystemDateTime();
        //
        return format(date, format);

    }

    /**
     * システム日付を取得する.
     *
     * @return システム日付
     */
    public static Date getSystemDateTime() {
        // カレンダインスタンス取得
        Calendar calendar = new GregorianCalendar();
        // 日付取得
        Date trialtime = new Date();
        // カレンダインスタンスに日付インスタンスを設定
        calendar.setTime(trialtime);
        //
        return calendar.getTime();
    }

    /**
     * 年を加算する.
     *
     * @param date 加算対象日付
     * @param addTime 加算年
     * @return 加算後日付
     */
    public static Date addYear(Date date, int addTime) {
        //
        return add(date, Calendar.YEAR , addTime);
    }

    /**
     * 月を加算する.
     *
     * @param date 加算対象日付
     * @param addTime 加算月
     * @return 加算後日付
     */
    public static Date addMonth(Date date, int addTime) {
        //
        return add(date, Calendar.MONTH , addTime);
    }

    /**
     * 日を加算する.
     *
     * @param date 加算対象日付
     * @param addDate 加算日
     * @return 加算後日付
     */
    public static Date addDate(Date date, int addDate) {
        //
        return add(date, Calendar.DATE , addDate);
    }

    /**
     * 時間を加算する.
     *
     * @param date 加算対象日付
     * @param addHour 加算時間
     * @return 加算後日付
     */
    public static Date addHour(Date date, int addHour) {
        //
        return add(date, Calendar.HOUR, addHour);
    }

    /**
     * 分を加算する.
     *
     * @param date 加算対象日付
     * @param addMinutes 加算分
     * @return 加算後日付
     */
    public static Date addMin(Date date, int addMinutes) {
        //
        return add(date, Calendar.MINUTE, addMinutes);
    }

    /**
     * 秒を加算する.
     *
     * @param date 加算対象日付
     * @param addSecond 加算秒
     * @return 加算後日付
     */
    public static Date addSec(Date date, int addSecond) {
        //
        return add(date, Calendar.SECOND, addSecond);
    }

    /**
     * ミリ秒を加算する.
     *
     * @param date 加算対象日付
     * @param addMilliSecond 加算ミリ秒
     * @return 加算後日付
     */
    public static Date addMilliSec(Date date, int addMilliSecond) {
        //
        return add(date, Calendar.MILLISECOND , addMilliSecond);
    }

    /**
     * 指定した単位の値を加算する.
     *
     * @param date 加算対象日付
     * @param field 加算単位
     * @param addTime 加算値
     * @return 加算後日付
     */
    private static Date add(Date date, int field, int addCount) {
        // カレンダインスタンス取得
        Calendar calendar = new GregorianCalendar();
        // カレンダインスタンスに日付インスタンスを設定
        calendar.setTime(date);
        // 秒を加算
        calendar.add(field, addCount);
        // Dateインスタンスを返す
        return calendar.getTime();

    }

    /**
     * グレゴリオ日数を取得する.
     * <pre>
     * グレゴリオ暦適用日初日(1582/10/15 金)を基準日として、経過日数を求める.
     * 基準日より前の日付の場合は、マイナス値を返す.
     * 尚、ユリウス暦からグレゴリオ暦への切替に伴い、1582/10/05 〜 1582/10/14の間
     * は、存在しない為、正確な値は戻らない。
     *
     * ・【参考】グレゴリオ暦について
     * 16世紀後半、当時用いられていたユリウス暦における季節
     * と実際の季節とのずれが顕著になっていた。
     * この為、教会法刷新のために召集されたトリエント公会議
     * （1545年 - 1563年）は、教皇に暦法改正の業務を委託した。
     * 教皇グレゴリウス13世は、これを受けて1579年に、シルレト
     * 枢機卿を中心とする委員会を発足させて、暦法の研究を始め
     * させた。
     * この委員会のメンバーには、当時の代表的な科学者であった
     * 天文学者アロイシウス・リリウスや、数学者クリストファー・
     * クラヴィウスらが含まれた。
     * 委員会の作業の末に完成した新しい暦は、<b>1582年2月24日に発
     * 布され、同年10月4日（木曜日）の翌日を10月15日（金曜日）
     * とすることを定めた</b>
     * </pre>
     *
     * @param targetDate 対象日
     * @return グレゴリオ初日から経過した日数
     */
    public static long getGregorianDateCount(Date targetDate) {
        long c = -1;

        try {
            // グレゴリオ基準日
            Date startDate = parge("15821015","yyyyMMdd");
            long startTime = startDate.getTime() / (24*60*60*1000);
            long targetTime = targetDate.getTime() /(24*60*60*1000);

            c = targetTime - startTime;

        }
        catch(Throwable th) {
            return -1;
        }
            return c;
    }
}

