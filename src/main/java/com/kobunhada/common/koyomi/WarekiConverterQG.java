package com.kobunhada.common.koyomi;
import java.util.Date;
import java.util.List;

import com.kobunhada.common.util.DateUtil;


/**
 *
 * @version 1.00
 * @author kobunhada
 */
public class WarekiConverterQG extends WarekiConverterBase {

    private List<QGKoyomiGroupBean> list = null;

    public WarekiConverterQG() {
        list = new QGKoyomiCollection().getCollection();

    }

    public String convertWareki(Date date) {
        // 暦Beanを取得
        QGKoyomiBean bean = getKoyomiBean(date);
        // 暦をフォーマット
        return bean.getWarekiYmd();

    }

    /**
     *
     *
     * @param dateStr
     * @param format
     * @return
     */
    public QGKoyomiBean getKoyomiBean(String dateStr, String format) {

        Date date = WarekiUtil.convertDate(dateStr, format);
        if(date == null) {
            return null;
        }

        // KoyomiBeanを取得
        return getKoyomiBean(date);
    }

    /**
     *
     *
     * @param date
     * @return
     */
    public QGKoyomiBean getKoyomiBean(Date date) {

        long gCount = DateUtil.getGregorianDateCount(date);

        int intGCount = Integer.parseInt("" + gCount);

        for(int i = 0; i < list.size(); i++) {
            QGKoyomiGroupBean koyomiGroup = (QGKoyomiGroupBean)list.get(i);

            if(koyomiGroup.isThisKoyomi(intGCount)) {

                QGKoyomiBean bean = koyomiGroup.getKoyomiBean(intGCount);

                return bean;
            }
        }

        return null;
    }

    public Date convertSeireki(String wareki) {
        List<String> warekiList = WarekiUtil.createWarekiElementList(wareki);

        int gengoCode = QGGengo.getGengoCode((String)warekiList.get(0));
        int year = Integer.parseInt((String)warekiList.get(1));
        int month =Integer.parseInt((String)warekiList.get(2));
        int day = Integer.parseInt((String)warekiList.get(3));
        int uruFlag = Integer.parseInt((String)warekiList.get(4));

        return convertSeireki(gengoCode, year, uruFlag, month, day);
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
    public Date convertSeireki(int gengoCode,
                                int warekiYear,
                                int warekiMonthUruFlag,
                                int warekiMonth,
                                int warekiDate ) {

        for(int i = 0; i < list.size(); i++) {
            //
            QGKoyomiGroupBean koyomiGroup = (QGKoyomiGroupBean)list.get(i);
            //
            if(koyomiGroup.isThisKoyomi(gengoCode,
                                        warekiYear,
                                        warekiMonthUruFlag,
                                        warekiMonth,
                                        warekiDate)) {

                return koyomiGroup.getSeirekiDate(
                            gengoCode, warekiYear, warekiMonth, warekiDate);
            }
        }

        return null;
    }

}