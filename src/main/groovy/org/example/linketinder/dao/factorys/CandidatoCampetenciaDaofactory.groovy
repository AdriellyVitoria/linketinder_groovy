package org.example.linketinder.dao.factorys

import org.example.linketinder.dao.implementacoes.CandidatoCompetenciaDaoImpl
import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.database.ConectarBanco

class CandidatoCampetenciaDaofactory {
    static CandidatoCompetenciaDao criarInstancia() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        return new CandidatoCompetenciaDaoImpl(conectarBanco)
    }
}
