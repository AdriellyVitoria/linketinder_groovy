package org.example.linketindermicrosservico.servidor

import com.sun.net.httpserver.HttpServer
import org.example.linketindermicrosservico.controllers.CandidatoController
import org.example.linketindermicrosservico.controllers.EmpresaController

class Servidor {

    private CandidatoController candidatoController
    private EmpresaController empresaController

    Servidor(CandidatoController candidatoController, EmpresaController empresaController) {
        this.candidatoController = candidatoController
        this.empresaController = empresaController
    }

    void iniciarServidor() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0)

            server.createContext("/candidato", { request -> candidatoController.handleRequest(request)})
            server.createContext("/empresa", { request -> empresaController.handleRequest(request)})

            server.start()
            println('servidor rodando na porta 8080')
        } catch (Exception e) {
            System.err.println("Erro ao inicar o servidor: " + e.message)
        }
    }
}
