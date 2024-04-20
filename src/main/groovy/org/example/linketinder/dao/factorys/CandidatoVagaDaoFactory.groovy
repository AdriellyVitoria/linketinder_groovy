package org.example.linketinder.dao.factorys

import org.example.linketinder.dao.implementacoes.CandidatoVagaDaoImpl
import org.example.linketinder.dao.interfaces.CandidatoVagaDao
import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.database.ConectarBanco

class CandidatoVagaDaoFactory {
    static CandidatoVagaDao criarInstancia() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        VagaCompetenciaDao vagaCompetenciaDao = VagaCompetenciaDaoFactory.criarInstancia()

        return new CandidatoVagaDaoImpl(conectarBanco, vagaCompetenciaDao)
    }
}
