package com.qiyu.robot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qiyu.robot.Adapter.MsgAdapter;
import com.qiyu.robot.util.HttpUtil;
import com.qiyu.robot.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText inputText;

    private Button send;

    private RecyclerView msgRecyclerView;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Log.d("MainActivity", Utility.handleWriteJson("图灵的图片"));

        initMsgs();
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send_button);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);//当有新消息时，刷新RecyclerView中显示
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);//定位到最后一行
                    inputText.setText("");//清空输入框

//                    Utility.handleWriteJson(content);

                    HttpUtil.sendOkHttpRequest("http://www.tuling123.com/openapi/api", content,new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseText = response.body().string();
                            final String result = Utility.handleParserJson(responseText);
                            Log.d("MainActivity", "onResponse: " + result);
                            if (!TextUtils.isEmpty(result)) {
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Msg msg = new Msg(result, Msg.TYPE_RECEIVED);
                                        msgList.add(msg);
                                        adapter.notifyItemInserted(msgList.size() - 1);//当有新消息时，刷新RecyclerView中显示
                                        msgRecyclerView.scrollToPosition(msgList.size() - 1);//定位到最后一行
                                    }
                                });
                            }
                        }
                    });
                }

            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello Shanks!", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
    }
}
