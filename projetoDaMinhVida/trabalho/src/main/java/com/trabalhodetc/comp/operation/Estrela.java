/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trabalhodetc.comp.operation;
import java.util.ArrayList;

import com.trabalhodetc.comp.model.Automato;
import com.trabalhodetc.comp.model.Estado;

public class Estrela extends Operacao {
    
    public Estrela() {
        maxAutomaton = 1;
        qtdAutomaton = 0;
        automatons = new Automato[maxAutomaton];
    }

    public Automato makeOperation() {
        Automato autEntrada = getAutomaton(0);
        if (autEntrada == null){
            System.out.println("O autômato setado é inválido!");
            return null;
        }
        Automato autSaida = new Automato(autEntrada);
        ArrayList<Estado> estadosFinais = autSaida.getEstadosFinais();
        if (estadosFinais == null){
            System.out.println("O autômato não possui estados finais!");
            return null;
        }
        Estado antigoInicial = autSaida.getEstadoInicial();
        if (antigoInicial == null){
            System.out.println("O autômato não possui estado inicial!");
            return null;
        }
        //Criar um estado novo (inicial e final)
        antigoInicial.unsetInicial();
        Estado novoInicial = autSaida.addEstado();
        novoInicial.setInicial();
        novoInicial.setFinal();
        //Colocar epsilon desse novo estado para o antigo inicial
        novoInicial.addTransicao(antigoInicial.getId(), "lambda");
        //Colocar epsilon dos estados finais para o antigo inicial
        for (Estado estado : estadosFinais){
            estado.addTransicao(antigoInicial.getId(), "lambda");
        }

        return autSaida;
    }

}