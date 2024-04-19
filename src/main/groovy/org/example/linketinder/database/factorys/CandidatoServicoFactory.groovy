package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBanco
import org.example.linketinder.database.servicos.CandidatoServico
import org.example.linketinder.database.servicos.CandidatoCompetenciaServico

class CandidatoServicoFactory {
    static CandidatoServico criarInstancia() {
        CandidatoCompetenciaServico servicoCandidatoCompetencia = CandidatoCompetenciaServicoFactory.criarInstancia()
        ConectarBanco servicoConectarBanco = ConectarBanco.criarInstancia()
        return new CandidatoServico(servicoCandidatoCompetencia, servicoConectarBanco)
    }
}
