package org.example.linketinder.factorys

import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.servicos.CandidatoServico
import org.example.linketinder.servicos.CandidatoCompetenciaServico

class CandidatoServicoFactory {
    static CandidatoServico criarInstancia() {
        CandidatoCompetenciaServico servicoCandidatoCompetencia = CandidatoCompetenciaServicoFactory.criarInstancia()
        ConectarBanco servicoConectarBanco = ConectarBanco.criarInstancia()
        return new CandidatoServico(servicoCandidatoCompetencia, servicoConectarBanco)
    }
}
