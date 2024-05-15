package AnyDesk;

import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Client implements Serializable {
 private static final long serialVersionUID = 1L; // Define serialVersionUID
    
    private transient EchoInterface server;
 public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099); // Adjust the hostname and port if needed
            server = (EchoInterface) registry.lookup("RemoteDesktopServer");
            System.out.println("Connected to server...");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void requestScreenshot() {
        try {
             server.getScreenshot();
        } catch (RemoteException e) {
            System.err.println("Error requesting screenshot: " + e.getMessage());
            e.printStackTrace();
            
        }
    }

    public void sendMousePosition(int x, int y) {
        try {
            // Send mouse position to the server
            server.sendMousePosition(x, y);
        } catch (RemoteException e) {
            System.err.println("Error sending mouse position: " + e.getMessage());
            e.printStackTrace();
        }
    }

 
 
   public void receiveScreenshot(BufferedImage screenshot) throws RemoteException {
        try {
            // Display the received screenshot in a JFrame
            JFrame frame = new JFrame("Received Screenshot");
            JLabel label = new JLabel(new ImageIcon(screenshot));
            frame.add(label);
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register() throws RemoteException {
     try {
      server.registerClient(this);
      System.out.println("haaaanaaahhh");
  } catch (Exception e) {
      System.err.println("Exception while registering client: " + e.getMessage());
      e.printStackTrace();
  }
  
    }
 
}
