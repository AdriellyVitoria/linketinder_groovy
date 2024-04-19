package org.example.linketinder.factorys

import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.servicos.CandidatoCompetenciaServico

class CandidatoCompetenciaServicoFactory {
    static CandidatoCompetenciaServico criarInstancia(){
        ConectarBanco conectarBancoServico = ConectarBanco.criarInstancia()
        return new CandidatoCompetenciaServico(conectarBancoServico)
    }
}
