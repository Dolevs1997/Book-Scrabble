package book_scrable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MyServer {
  private ClientHandler ch;
  private int port;
  private volatile boolean stop;

  public MyServer(int port, ClientHandler clientHandler) {
    this.port = port;
    this.ch = clientHandler;
    stop = false;
  }

  public void start() {
    new Thread(() -> {
      try {
        runServer();
      } catch (Exception e) {
        // System.out.println("Unable to connect server");
        e.printStackTrace();
      }
    }).start();
  }

  private void runServer() throws Exception {

    ServerSocket server = new ServerSocket(port);
    server.setSoTimeout(1000);
    while (!stop) {

      try {

        Socket aClient = server.accept(); // allocate seperate socket
        // System.out.println("Client connected to the server");
        try {
          ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
          aClient.getInputStream().close();
          aClient.getOutputStream().close();
          aClient.close();
          // System.out.println("Client disconnected from the server");

        } catch (IOException e) {
          // System.out.println("Problem with input / output");
        }
      } catch (SocketException se) {
        // System.out.println("Problem with a socket");
      } finally {
        // System.out.println("Safe Exit..\n");

      }
    }

    server.close();
    // System.out.println("\nServer Socket closed...");
  }

  public void close() {
    stop = true;
  }

}
