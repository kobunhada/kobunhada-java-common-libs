package com.kobunhada.common.koyomi;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kobunhada.common.util.DateUtil;
import com.kobunhada.common.util.StringUtil;


/**
 *
 *
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class WarekiUtil  {

    /**
     * プライベートコンストラクタ.
     */
    private WarekiUtil() {
    }

    /**
     *
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date convertDate(String dateStr, String format) {

        Date date = null;
        try {
            // Dateインスタンス取得
            date = DateUtil.parge(dateStr, format);
        }
        catch(Throwable th) {
            return null;
        }

        return date;
    }

    /**
     *
     *
     * @param wareki
     * @return
     */
    public static List<String> createWarekiElementList(String wareki) {
        // 和暦要素リストを初期化
        List warekiList = new ArrayList<List>();
        // 元号部を和暦要素リストへ追加
        parseGengo(wareki, warekiList);
        // 年月日の数値部を和暦要素リストへ追加
        parseWarekiYMD(wareki, warekiList);
        // 閏月有無フラグを和暦要素リストへ追加
        parseUruTsukiFlag(wareki, warekiList);
        // 和暦要素リストを返す
        return warekiList;
    }

    /**
     *
     *
     * @param wareki
     * @param warekiList
     */
    private static void parseGengo(String wareki, List<String> warekiList) {

        StringBuffer sb = new StringBuffer();

        // 元号部を取得
        for(int i = 0; i < wareki.length(); i++) {
            String tmp = wareki.substring(i, i + 1);
            if(!StringUtil.isNumberString(tmp)) {
                sb.append(tmp);
            }
            else {
                break;
            }
        }

        String gengo = "";
        String gannen = "";

        if(sb.toString().length() > 2) {
            gengo = sb.toString().substring(0, 2);
            gannen = "01";
        }
        else {
            gengo = sb.toString();
        }

        // リストに元号を追加
        warekiList.add(gengo);

        if(!StringUtil.isEmptyString(gannen)) {
            warekiList.add(gannen);
        }

    }

    /**
     *
     *
     * @param wareki
     * @param warekiList
     */
    private static void parseWarekiYMD(String wareki, List<String> warekiList) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < wareki.length(); i++ ) {
            String tmp = wareki.substring(i, i + 1);
            if(StringUtil.isNumberString(tmp)) {
                sb.append(tmp);
            }
            else if(sb.toString().length() > 0) {
                warekiList.add(sb.toString());
                sb = new StringBuffer();
            }
        }

        if(sb.toString().length() > 0) {
            warekiList.add(sb.toString());
        }
    }

    /**
     *
     *
     * @param wareki
     * @param warekiList
     */
    private static void parseUruTsukiFlag(String wareki, List<String> warekiList) {

        // 閏月フラグ値設定
        if(wareki.indexOf("閏") > -1) {
            warekiList.add("1");
        }
        else {
            warekiList.add("0");
        }

    }
}