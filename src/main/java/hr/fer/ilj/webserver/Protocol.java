package hr.fer.ilj.webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.regex.Matcher;

public class Protocol {
	private static final Logger LOG = LoggerFactory.getLogger(Protocol.class);

	public String processInput(BufferedReader in) throws IOException {

		String strline = in.readLine();
		
		System.out.println(strline);

		Pattern pattern = Pattern.compile("GET (.*) HTTP/1.1");
		
		Matcher matcher = pattern.matcher(strline);
		String path = null;

		if (matcher.find() && matcher.groupCount() == 1) {
			path = matcher.group(1);
		}
		
		String html = generateHtml(path);
		String s1 = "HTTP/1.1 200 OK/r/n " + "ContentLength: " + html.length() + html;
		System.out.println(s1);
		return s1;
	}

	private String generateHtml(String path) {
		String html = "<html> <head> </head> <body> <p> "
						+"put: "+ path +  " </p>"
						+ "<h1>Datoteke:</h1>"
						+ generateHtmlForFiles()
						+ " </body> </html>";
		return html;
	}

	public String generateHtmlForFiles() {
		return "";
	}

}
