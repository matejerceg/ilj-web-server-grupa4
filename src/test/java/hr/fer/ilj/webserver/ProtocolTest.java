package hr.fer.ilj.webserver;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

class ProtocolTest {

	@Test
	void rootResponse() throws IOException {

		Protocol p = new Protocol();

		BufferedReader in = new BufferedReader(new StringReader("GET / HTTP/1.1\r\n"));

		String protocolResult = p.processInput(in);

		assertThat(protocolResult).isEqualTo(
				"HTTP/1.1 200 OK/r/n ContentLength: 14<html> <head> </head> <body> <p> / </p> </body> </html>");
	}
	
	@Test
	void pathResponse() throws IOException {

		Protocol p = new Protocol();

		BufferedReader in = new BufferedReader(new StringReader("GET /path1/path2 HTTP/1.1\r\n"));

		String protocolResult = p.processInput(in);

		assertThat(protocolResult).isEqualTo(
				"HTTP/1.1 200 OK/r/n ContentLength: 25<html> <head> </head> <body> <p> /path1/path2 </p> </body> </html>");
	}

}
