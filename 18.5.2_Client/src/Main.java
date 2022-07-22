import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final int serverPort = 45300;
        long startTime;

        try (
                Scanner scanner = new Scanner(System.in);
                Socket socket = new Socket("127.0.0.1", serverPort);
                BufferedReader in = new BufferedReader(
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
            while (true) {
                System.out.println("Ожидается ввод пользователя");
                String userInput = scanner.nextLine();
                System.out.println("Ввод пользователя: " + userInput);
                if (userInput.equals("end")) {
                    break;
                }
                out.println(userInput);
                startTime = System.currentTimeMillis();
                System.out.println("Ожидание решения от сервера");
                System.out.println("Текст без пробелов: " + in.readLine());
                System.out.println("Ожидание ответа составило: " + (System.currentTimeMillis() - startTime) + " мс.");
            }


        } catch (Exception e) {
            System.out.println("Ooops :(");
        }
    }


}
