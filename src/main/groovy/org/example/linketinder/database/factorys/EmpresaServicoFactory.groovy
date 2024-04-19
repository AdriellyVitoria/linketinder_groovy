package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBanco
import org.example.linketinder.database.servicos.EmpresaServico

class EmpresaServicoFactory {
    static EmpresaServico criarInstancia() {
        ConectarBanco servicoConectarBanco = ConectarBanco.criarInstancia()
        return new EmpresaServico(servicoConectarBanco)
    }
}
