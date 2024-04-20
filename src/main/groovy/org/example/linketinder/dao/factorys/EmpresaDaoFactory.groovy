package org.example.linketinder.dao.factorys

import org.example.linketinder.dao.implementacoes.EmpresaDaoImpl
import org.example.linketinder.dao.interfaces.EmpresaDao
import org.example.linketinder.database.ConectarBanco

class EmpresaDaoFactory {
    static EmpresaDao criarInstancia() {
        ConectarBanco conectarBanco = ConectarBanco.criarInstancia()
        return new EmpresaDaoImpl(conectarBanco)
    }
}
