/*
 * Copyright 2016-2017 Tom Misawa, riversun.org@gmail.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of 
 * this software and associated documentation files (the "Software"), to deal in the 
 * Software without restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the 
 * Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all 
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 *  INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR 
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package org.example.wcs;

import java.util.Map;

import org.riversun.wcs.WcsClient;

/**
 * Watson ConversationにJavaプログラムから接続するサンプル<br>
 * - JavaプログラムからWatsonのContext変数を取得する<br>
 * <br>
 * 【実行方法】<br>
 * このコードと対になるWatson conversation側のworkspaceファイル(json)は以下からダウンロード可能。<br>
 * https://riversun.github.io/wcs/org.riversun.WcsContextTestJa.zip<br>
 * これをWatson Conversationのworkspaceにインポートして、username,password,workspaceIdをセットする<br>
 * 
 */
public class WcsExample02 {

    // Watson ConversationのWorkspaceにアクセスするためのusername,password,workspaceIdを編集する
    private static final String WATSON_CONVERSATION_USERNAME = "EDIT_ME_USERNAME_HERE";
    private static final String WATSON_CONVERSATION_PASSWORD = "EDIT_ME_PASSWORD_HERE";
    private static final String WATCON_CONVERSATION_WORKSPACE_ID = "EDIT_ME_WORKSPACE_ID_HERE";

    public static void main(String[] args)
    {

        // ユーザーIDは自由に設定してOK。ユーザー毎に異なるIDを生成すれば、同時に複数のユーザーがWatsonと対話できる
        String wcsClientId = "dummy_user_id";

        // Watson Conversationにアクセスするクライアントを作る
        // username,password,workspaceIdはWatson Conversationのworkspace画面で確認できる
        WcsClient watson = new WcsClient(
                WATSON_CONVERSATION_USERNAME,
                WATSON_CONVERSATION_PASSWORD,
                WATCON_CONVERSATION_WORKSPACE_ID);

        // 初回のアクセス(welcomeノードを呼び出す)を行う。このアクセスでWelcomeノードでセットされたContextが取得できる
        watson.startConversation(wcsClientId);

        // Watson Conversationのノード上でセットされたContext変数を取得する

        // String型のContext変数 "myParam01"の値を取得する
        String myParam01 = watson.getAsString(wcsClientId, "myParam01");
        System.out.println("myParam01=" + myParam01);

        // String型のContext変数 "myParam02"の値を取得する
        String myParam02 = watson.getAsString(wcsClientId, "myParam02");
        System.out.println("myParam02=" + myParam02);

        // Integer型のContext変数 "myParam03"の値を取得する
        Integer myParam03 = watson.getAsInteger(wcsClientId, "myParam03");
        System.out.println("myParam03=" + myParam03);

        // Boolean型のContext変数 "myParam04"の値を取得する
        Boolean myParam04 = watson.getAsBoolean(wcsClientId, "myParam04");
        System.out.println("myParam04=" + myParam04);

        // ネストされたContext変数 "myParam05"の値をMap<String,Object>として取得する
        Map<String, Object> myParam05 = watson.getAsMap(wcsClientId, "myParam05");

        String subParam01 = (String) myParam05.get("subParam01");
        System.out.println("myParam05.subParam01=" + subParam01);

        String subParam02 = (String) myParam05.get("subParam02");
        System.out.println("myParam05.subParam02=" + subParam02);

    }
}
