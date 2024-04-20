package org.example.linketinder.dao.factorys

import org.example.linketinder.dao.implementacoes.CandidatoDaoImpl
import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.database.ConectarBanco

class CandidatoDaoFactory {
    static CandidatoDao criarInstancia() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        CandidatoCompetenciaDao candidatoCompetenciaDao = CandidatoCampetenciaDaoFactory.criarInstancia()
        return new CandidatoDaoImpl(conectarBanco, candidatoCompetenciaDao)
    }
}
