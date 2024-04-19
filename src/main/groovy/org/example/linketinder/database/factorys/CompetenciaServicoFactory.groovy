package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBanco
import org.example.linketinder.database.servicos.CompetenciaServico

class CompetenciaServicoFactory {
    static CompetenciaServico criarInstancia() {
        ConectarBanco servicoConectarBanco = ConectarBanco.criarInstancia()
        return new CompetenciaServico(servicoConectarBanco)
    }
}
