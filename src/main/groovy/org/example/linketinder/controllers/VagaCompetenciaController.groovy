package org.example.linketinder.controllers

import org.example.linketinder.service.factory.ServiceFactory
import org.example.linketinder.service.interfaces.VagaCompetenciaService
import org.example.linketinder.views.CompetenciaViews

class VagaCompetenciaController {
    private static VagaCompetenciaService vagaCompetenciaService = ServiceFactory.criarInstanciaVagaCompetencia()

    static void inserir(Integer id_competencia, Integer id_vaga) {
        vagaCompetenciaService.inserir(id_competencia, id_vaga)
    }

    static boolean inserirCompetenciaVaga(Integer id_vaga) {
        CompetenciaViews.inserirCompetenciaVaga(id_vaga)
    }

    static void listarCompetenciasVaga(Integer id_vaga) {
        CompetenciaViews.listarCompetenciasVaga(
                vagaCompetenciaService.listarCompetencia(id_vaga)
        )
    }

    static void deletarCompetenciaVaga(Integer id_vaga) {
        CompetenciaViews.deletarCompetencicaVaga(id_vaga)
    }

    static boolean deletar(Integer id_competencia, Integer id_vaga) {
        return vagaCompetenciaService.deletar(id_competencia, id_vaga)
    }
}
