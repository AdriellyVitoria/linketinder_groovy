package org.example.linketindermicrosservico.controllers

import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import org.example.linketindermicrosservico.modelos.PessoaJuridica
import org.example.linketindermicrosservico.services.interfaces.EmpresaService

class EmpresaController {
    private EmpresaService empresaService

    EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService
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
            PessoaJuridica pessoaJuridica = new Gson().fromJson(requestBody, PessoaJuridica)
            empresaService.inserir(pessoaJuridica)
            String response = new Gson().toJson(pessoaJuridica)
            sendResponse(request, 201, response)
        } catch (Exception e) {
            sendResponse(request, 400, e.message)
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
