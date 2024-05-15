package AnyDesk;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

 
public class EchoInterfaceImpl extends UnicastRemoteObject implements EchoInterface{
    private Client client1;
    private Client client2;
 public EchoInterfaceImpl() throws RemoteException {
      
    }
    @Override
    public synchronized void registerClient(Client client) throws RemoteException {
        if (client1 == null) {
            System.out.println("je suis le sender");
            client1 = client;
        } else if (client2 == null) {
            System.out.println("je suis le reciever");
            client2 = client;
        } else {
            throw new RemoteException("Server is full");
        }
    }

    @Override
    public void getScreenshot() throws RemoteException {
         try {
            Robot robot = new Robot();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Rectangle screenSize = new Rectangle(toolkit.getScreenSize());
            BufferedImage screenshot = robot.createScreenCapture(screenSize);
            sendScreenshot(screenshot);
        } catch (AWTException e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
            e.printStackTrace();
            throw new RemoteException("Error capturing screenshot");
        }
    }
    @Override
    public void sendScreenshot(BufferedImage screenshot) throws RemoteException {
        if (client2 != null) {
            client2.receiveScreenshot(screenshot);
        } else {
            throw new RemoteException("Client 2 is not connected");
        }
    }

    @Override
    public void sendMousePosition(int x, int y) throws RemoteException {
        // Implementation to handle mouse position updates
    }
    
}
