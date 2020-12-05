//------------------------------------------------------------------------------
// KITASOFT API
// StringUtil.java
//
// Copyright(c) 2005-2011 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0.0	2006/11/16	IPPEI.KITAJIMA	新規作成
// ver 1.0.1    2008/03/05  IPPEI.KITAJIMA  replaceメソッドを修正
// ver 1.0.2    2008/03/15  IPPEI.KITAJIMA  replaceメソッドを修正
// ver 1.0.3    2009/11/03  IPPEI.KITAJIMA  getFileName, getFilePathについてディレクトリ区切り文字を"/"のみ
//                                          の対応から、"¥", "/"の対応へ修正
// ver 1.0.4    2011/02/26  IPPEI.KITAJIMA  countKeywordメソッドを追加
//------------------------------------------------------------------------------
package com.kobunhada.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.kobunhada.common.debug.SystemOut;


/**
 * 文字列操作ユーティリティクラス.
 *
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class StringUtil {

	/** 全角変換用テーブル */
	private static final String[] ZENKAKU_TBL = {
                             "ヴ","ガ","ギ","グ","ゲ","ゴ",
                             "ザ","ジ","ズ","ゼ","ゾ","ダ","ヂ","ヅ","デ","ド",
                             "バ","ビ","ブ","ベ","ボ","パ","ピ","プ","ペ","ポ",
                             "ア","イ","ウ","エ","オ","カ","キ","ク","ケ","コ",
                             "サ","シ","ス","セ","ソ","タ","チ","ツ","テ","ト",
                             "ナ","ニ","ヌ","ネ","ノ","ハ","ヒ","フ","ヘ","ホ",
                             "マ","ミ","ム","メ","モ","ヤ","ユ","ヨ","ワ","ン",
                             "ラ","リ","ル","レ","ロ","ァ","ィ","ゥ","ェ","ォ",
                             "１","２","３","４","５","６","７","８","９","０",
                             "ａ","ｂ","ｃ","ｄ","ｅ","ｆ","ｇ","ｈ","ｉ","ｊ",
                             "ｋ","ｌ","ｍ","ｎ","ｏ","ｐ","ｑ","ｒ","ｓ","ｔ",
                             "ｕ","ｖ","ｗ","ｘ","ｙ","Ａ","Ｂ","Ｃ","Ｄ","Ｅ",
                             "Ｆ","Ｇ","Ｈ","Ｉ","Ｊ","Ｋ","Ｌ","Ｍ","Ｎ","Ｏ",
                             "Ｐ","Ｑ","Ｒ","Ｓ","Ｔ","Ｕ","Ｖ","Ｗ","Ｘ","Ｙ",
                             "Ｚ","ｚ",
                             "ャ","ュ","ョ","ッ",
                             "！","”","＃","＄","％","＆","’","（","）","＝",
                             "｀","｜","−","＾","￥","＠","｛","｝","＊","＋",
                             "￣","「","」","：","；","、","。","／","＿","　",
                             "＜","＞","？","、","。","・","，","．",
                             "ヲ","ー","゛","゜","  "};

	/**
	 * 半角変換用テーブル
	 */
	private static final String[] HANKAKU_TBL={
                             "ｳﾞ","ｶﾞ","ｷﾞ","ｸﾞ","ｹﾞ","ｺﾞ",
                             "ｻﾞ","ｼﾞ","ｽﾞ","ｾﾞ","ｿﾞ","ﾀﾞ","ﾁﾞ","ﾂﾞ","ﾃﾞ","ﾄﾞ",
                             "ﾊﾞ","ﾋﾞ","ﾌﾞ","ﾍﾞ","ﾎﾞ","ﾊﾟ","ﾋﾟ","ﾌﾟ","ﾍﾟ","ﾎﾟ",
                             "ｱ", "ｲ", "ｳ", "ｴ", "ｵ", "ｶ", "ｷ", "ｸ", "ｹ", "ｺ",
                             "ｻ", "ｼ", "ｽ", "ｾ", "ｿ", "ﾀ", "ﾁ", "ﾂ", "ﾃ", "ﾄ",
                             "ﾅ", "ﾆ", "ﾇ", "ﾈ", "ﾉ", "ﾊ", "ﾋ", "ﾌ", "ﾍ", "ﾎ",
                             "ﾏ", "ﾐ", "ﾑ", "ﾒ", "ﾓ", "ﾔ", "ﾕ", "ﾖ", "ﾜ", "ﾝ",
                             "ﾗ", "ﾘ", "ﾙ", "ﾚ", "ﾛ", "ｧ", "ｨ", "ｩ", "ｪ", "ｫ",
                             "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                             "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                             "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                             "u", "v", "w", "x", "y", "A", "B", "C", "D", "E",
                             "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                             "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
                             "Z", "z",
                             "ｬ", "ｭ", "ｮ", "ｯ",
                             "!", "\"","#", "$", "%", "&", "'", "(", ")", "=",
                             "`", "|", "-", "^", "¥¥","@", "{", "}", "*", "+",
                             "~", "｢", "｣", ":", ";", "､", "｡", "/", "_", " ",
                             "<", ">", "?", "､", "｡", "･", ",", ".",
                             "ｦ", "ｰ", "ﾞ", "ﾟ", " "};

	/** 文字エンコーディング名称. */
	private static final String ENC = "UTF-8";

    /**
     * コンストラクタ.
     */
    private StringUtil() {
    }

    /**
     * 数値を指定の形にフォーマットする.
     *
     * @param value int型の数値
     * @param pattern フォーマット
     * @return フォーマットされた文字列
     */
    public static final String format(int value, String pattern) {
        DecimalFormat exFormat1 = new DecimalFormat(pattern);
        return exFormat1.format(value);

    }

    /**
     * 前ゼロ付加処理.<br>
     * 引数 target が、引数 lengthの桁数になる様に前ゼロを付加する.<br>
     * target.length() > length の場合は、targetをそのまま戻り値として返す.<br>
     * ＜例＞<br>
     * 引数 target:"12345"<br>
     * 引数 length:10<br>
     * 実行結果: "0000012345"<br>
     *
     * @param target 対象文字列
     * @param length 文字列の長さ
     * @return 前ゼロの付加された対象文字列
     */
    public static final String paddingZero(String target, int length) {

        //付加するゼロ数を取得
        int paddingCnt = length - target.length();
        //付加するゼロ文字列を取得
        String zero = createString("0",paddingCnt);

        return zero + target;
    }

    /**
     * long型の数値を指定の形にフォーマットする.
     *
     * @param value long型の数値
     * @param pattern フォーマット
     * @return フォーマットされた文字列
     */
    public static final String format(long value, String pattern) {

      DecimalFormat exFormat1 = new DecimalFormat(pattern);
        return exFormat1.format(value);

    }

    /**
     * double型の数値を指定の形にフォーマットする.
     *
     * @param value double型の数値
     * @param pattern フォーマット
     * @return フォーマットされた文字列
     */
    public static final String format(double value, String pattern) {

      DecimalFormat exFormat1 = new DecimalFormat(pattern);
        return exFormat1.format(value);

    }

    /**
     * 文字列が有効な数値かどうか判定する.有効な数値の場合は true を、それ以外
     * は false を返す.
     *
     * @param str 対象文字列
     * @return true:有効な数値、false:無効な数値
     */
    public static final boolean isNumberString(String str) {

        try{
            // 型変換が成功するか否かを判定
            new BigDecimal(str);

        } catch(Exception ex) {
            return false;
        }

        return true;
    }

    /**
     * 文字が 0〜9までの整数かどうかを判定する.左記条件に当てはまる場合は true
     * を、それ以外の場合は false を返す.
     *
     * @param str 対象文字
     * @return true:0〜9までの整数、false:それ以外
     */
	public static final boolean isIntegerFrom0To9(String str) {

		return str.equals("0") || str.equals("1") || str.equals("2") ||
                str.equals("3") || str.equals("4") || str.equals("5") ||
                str.equals("6") || str.equals("7") || str.equals("8") ||
                str.equals("9");
	}

    /**
     * 文字列が全角・半角スペースかどうか判定する.各スペースである場合はtrueを
     * そうでない場合はfalseを返す.
     *
     * @param str 判定対象文字列
     * @return true:全角・半角スペース、false:全角・半角スペース以外
     */
	public static final boolean isSpaceString(String str) {
        return str.equals(" ") || str.equals("　");
	}

    /**
     * 文字列から、全角・半角スペースを取り除く.
     *
     * @param inStr 対象文字列
     * @return 対象文字列から、全角・半角スペースを取り除いた文字列
     */
    public static final String deleteSpaceString(String inStr) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < inStr.length(); i++) {
            String str = inStr.substring(i,i+1);
            if(!isSpaceString(str)) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    /**
     * 文字列が null または空文字("")かどうかを判定する.左記条件に当てはまる場合
     * は true を、それ以外の場合は false を返す.
     *
     * @param str 判定対象文字列
     * @return true: null、または空文字、false:それ以外
     */
    public static final boolean isEmptyString(String str) {
		return (str == null || str.length() == 0);
    }


    /**
     * 文字列が null、空文字("")以外かどうかを判定する.左記条件に当てはまる場合
     * は true を、それ以外の場合は false を返す.
     *
     * @param str 判定対象文字列
     * @return true: null、または空文字、false:それ以外
     */
    public static final boolean isNotEmptyString(String str) {
    	return isEmptyString(str) == false;
    }

    /**
     * 指定長さ文字列生成.<br>
     * 対象文字列の後方に半角スペースを付加する.<br>
     *
     * @param target 対象文字列
     * @param length 文字列の長さ
     * @return 作成された文字列
     */
	public static final String paddingSpace(String target, int length) {

        // 付加する半角スペース数を取得
        int paddingCnt = length - target.length();
        //付加する個数の半角スペースを取得
        String space = createString(" ", paddingCnt);

        return target + space;
    }

    /**
     * 指定文字の文字列化.<br>
     * 指定された文字を指定された長さで生成する.<br>
     *
     * @param str 文字
     * @param  length 作成する文字列の長さ
     * @return buff 作成された文字列
     */
	public static final String createString(String str, int length) {

        StringBuffer bf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			bf.append(str);
        }
		return bf.toString();
	}

    /**
     * 指定文字列から指定文字を取り除く.<br>
     * 指定文字列から指定文字を取り除いた文字列を返却します.<br>
     *
     * @param target 取り除き対象文字列データ
     * @param delete 取り除く文字列データ
     * @return 作成された文字列
     */
    public static final String deleteString(String target, String delete) {

        return replace(target, delete, "");
    }

    /**
     * 半角文字を全角文字に変換する.<br>
     *
     * @param   args    全角変換対象文字列
     * @return  全角変換文字列
     */
	public static final String convertHalfToFull(String args) {

        // 返却用文字列格納変数
        StringBuffer zenkaku = new StringBuffer();

        try{
            // パラメータ文字列を１文字ずつチェック
            for(int i = 0; i < args.length(); i++) {
                // パラメータ文字列より１文字を取得
            	String worktext = args.substring(i, (i + 1));
                // 半角変換用テーブルと比較します
                for(int j = 0; j < HANKAKU_TBL.length; j++) {
                    // 対象１文字との比較
                    if(HANKAKU_TBL[j].equals(worktext) == true) {
                        // マッチしたので現在のインデックスで全角変換用テーブル
                        //より全角文字を取得
                        worktext = ZENKAKU_TBL[j];
                        break;
                    }
                }
                // 返却用変数に格納します
                zenkaku.append(worktext);
            }
        }
        catch(Exception e) {
            SystemOut.StackTrace(e);
        }

        // 変換文字列を返却
        return zenkaku.toString();
    }

    /**
     * 全角を半角に変換する.<br>
     *
     * @param args 半角変換対象文字列
     * @return 半角変換文字列
     */
	public static String convertFullToHalf(String args) {

        // 返却用文字列格納変数
        StringBuffer hankaku = new StringBuffer();

        try{
            // パラメータ文字列を１文字ずつチェックします
            for(int i = 0; i < args.length(); i++) {

                // パラメータ文字列より１文字を取得します
                String worktext = args.substring(i, (i + 1));

                // 全角変換用テーブルと比較します
                for(int j = 0; j < ZENKAKU_TBL.length; j++) {

                    // 対象１文字との比較
                    if(ZENKAKU_TBL[j].equals(worktext) == true) {
                        // マッチしたので現在のインデックスで半角変換用テーブル
                        //より半角文字を取得
                        worktext = HANKAKU_TBL[j];
                        break;
                    }
                }

                // 返却用変数に格納します
                hankaku.append(worktext);
            }
        }
        catch(Exception e) {
            SystemOut.StackTrace(e);
        }

        // 変換文字列を返却
        return hankaku.toString();
    }

	/**
	 * 全角スペースを半角スペースへ変換する.<br>
     *
	 * @param	str 変換対象文字列
	 * @return	変換後文字列
	 */
	public static String convertFullSpaceToHalfSpace(String str) {

        return replace(str, "　", " ");
    }

    /**
     * nullを""へ変換する. 引数:strがnull以外の場合はそのまま返す.
     *
     * @param str 対象文字列
     * @return 変換後文字列
     */
    public static String convertFromNull(String str) {
        if(isEmptyString(str)) {
            return "";
        }
        return str;
    }

	/**
	 * 文字列をバイト長で切り捨てる.<br>
     *
	 * @param str 文字列
	 * @param len 切り捨て長
	 * @return	切り捨て後の文字列
	 */
	public static String truncateByteLength(String str, int len) {

    	StringBuffer buff = new StringBuffer(str);

        for (int blen = str.getBytes().length, slen = str.length();
        	blen > len; blen = buff.toString().getBytes().length, slen--)
        	buff.setLength(slen);

		return buff.toString();
	}

	/**
	 * 指定文字列が半角カタカナを含むかどうか判断する.
	 *
	 * @param	str	文字列
	 * @return	半角カタカタあり:true、なし:false
	 * @throws	UnsupportedEncodingException
     *   指定された文字エンコーディングがサポートされていない場合
	 */
	public static boolean isIncludedHalfKatakana(String str)
    throws UnsupportedEncodingException {
		byte[] buffer = str.getBytes(ENC);

		for (int count = 0; count < buffer.length; count++) {
			// 全角１バイト目の場合
			if ((buffer[count] >= (byte) 0x81 && buffer[count] <= (byte) 0x9F) ||
				(buffer[count] >= (byte) 0xE0 && buffer[count] <= (byte) 0xEF)) {
				count++;		// ２バイト目を無視
				continue;
			// 半角カタカナの場合
			}
            else if (buffer[count] >= (byte) 0xA1 && buffer[count] <= (byte) 0xDF) {
				return true;
			}
		}

		return false;
	}

	/**
	 * ダブルクォーテーションのエスケープ.<br>
	 * 文字列中のダブルクォーテーションととエスケープ文字を
     * エスケープする.<br>
     *
	 * @param str 変換文字列
	 * @return エスケープ後の文字列
	 */
	public static final String getQuotationEscape(String str) {
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < str.length(); i++) {
			char chr = str.charAt(i);
			if(chr == '\"') {
				buf.append('\\');
			}
            else if(chr == '\\') {
				buf.append('\\');
			}
			buf.append(chr);
		}
		return buf.toString();
	}
	/**
	 * パッケージ名からモジュール名の取得.<br>
	 * 引数:strPackageNameからモジュール名を取得する.
     *
	 * @param strPackageName パッケージ名文字列
	 * @return モジュール名文字列
	 */
	public static final String getModuleName(String strPackageName) {
		return strPackageName.substring(
                strPackageName.lastIndexOf('.') + 1,strPackageName.length());
	}

	/**
	 * 文字列の前後から空白を削除.<br>
	 * 半角スペースと全角スペースを削除する.
     *
	 * @param src パッケージ名文字列
	 * @return 空白を取り除いた文字列
	 */
	public static final String trim(String src) {
		if(src != null) {
			String r = src;
			int x;
			for(x = 0; x < r.length(); x++) {
				char c = r.charAt(x);
				if((Character.isSpaceChar(c) == false)
                                     && ('　' != c)
                                     && ('\n' != c)
                                     && ('\r' != c)) {
					break;
				}
			}
			if(x > 0) {
				r = r.substring(x);
			}
			for(x = r.length() - 1; x >= 0; x--) {
				char c = r.charAt(x);
				if((Character.isSpaceChar(c) == false)
                                    && ('　' != c) && ('\n' != c)
                                    && ('\r' != c)) {
					break;
				}
			}
			if(x < r.length() - 1) {
				r = r.substring(0, x + 1);
			}
			return r;
		}else{
			return null;
		}
	}

    /**
     * 引数:str内にある全ての 引数:target文字列を引数:replaceに置換する.
     *
     * @param str 文字列
     * @param targetStr 置換対象文字列
     * @param replaceStr 置換後文字列
     * @return 置換処理後の文字列
     */
    public static String replace(
                            String str, String targetStr, String replaceStr) {

        StringBuffer sb = new StringBuffer();
        int targetStrLength = targetStr.length();
        int indexOf = 0;

        while(true) {
            indexOf = str.indexOf(targetStr);

            if(indexOf > 0) {
                sb.append(str.substring(0, indexOf) + replaceStr);
            }
            else if(indexOf == -1) {
                sb.append(str);
                break;
            }
            else if(indexOf == 0) {
                sb.append(replaceStr);
            }

            str = str.substring(indexOf + targetStrLength);
        }

       return sb.toString();

    }

    /**
     * パス付ファイル名からファイル名のみを抽出する.
     * <pre>
     * ディレクトリの区切り文字は "¥"、"/"の両方に対応する。
     * </pre>
     * @param fileName パス付ファイル名
     * @return ファイル名
     */
    public static String getFileName(String fileName) {

        String name = "";

        String replaceStr = replace(fileName, "¥¥", "/");

        int lastIndex = replaceStr.lastIndexOf("/");
        if(lastIndex < 0) {
            name = replaceStr;
        }else{
            name = replaceStr.substring(lastIndex +1,replaceStr.length());
        }

        return name;

    }

    /**
     * パス付ファイル名からパスのみ取得する.<br>
     * <pre>
     * ディレクトリの区切り文字は "¥"、"/"の両方に対応する。
     * </pre>
     *
     * @param fileName パス付ファイル名
     * @return パス
     */
    public static String getFilePath(String fileName) {

        String path = "";

        String replaceStr = replace(fileName, "¥¥", "/");

        int lastIndex = replaceStr.lastIndexOf("/");
        if(lastIndex < 0) {
            path = ".";
        }else{
            path = replaceStr.substring(0, lastIndex);
        }

        return path;

    }

    /**
     * 文字列を16進数表記の文字列へ変換する.<br>
     *
     * @param str 対象文字列
     * @param encodeType エンコードタイプ
     * @return 16進数表記の文字列
     */
    public static String convertFromStringToHexString(
                                            String str, String encodeType) {
        //Hex文字列格納用ストリングバッファ
        String hexString = null;
            try {
                // 文字列をエンコードタイプを指定してバイト配列へ変換
                byte[] bytes = str.getBytes(encodeType);
                //
                hexString = convertFromBytesToHexString(bytes);
            }
            catch(Throwable e) {
                SystemOut.StackTrace(e);
            }

        return hexString;

    }

    /**
     * 16進数表記文字列を文字列へ変換する.
     *
     * @param hexString 16進数表記文字列
     * @param encodeType エンコードタイプ
     * @return 文字列
     * @throws Throwable 予期しない例外
     */
    public static String convertFromHexStringToString(
                                        String hexString, String encodeType)
    throws Throwable {

        // 16進数表記文字列をバイト配列へ変換する
        byte[] bytes = convertFromHexStringToBytes(hexString);
        // バイト配列にエンコードタイプを指定して文字列へ変換する
        return new String(bytes, encodeType);

    }

    /**
     * 16進数表記文字列をバイト配列へ変換する.
     *
     * @param hexString 16進数表記文字列
     * @return バイト配列
     */
    public static byte[] convertFromHexStringToBytes(String hexString) {

        byte[] rtn = new byte[hexString.length() / 2];

        for(int i = 0; i < hexString.length(); i+=2) {

            String hex = hexString.substring(i, i + 2);

            int ix = Integer.parseInt(hex, 16) & 0X000000FF;

            rtn[i / 2] = (byte)ix;

        }

        return rtn;
    }

    /**
     * バイト配列を16進数表記文字列へ変換する.
     *
     * @param bytes バイト配列
     * @return 16進数表記文字列
     */
    public static String convertFromBytesToHexString(byte[] bytes) {

        //Hex文字列格納用ストリングバッファ
        StringBuffer sb = new StringBuffer();

        try {

            //バイト配列から、1バイトずつ取り出し、Hex文字列え変換する
            for(int i = 0; i < bytes.length; i++) {
                //1バイト取得
                int b = bytes[i];
                //取得したバイトにマスクを掛ける
                b &=  0x000000FF;
                String hex = Integer.toHexString(b).toUpperCase();

                if(hex.length() == 1) {
                    sb.append("0");
                }

                sb.append(hex);

            }
        }
        catch(Exception ex) {
            SystemOut.StackTrace(ex);
        }

        return sb.toString();

    }

    /**
     * OSに適した改行コードを取得する.
     *
     * @return 改行コード
     */
    public static String getChangingLine() {
        return System.getProperty("line.separator");
    }

    /**
     * 文字列に含まれるキーワードをカウントする.<br>
     * 対象文字列にキーワードがいくつあるかをカウントする.
     *
     * @param str 対象文字列
     * @param keyword キーワード
     * @return キーワード出現数
     */
    public static int countKeyword(String str, String keyword) {
    	if(isEmptyString(str) || isEmptyString(keyword)) {
    		return 0;
    	}

		int cnt = 0;
		for(int i = 0; i < str.length(); i++) {
			if(keyword.equals(str.substring(i, i + 1))) {
				cnt++;
			}
		}
		return cnt;
    }


}




