package com.kobunhada.common.koyomi;
import java.util.Date;
import java.util.List;

/**
 *
 * @version 1.00
 * @author kobunhada
 */
public class WarekiConverterFactory {

    private static Date dateShin = null;
    private static Date dateQG = null;
//    private static Date dateQJ = null;

    static {
        dateShin = WarekiUtil.convertDate("18730101", "yyyyMMdd");
        dateQG = WarekiUtil.convertDate("15821015", "yyyyMMdd");
//        dateQJ = WarekiUtil.convertDate("15821004", "yyyyMMdd");
    }

    private WarekiConverterFactory() {
    }

    public static WarekiConverterBase createWarekiConverter(String dateStr,
                                                              String format) {

        Date date = WarekiUtil.convertDate(dateStr, format);
        if(date == null) {
            return null;
        }

        return createWarekiConverter(date);

    }

    public static WarekiConverterBase createWarekiConverter(Date date) {

        // 1582/10/15(グレゴリオ暦の初日) より前の日付の場合
        // ※ユリウス暦1582/10/04 の翌日はグレゴリオ暦の1582/10/15
        if(date.compareTo(dateQG) < 0) {
            // ユリウス暦用和暦コンバータインスタンスを返す
            return new WarekiConverterQJ();
        }
        // 1873/01/01(日本のグレゴリオ暦採用初日) より前の日付の場合
        // 明治5年12/2(旧暦)の翌日は明治6年1/1(新暦)
        else if(date.compareTo(dateShin) < 0) {
            // 明治5年12/2(旧暦)以前用和暦コンバータインスタンスを返す.
            return new WarekiConverterQG();
        }
        // 1873/01/01(日本のグレゴリオ暦採用初日)以降の場合
        else {
            return new WarekiConverterShinreki();
        }
    }

    public static WarekiConverterBase createWarekiConverter(String wareki) {

        List<String> warekiList = WarekiUtil.createWarekiElementList(wareki);

        int gengoCode = QGGengo.getGengoCode(warekiList.get(0));
        int year = Integer.parseInt(warekiList.get(1));
        int month =Integer.parseInt(warekiList.get(2));
        int day = Integer.parseInt(warekiList.get(3));

        if((gengoCode < 242) ||
           (gengoCode == 242 && year == 10 && month < 9)) {
            return new WarekiConverterQJ();
        }

        else if((gengoCode == 242 && year > 10) ||
                 (gengoCode == 242 && year == 10 && month >= 9) ||
                 (gengoCode == 242 && year == 10 && month == 9 && day >= 19) ||
                 (gengoCode > 243 && gengoCode < 286) ||
                 (gengoCode == 286 && year < 6)) {
            return new WarekiConverterQG();
        }

        else {
            return new WarekiConverterShinreki();
        }

    }



}