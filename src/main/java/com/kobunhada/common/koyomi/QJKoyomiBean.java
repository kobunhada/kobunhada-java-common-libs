package com.kobunhada.common.koyomi;

public class QJKoyomiBean {
	private String gengo = "";
	private String kanshi = "";
	private String[] tsukiList = null;
	private String[] tsukiSeirekiList = null;

	public QJKoyomiBean(String gengo, String kanshi, String[] tsukiList, String[] tsukiSeirekiList) {

		this.gengo = gengo;
		this.kanshi = kanshi;
		this.tsukiList = new String[tsukiList.length];
		for(int i = 0; i < tsukiList.length; i++) {
			this.tsukiList[i] = tsukiList[i];
		}

		this.tsukiSeirekiList = new String[tsukiSeirekiList.length];
		for(int i = 0; i < tsukiSeirekiList.length; i++) {
			this.tsukiSeirekiList[i] = tsukiSeirekiList[i];
		}
	}

	public String getGengo() {
		return gengo;
	}

	public String getKanshi() {
		return kanshi;
	}

	public String getTsuki(int index) {
		return tsukiList[index];
	}

	public String[] getTsukiList() {
		return tsukiList;
	}

	public String getTsukiSeireki(int index) {
		return tsukiSeirekiList[index];
	}

	public String[] getTsukiSeirekiList() {
		return tsukiSeirekiList;
	}


}
