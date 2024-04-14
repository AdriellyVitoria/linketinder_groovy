package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBancoServico
import org.example.linketinder.database.servicos.VagaCompetenciaServico
import org.example.linketinder.database.servicos.VagaServico

class VagaServicoFactory {
    static VagaServico criarInstancia() {
        ConectarBancoServico conectarBancoServico = new ConectarBancoServico()
        VagaCompetenciaServico vagaCompetenciaServico = VagaCompetenciaServicoFactory.criarInstancia()
        return new VagaServico(vagaCompetenciaServico, conectarBancoServico)
    }
}
