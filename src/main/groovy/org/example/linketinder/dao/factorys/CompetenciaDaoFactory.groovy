package org.example.linketinder.dao.factorys

import org.example.linketinder.dao.implementacoes.CompetenciaDaoImpl
import org.example.linketinder.dao.interfaces.CompetenciaDao
import org.example.linketinder.database.ConectarBanco

class CompetenciaDaoFactory {
    static CompetenciaDao criarInstancia() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        return new CompetenciaDaoImpl(conectarBanco)
    }
}
