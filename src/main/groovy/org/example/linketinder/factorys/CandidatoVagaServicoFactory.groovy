package org.example.linketinder.factorys

import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.servicos.CandidatoVagaServico
import org.example.linketinder.servicos.VagaCompetenciaServico

class CandidatoVagaServicoFactory {
    static CandidatoVagaServico criarInstancia() {
        ConectarBanco conectarBancoServico = ConectarBanco.criarInstancia()
        VagaCompetenciaServico vagaCompetenciaServico = VagaCompetenciaServicoFactory.criarInstancia()

        return new CandidatoVagaServico(conectarBancoServico, vagaCompetenciaServico)
    }
}
