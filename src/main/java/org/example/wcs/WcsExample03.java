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

import java.util.List;
import java.util.Map;

import org.riversun.wcs.WcsClient;

import com.ibm.watson.developer_cloud.conversation.v1.model.Entity;
import com.ibm.watson.developer_cloud.conversation.v1.model.Intent;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

/**
 * Watson ConversationにJavaプログラムから接続するサンプル<br>
 * - JavaプログラムからWatsonのレスポンス詳細を取り扱う<br>
 * <br>
 * 【実行方法】<br>
 * このコードと対になるWatson conversation側のworkspaceファイル(json)は以下からダウンロード可能。<br>
 * https://riversun.github.io/wcs/org.riversun.WcsContextTestJa.zip<br>
 * これをWatson Conversationのworkspaceにインポートして、username,password,workspaceIdをセットする<br>
 * 
 */
public class WcsExample03 {

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

        // 初回のアクセス(welcomeノードを呼び出す)を行う。
        MessageResponse res = watson.startConversation(wcsClientId);

        // 生のcontext
        Map<String, Object> context = res.getContext();

        // Watsonへの入力（テキスト等)
        Map<String, Object> input = res.getInput();

        // Watsonへの入力テキスト
        String inputText = res.getInputText();

        // Watsonが入力を解析した結果、判定されたIntent
        List<Intent> intents = res.getIntents();

        // Watsonが入力を解析した結果、取得されたEntities
        List<Entity> entities = res.getEntities();

        // Watsonからの出力（レスポンス）
        Map<String, Object> output = res.getOutput();

        // Watsonからの出力テキスト(List形式)
        List<String> text = res.getText();

        // Watsonからの出力テキスト(List形式)を指定したStringで結合したもの
        String textConcatenated = res.getTextConcatenated("");

        // JSON形式のWatsonからのレスポンスを表示する
        System.out.println("Response JSON from Watson=\n" + res.toString());

    }
}
