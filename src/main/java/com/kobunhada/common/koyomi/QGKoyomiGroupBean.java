package com.kobunhada.common.koyomi;
import java.util.Date;

import com.kobunhada.common.util.DateUtil;
import com.kobunhada.common.util.StringUtil;

/**
 *
 * @version 1.00
 * @author kobunhada
 */
public class QGKoyomiGroupBean  {

    /** 開始年月日Bean. */
    private QGKoyomiBean koyomiStartBean = null;
    /** 終了年月日Bean. */
    private QGKoyomiBean koyomiEndBean = null;

    /**
     * デフォルトコンストラクタ.
     */
    public QGKoyomiGroupBean() {
    }

    /**
     * 引数付コンストラクタ.
     *
     * @param gregorianDateStart グレゴリオ暦開始日
     * @param gregorianDateEnd グレゴリオ暦終了日
     * @param warekiGengoCode 元号コード
     * @param warekiYearStart 和暦開始年
     * @param warekiMonthUruFlagStart 和暦開始閏月フラグ
     * @param warekiMonthStart 和暦開始月
     * @param warekiDateStart  和暦開始日
     * @param warekiYearEnd 和暦終了年
     * @param warekiMonthUruFlagEnd 和暦終了閏月フラグ
     * @param warekiMonthEnd 和暦終了月
     * @param warekiDateEnd 和暦終了日
     */
    public QGKoyomiGroupBean(int warekiGengoCode,
                           int gregorianDateStart,
                           int gregorianDateEnd,
                           int warekiYearStart,
                           int warekiMonthUruFlagStart,
                           int warekiMonthStart,
                           int warekiDateStart,
                           int warekiYearEnd,
                           int warekiMonthUruFlagEnd,
                           int warekiMonthEnd,
                           int warekiDateEnd) {

        this.koyomiStartBean = new QGKoyomiBean(gregorianDateStart,
                                               warekiGengoCode,
                                               warekiYearStart,
                                               warekiMonthUruFlagStart,
                                               warekiMonthStart,
                                               warekiDateStart);

        this.koyomiEndBean = new QGKoyomiBean(gregorianDateEnd,
                                               warekiGengoCode,
                                               warekiYearEnd,
                                               warekiMonthUruFlagEnd,
                                               warekiMonthEnd,
                                               warekiDateEnd);
    }

    /**
     *
     *
     * @param gregorianDate
     * @return
     */
    public boolean isThisKoyomi(int gregorianDate) {
        return  gregorianDate >= this.koyomiStartBean.getGregorianDate() &&
                 gregorianDate <= this.koyomiEndBean.getGregorianDate();
    }

    /**
     *
     *
     * @param gengoCode
     * @param warekiYear
     * @param warekiMonthUruFlag
     * @param warekiMonth
     * @param warekiDate
     * @return
     */
    public boolean isThisKoyomi(int gengoCode,
                                  int warekiYear,
                                  int warekiMonthUruFlag,
                                  int warekiMonth,
                                  int warekiDate ) {

        long target = getWarekiCompValue(gengoCode,
                                        warekiYear,
                                        warekiMonthUruFlag,
                                        warekiMonth,
                                        warekiDate);

        long start = getWarekiCompValue(koyomiStartBean.getWarekiGengoCode(),
                                       koyomiStartBean.getWarekiYear(),
                                       koyomiStartBean.getWarekiMonthUruFlag(),
                                       koyomiStartBean.getWarekiMonth(),
                                       koyomiStartBean.getWarekiDate());

        long end = getWarekiCompValue(koyomiEndBean.getWarekiGengoCode(),
                                     koyomiEndBean.getWarekiYear(),
                                     koyomiEndBean.getWarekiMonthUruFlag(),
                                     koyomiEndBean.getWarekiMonth(),
                                     koyomiEndBean.getWarekiDate());

//        System.out.println("" + target + " >= " + start + " && " + target + " <= " + end);

        return  target >= start &&  target <= end;

    }

    /**
     *
     *
     * @param gengoCode
     * @param warekiYear
     * @param warekiMonth
     * @param warekiDate
     * @return
     */
    public String getSeirekiDateStr(int gengoCode,
                                int warekiYear,
                                int warekiMonth,
                                int warekiDate)  {
        Date date = getSeirekiDate(gengoCode, warekiYear, warekiMonth, warekiDate);

        return DateUtil.format(date, "yyyy/MM/dd");
    }

    /**
     *
     *
     * @param gengoCode
     * @param warekiYear
     * @param warekiMonth
     * @param warekiDate
     * @return
     */
    public Date getSeirekiDate(int gengoCode,
                                int warekiYear,
                                int warekiMonth,
                                int warekiDate) {

        int addCount = koyomiStartBean.getGregorianDate() +
                        warekiDate - this.koyomiStartBean.getWarekiDate();

        Date gFirstDate = null;
        try {
            gFirstDate = DateUtil.parge("15821015", "yyyyMMdd");
        }
        catch(Throwable th) {
        }
        return DateUtil.addDate(gFirstDate, addCount);
    }

    /**
     *
     *
     * @param gregorianDate
     * @return
     */
    public QGKoyomiBean getKoyomiBean(int gregorianDate) {



        int addCount
            = gregorianDate - this.koyomiStartBean.getGregorianDate();


        return new QGKoyomiBean(gregorianDate,
                               this.koyomiStartBean.getWarekiGengoCode(),
                               this.koyomiStartBean.getWarekiYear(),
                               this.koyomiStartBean.getWarekiMonthUruFlag(),
                               this.koyomiStartBean.getWarekiMonth(),
                               this.koyomiStartBean.getWarekiDate() + addCount);
    }

    /**
     *
     *
     * @param gengoCode
     * @param warekiYear
     * @param warekiMonthUruFlag
     * @param warekiMonth
     * @param warekiDate
     * @return
     */
    private long getWarekiCompValue(int gengoCode,
                                  int warekiYear,
                                  int warekiMonthUruFlag,
                                  int warekiMonth,
                                  int warekiDate ) {

        String str = StringUtil.paddingZero("" + gengoCode, 3) +
                     StringUtil.paddingZero("" + warekiYear, 2) +
                     "" + warekiMonthUruFlag +
                     StringUtil.paddingZero("" + warekiMonth, 2) +
                      StringUtil.paddingZero("" + warekiDate, 2);

        return Long.parseLong(str);

    }

}