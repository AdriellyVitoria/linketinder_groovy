package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBanco
import org.example.linketinder.database.servicos.CandidatoVagaServico
import org.example.linketinder.database.servicos.VagaCompetenciaServico

class CandidatoVagaServicoFactory {
    static CandidatoVagaServico criarInstancia() {
        ConectarBanco conectarBancoServico = ConectarBanco.criarInstancia()
        VagaCompetenciaServico vagaCompetenciaServico = VagaCompetenciaServicoFactory.criarInstancia()

        return new CandidatoVagaServico(conectarBancoServico, vagaCompetenciaServico)
    }
}
