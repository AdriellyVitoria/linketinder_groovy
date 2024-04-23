package org.example.linketindermicrosservico.controllers

import com.sun.net.httpserver.HttpExchange
import org.example.linketindermicrosservico.modelos.PessoaFisica
import org.example.linketindermicrosservico.services.interfaces.CandidatoService
import com.google.gson.Gson

class CandidatoController {
    private CandidatoService candidatoService

    CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService
    }

    void handleRequest(HttpExchange request) {
        String metodo = request.getRequestMethod()

        if (metodo == "POST") {
            inserir(request)
        } else {
            sendResponse(request, 405, "Método não implementado")
        }
    }

    private void inserir(HttpExchange request) {
        try {
            String requestBody = new String(request.getRequestBody().readAllBytes())
            PessoaFisica pessoaFisica = new Gson().fromJson(requestBody, PessoaFisica)
            candidatoService.inserir(pessoaFisica)
            String response = new Gson().toJson(pessoaFisica)
            sendResponse(request, 201, response)
        } catch (Exception e) {
            sendResponse(request, 400, "Bad Request")
        }

    }

    private void sendResponse(HttpExchange request, Integer statusCode, String response) {
        try {
            request.getResponseHeaders().add("Content-Type", "application/json")
            request.sendResponseHeaders(statusCode, response ? response.getBytes().length : 0)
            OutputStream os = request.getResponseBody()
            if (response != null) {
                os.write(response.getBytes())
            }
            os.close()
        } catch (Exception e) {
            System.err.println("Erro ao enviar resposta: " + e.message)
        }
    }
}
