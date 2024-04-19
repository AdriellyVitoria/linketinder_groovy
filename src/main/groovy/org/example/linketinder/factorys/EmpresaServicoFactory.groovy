package org.example.linketinder.factorys

import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.servicos.EmpresaServico

class EmpresaServicoFactory {
    static EmpresaServico criarInstancia() {
        ConectarBanco servicoConectarBanco = ConectarBanco.criarInstancia()
        return new EmpresaServico(servicoConectarBanco)
    }
}
