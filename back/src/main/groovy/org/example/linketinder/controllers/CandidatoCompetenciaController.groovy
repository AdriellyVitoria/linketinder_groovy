package org.example.linketinder.controllers

import org.example.linketinder.service.factory.ServiceFactory
import org.example.linketinder.service.interfaces.CandidatoCompetenciaServico
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.views.CompetenciaViews
import org.example.linketinder.views.EditarPerfilCandidatoView

class CandidatoCompetenciaController {
    private static CandidatoCompetenciaServico candidatoCompetenciaServico = ServiceFactory.criarInstanciaCandidatoCompetencia()

    static void verCompetencias() {
        String cpf = LoginManager.candidato.cpf
        EditarPerfilCandidatoView.verCompetencias(
                candidatoCompetenciaServico.listarCompetencia(cpf)
        )
    }

    static boolean editarCandidatoCompetencia() {
        return CompetenciaViews.inserirCompetenciaCandidato()
    }

    static void inserir(Integer id_competencia) {
        String cpf = LoginManager.candidato.cpf
        candidatoCompetenciaServico.inserir(id_competencia, cpf)
    }

    static boolean deletar(Integer id_competencia) {
        String cpf = LoginManager.candidato.cpf
        return candidatoCompetenciaServico.deletar(id_competencia, cpf)
    }

    static void exibirDeletarCompetenciaCandidato() {
        CompetenciaViews.deletarCompetenciaCandidato()
    }
}
