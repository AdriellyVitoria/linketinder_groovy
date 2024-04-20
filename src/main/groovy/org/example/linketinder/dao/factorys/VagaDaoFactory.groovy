package org.example.linketinder.dao.factorys

import org.example.linketinder.dao.implementacoes.VagaCompetenciaDaoImpl
import org.example.linketinder.dao.implementacoes.VagaDaoImpl
import org.example.linketinder.dao.interfaces.VagaDao
import org.example.linketinder.database.ConectarBanco

class VagaDaoFactory {
    static VagaDao criarInstancia() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        VagaCompetenciaDaoImpl vagaCompetenciaDaoImpl = VagaCompetenciaDaoFactory.criarInstancia()
        return new VagaDaoImpl(conectarBanco, vagaCompetenciaDaoImpl)
    }
}
