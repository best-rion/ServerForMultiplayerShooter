import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Connector{
    public static List<Receiver> listOfThreads;
    public static HashMap<Integer,String> positions;

    public static void main(String[] args) {
        listOfThreads = new ArrayList<>();
        positions = new HashMap<>();
    
        try
        {
            @SuppressWarnings("resource")
            ServerSocket serverSocket = new ServerSocket(8040);


            Writer writer = new Writer();
            writer.start();
            
            int count = 0;

            while (true)
            {
                Socket socket = serverSocket.accept();

                Receiver receiver = new Receiver();

                receiver.streamReader = new InputStreamReader(socket.getInputStream());
                receiver.reader = new BufferedReader(receiver.streamReader);
                receiver.writer = new PrintWriter(socket.getOutputStream(), true);
                receiver.id = count;
                count++;

                listOfThreads.add(receiver);

                receiver.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
}
