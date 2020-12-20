package com.kobunhada.common.koyomi;
import java.util.Hashtable;
import java.util.Iterator;

import com.kobunhada.common.util.StringUtil;

/**
 *
 * @version 1.00
 * @author kobunhada
 */
public class QGGengo {
    /** 元号リスト. */
    private static Hashtable<String, GengoBean> table = new Hashtable<String, GengoBean>();

    /**
     * コンストラクタ.
     */
    private QGGengo() {
		table.put("289", new GengoBean(289, "平成"));
		table.put("288", new GengoBean(288, "昭和"));
		table.put("287", new GengoBean(287, "大正"));
		table.put("286", new GengoBean(286, "明治"));
		table.put("285", new GengoBean(285, "慶応"));
		table.put("284", new GengoBean(284, "元治"));
		table.put("283", new GengoBean(283, "文久"));
		table.put("282", new GengoBean(282, "万延"));
		table.put("281", new GengoBean(281, "安政"));
		table.put("280", new GengoBean(280, "嘉永"));
		table.put("279", new GengoBean(279, "弘化"));
		table.put("278", new GengoBean(278, "天保"));
		table.put("277", new GengoBean(277, "文政"));
		table.put("276", new GengoBean(276, "文化"));
		table.put("275", new GengoBean(275, "享和"));
		table.put("274", new GengoBean(274, "寛政"));
		table.put("273", new GengoBean(273, "天明"));
		table.put("272", new GengoBean(272, "安永"));
		table.put("271", new GengoBean(271, "明和"));
		table.put("270", new GengoBean(270, "宝暦"));
		table.put("269", new GengoBean(269, "寛延"));
		table.put("268", new GengoBean(268, "延享"));
		table.put("267", new GengoBean(267, "寛保"));
		table.put("266", new GengoBean(266, "元文"));
		table.put("265", new GengoBean(265, "文化"));
		table.put("264", new GengoBean(264, "元文"));
		table.put("263", new GengoBean(263, "文化"));
		table.put("262", new GengoBean(262, "元文"));
		table.put("261", new GengoBean(261, "文化"));
		table.put("260", new GengoBean(260, "元文"));
		table.put("259", new GengoBean(259, "享保"));
		table.put("258", new GengoBean(258, "正徳"));
		table.put("257", new GengoBean(257, "宝永"));
		table.put("256", new GengoBean(256, "元禄"));
		table.put("255", new GengoBean(255, "貞享"));
		table.put("254", new GengoBean(254, "天和"));
		table.put("253", new GengoBean(253, "延宝"));
		table.put("252", new GengoBean(252, "寛文"));
		table.put("251", new GengoBean(251, "万治"));
		table.put("250", new GengoBean(250, "明暦"));
		table.put("249", new GengoBean(249, "承応"));
		table.put("248", new GengoBean(248, "慶安"));
		table.put("247", new GengoBean(247, "正保"));
		table.put("246", new GengoBean(246, "寛永"));
		table.put("245", new GengoBean(245, "元和"));
		table.put("244", new GengoBean(244, "慶長"));
		table.put("243", new GengoBean(243, "文禄"));
		table.put("242", new GengoBean(242, "天正"));
		table.put("241", new GengoBean(241, "元亀"));
		table.put("240", new GengoBean(240, "永禄"));
		table.put("239", new GengoBean(239, "弘治"));
		table.put("238", new GengoBean(238, "天文"));
		table.put("237", new GengoBean(237, "享禄"));
		table.put("236", new GengoBean(236, "大永"));
		table.put("235", new GengoBean(235, "永正"));
		table.put("234", new GengoBean(234, "文亀"));
		table.put("233", new GengoBean(233, "明応"));
		table.put("232", new GengoBean(232, "延徳"));
		table.put("231", new GengoBean(231, "長享"));
		table.put("230", new GengoBean(230, "文明"));
		table.put("229", new GengoBean(229, "応仁"));
		table.put("228", new GengoBean(228, "文正"));
		table.put("227", new GengoBean(227, "寛正"));
		table.put("226", new GengoBean(226, "長禄"));
		table.put("225", new GengoBean(225, "康正"));
		table.put("224", new GengoBean(224, "享徳"));
		table.put("223", new GengoBean(223, "宝徳"));
		table.put("222", new GengoBean(222, "文安"));
		table.put("221", new GengoBean(221, "嘉吉"));
		table.put("220", new GengoBean(220, "永享"));
		table.put("219", new GengoBean(219, "正長"));
		table.put("218", new GengoBean(218, "応永"));
		table.put("217", new GengoBean(217, "明徳"));
		table.put("216", new GengoBean(216, "康応"));
		table.put("215", new GengoBean(215, "嘉慶"));
		table.put("214", new GengoBean(214, "至徳"));
		table.put("213", new GengoBean(213, "元中"));
		table.put("212", new GengoBean(212, "永徳"));
		table.put("211", new GengoBean(211, "弘和"));
		table.put("210", new GengoBean(210, "康暦"));
		table.put("209", new GengoBean(209, "永和"));
		table.put("208", new GengoBean(208, "天授"));
		table.put("207", new GengoBean(207, "文中"));
		table.put("206", new GengoBean(206, "建徳"));
		table.put("205", new GengoBean(205, "応安"));
		table.put("204", new GengoBean(204, "貞治"));
		table.put("203", new GengoBean(203, "康安"));
		table.put("202", new GengoBean(202, "延文"));
		table.put("201", new GengoBean(201, "文和"));
		table.put("200", new GengoBean(200, "観応"));
		table.put("199", new GengoBean(199, "正平"));
		table.put("198", new GengoBean(198, "貞和"));
		table.put("197", new GengoBean(197, "康永"));
		table.put("196", new GengoBean(196, "興国"));
		table.put("195", new GengoBean(195, "暦応"));
		table.put("194", new GengoBean(194, "延元"));
		table.put("193", new GengoBean(193, "建武"));
		table.put("192", new GengoBean(192, "正慶"));
		table.put("191", new GengoBean(191, "元弘"));
		table.put("190", new GengoBean(190, "元徳"));
		table.put("189", new GengoBean(189, "嘉暦"));
		table.put("188", new GengoBean(188, "正中"));
		table.put("187", new GengoBean(187, "元享"));
		table.put("186", new GengoBean(186, "元応"));
		table.put("185", new GengoBean(185, "文保"));
		table.put("184", new GengoBean(184, "正和"));
		table.put("183", new GengoBean(183, "応長"));
		table.put("182", new GengoBean(182, "延慶"));
		table.put("181", new GengoBean(181, "徳治"));
		table.put("180", new GengoBean(180, "嘉元"));
		table.put("179", new GengoBean(179, "乾元"));
		table.put("178", new GengoBean(178, "正安"));
		table.put("177", new GengoBean(177, "永仁"));
		table.put("176", new GengoBean(176, "正応"));
		table.put("175", new GengoBean(175, "弘安"));
		table.put("174", new GengoBean(174, "建治"));
		table.put("173", new GengoBean(173, "文永"));
		table.put("172", new GengoBean(172, "弘長"));
		table.put("171", new GengoBean(171, "文応"));
		table.put("170", new GengoBean(170, "正元"));
		table.put("169", new GengoBean(169, "正嘉"));
		table.put("168", new GengoBean(168, "康元"));
		table.put("167", new GengoBean(167, "建長"));
		table.put("166", new GengoBean(166, "宝治"));
		table.put("165", new GengoBean(165, "寛元"));
		table.put("164", new GengoBean(164, "仁治"));
		table.put("163", new GengoBean(163, "延応"));
		table.put("162", new GengoBean(162, "暦仁"));
		table.put("161", new GengoBean(161, "嘉禎"));
		table.put("160", new GengoBean(160, "文暦"));
		table.put("159", new GengoBean(159, "天福"));
		table.put("158", new GengoBean(158, "貞永"));
		table.put("157", new GengoBean(157, "寛喜"));
		table.put("156", new GengoBean(156, "安貞"));
		table.put("155", new GengoBean(155, "嘉禄"));
		table.put("154", new GengoBean(154, "元仁"));
		table.put("153", new GengoBean(153, "貞応"));
		table.put("152", new GengoBean(152, "承久"));
		table.put("151", new GengoBean(151, "建保"));
		table.put("150", new GengoBean(150, "建暦"));
		table.put("149", new GengoBean(149, "承元"));
		table.put("148", new GengoBean(148, "建永"));
		table.put("147", new GengoBean(147, "元久"));
		table.put("146", new GengoBean(146, "建仁"));
		table.put("145", new GengoBean(145, "正治"));
		table.put("144", new GengoBean(144, "建久"));
		table.put("143", new GengoBean(143, "文治"));
		table.put("142", new GengoBean(142, "元暦"));
		table.put("141", new GengoBean(141, "寿永"));
		table.put("140", new GengoBean(140, "養和"));
		table.put("139", new GengoBean(139, "治承"));
		table.put("138", new GengoBean(138, "安元"));
		table.put("137", new GengoBean(137, "承安"));
		table.put("136", new GengoBean(136, "嘉応"));
		table.put("135", new GengoBean(135, "仁安"));
		table.put("134", new GengoBean(134, "永万"));
		table.put("133", new GengoBean(133, "長寛"));
		table.put("132", new GengoBean(132, "応保"));
		table.put("131", new GengoBean(131, "永暦"));
		table.put("130", new GengoBean(130, "平治"));
		table.put("129", new GengoBean(129, "保元"));
		table.put("128", new GengoBean(128, "久寿"));
		table.put("127", new GengoBean(127, "仁平"));
		table.put("126", new GengoBean(126, "久安"));
		table.put("125", new GengoBean(125, "天養"));
		table.put("124", new GengoBean(124, "康治"));
		table.put("123", new GengoBean(123, "永治"));
		table.put("122", new GengoBean(122, "保延"));
		table.put("121", new GengoBean(121, "長承"));
		table.put("120", new GengoBean(120, "天承"));
		table.put("119", new GengoBean(119, "大治"));
		table.put("118", new GengoBean(118, "天治"));
		table.put("117", new GengoBean(117, "保安"));
		table.put("116", new GengoBean(116, "元永"));
		table.put("115", new GengoBean(115, "永久"));
		table.put("114", new GengoBean(114, "天永"));
		table.put("113", new GengoBean(113, "天仁"));
		table.put("112", new GengoBean(112, "嘉承"));
		table.put("111", new GengoBean(111, "長治"));
		table.put("110", new GengoBean(110, "康和"));
		table.put("109", new GengoBean(109, "承徳"));
		table.put("108", new GengoBean(108, "永長"));
		table.put("107", new GengoBean(107, "嘉宝"));
		table.put("106", new GengoBean(106, "寛治"));
		table.put("105", new GengoBean(105, "応徳"));
		table.put("104", new GengoBean(104, "永保"));
		table.put("103", new GengoBean(103, "承暦"));
		table.put("102", new GengoBean(102, "承保"));
		table.put("101", new GengoBean(101, "延久"));
		table.put("100", new GengoBean(100, "治暦"));
		table.put("99", new GengoBean(99, "康平"));
		table.put("98", new GengoBean(98, "天喜"));
		table.put("97", new GengoBean(97, "永承"));
		table.put("96", new GengoBean(96, "寛徳"));
		table.put("95", new GengoBean(95, "長久"));
		table.put("94", new GengoBean(94, "長暦"));
		table.put("93", new GengoBean(93, "長元"));
		table.put("92", new GengoBean(92, "万寿"));
		table.put("91", new GengoBean(91, "治安"));
		table.put("90", new GengoBean(90, "寛仁"));
		table.put("89", new GengoBean(89, "長和"));
		table.put("88", new GengoBean(88, "寛弘"));
		table.put("87", new GengoBean(87, "長宝"));
		table.put("86", new GengoBean(86, "長徳"));
		table.put("85", new GengoBean(85, "正暦"));
		table.put("84", new GengoBean(84, "永祚"));
		table.put("83", new GengoBean(83, "永延"));
		table.put("82", new GengoBean(82, "寛和"));
		table.put("81", new GengoBean(81, "永観"));
		table.put("80", new GengoBean(80, "天元"));
		table.put("79", new GengoBean(79, "貞元"));
		table.put("78", new GengoBean(78, "天延"));
		table.put("77", new GengoBean(77, "天禄"));
		table.put("76", new GengoBean(76, "安和"));
		table.put("75", new GengoBean(75, "康宝"));
		table.put("74", new GengoBean(74, "応和"));
		table.put("73", new GengoBean(73, "天徳"));
		table.put("72", new GengoBean(72, "天暦"));
		table.put("71", new GengoBean(71, "天慶"));
		table.put("70", new GengoBean(70, "承平"));
		table.put("69", new GengoBean(69, "延長"));
		table.put("68", new GengoBean(68, "延喜"));
		table.put("67", new GengoBean(67, "昌泰"));
		table.put("66", new GengoBean(66, "寛平"));
		table.put("65", new GengoBean(65, "仁和"));
		table.put("64", new GengoBean(64, "元慶"));
		table.put("63", new GengoBean(63, "貞観"));
		table.put("62", new GengoBean(62, "天安"));
		table.put("61", new GengoBean(61, "斎衡"));
		table.put("60", new GengoBean(60, "仁寿"));
		table.put("59", new GengoBean(59, "嘉祥"));
		table.put("58", new GengoBean(58, "承和"));
		table.put("57", new GengoBean(57, "天長"));
		table.put("56", new GengoBean(56, "弘仁"));
		table.put("55", new GengoBean(55, "大同"));
		table.put("54", new GengoBean(54, "延暦"));
		table.put("53", new GengoBean(53, "天応"));
		table.put("52", new GengoBean(52, "宝亀"));
		table.put("51", new GengoBean(51, "神護景雲"));
		table.put("50", new GengoBean(50, "天平神護"));
		table.put("49", new GengoBean(49, "天平宝字"));
		table.put("48", new GengoBean(48, "天平勝宝"));
		table.put("47", new GengoBean(47, "天平感宝"));
		table.put("46", new GengoBean(46, "天平"));
		table.put("45", new GengoBean(45, "神亀"));
		table.put("44", new GengoBean(44, "養老"));
		table.put("43", new GengoBean(43, "霊亀"));
		table.put("42", new GengoBean(42, "和銅"));
		table.put("41", new GengoBean(41, "慶雲"));
		table.put("40", new GengoBean(40, "大宝"));
		table.put("39", new GengoBean(39, "朱鳥"));
		table.put("38", new GengoBean(38, "白雉"));
		table.put("37", new GengoBean(37, "大化"));
		table.put("36", new GengoBean(36, "皇極"));
		table.put("35", new GengoBean(35, "舒明"));
		table.put("34", new GengoBean(34, "推古"));
		table.put("33", new GengoBean(33, "崇峻"));
		table.put("32", new GengoBean(32, "用明"));
		table.put("31", new GengoBean(31, "敏達"));
		table.put("30", new GengoBean(30, "欽明"));
		table.put("29", new GengoBean(29, "宣化"));
		table.put("28", new GengoBean(28, "安閑"));
		table.put("27", new GengoBean(27, "継体"));
		table.put("26", new GengoBean(26, "武烈"));
		table.put("25", new GengoBean(25, "仁賢"));
		table.put("24", new GengoBean(24, "顕宗"));
		table.put("23", new GengoBean(23, "清寧"));
		table.put("22", new GengoBean(22, "雄略"));
		table.put("21", new GengoBean(21, "安康"));
		table.put("20", new GengoBean(20, "允恭"));
		table.put("19", new GengoBean(19, "反正"));
		table.put("18", new GengoBean(18, "履中"));
		table.put("17", new GengoBean(17, "仁徳"));
		table.put("16", new GengoBean(16, "応神"));
		table.put("15", new GengoBean(15, "神功"));
		table.put("14", new GengoBean(14, "仲哀"));
		table.put("13", new GengoBean(13, "成務"));
		table.put("12", new GengoBean(12, "景行"));
		table.put("11", new GengoBean(11, "垂仁"));
		table.put("10", new GengoBean(10, "崇神"));
		table.put("9", new GengoBean(9, "開化"));
		table.put("8", new GengoBean(8, "孝元"));
		table.put("7", new GengoBean(7, "孝霊"));
		table.put("6", new GengoBean(6, "孝安"));
		table.put("5", new GengoBean(5, "孝昭"));
		table.put("4", new GengoBean(4, "懿徳"));
		table.put("3", new GengoBean(3, "安寧"));
		table.put("2", new GengoBean(2, "綏靖"));
		table.put("1", new GengoBean(1, "神武"));
    }

    /**
     *
     *
     * @param gengoCode
     * @return
     */
    public static String getGengoName(int gengoCode) {
        GengoBean bean = (GengoBean)table.get("" + gengoCode);
        if(StringUtil.isEmptyString(bean.getGengoName())) {
            return "";
        }
        return bean.getGengoName();
    }

    /**
     *
     *
     * @param gengoName
     * @return
     */
    public static int getGengoCode(String gengoName) {


        for(Iterator<String> ite = table.keySet().iterator(); ite.hasNext();) {
            GengoBean bean = (GengoBean)(table.get(ite.next()));

            if(gengoName.equals(bean.getGengoName())) {
                return bean.getGengoCode();
            }

        }

        return -1;
    }

}