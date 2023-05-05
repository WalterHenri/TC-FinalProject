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
        chat.request("eu queria que voce fizesse um afd com alfabeto 0 e 1 que aceita toda palavra terminada em 0");
    

        //MainWindow mainWindow = new MainWindow();
        //mainWindow.run();
         
    

    }

}
