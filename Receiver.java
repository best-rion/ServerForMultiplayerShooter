import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Receiver extends Thread {

    public static List<String> queue = new ArrayList<>();

    int id;
    InputStreamReader streamReader;
    BufferedReader reader;
    PrintWriter writer;
    String data;

    public void run()
    {
        char[] buffer;

        int c;

        int i;    

        while (true)
        {
            buffer = new char[16];

            try
            {
                c=0;

                boolean go=false, print=false;

                if(reader.read()=='S')
                {
                    go=true;
                    print=true;
                }

                while(go)
                { 
                    if((i=reader.read())=='E')
                    {
                        go=false;
                        break;
                    }
                    buffer[c]=(char)i;
                    c++;
                }
                if (print)
                {
                    data= Util.fillGap(String.copyValueOf(buffer));
                    Connector.positions.put(id, data);
                
                    print = false;
                }
            } catch (IOException  e) {
                e.printStackTrace();
            }
        }
    }
}
