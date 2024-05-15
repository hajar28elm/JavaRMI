package AnyDesk;

import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface EchoInterface extends Remote {
 void registerClient(Client client) throws RemoteException;
 void getScreenshot() throws RemoteException;
 void sendMousePosition(int x, int y) throws RemoteException;
 void sendScreenshot(BufferedImage screenshot) throws RemoteException;;

}