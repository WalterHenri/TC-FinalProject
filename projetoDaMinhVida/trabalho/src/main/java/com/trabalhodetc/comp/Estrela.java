package controller.operation;
import java.util.ArrayList;

import model.Automato;
import model.Estado;

public class Estrela extends Operacao {
    
    public Estrela() {
        automaton = new Automato();
    }

    public Automato makeOperation() {
        Automato autEntrada = getAutomaton(0);
        if (autEntrada == null){
            return null;
        }
        Automato autSaida = new Automato(autEntrada);
        ArrayList<Estado> estadosFinais = autSaida.getEstadosFinais();
        if (estadosFinais == null){
        
            return null;
        }
        Estado antigoInicial = autSaida.getEstadoInicial();
        if (antigoInicial == null){
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