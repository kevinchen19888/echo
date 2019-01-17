package base.bio.server;

import util.HostInfo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO echo 服务端程序demo
 */
public class BIOechoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(HostInfo.PORT);
        System.out.println("server has  started and  the port is:"+HostInfo.PORT);
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        boolean flag =true;

        while (flag){
            Socket client = serverSocket.accept();
            threadPool.submit(new EchoClientHandler(client));
        }
        threadPool.shutdown();
        serverSocket.close();
    }

    private static class EchoClientHandler implements Runnable {

        private Scanner scanner;
        private Socket client;

        private PrintStream out;

        public EchoClientHandler(Socket client) {
            this.client = client;
            try {
                scanner = new Scanner(this.client.getInputStream());
                scanner.useDelimiter("\n");// 设置换行符
                out = new PrintStream(this.client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            boolean flag = true;
            try {
                while (flag) {
                    // 如果有字符
                    if (scanner.hasNext()) {
                        String next = scanner.next().trim();
                        System.out.println("the  server received the  client send:  "+next);
                        if ("Bye".equalsIgnoreCase(next)) {
                            this.out.println("Bye");
                            flag = false;
                        } else {
                            this.out.println("Echo--> " + next);
                        }
                    }
                }
                this.scanner.close();
                this.out.close();
                this.client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
