package AnyDesk;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class MainServer {
 
   public static void main(String[] args) {
        try {
            EchoInterfaceImpl server = new EchoInterfaceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("RemoteDesktopServer", server);
            System.out.println("Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

