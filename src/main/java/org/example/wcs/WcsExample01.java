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

import org.riversun.wcs.WcsClient;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

/**
 * Watson ConversationにJavaプログラムから接続するサンプル<br>
 * - JavaプログラムからWatsonのContext変数をセットする <br>
 * <br>
 * 【実行方法】<br>
 * このコードと対になるWatson conversation側のworkspaceファイル(json)は以下からダウンロード可能。<br>
 * https://riversun.github.io/wcs/org.riversun.WcsContextTestJa.zip<br>
 * これをWatson Conversationのworkspaceにインポートして、username,password,workspaceIdをセットする<br>
 * 
 */
public class WcsExample01 {

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

        // 初回のアクセス(welcomeノードを呼び出す)を行う
        // このユーザーの初回のアクセス時のみ#startConversationを呼び出し、結果(wcsWelcomeRes)を受け取る
        MessageResponse wcsWelcomeRes = watson.startConversation(wcsClientId);

        // Watsonからのレスポンスを取得して表示する
        // Watsonからのレスポンステキストは、JSONの配列形式(Javaだと#getTextメソッドで取得するとList<String>にマップされる）
        // なので、それらを結合して取得するために #getTextConcatenatedメソッドが準備されている。
        System.out.println("FROM WATSON:" + wcsWelcomeRes.getTextConcatenated(""));

        // WatsonにContext変数をセットする
        // （Watsonへの次のリクエストと同時にこのcontextの内容が送出されWatson側で反映される)
        final String ctxKey = "myRemoteParam";
        final String ctxValue = "I need you!";
        watson.put(wcsClientId, ctxKey, ctxValue);

        // Watsonにテキストを送る
        final String myMessage01 = "Hi! Watson";
        MessageResponse wcsRes01 = watson.sendMessage(wcsClientId, myMessage01);
        System.out.println("FROM WATSON:" + wcsRes01.getTextConcatenated(""));

        // Watsonにテキストを送り、テキストとして結果を取得する
        final String myMessage02 = "Hello! Watson";
        String wcsResText = watson.sendMessageForText(wcsClientId, myMessage02);
        System.out.println("FROM WATSON:" + wcsResText);
    }
}
