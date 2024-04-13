package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ServicoConectarBanco
import org.example.linketinder.database.servicos.ServicoCandidato
import org.example.linketinder.database.servicos.ServicoCandidatoCompetencia

class ServicoCandidatoFactory {
    static ServicoCandidato criarInstancia() {
        ServicoCandidatoCompetencia servicoCandidatoCompetencia = new ServicoCandidatoCompetencia()
        ServicoConectarBanco servicoConectarBanco = new ServicoConectarBanco()
        return new ServicoCandidato(servicoCandidatoCompetencia, servicoConectarBanco)
    }
}
