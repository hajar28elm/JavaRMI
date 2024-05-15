package AnyDesk;


public class MainClient2 {
 public static void main(String[] args) {
      Client cl = new Client();
        try {
         cl.register();

        } catch (Exception e) {
            System.err.println("Client2 exception: " + e.toString());
            e.printStackTrace();
        }
    }
 
}
