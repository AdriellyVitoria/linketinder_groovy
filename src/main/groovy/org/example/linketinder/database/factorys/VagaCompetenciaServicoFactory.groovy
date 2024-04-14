package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBancoServico
import org.example.linketinder.database.servicos.VagaCompetenciaServico

class VagaCompetenciaServicoFactory {
    static VagaCompetenciaServico criarInstancia() {
        ConectarBancoServico conectarBancoServico = new ConectarBancoServico()

        return new VagaCompetenciaServico(conectarBancoServico)
    }
}
