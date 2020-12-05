//------------------------------------------------------------------------------
// KITASOFT API
// MessageDialogUtil.java
//
// Copyright(c) 2005-2007 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0.0	2007/02/25	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.swing;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * メッセージダイアログユーティリティクラス.
 * 
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class MessageDialogUtil {


    //----------------------------------------
    // クラス定数
    //----------------------------------------

    /** OK選択. */
    public static final int OK_SELECT = 0;
    /** CANCEL選択. */
    public static final int CANCEL_SELECT = 1;
    /** NO選択. */
    public static final int NO_SELECT = 2;
    /** YES選択. */
    public static final int YES_SELECT = 3;
    
    /**
     * コンストラクタ.
     */
    private MessageDialogUtil() {
    }
    
    /**
     * 情報ダイアログメッセージを表示する.
     * 
     * @param frame 親フレーム
     * @param title タイトル
     * @param msg メッセージ
     */
    public static void showInfomationMessage(Component frame,
                                             String title,
                                             String msg) {
        // 警告メッセージを表示
        showMessage(frame, title, msg, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * 警告ダイアログメッセージを表示する.
     * 
     * @param frame 親フレーム
     * @param title タイトル
     * @param msg メッセージ
     */
    public static void showWarningMessage(Component frame,
                                             String title,
                                             String msg) {
        // 警告メッセージを表示
        showMessage(frame, title, msg, JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * エラーダイアログメッセージを表示する.
     * 
     * @param frame 親フレーム
     * @param title タイトル
     * @param msg メッセージ
     */
    public static void showErrorMessage(Component frame,
                                             String title,
                                             String msg) {
        // 警告メッセージを表示
        showMessage(frame, title, msg, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * ダイアログメッセージを表示する.
     * 
     * @param frame 親フレーム
     * @param title タイトル
     * @param msg メッセージ
     * @param msgType メッセージタイプ
     */
    public static void showMessage(Component frame,
                                      String title,
                                      String msg,
                                      int msgType) {
        // メッセージを表示
        JOptionPane.showMessageDialog(frame, msg, title, msgType);
    }
    
    /**
     * 警告確認ダイアログを表示する(OK/Cancel).
     * 
     * @param frame 親フレーム
     * @param title タイトル
     * @param msg メッセージ
     * @return 処理結果
     */
    public static int showWarningOkCancel(Component frame,
                                            String title,
                                            String msg) {

        // 警告確認ダイアログを表示
        return showConfirm(frame, title, msg,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
    }

    /**
     * 警告確認ダイアログを表示する(Yes/No/Cancel).
     * 
     * @param frame 親フレーム
     * @param title タイトル
     * @param msg メッセージ
     * @return 処理結果
     */
    public static int showWarningYesNoCancel(Component frame,
                                                String title,
                                                String msg) {

        // 警告確認ダイアログを表示
        return showConfirm(frame, title, msg,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
    
    }
    
    /**
     * 質問確認ダイアログを表示する(OK/Cancel).
     * 
     * @param frame 親フレーム
     * @param title タイトル
     * @param msg メッセージ
     * @return 処理結果
     */
    public static int showQuestionOkCancel(Component frame,
                                            String title,
                                            String msg) {

        // 質問確認ダイアログを表示
        return showConfirm(frame, title, msg,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
    
    }

    /**
     * 質問確認ダイアログを表示する(Yes/No/Cancel).
     * 
     * @param frame 親フレーム
     * @param title タイトル
     * @param msg メッセージ
     * @return 処理結果
     */
    public static int showQuestionYesNoCancel(Component frame,
                                            String title,
                                            String msg) {

        // 質問確認ダイアログを表示
        return showConfirm(frame, title, msg,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
    
    }
    
    /**
     * 確認ダイアログを表示する.
     * 
     * @param frame 親フレーム
     * @param title タイトル
     * @param msg メッセージ
     * @param optionType 表示ボタンタイプ
     * @param msgType メッセージタイプ
     * @return 処理結果 
     */
    public static int showConfirm(Component frame,
                                      String title,
                                      String msg,
                                      int optionType,
                                      int msgType) {
        // 確認メッセージを表示
        int rtn = JOptionPane.showConfirmDialog(frame,
                                              msg,
                                              title,
                                              optionType,
                                              msgType);
        // コードを変換して返す
        return convertCode(rtn);
    }

    /**
     * Swingのダイアログ結果コードをKitaSoft API用コードへ変換する.
     * 
     * @param msgCode Swingのダイアログ結果コード
     * @return Kitasoft API用コード
     */
    private static int convertCode(int msgCode) {
        
        if(msgCode == JOptionPane.OK_OPTION) {
            return OK_SELECT;
        }
        else if(msgCode == JOptionPane.CANCEL_OPTION) {
            return CANCEL_SELECT;
        }        
        else if(msgCode == JOptionPane.YES_OPTION) {
            return YES_SELECT;
        }        
        else if(msgCode == JOptionPane.NO_OPTION) {
            return NO_SELECT;
        }        
        
        return -1;
    }
}