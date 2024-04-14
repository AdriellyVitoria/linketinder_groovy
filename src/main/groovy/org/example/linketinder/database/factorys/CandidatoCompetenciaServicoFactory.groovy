package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBancoServico
import org.example.linketinder.database.servicos.CandidatoCompetenciaServico

class CandidatoCompetenciaServicoFactory {
    static CandidatoCompetenciaServico criarInstancia(){
        ConectarBancoServico conectarBancoServico = new ConectarBancoServico()
        return new CandidatoCompetenciaServico(conectarBancoServico)
    }
}
