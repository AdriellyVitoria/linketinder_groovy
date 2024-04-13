package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBancoServico
import org.example.linketinder.database.servicos.CandidatoServico
import org.example.linketinder.database.servicos.CandidatoCompetenciaServico

class CandidatoServicoFactory {
    static CandidatoServico criarInstancia() {
        CandidatoCompetenciaServico servicoCandidatoCompetencia = new CandidatoCompetenciaServico()
        ConectarBancoServico servicoConectarBanco = new ConectarBancoServico()
        return new CandidatoServico(servicoCandidatoCompetencia, servicoConectarBanco)
    }
}
