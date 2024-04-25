package org.example.linketinder.controllers

import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.service.interfaces.CandidatoService

class CandidatoController extends Controller {
    private CandidatoService candidatoService

    CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService
    }

    @Override
    protected void handlePostRequest(HttpExchange request) {
        try {
            String requestBody = new String(request.getRequestBody().readAllBytes())
            PessoaFisica pessoaFisica = new Gson().fromJson(requestBody, PessoaFisica)
            candidatoService.inserir(pessoaFisica)
            String response = new Gson().toJson(pessoaFisica)
            sendResponse(request, 201, response)
        } catch (Exception e) {
            sendResponse(request, 400, e.message)
        }
    }
}
