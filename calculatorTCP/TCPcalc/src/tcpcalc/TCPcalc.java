package tcpcalc;
/**
 *
 * @author 141330
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
//import java.net.UnknownHostException;
import java.util.Scanner;
 
public class TCPcalc
{
    public static void main(String[] args) throws IOException
    {
        InetAddress ip = InetAddress.getLocalHost();
        int port = 1234;
        Scanner sc = new Scanner(System.in);
 
        // Step 1: Open the socket connection.
        Socket s = new Socket(ip, port);
 
        // Step 2: Communication-get the input and output stream
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
 
        while (true)
        {
            System.out.print("Enter the basic equation to calculate: ");
            String inp = sc.nextLine();
 
            if (inp.equals("bye"))
                break;
 
            // send the equation to server
            System.out.println("Sending Equation to TCP Server, " + ip + ":" + port);
            dos.writeUTF(inp);
 
            // wait till request is processed and sent back to client
            String ans = dis.readUTF();
            System.out.println("Recieved Answer = " + ans);
        }
    }
}