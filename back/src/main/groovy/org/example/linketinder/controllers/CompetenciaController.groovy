package org.example.linketinder.controllers

import org.example.linketinder.service.factory.ServiceFactory
import org.example.linketinder.service.interfaces.CompetenciaService
import org.example.linketinder.views.CompetenciaViews

class CompetenciaController {
    private static CompetenciaService competenciaService = ServiceFactory.criarInstanciaCompetencia()

    static void listarCompetencias() {
        CompetenciaViews.listarCompetencia(competenciaService.listarTodas())
    }

    static void criarCompetencia(String competencia) {
        competenciaService.inserir(competencia)
    }

    static void exibirInserirNovaCompetencia() {
        CompetenciaViews.inserirNovaCompetencia()
    }
}
