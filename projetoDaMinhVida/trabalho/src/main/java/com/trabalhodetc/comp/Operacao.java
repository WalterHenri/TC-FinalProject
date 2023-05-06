package com.trabalhodetc.comp;


/**
 * Classe abstrata a ser herdada pelas operações padrões do sistema.
 * 
 * 
 * @author Pedro Dantas
 */
public abstract class Operacao {
    public Automato automaton;

    public void setAutomaton(Automato aut) {
       this.automaton = aut;
    }

    public Automato getAutomaton(int id) {
        return automaton;
    }
    
    public abstract Automato makeOperation();

}