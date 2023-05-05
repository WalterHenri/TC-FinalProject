/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trabalhodetc.comp.operation;
import com.trabalhodetc.comp.model.Automato;
import com.trabalhodetc.comp.model.Estado;

public class Complemento extends Operacao {
    
    public Complemento() {
        maxAutomaton = 1;
        qtdAutomaton = 0;
        automatons = new Automato[maxAutomaton];
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
