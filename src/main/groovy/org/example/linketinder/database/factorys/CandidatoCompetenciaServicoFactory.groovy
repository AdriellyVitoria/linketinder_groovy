package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBanco
import org.example.linketinder.database.servicos.CandidatoCompetenciaServico

class CandidatoCompetenciaServicoFactory {
    static CandidatoCompetenciaServico criarInstancia(){
        ConectarBanco conectarBancoServico = ConectarBanco.criarInstancia()
        return new CandidatoCompetenciaServico(conectarBancoServico)
    }
}
