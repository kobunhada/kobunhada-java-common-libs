package com.kobunhada.common.koyomi;

import java.util.Date;

import com.kobunhada.common.util.DateUtil;

public class QJKoyomiRangeBean {
	private String gengo = "";
	private String startYear = "";
	private String endYear = "";

	public QJKoyomiRangeBean(String gengo, String startYmd, String endYmd) {
		this.gengo = gengo;
		this.startYear = startYmd;
		this.endYear = endYmd;
	}

	public String getGengo() {
		return gengo;
	}

	public void setGengo(String gengo) {
		this.gengo = gengo;
	}

	public String getStartYmdString() {
		return startYear;
	}

	public String getEndYmdString() {
		return endYear;
	}

	public Date getStartYmd() {
		try {
			return DateUtil.parge(startYear, "yyyy/M/d");
		} catch (Throwable e) {
			return null;
		}
	}

	public Date getEndYmd() {
		try {
			return DateUtil.parge(endYear, "yyyy/M/d");
		} catch (Throwable e) {
			return null;
		}
	}

}
