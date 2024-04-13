package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBancoServico
import org.example.linketinder.database.servicos.CompetenciaServico

class CompetenciaServicoFactory {
    static CompetenciaServico criarInstancia() {
        ConectarBancoServico servicoConectarBanco = new ConectarBancoServico()
        return new CompetenciaServico(servicoConectarBanco)
    }
}
