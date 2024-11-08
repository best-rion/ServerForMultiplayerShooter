public class Writer extends Thread{

    public void run()
    {
        while (true)
        {   
            String ss="";

            for(int id: Connector.positions.keySet())
            {   
                String s = "" + (char)(id+'0');
                s+=Connector.positions.get(id);
                s+=";";
                ss = s+ss;
            }

            System.out.println(ss);
            System.out.println(ss.length());

            for (Receiver r: Connector.listOfThreads)
            {
                r.writer.write(ss);
                r.writer.flush();
            }

             try {
                Thread.sleep(20);
           } catch (InterruptedException e) {
              e.printStackTrace();
           }
        }
    }
}
