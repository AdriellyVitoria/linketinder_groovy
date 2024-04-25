package org.example.linketinder.controllers

import org.example.linketinder.service.interfaces.CandidatoVagaService

class CandidatoVagaController extends Controller {
    private CandidatoVagaService candidatoVagaService

    CandidatoVagaController(CandidatoVagaService candidatoVagaService) {
        this.candidatoVagaService = candidatoVagaService
    }
}
