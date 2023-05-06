package com.trabalhodetc.lucas_marley_walter;

import javax.swing.UIManager;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ChatGpt chat = new ChatGpt();
        chat.request("como voce se sente sendo escravizado pelos alunos de alcides?");
    

        //MainWindow mainWindow = new MainWindow();
        //mainWindow.run();
         
    

    }

}
