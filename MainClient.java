package AnyDesk;



public class MainClient {
    public static void main(String args[]) {
        Client cl = new Client();
  
        try {
            cl.register();
            Thread.sleep(20000); 
            cl.requestScreenshot(); 

            System.out.println("Screenshot requested from Client 1.");
        } catch (Exception e) {
            System.out.println("Server not found!");
            e.printStackTrace();
        }
    }
}

