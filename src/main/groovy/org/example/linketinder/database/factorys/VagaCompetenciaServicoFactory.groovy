package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBanco
import org.example.linketinder.database.servicos.VagaCompetenciaServico

class VagaCompetenciaServicoFactory {
    static VagaCompetenciaServico criarInstancia() {
        ConectarBanco conectarBancoServico = ConectarBanco.criarInstancia()

        return new VagaCompetenciaServico(conectarBancoServico)
    }
}
