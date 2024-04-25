package org.example.linketinder.servidor

import com.sun.net.httpserver.HttpServer
import org.example.linketinder.controllers.CandidatoCompetenciaController
import org.example.linketinder.controllers.CandidatoController
import org.example.linketinder.controllers.CandidatoVagaController
import org.example.linketinder.controllers.CompetenciaController
import org.example.linketinder.controllers.EmpresaController
import org.example.linketinder.controllers.LoginController
import org.example.linketinder.controllers.VagaCompetenciaController
import org.example.linketinder.controllers.VagaController
import org.example.linketinder.factorys.ControllerFactory

class Servidor {

    private CandidatoCompetenciaController candidatoCompetenciaController
    private CandidatoController candidatoController
    private CandidatoVagaController candidatoVagaController
    private CompetenciaController competenciaController
    private EmpresaController empresaController
    private VagaCompetenciaController vagaCompetenciaController
    private VagaController vagaController
    private LoginController loginController

    Servidor() {
        candidatoCompetenciaController = ControllerFactory.criarCandidatoCompetencia()
        candidatoController = ControllerFactory.criarCandidato()
        candidatoVagaController = ControllerFactory.criarCandidatoVaga()
        competenciaController = ControllerFactory.criarCompetencia()
        empresaController = ControllerFactory.criarEmpresa()
        vagaCompetenciaController = ControllerFactory.criarVagaCompetencia()
        vagaController = ControllerFactory.criarVaga()
        loginController = ControllerFactory.criarLogin()
    }

    void iniciarServidor() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0)

            server.createContext("/candidato/competencia", {request -> candidatoCompetenciaController.handleRequest(request)})
            server.createContext("/candidato", {request -> candidatoController.handleRequest(request)})
            server.createContext("/candidato/vaga", {request -> candidatoVagaController.handleRequest(request)})
            server.createContext("/competencia", {request -> competenciaController.handleRequest(request)})
            server.createContext("/empresa", {request -> empresaController.handleRequest(request)})
            server.createContext("/vaga/competencia", {request -> vagaCompetenciaController.handleRequest(request)})
            server.createContext("/vaga", {request -> vagaController.handleRequest(request)})
            server.createContext("/login", {request -> loginController.handleRequest(request)})

            server.start()
            println('servidor rodando na porta 8080')
        } catch (Exception e) {
            System.err.println("Erro ao inicar o servidor: " + e.message)
        }
    }
}
