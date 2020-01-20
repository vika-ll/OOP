package sample;
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.sound.sampled.*;


public class Controller {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    Button Send_Msg;
    @FXML
    private TextField Msg;
    @FXML
    private TextField Name;
    @FXML
    private TextArea text;
    @FXML
    private Button back;

    @FXML
    void initialize() {
back.setOnAction(actionEvent -> {
    back.getScene().getWindow().hide();
    FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("/sample/Avtorization.fxml"));
    try {
        loader.load();
    } catch (IOException e) {
        e.printStackTrace();
    }
    Parent parent=loader.getRoot();
    Stage stage=new Stage();
    stage.setScene(new Scene(parent));
    stage.showAndWait();

});
        new ClientSomthing("localhost", 8071);
    }

    class ClientSomthing {
        private Socket socket;
        private BufferedReader in;
        private BufferedWriter out;
        private BufferedReader inputUser;
        private String addr;
        private int port;
        private String nickname;
        Date date ;
        public ClientSomthing(String addr, int port) {
            date = new Date();
            this.addr = addr;
            this.port = port;
            try {
                this.socket = new Socket(addr, port);
            } catch (IOException e) {
                System.err.println("Socket failed");
            }
            try {
                inputUser = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.pressNickname();
                new ReadMsg().start();
                new WriteMsg().start();
            } catch (IOException e) {
            }
        }
        private void pressNickname() {
            System.out.print("Press your nick: ");
            try {
                nickname = inputUser.readLine();
            } catch (IOException ignored) {
            }
        }
        private class ReadMsg extends Thread {
            @Override
            public void  run() {
                String str;
                String name;
                try {
                    while (true) {
                        name=in.readLine();
                        str = in.readLine();
                        if(name.equals(nickname)) {
                            sound();
                            System.out.println(str);
                            text.appendText(str + "\n");
                        }
                    }
                } catch (IOException e) {

                }
            }
        }
        public class WriteMsg extends Thread {
            @Override
            public void run() {
                Date dateNow = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat(" yyyy.MM.dd  hh:mm:ss  ");


                while (true) {
                    Send_Msg.setOnAction(actionEvent -> {
                        try {
                            out.write(Name.getText()+"\n");
                            out.flush();
                            out.write( nickname + ": " + Msg.getText()+" time : "+formatForDateNow.format(dateNow) + "\n");
                            text.appendText( nickname + ": " + Msg.getText()+" time : "+formatForDateNow.format(dateNow) + "\n");
                            out.flush();
                            System.out.println(nickname + ": " + Msg.getText()+" time : "+formatForDateNow.format(dateNow) + "\n");
                       Msg.clear();
                        } catch (IOException e) {
                        }
                    });

                }
            }
        }
        public void sound(){
            try {
                File soundFile = new File("ARTLLRYB.wav"); //Звуковой файл

                AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

                Clip clip = AudioSystem.getClip();

                clip.open(ais);

                clip.setFramePosition(0);
                clip.start();
                Thread.sleep(clip.getMicrosecondLength()/1000);
                clip.stop(); //Останавливаем
                clip.close(); //Закрываем
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
                exc.printStackTrace();
            } catch (InterruptedException exc) {}

        }
    }
}



