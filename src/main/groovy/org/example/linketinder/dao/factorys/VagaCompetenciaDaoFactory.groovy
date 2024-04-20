package org.example.linketinder.dao.factorys

import org.example.linketinder.dao.implementacoes.VagaCompetenciaDaoImpl
import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.database.ConectarBanco

class VagaCompetenciaDaoFactory {
    static VagaCompetenciaDao criarInstancia() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        return new VagaCompetenciaDaoImpl(conectarBanco)
    }
}
