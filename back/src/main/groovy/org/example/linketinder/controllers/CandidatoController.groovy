package org.example.linketinder.controllers

import com.sun.net.httpserver.HttpExchange
import org.example.linketinder.exceptions.DadosDuplicadosException
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
            PessoaFisica pessoaFisica = gson.fromJson(requestBody, PessoaFisica)
            candidatoService.inserir(pessoaFisica)
            String response = gson.toJson(pessoaFisica)
            sendResponse(request, 201, response)
        } catch (DadosDuplicadosException e) {
            sendResponse(request, 409, e.message)
        } catch (Exception e) {
            sendResponse(request, 400, e.message)
        }
    }
}
