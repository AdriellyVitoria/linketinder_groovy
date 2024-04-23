package org.example.linketindermicrosservico.dao.factory

import org.example.linketindermicrosservico.dao.implementacoes.CandidatoDaoImpl
import org.example.linketindermicrosservico.dao.implementacoes.EmpresaDaoImpl
import org.example.linketindermicrosservico.dao.interfaces.CandidatoDao
import org.example.linketindermicrosservico.dao.interfaces.EmpresaDao
import org.example.linketindermicrosservico.database.ConectarBanco

class DaoFactory {
    static CandidatoDao criarInstanciaCandidato() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        return new CandidatoDaoImpl(conectarBanco)
    }

    static EmpresaDao criarInstanciaEmpresa() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        return new EmpresaDaoImpl(conectarBanco)
    }
}
