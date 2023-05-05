/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trabalhodetc.comp.operation;
import com.trabalhodetc.comp.model.Automato;

/**
 * Classe abstrata a ser herdada pelas operações padrões do sistema.
 * 
 */
public abstract class Operacao {
    public Automato[] automatons;
    public int maxAutomaton;
    public int qtdAutomaton;

    public void setAutomaton(Automato aut, int id) {
        if (id < 0 || id > maxAutomaton-1){
            System.out.println("Índice inválido!");
            return;
        }
        if (qtdAutomaton < maxAutomaton && automatons[id] == null){
            qtdAutomaton++;
        }
        automatons[id] = aut;
    }

    public Automato getAutomaton(int id) {
        if (id < 0 || id > maxAutomaton-1){
            System.out.println("Índice inválido!");
            return null;
        }
        return automatons[id];
    }
    
    public abstract Automato makeOperation();

}
