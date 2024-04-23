package org.example.linketindermicrosservico.services.factory

import org.example.linketindermicrosservico.dao.factory.DaoFactory
import org.example.linketindermicrosservico.dao.interfaces.CandidatoDao
import org.example.linketindermicrosservico.dao.interfaces.EmpresaDao
import org.example.linketindermicrosservico.services.implemetacoes.CandidatoServiceImpl
import org.example.linketindermicrosservico.services.implemetacoes.EmpresaServiceImpl
import org.example.linketindermicrosservico.services.interfaces.CandidatoService
import org.example.linketindermicrosservico.services.interfaces.EmpresaService

class ServiceFactory {
    static CandidatoService criarInstanciaCandidato() {
        CandidatoDao candidatoDao = DaoFactory.criarInstanciaCandidato()
        return new CandidatoServiceImpl(candidatoDao)
    }

    static EmpresaService criarInstanciaEmpresa() {
        EmpresaDao empresaDao = DaoFactory.criarInstanciaEmpresa()
        return new EmpresaServiceImpl(empresaDao)
    }
}
