package com.kobunhada.common.koyomi;

/**
 * 
 * @version 1.00
 * @author Ippei.Kitajima
 */
public class GengoBean  {

    private int gengoCode = 0;
    private String gengoName = "";
    
    public GengoBean() {
    }

    public GengoBean(int gengoCode, String gengoName) {
        this.gengoCode = gengoCode;
        this.gengoName = gengoName;
    }


    public void setGengoCode(int gengoCode) {
        this.gengoCode = gengoCode;
    }


    public int getGengoCode() {
        return gengoCode;
    }


    public void setGengoName(String gengoName) {
        this.gengoName = gengoName;
    }


    public String getGengoName() {
        return gengoName;
    }
}