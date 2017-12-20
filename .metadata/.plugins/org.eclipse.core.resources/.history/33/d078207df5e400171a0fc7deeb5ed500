package simple;

import java.net.*;
import java.io.*;

public class CThread extends Thread {
	private Socket socket = null;
	private Client client = null;
	private DataInputStream IStream = null;
	boolean updated = false;

	public CThread(Client CC, Socket SS) {
		client = CC;
		socket = SS;
		open();
		start();
	}

	public void open() {
		try {
			IStream = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			client.stop();
		}
	}

	public void close() {
		try {
			if (IStream != null)
				IStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void run() {
		while (true) {
			try {
				client.display(IStream.readUTF());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				client.stop();
			}
		}
	}
}