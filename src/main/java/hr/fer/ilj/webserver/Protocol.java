package hr.fer.ilj.webserver;

import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Protocol {
  private static final Logger LOG = LoggerFactory.getLogger(Protocol.class);

  public String processInput(BufferedReader in) throws IOException {
    return "default";
  }

}
