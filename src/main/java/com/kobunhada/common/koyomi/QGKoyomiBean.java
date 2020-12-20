package com.kobunhada.common.koyomi;

/**
 *
 *
 * @author kobunhada
 * @version 1.00
 */
public class QGKoyomiBean  {

    /** グレゴリオ暦日. */
    private  int gregorianDate = 0;
    /** 元号コード. */
    private  int warekiGengoCode = 0;
    /** 和暦年. */
    private  int warekiYear = 0;
    /** 和暦閏月フラグ. */
    private  int warekiMonthUruFlag = 0;
    /** 和暦月. */
    private  int warekiMonth = 0;
    /** 和暦日. */
    private  int warekiDate = 0;

    public QGKoyomiBean() {
    }

    public QGKoyomiBean(int gregorianDate,
                       int warekiGengoCode,
                       int warekiYear,
                       int warekiMonthUruFlag,
                       int warekiMonth,
                       int warekiDate) {

        this.gregorianDate = gregorianDate;
        this.warekiGengoCode = warekiGengoCode;
        this.warekiYear = warekiYear;
        this.warekiMonthUruFlag = warekiMonthUruFlag;
        this.warekiMonth = warekiMonth;
        this.warekiDate = warekiDate;


    }

    public void setGregorianDate(int gregorianDate) {
        this.gregorianDate = gregorianDate;
    }


    public int getGregorianDate() {
        return gregorianDate;
    }


    public void setWarekiGengoCode(int warekiGengoCode) {
        this.warekiGengoCode = warekiGengoCode;
    }


    public int getWarekiGengoCode() {
        return warekiGengoCode;
    }


    public void setWarekiYear(int warekiYear) {
        this.warekiYear = warekiYear;
    }


    public int getWarekiYear() {
        return warekiYear;
    }


    public void setWarekiMonthUruFlag(int warekiMonthUruFlag) {
        this.warekiMonthUruFlag = warekiMonthUruFlag;
    }


    public int getWarekiMonthUruFlag() {
        return warekiMonthUruFlag;
    }

    public String getWarekiMonthUru() {
        if(this.getWarekiMonthUruFlag() == 1) {
            return "閏";
        }
        return "";
    }

    public void setWarekiMonth(int warekiMonth) {
        this.warekiMonth = warekiMonth;
    }


    public int getWarekiMonth() {
        return warekiMonth;
    }


    public void setWarekiDate(int warekiDate) {
        this.warekiDate = warekiDate;
    }


    public int getWarekiDate() {
        return warekiDate;
    }

    public String getWarekiYmd() {

        // 元号取得
        String gengo = QGGengo.getGengoName(this.warekiGengoCode);
        // 年取得
        String year = "";
        if(warekiYear == 1) {
            year = "元";
        }
        else {
            year = "" + warekiYear;
        }
        return gengo + year + "年" +
                this.getWarekiMonthUru() + warekiMonth + "月" +
                warekiDate + "日";
    }

}