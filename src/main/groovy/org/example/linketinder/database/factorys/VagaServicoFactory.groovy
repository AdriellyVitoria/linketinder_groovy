package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBanco
import org.example.linketinder.database.servicos.VagaCompetenciaServico
import org.example.linketinder.database.servicos.VagaServico

class VagaServicoFactory {
    static VagaServico criarInstancia() {
        ConectarBanco conectarBancoServico = ConectarBanco.criarInstancia()
        VagaCompetenciaServico vagaCompetenciaServico = VagaCompetenciaServicoFactory.criarInstancia()
        return new VagaServico(vagaCompetenciaServico, conectarBancoServico)
    }
}
