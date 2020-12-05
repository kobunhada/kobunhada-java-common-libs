package com.kobunhada.common.swing;

/**
 * 
 * 
 * @version 1.00
 * @author Ippei.Kitajima
 */
public abstract class JTableConfig  {

    public abstract void setCellConfig(JTableEx table);
    
    public abstract boolean isCellEditable(int row, int column);
    
    
}