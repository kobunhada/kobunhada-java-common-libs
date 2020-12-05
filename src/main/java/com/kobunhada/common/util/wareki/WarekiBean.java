package com.kobunhada.common.util.wareki;

public class WarekiBean {
	private String gengo = "";
	private String yomi = "";
	private int kaishi = -1;
	private int shuryo = -1;


	public WarekiBean(String gengo, String yomi, int kaishi, int shuryo) {
		this.gengo = gengo;
		this.yomi = yomi;
		this.kaishi = kaishi;
		this.shuryo = shuryo;
	}

	public String getGengo() {
		return gengo;
	}
	public void setGengo(String gengo) {
		this.gengo = gengo;
	}
	public String getYomi() {
		return yomi;
	}
	public void setYomi(String yomi) {
		this.yomi = yomi;
	}
	public int getKaishi() {
		return kaishi;
	}
	public void setKaishi(int kaishi) {
		this.kaishi = kaishi;
	}
	public int getShuryo() {
		return shuryo;
	}
	public void setShuryo(int shuryo) {
		this.shuryo = shuryo;
	}

}
