//------------------------------------------------------------------------------
// KITASOFT API
// Calc.java
//
// Copyright(c) 2005-2006 Ippei.Kitajima. All Right Reserved
//
// [改訂履歴]
// ver 1.0	2006/06/01	IPPEI.KITAJIMA	新規作成
//------------------------------------------------------------------------------
package com.kobunhada.common.util.calc;

import java.math.BigDecimal;

import java.util.Stack;
import java.util.Vector;

import com.kobunhada.common.util.StringUtil;


/**
 * 四則演算クラス.
 *
 * @author Ippei.Kitajima
 * @version 1.00
 */
public class Calc {

    /**
     * 四則演算を実行する. <br>
     * 引数 in の文字列を計算し、結果を文字列にて返す.<br>
     *
     * @param in 四則演算式文字列
     * @return 計算結果文字列
     * @throws java.lang.Exception 予期しない例外
     */
    public static String calc(String in) throws Exception {

        // 中置記法文字列を逆ポーランド記法式 に変換し、各要素毎にVecterに保持する.
        Vector<TokenElement> vec = convInvPolish(in);
        // ポーランド記法式リストを計算し、結果文字列を返す。
        return calcInvPolish(vec);

    }

    /**
     * 中置記法文字列を逆ポーランド記法式 に変換し、各要素毎にVecterに保持する.
     *
     * @param in 中置記法文字列(四則演算式文字列)
     * @return 逆ポーランド記法式を要素毎に格納したVectorインスタンス
     * @throws java.lang.Exception 予期しない例外
     */
	public static Vector<TokenElement> convInvPolish(String in)
    throws Exception {

		// 式（中置記法）文字列の両端に括弧をつける
        // 式（中置記法）文字列の全半角スペースを取り除く
		String inStr = StringUtil.deleteSpaceString( "(" + in + ")" );

        // 前回生成したトークン格納
		TokenElement old = null;
        // 生成したトークン格納
        TokenElement token = null;
        // 生成したトークンを格納
        Vector<TokenElement> opList = new Vector<TokenElement>();
        // 逆ポーランド記法式の順序でトークンを格納するリスト
        Vector<TokenElement> out = new Vector<TokenElement>();

        //----------------------------------------
        //
        //----------------------------------------
		for(int i=0; i< inStr.length(); i++){
			// 文字列から1文字取り出す
			String str = inStr.substring(i,i+1);

            //----------------------------------------
			// 1.文字が数値 2.文字が「-」且つ、前文字が四則演算子または左括弧
            //----------------------------------------
			if(StringUtil.isIntegerFrom0To9(str) ||
                str.equals("-") &&
                (isEnzanshiString(old.getValue()) || old.getValue().equals( "(" )))
            {

                token = setTokenNumber(inStr,i);
                i += token.getValue().length()-1;
                out.add(token);
			}
            //----------------------------------------
			// 文字が四則演算子の場合
            //----------------------------------------
			else if(isEnzanshiString(str)){
                token = setEnzanshiToken(str);
                // 演算子スタック処理
                while(opList.size() > 0){
                    TokenElement tmp = (TokenElement)opList.firstElement();

                    if(token.getPriority() > tmp.getPriority()){
                        opList.add(0,token);
                        break;
                    }
                    else{
                        out.add(tmp);
                        opList.remove(0);
                    }
                }
			}

            //----------------------------------------
			// 文字が左括弧の場合
            //----------------------------------------
			else if(str.equals( "(" )){
                token = new TokenLParenthesis();
                opList.add(0,token);
			}

            //----------------------------------------
			// 文字が右括弧の場合
            //----------------------------------------
			else if(str.equals( ")" )){
                token = new TokenRParenthesis();

                // 演算子スタック処理
                while(opList.size() > 0 ){
                    TokenElement tmp = (TokenElement)opList.firstElement();
                    if(tmp.getValue().equals(CalcConstant.TYPE_LPARENTHESIS)){
                        // 右カッコは左カッコといっしょに消える
                        opList.remove(0);
                        break;
                    }
                    else{
                        out.add(tmp);
                        opList.remove(0);
                    }
                }
			}
            //----------------------------------------
			// 文字が上記の何れにも当てはまらない場合（エラー）
            //----------------------------------------
            else{
                throw new RuntimeException("引数に不正な文字が存在します");
            }

            //次文字処理で使用する為にトークンを保持
            old = token;
		}


        return out;
	}

    /**
     * 逆ポーランド記法で記述された式を計算する.<br>
     * 逆ポーランド記法の要素で構成された Vector型インスタンスから、要素を取り出
     * して計算する。<br>
     *
     * @param tokenList 逆ポーランド記法の要素で構成された Vector型インスタンス
     * @return 計算結果
     * @throws java.lang.RuntimeException 不正な逆ポーランド記法の要素が存在した
     * 場合の例外
     */
    public static String calcInvPolish(Vector<TokenElement> tokenList)
    throws RuntimeException {

        // スタック
        Stack<TokenElement> stack = new Stack<TokenElement>();

        //----------------------------------------
        // トークンリストから、要素を1つづつ取り出し、計算する
        //----------------------------------------
        for(int i = 0; i < tokenList.size(); i++){

            TokenElement token = (TokenElement)tokenList.elementAt(i);

            //----------------------------------------
            //トークンが数値の場合
            //----------------------------------------
            if(StringUtil.isNumberString(token.getValue() ) ) {
                //スタックへ追加する
                stack.push(token);
            }
            //----------------------------------------
            //トークンが演算子の場合
            //----------------------------------------
            else if(isEnzanshiString(token.getValue())) {
                //スタックから要素を2つ取り出し、計算。結果をスタックへ追加する
                stack = calclate(stack,token);
            }
            //----------------------------------------
            //トークンが数値、演算子以外の場合はエラー
            //----------------------------------------
            else {
                // RuntimeExceptionをスロー
                throw new RuntimeException("引数に不正なトークンが存在します。");
            }
        }

        return ((TokenElement)stack.pop()).getValue();

    }
    /**
     * スタックから要素を2つ取り出し、計算する.<br>
     * 引数 stack より、2つの要素を取り出し token の式で計算。結果を文字列に変換
     * し、stackへ追加する.
     *
     * @param stack 数値を格納するスタック
     * @param token 演算子
     * @return 計算結果を格納したスタック
     */
    private static Stack<TokenElement> calclate(Stack<TokenElement> stack, TokenElement token){

        // スタックより1番目の要素を取り出す
        BigDecimal dec1
                = ((TokenNumberElement)stack.pop()).getBigDecimalValue();
        // スタックより2番目の要素を取り出す
        BigDecimal dec2
                = ((TokenNumberElement)stack.pop()).getBigDecimalValue();
        // 1、2番目の数値をtokenで指定された演算子で計算する
        BigDecimal dec3
                = ((TokenOperationElement)token).execute(dec2,dec1);

        // 計算結果をスタックへ追加する
        stack.push(new TokenNumberElement(dec3.toString()));

        return stack;
    }

    /**
     * 文字が演算子かどうかを判定する.演算子の場合は trueを、それ以外の場合は
     *  false を返す.
     *
     * @param str 対象文字
     * @return true:演算子、false:それ以外
     */
	private static boolean isEnzanshiString(String str){
		return str.equals("+") || str.equals("-") ||
               str.equals("*") || str.equals("/");
	}

    /**
     * 開始位置から数値型として有効な範囲の文字列を抽出したものから、TokenNumberElement
     * 型インスタンスを生成する。<br>
     * 抽出中、連続した小数点(.)が存在する場合は、RuntimeException を発生させる.
     *
     * @param inStr 対象文字列
     * @param startIndex 開始位置
     * @return 抽出した数値でインスタンス化した TokenNumberElement オブジェクト
     * @throws java.lang.Exception 予期しない例外
     */
    private static TokenNumberElement setTokenNumber(String inStr,int startIndex)
    throws Exception {

        StringBuffer sb = new StringBuffer(inStr.substring(startIndex,startIndex+1));
        //小数点出現回数
        int decimalPointCnt = 0;

        for(int i = startIndex + 1; i < inStr.length(); i++){
            // 文字列から1文字取り出す
			String str = inStr.substring(i,i+1);

            //取り出した文字列が0〜9の整数である場合
            if(StringUtil.isIntegerFrom0To9(str) ){
                sb.append(str);
            }

            //取り出した文字列が . の場合
            else if(str.equals(".")){

                //前文字も . の場合は例外を発生させる
                if(decimalPointCnt > 0 ){
                    throw new RuntimeException(
                        "入力した文字列が不正です(小数点が連続しています。)");
                }
                //文字列を追加する.
                sb.append(str);
                //小数点出現回数をカウントアップ
                decimalPointCnt++;
            }

            //取り出した文字列が上記以外の場合
            else{
                //ループを抜ける
                break;
            }

        }

        return new TokenNumberElement(sb.toString());
    }

    /**
     * 四則演算子文字列から四則演算子トークンインスタンスを生成する.<br>
     * 文字列が四則演算子以外の場合は null を返す.
     *
     * @param str 四則演算子文字
     * @return 四則演算子トークンインスタンス
     */
    private static TokenElement setEnzanshiToken(String str){

        //四則演算子トークン
        TokenElement token = null;
        //演算子が + の場合
		if(str.equals("+")){
            token = new TokenPlusElement();
        }

        //演算子が - の場合
        else if(str.equals("-")){
			token = new TokenMinusElement();
		}

        //演算子が * の場合
		else if(str.equals("*")){
            token = new TokenMultiElement();
        }

        //演算子が / の場合
        else if(str.equals("/")){
			token = new TokenDivideElement();
		}
        return token;

    }

}