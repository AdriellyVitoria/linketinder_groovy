package org.example.linketinder.factorys

import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.servicos.VagaCompetenciaServico

class VagaCompetenciaServicoFactory {
    static VagaCompetenciaServico criarInstancia() {
        ConectarBanco conectarBancoServico = ConectarBanco.criarInstancia()

        return new VagaCompetenciaServico(conectarBancoServico)
    }
}
