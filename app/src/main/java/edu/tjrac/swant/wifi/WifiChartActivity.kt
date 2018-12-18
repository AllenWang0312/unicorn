package edu.tjrac.swant.wifi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yckj.baselib.util.L
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_wifi_chart.*
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import java.net.UnknownHostException

class WifiChartActivity : AppCompatActivity() {

    internal var receiv_port = 55555
    internal var send_port = 44444
    internal var receiver: ServerSocket? = null
    internal var sender: Socket? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi_chart)
        Thread(Runnable {
            // 声明一个ServerSocket对象
            try {
                // 创建一个ServerSocket对象，并让这个Socket在1989端口监听
                receiver = ServerSocket(55555)
                L.i("receiver socket create success")
                // 调用ServerSocket的accept()方法，接受客户端所发送的请求，
                // 如果客户端没有发送数据，那么该线程就停滞不继续

                val socket = receiver!!.accept()
                // 从Socket当中得到InputStream对象
                val inputStream = socket.getInputStream()
                val buffer = ByteArray(1024 * 4)
                var temp = emptyArray<Byte>()
                // 从InputStream当中读取客户端所发送的数据
//                while ((temp = inputStream.read(buffer)) ) {
//                    L.i("receiver:", String(buffer, 0, temp))
//                    //                        System.out.println();
//                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }).start()

    }

    //    @OnClick(R.id.bt_connect)
    internal fun connect() {
        Thread(Runnable {
            val ip = et_ip_chart_to.getText().toString()
            L.i("connect", ip)
            try {// 创建一个Socket对象，并指定服务端的IP及端口号
                sender = Socket(ip, 55555)
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

                /** 或创建一个报文，使用BufferedWriter写入,看你的需求  */

                /** 或创建一个报文，使用BufferedWriter写入,看你的需求  */
                //          String socketData = "[2143213;21343fjks;213]";
                //          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                //                  socket.getOutputStream()));
                //          writer.write(socketData.replace("\n", " ") + "\n");
                //          writer.flush();
                /** */
                /** */
            } catch (e: UnknownHostException) {
                L.i("", "")
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }).start()

    }

//    @OnClick(R.id.bt_send)
    internal fun send() {
        try {
            val outputStream = sender!!.getOutputStream()
            outputStream.write(bt_send.getText().toString().toByteArray())
            outputStream.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun onDestroy() {
        try {
            if (receiver != null) receiver!!.close()
            if (sender != null) sender!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        super.onDestroy()
    }
}
