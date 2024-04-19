package org.example.linketinder.factorys

import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.servicos.VagaCompetenciaServico
import org.example.linketinder.servicos.VagaServico

class VagaServicoFactory {
    static VagaServico criarInstancia() {
        ConectarBanco conectarBancoServico = ConectarBanco.criarInstancia()
        VagaCompetenciaServico vagaCompetenciaServico = VagaCompetenciaServicoFactory.criarInstancia()
        return new VagaServico(vagaCompetenciaServico, conectarBancoServico)
    }
}
