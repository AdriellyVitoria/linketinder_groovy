package org.example.linketindermicrosservico

import org.example.linketindermicrosservico.controllers.CandidatoController
import org.example.linketindermicrosservico.controllers.EmpresaController
import org.example.linketindermicrosservico.controllers.FactoryController
import org.example.linketindermicrosservico.servidor.Servidor

class Main {
    static void main(String[] args) {
        CandidatoController candidatoController = FactoryController.criarInstanciaCandidato()
        EmpresaController empresaController = FactoryController.criarInstanciaEmpresa()

        Servidor servidor = new Servidor(candidatoController, empresaController)

        servidor.iniciarServidor()
    }
}
