package com.trabalhodetc.comp;

public class Complemento extends Operacao {
    
    public Complemento() {
        automaton = new Automato();
    }

    public Automato makeOperation() {
        Automato automato = getAutomaton(0);

        for (Estado estado : automato.getEstados()) {

            //Pega o estado final e transforma em estado normal

            if(estado.isFinal()){

                estado.unsetFinal();

            }

            //Pega o estado que não é final e transforma em final

            else{

                estado.setFinal();

            }
            
        }

        return automato;

    }
    
}