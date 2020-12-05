package com.kobunhada.common.swing;
import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * ファイルフィルタクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class FileFilterEx extends FileFilter  {
    
    /** 拡張子. */
    private String extension = "";
    
    /**
     * デフォルトコンストラクタ.
     */
    public FileFilterEx() {
        this(".txt");
    }
    
    /**
     * 引数付コンストラクタ.
     * 
     * @param extension 拡張子
     */
    public FileFilterEx(String extension) {
        this.extension = extension;
    }
    
    /**
     * 許可するファイルか否かを判定する.
     * 
     * @param file ファイルオブジェクト
     * @return true 許可するファイル、false それ以外
     */
    public boolean accept(File file) {
        String fileName = file.getName();
        
        if(fileName.endsWith(extension)) {
            return true;
        }
        
        return false;
        
    }
    
    /**
     * Descriptionを取得する.
     * 
     * @return Description
     */
    public String getDescription() {
        return "*" + extension;
    }
    
    /**
     * 拡張子を取得する.
     * 
     * @return 拡張子
     */
    public String getExtension() {
        return extension;
    }
}