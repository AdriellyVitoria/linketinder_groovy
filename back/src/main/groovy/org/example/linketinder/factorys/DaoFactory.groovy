package org.example.linketinder.factorys

import org.example.linketinder.dao.implementacoes.CandidatoCompetenciaDaoImpl
import org.example.linketinder.dao.implementacoes.CandidatoDaoImpl
import org.example.linketinder.dao.implementacoes.CandidatoVagaDaoImpl
import org.example.linketinder.dao.implementacoes.CompetenciaDaoImpl
import org.example.linketinder.dao.implementacoes.EmpresaDaoImpl
import org.example.linketinder.dao.implementacoes.VagaCompetenciaDaoImpl
import org.example.linketinder.dao.implementacoes.VagaDaoImpl
import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.dao.interfaces.CandidatoVagaDao
import org.example.linketinder.dao.interfaces.CompetenciaDao
import org.example.linketinder.dao.interfaces.EmpresaDao
import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.dao.interfaces.VagaDao
import org.example.linketinder.database.ConectarBanco

class DaoFactory {
    static CandidatoCompetenciaDao criarInstanciaCandidatoCompetencia() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        return new CandidatoCompetenciaDaoImpl(conectarBanco)
    }

    static CandidatoDao criarInstanciaCandidato() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        return new CandidatoDaoImpl(conectarBanco)
    }

    static CandidatoVagaDao criarInstanciaCandidatoVaga() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()

        return new CandidatoVagaDaoImpl(conectarBanco)
    }

    static CompetenciaDao criarInstanciaCompetencia() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        return new CompetenciaDaoImpl(conectarBanco)
    }

    static EmpresaDao criarInstanciaEmpresa() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        return new EmpresaDaoImpl(conectarBanco)
    }

    static VagaCompetenciaDao criarInstanciaVagaCompetencia() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        return new VagaCompetenciaDaoImpl(conectarBanco)
    }

    static VagaDao criarInstanciaVaga() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        VagaCompetenciaDao vagaCompetenciaDao = criarInstanciaVagaCompetencia()
        return new VagaDaoImpl(conectarBanco, vagaCompetenciaDao)
    }
}
