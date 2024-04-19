package org.example.linketinder.factorys

import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.servicos.CompetenciaServico

class CompetenciaServicoFactory {
    static CompetenciaServico criarInstancia() {
        ConectarBanco servicoConectarBanco = ConectarBanco.criarInstancia()
        return new CompetenciaServico(servicoConectarBanco)
    }
}
