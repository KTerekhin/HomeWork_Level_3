package client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ClientPart extends JFrame {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nick;
    private JTextField messageField;
    private JButton button;
    private JTextArea chatArea;
    private JLabel nickField;

    public ClientPart() {
        connectToServer();
    }

    public void connectToServer() {
        try {
            socket = new Socket("localhost", 1111);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Не удалось подключиться к серверу");
            e.printStackTrace();
        }
        prepareChatWindow();
        new Thread(() -> {
            try {
                while (true) {
                    if (in.available() > 0) {
                        String str = in.readUTF();
                        chatArea.append(str);
                        chatArea.append("\n");
                        if (str.startsWith("/authok ")) {
                            nick = str.split("\\s")[1];
                            nickField.setText(nick);
                        }
                        if (str.startsWith("/newnickok ")) {
                            nick = str.split("\\s")[1];
                            nickField.setText(nick);
                        }
                        if (str.startsWith("/end ")) {
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    closeConnection();
                    nickField.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public synchronized void prepareChatWindow() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 20, 400, 400);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        JScrollPane jsp = new JScrollPane(chatArea);
        jPanel.add(jsp, BorderLayout.CENTER);
        chatArea.setBackground(new Color(36, 243, 143));

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());

        messageField = new JTextField();
        jPanel1.add(messageField, BorderLayout.CENTER);

        nickField = new JLabel(nick);
        jPanel1.add(nickField, BorderLayout.WEST);

        button = new JButton("Send");
        jPanel1.add(button, BorderLayout.EAST);
        jPanel.add(jPanel1, BorderLayout.SOUTH);
        button.setBackground(new Color(242, 0, 225));

        messageField.addActionListener(e -> {
            try {
                sendMessage();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        button.addActionListener(e -> {
            try {
                sendMessage();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        add(jPanel);

        setVisible(true);
    }

    private void sendMessage() throws IOException {
        String messageStr = messageField.getText();
        out.writeUTF(messageStr);
        out.flush();
        messageField.setText("");

    }

    private void saveHistory() {
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();        //CURRENT TIME

    }

    public void closeConnection() throws IOException {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
