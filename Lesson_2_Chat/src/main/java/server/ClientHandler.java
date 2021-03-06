package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nick;

    public String getNick() {
        return nick;
    }

    public void changeNickname(String nickname) {
        this.nick = nickname;
    }

    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        this.nick = "";

        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    doAuth();
                    readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Error occurred during client handler initialization");
        }
    }

    public void doAuth() throws IOException {
        while (true) {
            if (in.available() > 0) {
                String str = in.readUTF();
                if (str.startsWith("/auth")) {
                    String[] parts = str.split("\\s", 3);
                    String nickname = server.getAuthService().getNickByLoginAndPass(parts[1], parts[2]);
                    if (nickname != null) {
                        if (server.isNickFree(nickname)) {
                            sendMessage("/authok " + nickname);
                            nick = nickname;
                            System.out.println(String.format("%s подключился к чату", nick));
                            server.broadcastMessage(nick + " come in chat");
                            server.subscribe(this);
                            return;
                        } else {
                            sendMessage(String.format("Пользователь с ником [%s] уже используется", nickname));
                        }
                    } else {
                        sendMessage("Неверный логин и/или пароль.");
                    }
                }
            }
        }
    }

    public void readMessage() throws IOException {
        while (true) {
            if (in.available() > 0) {
                String msg = in.readUTF();
                System.out.println(nick + ": " + msg);
                if (msg.startsWith("/")) {
                    if (msg.equals("/end")) {
                        break;
                    } else if (msg.startsWith("/w")) {
                        String[] parts = msg.split("\\s", 3);
                        String userNick = parts[1];
                        String message = String.format("%s to %s: %s", nick, userNick, parts[2]);
                        server.sendMsgByNick(userNick, message);
                    } else if (msg.startsWith("/newnick")) {
                        String[] parts = msg.split("\\s", 4);
                        String nickName = parts[3];
                        if (server.getAuthService().changeNickname(parts[1], parts[2], parts[3])) {
                            server.broadcastMessage(String.format("%s сменил никнейм на %s", nick, nickName));
                            sendMessage("/newnickok " + nickName);
                            nick = nickName;
                        } else {
                            sendMessage(String.format("Пользователь с ником [%s] уже существует", nickName));
                        }
                    } else {
                        server.broadcastMessage("Такого пользователя нет в данном чате");
                    }
                } else {
                    server.broadcastMessage(nick + ": " + msg);
                }
            }
        }
    }

    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(nick + " покинул чат");
        System.out.println(nick + " покинул чат.");
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
