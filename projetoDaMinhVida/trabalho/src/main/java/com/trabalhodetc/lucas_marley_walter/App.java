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
        chat.setPath("a.jff");
        chat.request("crie um automato que aceita números pares de 0");
        
        System.out.println(chat.buffer.toString());
        //MainWindow mainWindow = new MainWindow();
        //mainWindow.run();
         
    

    }

}
