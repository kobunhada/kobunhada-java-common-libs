package com.kobunhada.common.koyomi;
import java.util.Date;

/**
 * 
 * 
 * @author kobunhada
 * @version 1.00
 */
public abstract class WarekiConverterBase {

    
    public WarekiConverterBase() {
    }
    
    public String convertWareki(String dateStr, String format) {
        Date date = WarekiUtil.convertDate(dateStr, format);
        if(date == null) {
            return "";
        }
        
        return convertWareki(date);
    }

    public abstract String convertWareki(Date date);
    
    public abstract Date convertSeireki(String wareki);
}