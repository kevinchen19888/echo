package base.bio.client;

import util.HostInfo;
import util.InputDataUtil;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * BIO echo 客户端程序demo
 */
public class BIOclient {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket(HostInfo.HOST_DOMAIN, HostInfo.PORT);
        // 接收服务端信息内容
        Scanner scanner = new Scanner(client.getInputStream());
        scanner.useDelimiter("\n");
        // 向服务端发送信息内容
        PrintStream output = new PrintStream(client.getOutputStream());
        boolean flag = true;

        while (flag) {
            String inputData = InputDataUtil.getInputStr("please input the str to send:").trim();
            output.println(inputData);// 发送数据到服务端
            if (scanner.hasNext()) {
                String echo = scanner.next().trim();
                System.out.println("the server echo is:" + echo);
            }
            if ("Bye".equalsIgnoreCase(inputData)) {
                flag = false;
                System.out.println("end echo");
            }
        }
        scanner.close();
        output.close();
        client.close();
    }
}
