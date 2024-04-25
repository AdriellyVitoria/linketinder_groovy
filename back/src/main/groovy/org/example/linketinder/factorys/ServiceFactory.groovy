package org.example.linketinder.factorys

import org.example.linketinder.factorys.DaoFactory
import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.dao.interfaces.CandidatoVagaDao
import org.example.linketinder.dao.interfaces.CompetenciaDao
import org.example.linketinder.dao.interfaces.EmpresaDao
import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.dao.interfaces.VagaDao
import org.example.linketinder.service.implementacoes.CandidatoServiceImpl
import org.example.linketinder.service.implementacoes.CandidatoCompetenciaServiceImpl
import org.example.linketinder.service.implementacoes.CandidatoVagaServiceImpl
import org.example.linketinder.service.implementacoes.CompetenciaServiceImpl
import org.example.linketinder.service.implementacoes.EmpresaServiceImpl
import org.example.linketinder.service.implementacoes.VagaCompetenciaServiceImpl
import org.example.linketinder.service.implementacoes.VagaServiceImpl
import org.example.linketinder.service.interfaces.CandidatoCompetenciaServico
import org.example.linketinder.service.interfaces.CandidatoService
import org.example.linketinder.service.interfaces.CandidatoVagaService
import org.example.linketinder.service.interfaces.CompetenciaService
import org.example.linketinder.service.interfaces.EmpresaService
import org.example.linketinder.service.interfaces.VagaCompetenciaService
import org.example.linketinder.service.interfaces.VagaService

class ServiceFactory {
    static CandidatoService criarInstanciaCandidato() {
        CandidatoDao candidatoDao = DaoFactory.criarInstanciaCandidato()
        CandidatoCompetenciaDao candidatoCompetenciaDao = DaoFactory.criarInstanciaCandidatoCompetencia()
        return new CandidatoServiceImpl(candidatoDao, candidatoCompetenciaDao)
    }

    static EmpresaService criarInstanciaEmpresa() {
        EmpresaDao empresaDao = DaoFactory.criarInstanciaEmpresa()
        return new EmpresaServiceImpl(empresaDao)
    }

    static CandidatoCompetenciaServico criarInstanciaCandidatoCompetencia() {
        CandidatoCompetenciaDao candidatoCompetenciaDao = DaoFactory.criarInstanciaCandidatoCompetencia()
        return new CandidatoCompetenciaServiceImpl(candidatoCompetenciaDao)
    }

    static CompetenciaService criarInstanciaCompetencia() {
        CompetenciaDao competenciaDao = DaoFactory.criarInstanciaCompetencia()
        return new CompetenciaServiceImpl(competenciaDao)
    }

    static VagaCompetenciaService criarInstanciaVagaCompetencia() {
        VagaCompetenciaDao vagaCompetenciaDao = DaoFactory.criarInstanciaVagaCompetencia()
        return new VagaCompetenciaServiceImpl(vagaCompetenciaDao)
    }

    static VagaService criarInstanciaVaga() {
        VagaDao vagaDao = DaoFactory.criarInstanciaVaga()
        VagaCompetenciaDao vagaCompetenciaDao = DaoFactory.criarInstanciaVagaCompetencia()
        return new VagaServiceImpl(vagaDao,vagaCompetenciaDao )
    }

    static CandidatoVagaService criarInstanciaCandidatoVaga() {
        CandidatoVagaDao candidatoVagaDao = DaoFactory.criarInstanciaCandidatoVaga()
        VagaCompetenciaDao vagaCompetenciaDao = DaoFactory.criarInstanciaVagaCompetencia()
        return new CandidatoVagaServiceImpl(candidatoVagaDao, vagaCompetenciaDao)
    }
}
