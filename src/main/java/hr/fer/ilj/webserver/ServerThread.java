package hr.fer.ilj.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerThread extends Thread {
  private static final Logger LOG = LoggerFactory.getLogger(ServerThread.class);
  private Socket socket;

  public ServerThread(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

      Protocol protocol = new Protocol();

      String response = protocol.processInput(in);
      out.println(response);
    } catch (IOException ioe) {
      LOG.error("Error in communication.", ioe);
    } finally {
      try {
        socket.close();
      } catch (Exception e) {
      }
    }
  }
}
