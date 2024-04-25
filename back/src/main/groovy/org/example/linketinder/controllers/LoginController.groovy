package org.example.linketinder.controllers

import com.sun.net.httpserver.HttpExchange
import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.modelos.PessoaJuridica
import org.example.linketinder.service.interfaces.LoginService

class LoginController extends Controller {
    private LoginService loginService

    LoginController(LoginService loginService) {
        this.loginService = loginService
    }

    @Override
    protected void handlePostRequest(HttpExchange request) {
        String url = request.getRequestURI().getPath()
        if (url == '/login/candidato') {
            handleLoginCandidato(request)
        } else if (url == '/login/empresa') {
            handleLoginEmpresa(request)
        } else {
            sendResponse(request, 400, "Rota incorreta")
        }
    }

    private void handleLoginCandidato(HttpExchange request) {
        try {
            String requestBody = new String(request.getRequestBody().readAllBytes())
            LoginRequest loginRequest = gson.fromJson(requestBody, LoginRequest)
            PessoaFisica candidato = loginService.entradaCandidato(loginRequest)
            String response = gson.toJson(candidato)
            sendResponse(request, 200, response)
        } catch (Exception e) {
            sendResponse(request, 401, e.message)
        }
    }

    private void handleLoginEmpresa(HttpExchange request) {
        try {
            String requestBody = new String(request.getRequestBody().readAllBytes())
            LoginRequest loginRequest = gson.fromJson(requestBody, LoginRequest)
            PessoaJuridica empresa = loginService.entradaEmpresa(loginRequest)
            String response = gson.toJson(empresa)
            sendResponse(request, 200, response)
        } catch (Exception e) {
            sendResponse(request, 401, e.message)
        }
    }
}
