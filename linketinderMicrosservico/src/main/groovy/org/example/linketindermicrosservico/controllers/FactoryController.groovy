package org.example.linketindermicrosservico.controllers

import org.example.linketindermicrosservico.services.factory.ServiceFactory
import org.example.linketindermicrosservico.services.interfaces.CandidatoService
import org.example.linketindermicrosservico.services.interfaces.EmpresaService

class FactoryController {

    static EmpresaController criarInstanciaEmpresa() {
        EmpresaService empresaService = ServiceFactory.criarInstanciaEmpresa()
        return new EmpresaController(empresaService)
    }

    static CandidatoController criarInstanciaCandidato() {
        CandidatoService candidatoService = ServiceFactory.criarInstanciaCandidato()
        return new CandidatoController(candidatoService)
    }
}
