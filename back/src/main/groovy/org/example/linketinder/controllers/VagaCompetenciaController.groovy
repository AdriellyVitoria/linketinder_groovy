package org.example.linketinder.controllers

import org.example.linketinder.service.interfaces.VagaCompetenciaService

class VagaCompetenciaController extends Controller {
    private VagaCompetenciaService vagaCompetenciaService

    VagaCompetenciaController(VagaCompetenciaService vagaCompetenciaService) {
        this.vagaCompetenciaService = vagaCompetenciaService
    }
}
