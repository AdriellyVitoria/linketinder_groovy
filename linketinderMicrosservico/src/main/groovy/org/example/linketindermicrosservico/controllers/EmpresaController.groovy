package org.example.linketindermicrosservico.controllers

import com.sun.net.httpserver.HttpExchange
import org.example.linketindermicrosservico.modelos.PessoaJuridica
import org.example.linketindermicrosservico.services.interfaces.EmpresaService

class EmpresaController {
    private EmpresaService empresaService

    EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService
    }

    boolean inserir(PessoaJuridica empresa) {
        return empresaService.inserir(empresa)
    }

    void handleRequest(HttpExchange request) {

    }
}
