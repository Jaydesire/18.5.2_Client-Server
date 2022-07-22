import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        
        final int serverPort = 45300;
        System.out.println("Сервер запущен...");

        try (
                ServerSocket serverSocket = new ServerSocket(serverPort);
                Socket socket = serverSocket.accept();
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()
                                )
                        );
                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()
                        ), true
                )
        ) {
            System.out.println("Сервер подключен...");
            while (true) {
                System.out.println("Ожидается ввод пользователя");
                String userText = in.readLine();
                System.out.println("Ввод пользователя " + userText);
                userText = userText.replaceAll(" ", "");

                System.out.println("Текст без пробелов: " + userText);
                out.println(userText);
            }
        } catch (Exception e) {
            System.out.println("Ooops :(");
        }
    }
}

