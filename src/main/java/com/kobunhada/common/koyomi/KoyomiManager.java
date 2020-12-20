package com.kobunhada.common.koyomi;
import java.util.Date;

import com.kobunhada.common.koyomi.WarekiConverterBase;
import com.kobunhada.common.util.DateUtil;


/**
 *
 *
 * @author kobunhada
 * @version 1.00
 */
public class KoyomiManager  {


    /**
     * コンストラクタ.
     */
    private KoyomiManager() {
    }

    public static String convertWareki(String dateStr, String format) {
        Date date = WarekiUtil.convertDate(dateStr, format);

        if(date == null) {
            return null;
        }

        return convertWareki(date);
    }

    /**
     *
     *
     * @param formatType
     * @return
     */
    public static String convertWareki(Date date) {

        String str = null;

        try {
            // 和暦コンバータインスタンス取得
            WarekiConverterBase converter
                    = WarekiConverterFactory.createWarekiConverter(date);
            str = converter.convertWareki(date);
        }
        catch(Throwable th) {
        }

        return str;

    }

    /**
     *
     *
     * @param wareki
     * @return
     */
    public static Date convertSeireki(String wareki) {

        Date date = null;

        try {
        // 和暦コンバータインスタンス取得
            WarekiConverterBase converter
                    = WarekiConverterFactory.createWarekiConverter(wareki);

            date = converter.convertSeireki(wareki);
        }
        catch(Throwable th) {
        }

        return date;

    }

    public static String convertSeirekiString(String wareki) {
        return convertSeirekiString(wareki, "yyyy/MM/dd");
    }

    public static String convertSeirekiString(String wareki, String format) {

        Date date = convertSeireki(wareki);

        if(date == null) {
            return null;
        }

        return DateUtil.format(date, format);
    }

}