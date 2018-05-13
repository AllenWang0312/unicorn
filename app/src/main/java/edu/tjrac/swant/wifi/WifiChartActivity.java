package edu.tjrac.swant.wifi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.yckj.baselib.util.L;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.tjrac.swant.unicorn.R;

public class WifiChartActivity extends AppCompatActivity {

    int receiv_port = 55555;
    int send_port = 44444;
    ServerSocket receiver;
    Socket sender;
    @BindView(R.id.et_ip_chart_to) EditText mEtIpChartTo;
    @BindView(R.id.bt_connect) Button mBtConnect;
    @BindView(R.id.et_content) EditText mEtContent;
    @BindView(R.id.bt_send) Button mBtSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_chart);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 声明一个ServerSocket对象
                try {
                    // 创建一个ServerSocket对象，并让这个Socket在1989端口监听
                    receiver = new ServerSocket(55555);
                    L.i("receiver socket create success");
                    // 调用ServerSocket的accept()方法，接受客户端所发送的请求，
                    // 如果客户端没有发送数据，那么该线程就停滞不继续

                    Socket socket = receiver.accept();
                    // 从Socket当中得到InputStream对象
                    InputStream inputStream = socket.getInputStream();
                    byte buffer[] = new byte[1024 * 4];
                    int temp = 0;
                    // 从InputStream当中读取客户端所发送的数据
                    while ((temp = inputStream.read(buffer)) != -1) {
                        L.i("receiver:", new String(buffer, 0, temp));
//                        System.out.println();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_connect)
    void connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String ip = mEtIpChartTo.getText().toString();
                L.i("connect", ip);
                try {// 创建一个Socket对象，并指定服务端的IP及端口号
                    sender = new Socket(ip, 55555);
                    // 创建一个InputStream用户读取要发送的文件。
//            InputStream inputStream = new FileInputStream("e://a.txt");
//            // 获取Socket的OutputStream对象用于发送数据。
//            OutputStream outputStream = sender.getOutputStream();
//            // 创建一个byte类型的buffer字节数组，用于存放读取的本地文件
//            byte buffer[] = new byte[4 * 1024];
//            int temp = 0;
//            // 循环读取文件
//            while ((temp = inputStream.read(buffer)) != -1) {
//                // 把数据写入到OuputStream对象中
//                outputStream.write(buffer, 0, temp);
//            }
                    // 发送读取的数据到服务端
//            outputStream.flush();

                    /** 或创建一个报文，使用BufferedWriter写入,看你的需求 **/
//          String socketData = "[2143213;21343fjks;213]";
//          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
//                  socket.getOutputStream()));
//          writer.write(socketData.replace("\n", " ") + "\n");
//          writer.flush();
                    /************************************************/
                } catch (UnknownHostException e) {
                    L.i("", "");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @OnClick(R.id.bt_send)
    void send() {
        try {
            OutputStream outputStream = sender.getOutputStream();
            outputStream.write(mBtSend.getText().toString().getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            if (receiver != null) receiver.close();
            if (sender != null) sender.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
