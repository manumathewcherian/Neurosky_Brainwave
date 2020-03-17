import java.io.*;
import java.net.*;
import java.nio.charset.*;
//import sun.nio.cs.StandardCharsets;

public class LiveView
{
    

    public static void main(String[]args)
    {  
        try
        {
            final InetAddress TGCS_IP = InetAddress.getByName("127.0.0.1");
            final int TGCS_PORT = 13854;
            Socket socket = new Socket(TGCS_IP, TGCS_PORT);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream((socket.getInputStream()));
            byte [] ipBuffer = new byte[1024];
            byte [] opBuffer;
            String str;
            str = "{ \"enableRawOutput\" : true, \"format\" : \"Json\" }";
            System.out.println(str);
            opBuffer = str.getBytes(StandardCharsets.US_ASCII);
            dos.write(opBuffer,0,opBuffer.length);
            str = "{ \"enableRawOutput\" : true, \"format\" : \"Json\" }";
            System.out.println(str);
            opBuffer = str.getBytes(StandardCharsets.US_ASCII);
            dos.write(opBuffer,0,opBuffer.length);
            dos.flush();
            str = "{\"appName\": \"LiveView\", \"appKey\": \"0123456789abcdef0123456789abcdef01234567\"}";
            System.out.println(str);
            opBuffer = str.getBytes(StandardCharsets.US_ASCII);
            dos.write(opBuffer,0,opBuffer.length);
            dos.flush();
            while (true)
            {
                    int c = dis.read(ipBuffer);
                    String [] packets = new String(ipBuffer, 0, c).split("\r");
                    for (String p : packets)
                    {
                        System.out.println(p);
                    }        
            }
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getLocalizedMessage());
        }

    }
}