package com.kobunhada.common.koyomi;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kobunhada.common.util.DateUtil;
import com.kobunhada.common.util.StringUtil;


/**
 *
 *
 *
 * @author kobunhada
 * @version 1.00
 */
public class WarekiConverterShinreki extends WarekiConverterBase  {

    /** . */
    // private static Date meijiDate = null;
    /** . */
    private static Date taishoDate = null;
    /** . */
    private static Date showaDate = null;
    /** . */
    private static Date heiseiDate = null;
    /** .*/
    private static Date reiwaDate = null;

    static {
        // 明治6年1月1日(西暦切替日)
        // meijiDate = WarekiUtil.convertDate("18730101", "yyyyMMdd");
        // 大正初日
        taishoDate = WarekiUtil.convertDate("19120730", "yyyyMMdd");
        // 昭和初日
        showaDate = WarekiUtil.convertDate("19261225", "yyyyMMdd");
        // 平成初日
        heiseiDate = WarekiUtil.convertDate("19890108", "yyyyMMdd");
        // 令和初日
        reiwaDate = WarekiUtil.convertDate("20190501", "yyyyMMdd");
    }

    /**
     * デフォルトコンストラクタ.
     */
    public WarekiConverterShinreki() {
        super();
    }

    /**
     *
     *
     * @param date
     * @return
     */
    public String convertWareki(Date date) {

        String gengo = getGengoName(date);
        int intYear = getWarekiYear(date);
        String year = "";
        if(intYear == 1) {
            year = "元年";
        }
        else {
            year = StringUtil.paddingZero("" + intYear, 2) + "年";
        }

        String mmdd = DateUtil.format(date, "M月d日");

        return gengo + year + mmdd;
    }

    private int getWarekiYear(Date date) {

        int year = Integer.parseInt(DateUtil.format(date, "yyyy"));

        // 日付が令和初日以降の場合
        if (date.compareTo(reiwaDate) >= 0) {
            return year - 2019 + 1;
        }
        // 日付が平成初日以降の場合
        else if (date.compareTo(heiseiDate) >= 0) {
            return year - 1989 + 1;
        }
        // 日付が昭和初日以降の場合
        else if(date.compareTo(showaDate) >= 0) {
            return year - 1926 + 1;
        }
        // 日付が大正初日以降の場合
        else if(date.compareTo(taishoDate) >= 0) {
            return year - 1912 + 1;
        }
        // 日付が大正初日より前の場合
        else {
            return year - 1873 + 1;
        }
    }

    /**
     *
     *
     * @param date
     * @return
     */
    public String getGengoName(Date date) {

        // 日付が平成初日以降の場合
        if(date.compareTo(heiseiDate) >= 0) {
            return "平成";
        }
        // 日付が昭和初日以降の場合
        else if(date.compareTo(showaDate) >= 0) {
            return "昭和";
        }
        // 日付が大正初日以降の場合
        else if(date.compareTo(taishoDate) >= 0) {
            return "大正";
        }
        // 日付が大正初日より前の場合
        else {
            return "明治";
        }
    }


    public Date convertSeireki(String wareki) {
        List<String> warekiList = WarekiUtil.createWarekiElementList(wareki);
        String gengo = (String)warekiList.get(0);
        int year = Integer.parseInt(warekiList.get(1));
        int month =Integer.parseInt(warekiList.get(2));
        int day = Integer.parseInt(warekiList.get(3));

        if(gengo.equals("明治")) {
            year += 1867;
        }
        else if(gengo.equals("大正")) {
            year = year + 1912 - 1;
        }
        else if(gengo.equals("昭和")) {
            year = year + 1926 - 1;
        }
        else if(gengo.equals("平成")) {
            year = year + 1989 - 1;
        }
        else {

        }

        String seirekiStr = "" + year +
                             StringUtil.paddingZero("" + month, 2) +
                             StringUtil.paddingZero("" + day, 2);

        Date date = null;
        try {
            date = DateUtil.parge(seirekiStr, "yyyyMMdd");        }
        catch(Throwable th) {
        }

        return date;

    }

}