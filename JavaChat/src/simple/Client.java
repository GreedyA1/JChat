package simple;

import java.net.*;
import java.applet.*;
import javax.swing.JTextField;
import java.io.*;
import java.awt.*;

public class Client extends Applet {
	private Socket socket = null;
	private CThread cThreat = null;
	private SThread clients[] = new SThread[50];
	private DataOutputStream streamOut = null;

	private TextArea display = new TextArea();
	private TextField textField = new TextField();
	private JTextField ServerAddressField = new JTextField();
	private JTextField clientUsernameField = new JTextField();

	private int clientCount = 0;
	private Button connect = new Button("Connect"), edit = new Button("edit");
	private String serverName = "0.0.0.0";
	private int serverPort = 5566;

	public void init() {
		Panel menu = new Panel();
		Panel North = new Panel();
		Panel South = new Panel();

		setLayout(new BorderLayout());
		South.setLayout(new BorderLayout());
		South.add("West", menu);
		South.add("Center", textField);
		North.setLayout(new GridLayout(1, 4));
		North.add(ServerAddressField);
		North.add(clientUsernameField);
		North.add(edit);
		North.add(connect);

		add("North", North);
		add("Center", display);
		add("South", South);

		ServerAddressField.setText(serverName);
		ServerAddressField.setEditable(true);
		clientUsernameField.setText("Client");
		clientUsernameField.setEditable(true);
		textField.setEnabled(false);
		edit.setEnabled(false);
		setSize(500, 400);
	}

	public void connect(String serverName, int serverPort) {
		display("Connecting");
		try {
			socket = new Socket(serverName, serverPort);
			display("Connected: " + socket);
			open();
			connect.setEnabled(false);
			edit.setEnabled(true);
		} catch (Exception e) {
			display(e.getMessage());
		}
	}

	private void registerNickname() {
		try {
			streamOut.writeUTF("###" + clientUsernameField.getText());
			streamOut.flush();
		} catch (Exception e) {
			display(e.getMessage());
			close();
		}
	}
	
	public boolean action(Event e, Object o) {
		if (e.target == edit) {
			textField.setEnabled(false);
			edit.setEnabled(false);
			connect.setEnabled(true);
			ServerAddressField.setEditable(true);
			clientUsernameField.setEditable(true);
			close();
		} else if (e.target == connect) {
			textField.setEnabled(true);
			connect(ServerAddressField.getName(), serverPort);
			ServerAddressField.setEditable(false);
			clientUsernameField.setEditable(false);
			registerNickname();
		} else if (e.target == textField) {
			send();
			textField.requestFocus();
		}
		return true;
	}

	public void close() {
		try {
			if (streamOut != null)
				streamOut.close();
			if (socket != null)
				socket.close();
		} catch (Exception e) {
			display(e.getMessage());
		}
		cThreat.close();
		cThreat.stop();
	}

	public void display(String text) {
		display.appendText(text + "\n");
	}

	private void send() {
		try {
			streamOut.writeUTF(textField.getText());
			streamOut.flush();
			textField.setText("");
		} catch (Exception e) {
			display(e.getMessage());
			close();
		}
	}

	public void open() {
		try {
			streamOut = new DataOutputStream(socket.getOutputStream());
			cThreat = new CThread(this, socket);
		} catch (Exception e) {
			display(e.getMessage());
		}

	}
}