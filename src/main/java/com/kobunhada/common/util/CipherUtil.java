//------------------------------------------------------------------------------
// KITASOFT API
// CipherUtil.java
//
// Copyright(c) 2005-2007 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2007/01/24	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------

package com.kobunhada.common.util;

import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.security.SecureRandom;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.kobunhada.common.debug.SystemOut;
import com.kobunhada.common.io.FileManager;


/**
 * 暗号化・復号化ユーティリティクラス.
 *
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class CipherUtil {

    //----------------------------------------
    // publicクラス定数
    //----------------------------------------

    /** AES暗号化鍵サイズ デフォルト(128bit). */
    public static final int AES_KEY_SIZE_DEFAULT = 128;
    /** AES暗号化鍵サイズ 192bit. */
    public static final int AES_KEY_SIZE_192 = 192;
    /** AES暗号化鍵サイズ 256bit. */
    public static final int AES_KEY_SIZE_256 = 256;

    //----------------------------------------
    // privateクラス定数
    //----------------------------------------

    /** 文字列変換Mapオブジェクト識別子. */
    private static final String MAP_CONVERT_SIGN_STRING
        = "This character string was converted by the " +
           "kitasoft.common.util.CipherUtil.convertMapToString function.";

    /** Mapパラメタ用有効期限要素キー */
    private static final String MAP_PARAMS_EXPIRATION_DATE = "expiration_date";
    /** Mapパラメタ用有効期限日付フォーマット. */
    private static final String MAP_PARAMS_EXPIRATION_DATE_FORMAT
                                        = "yyyy/MM/dd HH:mm:ss SSS";
    /** AES暗号化鍵元文字列デフォルト. */
    private static final String AES_KEY_BASE_DEFAULT
                                                = "Shuhei.Kitajima.20060904";
    /** デフォルトハッシュキー */
    private static final String DEFAULT_HASH_KEY
                                                = "Shuhei.Kitajima.19830715";

    //----------------------------------------
    // クラス定数
    //----------------------------------------

    /** 暗号・復号化オブジェクト. */
    private static Cipher aes = null;

    /**
     * スタティックコンストラクタ.
     */
    static {
        try {
            // AES用暗号化クラスインスタンスを取得
            aes = Cipher.getInstance("AES/CBC/PKCS5Padding");

        }
        catch(Throwable e) {
            SystemOut.StackTrace(e);
        }
    }

    /**
     * コンストラクタ.
     */
    private CipherUtil() {
    }

    /**
     * 暗号化対象文字列格納用Mapインスタンスを取得する.
     *
     * @return Mapインスタンス
     */
    public static Map<String, String> getParamsMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put(MAP_PARAMS_EXPIRATION_DATE,
                DateUtil.getSystemDateTime(MAP_PARAMS_EXPIRATION_DATE_FORMAT));

        return map;
    }

    /**
     * 初期ベクタを生成する.
     *
     * @return 初期ベクタ
     * @throws Throwable 予期しない例外
     */
    public static String createIV() throws Throwable {
        // 疑似乱数ジェネレータインスタンス取得
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        // 初期ベクタを取得し、16進数表記文字列に変換して返す
        return StringUtil.convertFromBytesToHexString(random.generateSeed(16));

    }

    /**
     * デフォルト初期ベクタを取得する.<br>
     * 当メソッドにて取得できる初期ベクタは、常に同一です。ランダムな初期ベクタを
     * 取得したい場合は、createIVメソッドを使用して下さい.
     *
     * @return 初期ベクタ
     * @throws Throwable 予期しない例外
     */
    public static String getDefaultIV() throws Throwable {
        return createMD5HashString(DEFAULT_HASH_KEY);
    }

    /**
     * ハッシュ文字列を生成する.<br>
     * ハッシュ化アルゴリズムは、当クラスのデフォルトアルゴリズムが使用されます.
     *
     * @param value ハッシュ化対象文字列
     * @return ハッシュ化文字列
     * @throws Throwable 予期しない例外
     */
    public static String createDefalutHashString(String value)
    throws Throwable {
        return createMD5HashString(value);
    }


    /**
     * MD5ハッシュ文字列を生成する.
     *
     * @param value ハッシュ化対象文字列
     * @return MD5ハッシュ化文字列
     * @throws Throwable 予期しない例外
     */
    public static String createMD5HashString(String value) throws Throwable {
        return createHashString(value, "MD5");
    }

    /**
     * SHA-1ハッシュ文字列を生成する.
     *
     * @param value ハッシュ化対象文字列
     * @return SHA-1ハッシュ文字列
     * @throws Throwable 予期しない例外
     */
    public static String createSHA1HashString(String value)
    throws Throwable {
        return createHashString(value, "SHA-1");
    }
    /**
     * SHA-256ハッシュ文字列を生成する.
     *
     * @param value ハッシュ化対象文字列
     * @return SHA-256ハッシュ文字列
     * @throws Throwable 予期しない例外
     */
    public static String createSHA256HashString(String value)
    throws Throwable {
        return createHashString(value, "SHA-256");
    }
    /**
     * SHA-384ハッシュ文字列を生成する.
     *
     * @param value ハッシュ化対象文字列
     * @return SHA-384ハッシュ文字列
     * @throws Throwable 予期しない例外
     */
    public static String createSHA384HashString(String value)
    throws Throwable {
        return createHashString(value, "SHA-384");
    }
    /**
     * SHA-512ハッシュ文字列を生成する.
     *
     * @param value ハッシュ化対象文字列
     * @return SHA-512ハッシュ文字列
     * @throws Throwable 予期しない例外
     */
    public static String createSHA512HashString(String value)
    throws Throwable {
        return createHashString(value, "SHA-512");
    }

    /**
     * 指定のアルゴリズムにてハッシュ文字列を生成する.<br>
     * 引数:valueの文字列を引数:algorithmで指定したアルゴリズムによってハッシュ
     * 文字列を生成する.
     *
     * @param value ハッシュ化対象文字列
     * @param algorithm ハッシュアルゴリズム
     * @return ハッシュ文字列
     * @throws Throwable 予期しない例外
     */
    public static String createHashString(String value, String algorithm)
    throws Throwable {
        // ハッシュ化対象文字列をバイト配列に変換
        byte[] key = value.getBytes(FileManager.ENCODE_TYPE_UTF_8);
        // MD5ハッシュ化処理を実行したバイト配列をHEX文字列に変換して返す
        return StringUtil.convertFromBytesToHexString(
                        MessageDigest.getInstance(algorithm).digest(key)
                );

    }

    /**
     * AES暗号鍵文字列を生成する.
     *
     * @return AES暗号化鍵文字列
     * @throws Throwable 予期しない例外
     */
    public static String createAESKey() throws Throwable {
        return createAESKey(AES_KEY_BASE_DEFAULT);
    }
    /**
     * AES暗号鍵文字列を生成する.
     *
     * @param keyBase AES鍵生成元文字列
     * @return AES暗号化鍵文字列
     * @throws Throwable 予期しない例外
     */
    public static String createAESKey(String keyBase) throws Throwable {
        return createAESKey(keyBase, AES_KEY_SIZE_DEFAULT);
    }
    /**
     * AES暗号鍵文字列を生成する.
     *
     * @param keySize 生成するキーのサイズ(bit単位)
     * @return AES暗号化鍵文字列
     * @throws Throwable 予期しない例外
     */
    public static String createAESKey(int keySize) throws Throwable {
        return createAESKey(AES_KEY_BASE_DEFAULT, keySize);
    }
    /**
     * AES暗号鍵文字列を生成する.
     *
     * @param keyBase AES鍵生成元文字列
     * @param keySize 生成するキーのサイズ(bit単位)
     * @return AES暗号化鍵文字列
     * @throws Throwable 予期しない例外
     */
    public static String createAESKey(String keyBase, int keySize)
    throws Throwable {

        //128bitサイズのキー文字列を生成
        String tmp = createMD5HashString(keyBase);

        //----------------------------------------
        //指定したキーサイズ毎に、キー文字列を加工
        //----------------------------------------

        //指定キーサイズがデフォルト(128bit)の場合
        if(keySize == AES_KEY_SIZE_DEFAULT) {
            //そのまま返す
            return tmp;
        }
        //指定キーサイズが256bitの場合
        else if(keySize == AES_KEY_SIZE_256) {
            // 128bitサイズのキー文字列と、左記を更にMD5ハッシュ処理した文字列
            //を連結して返す。
            return tmp + createMD5HashString(tmp);

        }
        //指定キーサイズが192bitの場合
        else if(keySize == AES_KEY_SIZE_192) {
            // 128bitサイズのキー文字列と、左記を更にMD5ハッシュ処理した文字列
            //を連結し、そこから196bit分を抽出した文字列を返す。
            return (tmp + createMD5HashString(tmp)).substring(4, 52);
        }

        return null;

    }

    /**
     * 暗号化処理を実行する.
     *
     * @param key 暗号化鍵
     * @param input 暗号化対象文字列
     * @return 暗号化文字列
     * @throws Throwable 予期しない例外
     */
    public static String encrypt(String key, String input) throws Throwable {
        return encrypt(key, input, getDefaultIV());
    }
    /**
     * 暗号化処理を実行する.
     *
     * @param key 暗号化鍵
     * @param input 暗号化対象文字列
     * @param iv 初期ベクタ
     * @return 暗号化文字列
     * @throws Throwable 予期しない例外
     */
    public static String encrypt(String key, String input, String iv)
    throws Throwable {
        // 暗号化鍵文字列をバイト配列へ変換する
        byte[] bytesKey = StringUtil.convertFromHexStringToBytes(key);
        // 暗号化処理を行い、結果を返す
        return encrypt(bytesKey, input, iv);
    }

    /**
     * 暗号化処理を実行する.
     *
     * @param bytesKey 暗号化鍵
     * @param input 暗号化対象文字列
     * @param iv 初期ベクタ
     * @return 暗号化された文字列
     * @throws Throwable 予期しない例外
     */
    public static String encrypt(byte[] bytesKey, String input, String iv)
    throws Throwable {
        // AES用暗号化鍵を生成する
        SecretKeySpec skey = new SecretKeySpec(bytesKey, "AES");
        // 暗号化モードに初期化
        aes.init(Cipher.ENCRYPT_MODE,
                skey,
                new IvParameterSpec(
                        StringUtil.convertFromHexStringToBytes(iv)));
        // AES暗号化
        byte[] encryptBytes
                    = aes.doFinal(input.getBytes(FileManager.ENCODE_TYPE_UTF_8));
        // 暗号化されたバイト配列をHEX文字列へ変換して返す
        return StringUtil.convertFromBytesToHexString(encryptBytes);

    }

    /**
     * 暗号化処理を実行する.
     *
     * @param key 暗号化鍵
     * @param input 暗号化対象オブジェクトを要素に持つMapインスタンス
     * @return 暗号化文字列
     * @throws Throwable 予期しない例外
     */
    public static String encryptMap(String key, Map<String, String> input) throws Throwable {

        return encryptMap(key, input, getDefaultIV());
    }

    /**
     * 暗号化処理を実行する.
     *
     * @param key 暗号化鍵
     * @param input 暗号化対象オブジェクトを要素に持つMapインスタンス
     * @param iv 初期ベクタ
     * @return 暗号化文字列
     * @throws Throwable 予期しない例外
     */
    public static String encryptMap(String key, Map<String, String> input, String iv)
    throws Throwable {
        // Mapオブジェクトを文字列へ変換
        String inputString = convertMapToString(input);
        // 暗号化処理を実行して暗号化文字列を返す
        return encrypt(key, inputString, iv);
    }

    /**
     * 復号化処理を実行する.
     *
     * @param key 復号化鍵
     * @param input 復号化対象文字列
     * @return 復号化文字列
     * @throws Throwable 予期しない例外
     */
    public static String decrypt(String key, String input) throws Throwable {
        return decrypt(key, input, getDefaultIV());
    }
    /**
     * 復号化処理を実行する.
     *
     * @param key 復号化鍵
     * @param input 復号化対象文字列
     * @param iv 初期ベクタ
     * @return 復号化文字列
     * @throws Throwable 予期しない例外
     */
    public static String decrypt(String key, String input, String iv)
    throws Throwable {
        // 復号化文字列をバイト配列へ変換
        byte[] bytesKey = StringUtil.convertFromHexStringToBytes(key);
        // AES暗号化鍵オブジェクトを生成
        SecretKeySpec skey = new SecretKeySpec(bytesKey, "AES");
        // アルゴリズムパラメタインスタンスを取得
        AlgorithmParameters algParams = AlgorithmParameters.getInstance("AES");
        // 初期ベクタオブジェクトを取得
        IvParameterSpec ivParamSpec
            = new IvParameterSpec(StringUtil.convertFromHexStringToBytes(iv));
        // アルゴリズムパラメタに初期ベクタオブジェクトを設定し初期化
        algParams.init(ivParamSpec);
        // 復号化モードに初期化
        aes.init(Cipher.DECRYPT_MODE, skey, algParams);
        // 復号化
        byte[] m2 = aes.doFinal(StringUtil.convertFromHexStringToBytes(input));
        // バイト配列を16進数文字列へ変換
        String rtnHexStr = StringUtil.convertFromBytesToHexString(m2);
        // 16進数文字列を文字列へ変換して返す
        return StringUtil.convertFromHexStringToString(
                                              rtnHexStr,
                                              FileManager.ENCODE_TYPE_UTF_8);

    }

    /**
     * 復号化処理を実行する.
     *
     * @param key 復号化鍵
     * @param input 復号化対象文字列
     * @return 復号化したMapインスタンス
     * @throws Throwable 予期しない例外
     */
    public static Map<String, String> decryptMap(String key, String input)
    throws Throwable {
        return decryptMap(key, input, getDefaultIV());
    }

    /**
     * 復号化処理を実行する.
     *
     * @param key 復号化鍵
     * @param input 復号化対象文字列
     * @param iv 初期ベクタ
     * @return 復号化したMapインスタンス
     * @throws Throwable 予期しない例外
     */
    public static Map<String, String> decryptMap(String key, String input, String iv)
    throws Throwable {
        // 復号化処理を実行
        String decryptStr = decrypt(key, input, iv);
        // 文字列をMapインスタンスに変換して返す
        return convertParamStringToMap(decryptStr);

    }

    /**
     * 復号化処理を実行する(改竄チェック付).
     *
     * @param key 復号化鍵
     * @param input 復号化対象文字列
     * @param iv 初期ベクタ
     * @param hash ハッシュ文字列
     * @return 復号化したMapインスタンス
     * @throws Throwable 予期しない例外
     */
    public static Map<String, String> decryptMap(String key,
                                  String input,
                                  String iv,
                                  String hash) throws Throwable {

        // 復号化処理
        String decryptStr = decrypt(key, input, iv);
        // 復号化文字列からハッシュ文字列を生成
        String compareHash = createDefalutHashString(decryptStr);
        // 改竄チェック
        if(!compareHash.equals(hash)) {
            return null;
        }
        // 復号化文字列をMapインスタンスに変換して返す
        return convertParamStringToMap(decryptStr);

    }

    /**
     * 復号化処理を実行する(有効期限付).
     *
     * @param key 復号化鍵
     * @param input 復号化対象文字列
     * @param iv 初期ベクタ
     * @param limitSec 有効期限秒数
     * @return 復号化したMapインスタンス
     * @throws Throwable 予期しない例外
     */
    public static Map<String, String> decryptMap(String key,
                                  String input,
                                  String iv,
                                  int limitSec) throws Throwable {
        // 復号化処理を実行
        Map<String, String> map = decryptMap(key, input, iv);
        // システム日付を取得する.
        Date sysDate = DateUtil.getSystemDateTime();
        // 有効期限元日付を取得する
        Date expDate = DateUtil.parge(
                            (String)map.get(CipherUtil.MAP_PARAMS_EXPIRATION_DATE),
                            CipherUtil.MAP_PARAMS_EXPIRATION_DATE_FORMAT);
        // 有効期限日付を取得
        Date date = DateUtil.addSec(expDate, limitSec);
        // 有効期限日付がシステム日付より小さい場合は有効期限切れとして結果を
        // nullで返す
        if(date.compareTo(sysDate) < 0) {
            return null;
        }

        return map;

    }

    /**
     * 復号化処理を実行する(有効期限チェック、改竄チェック付).
     *
     * @param key 復号化鍵
     * @param input 復号化対象文字列
     * @param iv 初期ベクタ
     * @param hash ハッシュ文字列
     * @param limitSec 有効期限秒数
     * @return 復号化したMapインスタンス
     * @throws Throwable 予期しない例外
     */
    public static Map<String, String> decryptMap(String key,
                                  String input,
                                  String iv,
                                  String hash,
                                  int limitSec) throws Throwable {

        //----------------------------------------
        // 復号化処理(有効期限チェック付)
        //----------------------------------------
        Map<String, String> map = decryptMap(key, input, iv, hash);
        if(map == null) {
           return null;
        }

        //----------------------------------------
        // 有効期限チェック
        //----------------------------------------

        // システム日付を取得する.
        Date sysDate = DateUtil.getSystemDateTime();
        // 有効期限元日付を取得する
        Date expDate = DateUtil.parge(
                            (String)map.get(CipherUtil.MAP_PARAMS_EXPIRATION_DATE),
                            CipherUtil.MAP_PARAMS_EXPIRATION_DATE_FORMAT);
        // 有効期限日付を取得
        Date date = DateUtil.addSec(expDate, limitSec);
        // 有効期限日付がシステム日付より小さい場合は有効期限切れとして結果を
        // nullで返す
        if(date.compareTo(sysDate) < 0) {
            return null;
        }

        return map;

    }

    /**
     * 文字列が、Mapインスタンスを変換した文字列かどうかを判定する.<br>
     * 当メソッドは、引数:valueがMapインスタンスを convertMapToStringを使用して
     * 変換された文字列であるかどうかを判定します.<br>
     * 変換された文字列である場合は true を、そうでない場合は falseを返します.
     *
     * @param value 判定対象文字列
     * @return true:convertMapToStringを使用して変換された文字列である
     * 、false:そうではない
     */
    private static boolean isConvertMapString(String value) {

        return MAP_CONVERT_SIGN_STRING.equals(
                value.substring(0, MAP_CONVERT_SIGN_STRING.length()));

    }

    /**
     * Mapインスタンスを文字列へ変換する.<br>
     * ※当メソッドにて変換できるMapインスタンスは、要素に文字列型、若しくは文字
     * 列型に変換可能なデータ型を持つインスタンスに限ります.
     *
     * @param map 変換対象Mapインスタンス
     * @return 変換された文字列
     */
    public static String convertMapToString(Map<String, String> map) {
        // イテレータインスタンスを取得
        Iterator<String> ite = map.keySet().iterator();
        // 文字列バッファインスタンスを取得
        StringBuffer sb = new StringBuffer();
        // Map変換文字列識別子をバッファに追加
        sb.append(MAP_CONVERT_SIGN_STRING);

        // Mapインスタンスを文字列へ変換
        while(ite.hasNext()) {
            // 要素キーを取得
            String key = (String)ite.next();
            // 要素値を取得
            String value
                    = StringUtil.convertFromStringToHexString(
                          (String)map.get(key),
                          FileManager.ENCODE_TYPE_UTF_8
                      );
            // 要素キーと要素値を、「" " + "要素キー" + "=" + 要素値」の形式にし
            //てバッファに追加
            sb.append(" " + key + "=" + value);
        }
        // 変換後文字列を返す
        return sb.toString();

    }

    /**
     * 文字列を、Mapインスタンスへ変換する.<br>
     * 変換できない場合は null を返す.<br>
     * ※当メソッドは、引数:valueがMapインスタンスを convertMapToStringを使用して
     * 変換された文字列である場合のみ、Mapインスタンスへ変換する事が出来ます.<br>
     *
     * @param value 変換対象文字列
     * @return 変換後Mapインスタンス
     * @throws Throwable 予期しない例外
     */
    public static Map<String, String> convertParamStringToMap(String value)
    throws Throwable {

        // 変換できない文字列の場合はnullを返す
        if(!isConvertMapString(value)) {
            return null;
        }

        // 戻り値用Mapインスタンス
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        // 変換対象文字列部分のみを取得
        String tmp = value.substring(MAP_CONVERT_SIGN_STRING.length() + 1);



        // 変換対象文字列を半角スペース毎に分割する
        StringTokenizer tokeniz = new StringTokenizer(tmp, " ");
        // 分割数を取得する
        int count = tokeniz.countTokens();
        // Mapインスタンスに変換する
        for (int idx = 0; idx < count; idx++) {
            // 分割した内の1件を取得する
            String str = tokeniz.nextToken();
            // 取得した文字列内で、"="の出現位置を取得する.
            int equalStrIndex = str.indexOf("=");
            // "="出現位置以前の文字列を取得(要素キー)
            String key = str.substring(0, equalStrIndex);
            // "="出現位置以後の文字列を取得(要素値)
            String val = StringUtil.convertFromHexStringToString(
                                    str.substring(equalStrIndex + 1),
                                    FileManager.ENCODE_TYPE_UTF_8);
            // Mapに要素を追加する
            map.put(StringUtil.trim(key), val);

        }
        // Mapインスタンスを返す
        return map;
    }
}