package com.kobunhada.common.koyomi;
import java.util.Date;
import java.util.List;

import com.kobunhada.common.util.DateUtil;

/**
 *
 * @version 1.00
 * @author kobunhada
 */
public class WarekiConverterQJ extends WarekiConverterBase {

	private QJKoyomiCollection collection = null;

	private static final String DATE_FORMAT  ="yyyy/MM/dd";

    public WarekiConverterQJ() {
    	collection = QJKoyomiCollection.getInstance();
    }

    public String convertWareki(Date date) {
    	String gengo = getTargetGengo(date);

    	String wareki = getWareki(gengo, date);



        return wareki;
    }

    private String getWareki(String gengo, Date date) {

    	List<QJKoyomiBean> list = collection.getShousaiList();

    	QJKoyomiBean bean = null;

    	String[] tsukiSeirekiList;

    	for(int i = 0; i < list.size(); i++) {
    		bean = list.get(i);

    		if(bean.getGengo().indexOf(gengo) >= 0) {
    			tsukiSeirekiList = bean.getTsukiSeirekiList();
    			int length = tsukiSeirekiList.length;

				Date seireki = null;
    			for(int j = 1; j < length; j++) {
    				try {
    					seireki = DateUtil.parge(tsukiSeirekiList[j], DATE_FORMAT);
					} catch (Throwable e) {
						System.exit(0);
					}

					if(date.compareTo(seireki) == 0) {
						String warekiTsu = bean.getTsukiList()[j] + "月";
						Date aaadate = null;
						try {
							aaadate = DateUtil.parge(bean.getTsukiSeireki(j), DATE_FORMAT);
						} catch (Throwable e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}

//						System.out.println(DateUtil.format(date, format) + " " + DateUtil.format(seireki, format) + " " + DateUtil.format(aaadate, format));

						int warekiDay = DateUtil.diffDays(date, aaadate) + 1;

						String ret = bean.getGengo() + warekiTsu + warekiDay + "日";

						return ret;
					}
					else if(date.compareTo(seireki) < 0) {
						String format  ="yyyy/MM/dd";
						String warekiTsu = bean.getTsukiList()[j - 1] + "月";
						Date aaadate = null;
						try {
							aaadate = DateUtil.parge(bean.getTsukiSeireki(j - 1), DATE_FORMAT);
						} catch (Throwable e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}

//						System.out.println(DateUtil.format(date, format) + " " + DateUtil.format(seireki, format) + " " + DateUtil.format(aaadate, format));

						int warekiDay = DateUtil.diffDays(date, aaadate) + 1;

						String ret = bean.getGengo() + warekiTsu + warekiDay + "日";

						return ret;
					}
    			}
    		}
    	}

    	return null;
    }

    /**
     *
     *
     * @param date
     * @return
     */
    private String getTargetGengo(Date date) {
    	// 範囲の取得
    	List<QJKoyomiRangeBean> list = collection.getRangeList();

    	QJKoyomiRangeBean rangeBean = null;
    	for(int i = 0; i < list.size(); i++) {
    		rangeBean = list.get(i);

    		if(date.compareTo(rangeBean.getStartYmd()) >= 0 && date.compareTo(rangeBean.getEndYmd()) <= 0) {
    			return rangeBean.getGengo();
    		}
    	}

    	return null;
    }


    public Date convertSeireki(String wareki) {
    	String gengo = wareki.substring(0, wareki.indexOf("年") + 1);
    	String tsuki = wareki.substring(wareki.indexOf("年") + 1, wareki.indexOf("月"));
    	String nichi = wareki.substring(wareki.indexOf("月") + 1, wareki.indexOf("日"));

    	List<QJKoyomiBean> list = collection.getShousaiList();

    	QJKoyomiBean bean = null;

    	Date date = null;

    	for(int i = 0; i < list.size(); i++) {
    		bean = list.get(i);

    		if(gengo.equals(bean.getGengo())) {
    			String[] tsukiList = bean.getTsukiList();
    			for(int j = 0; j < tsukiList.length; j++) {
    				if(tsuki.equals(tsukiList[j])) {
    					try {
							date = DateUtil.addDate(DateUtil.parge(bean.getTsukiSeireki(j), "yyyy/MM/dd"), Integer.parseInt(nichi) -1);

							return date;

						} catch (NumberFormatException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						} catch (Throwable e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
    				}
    			}
    		}
    	}






    	return null;
    }

}