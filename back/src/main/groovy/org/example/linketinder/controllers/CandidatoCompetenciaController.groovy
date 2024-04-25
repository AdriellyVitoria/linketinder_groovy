package org.example.linketinder.controllers

import org.example.linketinder.service.interfaces.CandidatoCompetenciaServico

class CandidatoCompetenciaController extends Controller {
    private CandidatoCompetenciaServico candidatoCompetenciaServico

    CandidatoCompetenciaController(CandidatoCompetenciaServico candidatoCompetenciaServico) {
        this.candidatoCompetenciaServico = candidatoCompetenciaServico
    }
}
