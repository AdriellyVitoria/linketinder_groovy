package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBancoServico
import org.example.linketinder.database.servicos.CandidatoVagaServico
import org.example.linketinder.database.servicos.VagaCompetenciaServico

class CandidatoVagaServicoFactory {
    static CandidatoVagaServico criarInstancia() {
        ConectarBancoServico conectarBancoServico = new ConectarBancoServico()
        VagaCompetenciaServico vagaCompetenciaServico = VagaCompetenciaServicoFactory.criarInstancia()

        return new CandidatoVagaServico(conectarBancoServico, vagaCompetenciaServico)
    }
}
