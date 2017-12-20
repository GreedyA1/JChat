package simple;

import java.net.*;
import java.io.*;

public class SThread extends Thread {
	private Server Server = null;
	private Socket Socket = null;
	private int ID = -1;
	private DataInputStream IStream = null;
	private DataOutputStream OStream = null;

	public SThread(Server SServer, Socket SSocket) {
		super();
		Server = SServer;
		Socket = SSocket;
		ID = Socket.getPort();
	}

	public void send(String msg) {
		try {
			OStream.writeUTF(msg);
			OStream.flush();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Server.remove(ID);
			stop();
		}
	}

	public void run() {
		System.out.println(ID + " Server running.");
		while (true) {
			try {
				Server.handle(ID, IStream.readUTF());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				Server.remove(ID);
				stop();
			}
		}
	}

	public void open() throws Exception {
		IStream = new DataInputStream(new BufferedInputStream(Socket.getInputStream()));
		OStream = new DataOutputStream(new BufferedOutputStream(Socket.getOutputStream()));
	}

	public void close() throws Exception {
		if (Socket != null)
			Socket.close();
		if (IStream != null)
			IStream.close();
		if (OStream != null)
			OStream.close();
	}

	public int returnID() {
		return ID;
	}
}
