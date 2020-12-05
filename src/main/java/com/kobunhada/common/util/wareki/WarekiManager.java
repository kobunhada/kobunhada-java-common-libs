package com.kobunhada.common.util.wareki;

import java.util.ArrayList;
import java.util.List;

public class WarekiManager {
	private List<WarekiBean> warekiList = null;

	private List<WarekiBean>nanchoList = null;

	/**
	 * コンストラクタ
	 */
	public WarekiManager() {
		// 和暦リスト
		warekiList = new ArrayList<WarekiBean>();
		warekiList.add(new WarekiBean("大化", "たいか ",645,650));
		warekiList.add(new WarekiBean("白雉", "はくち ",650,654));
		warekiList.add(new WarekiBean("朱鳥", "しゅちょう ",686,686));
		warekiList.add(new WarekiBean("大宝", "たいほう ",701,704));
		warekiList.add(new WarekiBean("慶雲", "きょううん（けいうん） ",704,708));
		warekiList.add(new WarekiBean("和銅", "わどう ",708,715));
		warekiList.add(new WarekiBean("霊亀", "れいき ",715,717));
		warekiList.add(new WarekiBean("養老", "ようろう ",717,724));
		warekiList.add(new WarekiBean("神亀", "じんき ",724,729));
		warekiList.add(new WarekiBean("天平", "てんぴょう ",729,749));
		warekiList.add(new WarekiBean("天平感宝 ", "てんぴょうかんぽう",749,749));
		warekiList.add(new WarekiBean("天平勝宝 ", "てんぴょうしょうほう ",749,757));
		warekiList.add(new WarekiBean("天平宝字 ", "てんぴょうほうじ ",757,765));
		warekiList.add(new WarekiBean("天平神護 ", "てんぴょうじんご ",765,767));
		warekiList.add(new WarekiBean("神護景雲 ", "じんごけいうん ",767,770));
		warekiList.add(new WarekiBean("宝亀", "ほうき ",770,781));
		warekiList.add(new WarekiBean("天応", "てんおう ",781,782));
		warekiList.add(new WarekiBean("延暦", "えんりゃく ",782,806));
		warekiList.add(new WarekiBean("大同", "だいどう ",806,810));
		warekiList.add(new WarekiBean("弘仁", "こうにん ",810,824));
		warekiList.add(new WarekiBean("天長", "てんちょう ",824,834));
		warekiList.add(new WarekiBean("承和", "じょうわ（しょうわ） ",834,848));
		warekiList.add(new WarekiBean("嘉祥", "かしょう（かじょう） ",848,851));
		warekiList.add(new WarekiBean("仁寿", "にんじゅ ",851,854));
		warekiList.add(new WarekiBean("斉衡", "さいこう ",854,857));
		warekiList.add(new WarekiBean("天安", "てんあん（てんなん） ",857,859));
		warekiList.add(new WarekiBean("貞観", "じょうがん（じょうかん） ",859,877));
		warekiList.add(new WarekiBean("元慶", "がんぎょう（げんけい） ",877,885));
		warekiList.add(new WarekiBean("仁和", "にんな ",885,889));
		warekiList.add(new WarekiBean("寛平", "かんぴょう ",889,898));
		warekiList.add(new WarekiBean("昌泰", "しょうたい ",898,901));
		warekiList.add(new WarekiBean("延喜", "えんぎ ",901,923));
		warekiList.add(new WarekiBean("延長", "えんちょう ",923,931));
		warekiList.add(new WarekiBean("承平", "しょうへい（じょうへい） ",931,938));
		warekiList.add(new WarekiBean("天慶", "てんぎょう ",938,947));
		warekiList.add(new WarekiBean("天暦", "てんりゃく ",947,957));
		warekiList.add(new WarekiBean("天徳", "てんとく ",957,961));
		warekiList.add(new WarekiBean("応和", "おうわ ",961,964));
		warekiList.add(new WarekiBean("康保", "こうほう ",964,968));
		warekiList.add(new WarekiBean("安和", "あんな ",968,970));
		warekiList.add(new WarekiBean("天禄", "てんろく ",970,973));
		warekiList.add(new WarekiBean("天延", "てんえん ",973,976));
		warekiList.add(new WarekiBean("貞元", "じょうげん ",976,978));
		warekiList.add(new WarekiBean("天元", "てんげん ",978,983));
		warekiList.add(new WarekiBean("永観", "えいかん ",983,985));
		warekiList.add(new WarekiBean("寛和", "かんな ",985,987));
		warekiList.add(new WarekiBean("永延", "えいえん ",987,989));
		warekiList.add(new WarekiBean("永祚", "えいそ ",989,990));
		warekiList.add(new WarekiBean("正暦", "しょうりゃく ",990,995));
		warekiList.add(new WarekiBean("長徳", "ちょうとく ",995,999));
		warekiList.add(new WarekiBean("長保", "ちょうほう（ちょうほ） ",999,1004));
		warekiList.add(new WarekiBean("寛弘", "かんこう ",1004,1012));
		warekiList.add(new WarekiBean("長和", "ちょうわ ",1012,1017));
		warekiList.add(new WarekiBean("寛仁", "かんにん ",1017,1021));
		warekiList.add(new WarekiBean("治安", "じあん ",1021,1024));
		warekiList.add(new WarekiBean("万寿", "まんじゅ ",1024,1028));
		warekiList.add(new WarekiBean("長元", "ちょうげん ",1028,1037));
		warekiList.add(new WarekiBean("長暦", "ちょうりゃく ",1037,1040));
		warekiList.add(new WarekiBean("長久", "ちょうきゅう ",1040,1044));
		warekiList.add(new WarekiBean("寛徳", "かんとく ",1044,1046));
		warekiList.add(new WarekiBean("永承", "えいしょう ",1046,1053));
		warekiList.add(new WarekiBean("天喜", "てんぎ（てんき） ",1053,1058));
		warekiList.add(new WarekiBean("康平", "こうへい ",1058,1065));
		warekiList.add(new WarekiBean("治暦", "じりゃく ",1065,1069));
		warekiList.add(new WarekiBean("延久", "えんきゅう ",1069,1074));
		warekiList.add(new WarekiBean("承保", "じょうほう ",1074,1077));
		warekiList.add(new WarekiBean("承暦", "じょうりゃく（しょうりゃく） ",1077,1081));
		warekiList.add(new WarekiBean("永保", "えいほう（えいほ） ",1081,1084));
		warekiList.add(new WarekiBean("応徳", "おうとく ",1084,1087));
		warekiList.add(new WarekiBean("寛治", "かんじ ",1087,1094));
		warekiList.add(new WarekiBean("嘉保", "かほう ",1094,1096));
		warekiList.add(new WarekiBean("永長", "えいちょう ",1096,1097));
		warekiList.add(new WarekiBean("承徳", "じょうとく ",1097,1099));
		warekiList.add(new WarekiBean("康和", "こうわ ",1099,1104));
		warekiList.add(new WarekiBean("長治", "ちょうじ ",1104,1106));
		warekiList.add(new WarekiBean("嘉承", "かしょう ",1106,1108));
		warekiList.add(new WarekiBean("天仁", "てんにん ",1108,1110));
		warekiList.add(new WarekiBean("天永", "てんえい ",1110,1113));
		warekiList.add(new WarekiBean("永久", "えいきゅう ",1113,1118));
		warekiList.add(new WarekiBean("元永", "げんえい ",1118,1120));
		warekiList.add(new WarekiBean("保安", "ほうあん ",1120,1124));
		warekiList.add(new WarekiBean("天治", "てんじ ",1124,1126));
		warekiList.add(new WarekiBean("大治", "だいじ ",1126,1131));
		warekiList.add(new WarekiBean("天承", "てんしょう（てんじょう） ",1131,1132));
		warekiList.add(new WarekiBean("長承", "ちょうしょう（ちょうじょう） ",1132,1135));
		warekiList.add(new WarekiBean("保延", "ほうえん ",1135,1141));
		warekiList.add(new WarekiBean("永治", "えいじ ",1141,1142));
		warekiList.add(new WarekiBean("康治", "こうじ ",1142,1144));
		warekiList.add(new WarekiBean("天養", "てんよう ",1144,1145));
		warekiList.add(new WarekiBean("久安", "きゅうあん ",1145,1151));
		warekiList.add(new WarekiBean("仁平", "にんぺい（にんぴょう） ",1151,1154));
		warekiList.add(new WarekiBean("久寿", "きゅうじゅ ",1154,1156));
		warekiList.add(new WarekiBean("保元", "ほうげん ",1156,1159));
		warekiList.add(new WarekiBean("平治", "へいじ ",1159,1160));
		warekiList.add(new WarekiBean("永暦", "えいりゃく ",1160,1161));
		warekiList.add(new WarekiBean("応保", "おうほう（おうほ） ",1161,1163));
		warekiList.add(new WarekiBean("長寛", "ちょうかん ",1163,1165));
		warekiList.add(new WarekiBean("永万", "えいまん ",1165,1166));
		warekiList.add(new WarekiBean("仁安", "にんあん（にんなん） ",1166,1169));
		warekiList.add(new WarekiBean("嘉応", "かおう ",1169,1171));
		warekiList.add(new WarekiBean("承安", "しょうあん（じょうあん） ",1171,1175));
		warekiList.add(new WarekiBean("安元", "あんげん ",1175,1177));
		warekiList.add(new WarekiBean("治承", "じしょう ",1177,1181));
		warekiList.add(new WarekiBean("養和", "ようわ ",1181,1182));
		warekiList.add(new WarekiBean("寿永", "じゅえい ",1182,1185));
		warekiList.add(new WarekiBean("元暦", "げんりゃく ",1184,1185));
		warekiList.add(new WarekiBean("文治", "ぶんじ ",1185,1190));
		warekiList.add(new WarekiBean("建久", "けんきゅう ",1190,1199));
		warekiList.add(new WarekiBean("正治", "しょうじ ",1199,1201));
		warekiList.add(new WarekiBean("建仁", "けんにん ",1201,1204));
		warekiList.add(new WarekiBean("元久", "げんきゅう ",1204,1206));
		warekiList.add(new WarekiBean("建永", "けんえい ",1206,1207));
		warekiList.add(new WarekiBean("承元", "じょうげん ",1207,1211));
		warekiList.add(new WarekiBean("建暦", "けんりゃく ",1211,1213));
		warekiList.add(new WarekiBean("建保", "けんぽう（けんぽ） ",1213,1219));
		warekiList.add(new WarekiBean("承久", "じょうきゅう（しょうきゅう） ",1219,1222));
		warekiList.add(new WarekiBean("貞応", "じょうおう ",1222,1224));
		warekiList.add(new WarekiBean("元仁", "げんにん ",1224,1225));
		warekiList.add(new WarekiBean("嘉禄", "かろく ",1225,1227));
		warekiList.add(new WarekiBean("安貞", "あんてい ",1227,1229));
		warekiList.add(new WarekiBean("寛喜", "かんぎ ",1229,1232));
		warekiList.add(new WarekiBean("貞永", "じょうえい ",1232,1233));
		warekiList.add(new WarekiBean("天福", "てんぷく ",1233,1234));
		warekiList.add(new WarekiBean("文暦", "ぶんりゃく ",1234,1235));
		warekiList.add(new WarekiBean("嘉禎", "かてい ",1235,1238));
		warekiList.add(new WarekiBean("暦仁", "りゃくにん ",1238,1239));
		warekiList.add(new WarekiBean("延応", "えんおう ",1239,1240));
		warekiList.add(new WarekiBean("仁治", "にんじ ",1240,1243));
		warekiList.add(new WarekiBean("寛元", "かんげん ",1243,1247));
		warekiList.add(new WarekiBean("宝治", "ほうじ ",1247,1249));
		warekiList.add(new WarekiBean("建長", "けんちょう ",1249,1256));
		warekiList.add(new WarekiBean("康元", "こうげん ",1256,1257));
		warekiList.add(new WarekiBean("正嘉", "しょうか ",1257,1259));
		warekiList.add(new WarekiBean("正元", "しょうげん ",1259,1260));
		warekiList.add(new WarekiBean("文応", "ぶんおう ",1260,1261));
		warekiList.add(new WarekiBean("弘長", "こうちょう ",1261,1264));
		warekiList.add(new WarekiBean("文永", "ぶんえい ",1264,1275));
		warekiList.add(new WarekiBean("建治", "けんじ ",1275,1278));
		warekiList.add(new WarekiBean("弘安", "こうあん ",1278,1288));
		warekiList.add(new WarekiBean("正応", "しょうおう ",1288,1293));
		warekiList.add(new WarekiBean("永仁", "えいにん ",1293,1299));
		warekiList.add(new WarekiBean("正安", "しょうあん ",1299,1302));
		warekiList.add(new WarekiBean("乾元", "けんげん ",1302,1303));
		warekiList.add(new WarekiBean("嘉元", "かげん ",1303,1306));
		warekiList.add(new WarekiBean("徳治", "とくじ ",1306,1308));
		warekiList.add(new WarekiBean("延慶", "えんぎょう（えんきょう） ",1308,1311));
		warekiList.add(new WarekiBean("応長", "おうちょう ",1311,1312));
		warekiList.add(new WarekiBean("正和", "しょうわ ",1312,1317));
		warekiList.add(new WarekiBean("文保", "ぶんぽう（ぶんぽ） ",1317,1319));
		warekiList.add(new WarekiBean("元応", "げんおう ",1319,1321));
		warekiList.add(new WarekiBean("元亨", "げんこう ",1321,1324));
		warekiList.add(new WarekiBean("正中", "しょうちゅう ",1324,1326));
		warekiList.add(new WarekiBean("嘉暦", "かりゃく ",1326,1329));
		warekiList.add(new WarekiBean("元徳", "げんとく ",1329,1331));
		warekiList.add(new WarekiBean("元徳", "げんとく ",1329,1332));
		warekiList.add(new WarekiBean("正慶", "しょうけい（しょうきょう） ",1332,1333));

		warekiList.add(new WarekiBean("建武", "けんむ ",1334,1338));
		warekiList.add(new WarekiBean("暦応", "りゃくおう ",1338,1342));
		warekiList.add(new WarekiBean("康永", "こうえい ",1342,1345));
		warekiList.add(new WarekiBean("貞和", "じょうわ ",1345,1350));
		warekiList.add(new WarekiBean("観応", "かんおう（かんのう） ",1350,1352));
		warekiList.add(new WarekiBean("文和", "ぶんな（ぶんわ） ",1352,1356));
		warekiList.add(new WarekiBean("延文", "えんぶん ",1356,1361));
		warekiList.add(new WarekiBean("康安", "こうあん ",1361,1362));
		warekiList.add(new WarekiBean("貞治", "じょうじ ",1362,1368));
		warekiList.add(new WarekiBean("応安", "おうあん ",1368,1375));
		warekiList.add(new WarekiBean("永和", "えいわ ",1375,1379));
		warekiList.add(new WarekiBean("康暦", "こうりゃく ",1379,1381));
		warekiList.add(new WarekiBean("永徳", "えいとく ",1381,1384));
		warekiList.add(new WarekiBean("至徳", "しとく ",1384,1387));
		warekiList.add(new WarekiBean("嘉慶", "かけい（かきょう） ",1387,1389));
		warekiList.add(new WarekiBean("康応", "こうおう ",1389,1390));

		warekiList.add(new WarekiBean("明徳", "めいとく ",1390,1394));
		warekiList.add(new WarekiBean("応永", "おうえい ",1394,1428));
		warekiList.add(new WarekiBean("正長", "しょうちょう ",1428,1429));
		warekiList.add(new WarekiBean("永享", "えいきょう ",1429,1441));
		warekiList.add(new WarekiBean("嘉吉", "かきつ ",1441,1444));
		warekiList.add(new WarekiBean("文安", "ぶんあん ",1444,1449));
		warekiList.add(new WarekiBean("宝徳", "ほうとく ",1449,1452));
		warekiList.add(new WarekiBean("享徳", "きょうとく ",1452,1455));
		warekiList.add(new WarekiBean("康正", "こうしょう ",1455,1457));
		warekiList.add(new WarekiBean("長禄", "ちょうろく ",1457,1460));
		warekiList.add(new WarekiBean("寛正", "かんしょう ",1460,1466));
		warekiList.add(new WarekiBean("文正", "ぶんしょう ",1466,1467));
		warekiList.add(new WarekiBean("応仁", "おうにん ",1467,1469));
		warekiList.add(new WarekiBean("文明", "ぶんめい ",1469,1487));
		warekiList.add(new WarekiBean("長享", "ちょうきょう ",1487,1489));
		warekiList.add(new WarekiBean("延徳", "えんとく ",1489,1492));
		warekiList.add(new WarekiBean("明応", "めいおう ",1492,1501));
		warekiList.add(new WarekiBean("文亀", "ぶんき ",1501,1504));
		warekiList.add(new WarekiBean("永正", "えいしょう ",1504,1521));
		warekiList.add(new WarekiBean("大永", "たいえい（だいえい） ",1521,1528));
		warekiList.add(new WarekiBean("享禄", "きょうろく ",1528,1532));
		warekiList.add(new WarekiBean("天文", "てんぶん ",1532,1555));
		warekiList.add(new WarekiBean("弘治", "こうじ ",1555,1558));
		warekiList.add(new WarekiBean("永禄", "えいろく ",1558,1570));
		warekiList.add(new WarekiBean("元亀", "げんき ",1570,1573));
		warekiList.add(new WarekiBean("天正", "てんしょう ",1573,1592));
		warekiList.add(new WarekiBean("文禄", "ぶんろく ",1592,1596));
		warekiList.add(new WarekiBean("慶長", "けいちょう ",1596,1615));
		warekiList.add(new WarekiBean("元和", "げんな ",1615,1624));
		warekiList.add(new WarekiBean("寛永", "かんえい ",1624,1644));
		warekiList.add(new WarekiBean("正保", "しょうほう（しょうほ） ",1644,1648));
		warekiList.add(new WarekiBean("慶安", "けいあん ",1648,1652));
		warekiList.add(new WarekiBean("承応", "じょうおう ",1652,1655));
		warekiList.add(new WarekiBean("明暦", "めいれき ",1655,1658));
		warekiList.add(new WarekiBean("万治", "まんじ ",1658,1661));
		warekiList.add(new WarekiBean("寛文", "かんぶん ",1661,1673));
		warekiList.add(new WarekiBean("延宝", "えんぽう ",1673,1681));
		warekiList.add(new WarekiBean("天和", "てんな ",1681,1684));
		warekiList.add(new WarekiBean("貞享", "じょうきょう ",1684,1688));
		warekiList.add(new WarekiBean("元禄", "げんろく ",1688,1704));
		warekiList.add(new WarekiBean("宝永", "ほうえい ",1704,1711));
		warekiList.add(new WarekiBean("正徳", "しょうとく ",1711,1716));
		warekiList.add(new WarekiBean("享保", "きょうほう（きょうほ） ",1716,1736));
		warekiList.add(new WarekiBean("元文", "げんぶん ",1736,1741));
		warekiList.add(new WarekiBean("寛保", "かんぽう（かんぽ） ",1741,1744));
		warekiList.add(new WarekiBean("延享", "えんきょう ",1744,1748));
		warekiList.add(new WarekiBean("寛延", "かんえん ",1748,1751));
		warekiList.add(new WarekiBean("宝暦", "ほうれき ",1751,1764));
		warekiList.add(new WarekiBean("明和", "めいわ ",1764,1772));
		warekiList.add(new WarekiBean("安永", "あんえい ",1772,1781));
		warekiList.add(new WarekiBean("天明", "てんめい ",1781,1789));
		warekiList.add(new WarekiBean("寛政", "かんせい ",1789,1801));
		warekiList.add(new WarekiBean("享和", "きょうわ ",1801,1804));
		warekiList.add(new WarekiBean("文化", "ぶんか ",1804,1818));
		warekiList.add(new WarekiBean("文政", "ぶんせい ",1818,1830));
		warekiList.add(new WarekiBean("天保", "てんぽう ",1830,1844));
		warekiList.add(new WarekiBean("弘化", "こうか ",1844,1848));
		warekiList.add(new WarekiBean("嘉永", "かえい ",1848,1854));
		warekiList.add(new WarekiBean("安政", "あんせい ",1854,1860));
		warekiList.add(new WarekiBean("万延", "まんえん ",1860,1861));
		warekiList.add(new WarekiBean("文久", "ぶんきゅう ",1861,1864));
		warekiList.add(new WarekiBean("元治", "げんじ ",1864,1865));
		warekiList.add(new WarekiBean("慶応", "けいおう ",1865,1868));
		warekiList.add(new WarekiBean("明治", "めいじ ",1868,1912));
		warekiList.add(new WarekiBean("大正", "たいしょう ",1912,1926));
		warekiList.add(new WarekiBean("昭和", "しょうわ ",1926,1989));
		warekiList.add(new WarekiBean("平成", "へいせい ",1989,9999));


		// 南朝リスト
		nanchoList.add(new WarekiBean("延元", "えんげん", 1336, 1340));
		nanchoList.add(new WarekiBean("興国", "こうこく", 1340, 1346));
		nanchoList.add(new WarekiBean("正平", "しょうへい", 1346, 1370));
		nanchoList.add(new WarekiBean("建徳", "けんとく", 1370, 1372));
		nanchoList.add(new WarekiBean("文中", "ぶんちゅう", 1372, 1375));
		nanchoList.add(new WarekiBean("天授", "てんじゅ", 1375, 1381));
		nanchoList.add(new WarekiBean("弘和", "こうわ", 1381, 1384));
		nanchoList.add(new WarekiBean("元中", "げんちゅう", 1384, 1392));

	}

	public String getNanchoWarekiString(int seireki) {
		WarekiBean bean = getWarekiBean(seireki, this.nanchoList);
		if(bean == null) {
			return null;
		}
		return getRekiString(seireki, bean);
	}

	public String getWarekiString(int seireki) {
		WarekiBean bean = getWarekiBean(seireki, this.warekiList);
		if(bean == null) {
			return null;
		}

		return getRekiString(seireki, bean);

	}

	private String getRekiString(int seireki, WarekiBean bean) {
		StringBuilder builder = new StringBuilder();
		builder.append(bean.getGengo());

		int nen = seireki - bean.getKaishi() + 1;
		if(nen == 1) {
			builder.append("元");
		}
		else {
			builder.append(nen);
		}
		builder.append("年");

		return builder.toString();
	}

	/**
	 * WarekiBeanを取得する
	 *
	 * @param seireki 西暦
	 * @param list
	 * @return
	 */
	private WarekiBean getWarekiBean(int seireki, List<WarekiBean> list) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getKaishi() <= seireki && list.get(i).getShuryo() >= seireki) {
				return list.get(i);
			}
		}
		return null;
	}

}
