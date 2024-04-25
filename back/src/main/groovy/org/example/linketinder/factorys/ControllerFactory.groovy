package org.example.linketinder.factorys

import org.example.linketinder.controllers.CandidatoCompetenciaController
import org.example.linketinder.controllers.CandidatoController
import org.example.linketinder.controllers.CandidatoVagaController
import org.example.linketinder.controllers.CompetenciaController
import org.example.linketinder.controllers.EmpresaController
import org.example.linketinder.controllers.LoginController
import org.example.linketinder.controllers.VagaCompetenciaController
import org.example.linketinder.controllers.VagaController
import org.example.linketinder.service.interfaces.CandidatoCompetenciaServico
import org.example.linketinder.service.interfaces.CandidatoService
import org.example.linketinder.service.interfaces.CandidatoVagaService
import org.example.linketinder.service.interfaces.CompetenciaService
import org.example.linketinder.service.interfaces.EmpresaService
import org.example.linketinder.service.interfaces.LoginService
import org.example.linketinder.service.interfaces.VagaCompetenciaService
import org.example.linketinder.service.interfaces.VagaService

class ControllerFactory {
    static LoginController criarLogin() {
        LoginService loginService = ServiceFactory.criarLogin()
        return new LoginController(loginService)
    }

    static CandidatoCompetenciaController criarCandidatoCompetencia() {
        CandidatoCompetenciaServico candidatoCompetenciaServico = ServiceFactory.criarInstanciaCandidatoCompetencia()
        return new CandidatoCompetenciaController(candidatoCompetenciaServico)
    }

    static EmpresaController criarEmpresa() {
        EmpresaService empresaService = ServiceFactory.criarInstanciaEmpresa()
        return new EmpresaController(empresaService)
    }

    static CandidatoController criarCandidato() {
        CandidatoService candidatoService = ServiceFactory.criarInstanciaCandidato()
        return new CandidatoController(candidatoService)
    }

    static CandidatoVagaController criarCandidatoVaga() {
        CandidatoVagaService candidatoVagaService = ServiceFactory.criarInstanciaCandidatoVaga()
        return new CandidatoVagaController(candidatoVagaService)
    }

    static CompetenciaController criarCompetencia() {
        CompetenciaService competenciaService = ServiceFactory.criarInstanciaCompetencia()
        return new CompetenciaController(competenciaService)
    }

    static VagaController criarVaga() {
        VagaService vagaService = ServiceFactory.criarInstanciaVaga()
        return new VagaController(vagaService)
    }

    static VagaCompetenciaController criarVagaCompetencia() {
        VagaCompetenciaService vagaCompetenciaService = ServiceFactory.criarInstanciaVagaCompetencia()
        return new VagaCompetenciaController(vagaCompetenciaService)
    }
}
