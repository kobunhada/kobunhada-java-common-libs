//------------------------------------------------------------------------------
// KITASOFT API
// PopupMenuJTextComponent.java
//
// Copyright(c) 2005-2008 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2008/02/17	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.text.JTextComponent;

/**
 * JTextComponent用ポップアップメニュー表示クラス.
 * 使用例を以下に示す.<br>
 *<br>
 * JTextField name = new JTextField("新規作成");<br>
 * PopupMenuJTextComponent.addPopupMenuMouseListener(name);<br>
 * getContentPane().add(name);<br>
 *
 */
public class PopupMenuJTextComponent {

    /**
     * ポップアップメニューにテキスト編集メニューを追加する.<br>
     * <br>
     * ※テキスト編集メニューのみで良い場合は、addPopupMenuMouseListenerを使用
     * する事.
     * 
     * @param comp ポップアップメニュー追加コンポーネント
     * @param popup ポップアップメニュー
     */
    public static void addMenuItems(JTextComponent comp, JPopupMenu popup) {
        
        boolean isEditable = comp.isEditable();

        ActionMap map = comp.getActionMap();

        if (comp instanceof JPasswordField) {
            // 貼り付け
            addMenuItemPaste(map, popup, isEditable);
            // セパレータ追加
            popup.addSeparator();
            // 全て選択
            addMenuItemAllSelect(map, popup, isEditable);
        }
        else {
            // 切り取り
            addMenuItemCut(map, popup, isEditable);
            // コピー
            addMenuItemCopy(map, popup, isEditable);
            // 貼り付け
            addMenuItemPaste(map, popup, isEditable);
            // セパレータ
            popup.addSeparator();
            // 全て選択
            addMenuItemAllSelect(map, popup, isEditable);
        }
    }
    
    

    /**
     * テキスト編集メニューを持つポップアップメニューを表示させる.<br>
     * テキスト編集メニュー以外のメニューを追加したい場合は、Popupメニューインス
     * タンスを別途生成し、addMenuItems(JTextComponent, JPopupMenu)を使用する事.
     * 
     * @param comp ポップアップメニュー
     */
    public static void addPopupMenuMouseListener(JTextComponent comp) {
        comp.addMouseListener(new TextComponentMouseListener(comp));
    }
    
    private static void addMenuItemCopy(ActionMap map,
                                            JPopupMenu popup,
                                            boolean isEditable) {
        popup.add(createMenuItem(map.get("copy-to-clipboard"),
                                        "コピー(C)",
                                        KeyEvent.VK_C,
                                        true));
    }

    private static void addMenuItemPaste(ActionMap map,
                                            JPopupMenu popup,
                                            boolean isEditable) {
        popup.add(createMenuItem(map.get("paste-from-clipboard"),
                                "貼り付け(P)",
                                KeyEvent.VK_P,
                                isEditable));
    }

    private static void addMenuItemCut(ActionMap map,
                                            JPopupMenu popup,
                                            boolean isEditable) {
        popup.add(createMenuItem(map.get("cut-to-clipboard"),
                                        "切り取り(T)",
                                        KeyEvent.VK_T,
                                        isEditable));
    }
    
    private static void addMenuItemAllSelect(ActionMap map,
                                            JPopupMenu popup,
                                            boolean isEditable) {
        popup.add(createMenuItem(map.get("select-all"),
                                "すべて選択(A)",
                                KeyEvent.VK_A, true));
    }

    /**
     * メニューアイテムを生成する.
     * 
     * @param action アクション
     * @param text メニュータイトル
     * @param mnemonic キーイベントコード
     * @param enable 
     * @return メニューアイテム
     */
    private static JMenuItem createMenuItem(Action action,
                                              String text,
                                              int mnemonic,
                                              boolean enable) {

        JMenuItem menu = new JMenuItem(action);
        menu.setText(text);
        menu.setMnemonic(mnemonic);
        menu.setEnabled(enable);
        return menu;
    }

    /**
     * マウスイベントリスナクラス.
     * 
     * @author Ippei.Kitajima
     * @version 1.00
     */
    static class TextComponentMouseListener extends MouseAdapter {
        final JTextComponent comp;
        TextComponentMouseListener(JTextComponent comp) {
            this.comp = comp;
        }
        
        /**
         * マウスボタンリリース時イベント.
         * 
         * @param ev マウスイベント
         */
        public void mouseReleased(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                JPopupMenu popup = new JPopupMenu();
                addMenuItems(comp, popup);
                popup.show(comp, ev.getX(), ev.getY());
            }
        }
    }
}
