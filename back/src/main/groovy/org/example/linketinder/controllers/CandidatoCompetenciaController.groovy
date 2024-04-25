package org.example.linketinder.controllers

import com.sun.net.httpserver.HttpExchange
import org.example.linketinder.service.interfaces.CandidatoCompetenciaServico

class CandidatoCompetenciaController extends Controller {
    private CandidatoCompetenciaServico candidatoCompetenciaServico

    CandidatoCompetenciaController(CandidatoCompetenciaServico candidatoCompetenciaServico) {
        this.candidatoCompetenciaServico = candidatoCompetenciaServico
    }

    @Override
    protected void handleGetRequest(HttpExchange request) {
        sendResponse(request, 200, 'aqui')
    }
}
