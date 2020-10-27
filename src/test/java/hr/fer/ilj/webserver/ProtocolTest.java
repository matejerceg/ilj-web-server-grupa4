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

		assertThat(protocolResult).startsWith(
				"HTTP/1.1 200 OK/r/n ContentLength: ");
		
		assertThat(protocolResult).contains(
				"put: / ");
	}
	
	@Test
	void pathResponse() throws IOException {

		Protocol p = new Protocol();

		BufferedReader in = new BufferedReader(new StringReader("GET /path1/path2 HTTP/1.1\r\n"));

		String protocolResult = p.processInput(in);

		assertThat(protocolResult).contains(
				"put: /path1/path2 ");
	}
	
	@Test
	void filesResponse() throws IOException {

		Protocol p = new Protocol() {

			@Override
			public String generateHtmlForFiles() {
				return "d";
			}
			
		};

		BufferedReader in = new BufferedReader(new StringReader("GET /path1/path2 HTTP/1.1\r\n"));

		String protocolResult = p.processInput(in);

		assertThat(protocolResult).contains(
				"<h1>Datoteke:</h1>d");
	}
	
	

}
