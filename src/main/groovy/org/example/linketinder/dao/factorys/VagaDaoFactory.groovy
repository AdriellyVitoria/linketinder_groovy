package org.example.linketinder.dao.factorys

import org.example.linketinder.dao.implementacoes.VagaDaoImpl
import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.dao.interfaces.VagaDao
import org.example.linketinder.database.ConectarBanco

class VagaDaoFactory {
    static VagaDao criarInstancia() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        VagaCompetenciaDao vagaCompetenciaDao = VagaCompetenciaDaoFactory.criarInstancia()
        return new VagaDaoImpl(conectarBanco, vagaCompetenciaDao)
    }
}
