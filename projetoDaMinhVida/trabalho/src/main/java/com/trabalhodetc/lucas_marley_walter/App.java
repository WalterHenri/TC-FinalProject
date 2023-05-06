package com.trabalhodetc.lucas_marley_walter;

import javax.swing.UIManager;

public class App {
    public static void main(String[] args) {
        
       Automato e = Automato.loadFromJff("D:\\Documentos\\UFS\\TC\\teste.jff");
        Automato afd = Conversor.converter(e);
        AutomatoWriter.saveInJff("D:\\Documentos\\UFS\\TC\\teste2.jff", afd);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //ChatGpt chat = new ChatGpt();
        //chat.setPath("a.jff");
        //chat.request("crie um automato que aceita números pares de 0");
        //chat.setPath("a.jff");
        //chat.saveFile();
        
        MainWindow mainWindow = new MainWindow();
        mainWindow.run();
         

    }

}
