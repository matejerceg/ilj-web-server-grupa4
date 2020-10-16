package hr.fer.ilj.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
  private static final Logger LOG = LoggerFactory.getLogger(Server.class);

  public static void main(String[] args) {
    try (ServerSocket ssc = new ServerSocket(8080);) {
      LOG.info("Server started and listening");
      while (true) {
        Socket scClient = ssc.accept();
        ServerThread st = new ServerThread(scClient);
        st.start();
      }
    } catch (IOException e) {
      LOG.error("Error in startign server.", e);
    }
  }
}
