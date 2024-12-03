import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Receiver extends Thread {
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

                int ch = reader.read();
                if(ch=='S')
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
                    if (i=='L')
                    {
                        Connector.listOfThreads.remove(this);
                        System.out.println((char) i);
                    }
                    if (i=='F')
                    {
                        System.out.println((char) i);
                    }
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
