package org.example.linketinder.controllers

import org.example.linketinder.service.interfaces.CompetenciaService

class CompetenciaController extends Controller {
    private CompetenciaService competenciaService

    CompetenciaController(CompetenciaService competenciaService) {
        this.competenciaService = competenciaService
    }
}
