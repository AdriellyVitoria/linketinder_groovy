package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ConectarBancoServico
import org.example.linketinder.database.servicos.EmpresaServico

class EmpresaServicoFactory {
    static EmpresaServico criarInstancia() {
        ConectarBancoServico servicoConectarBanco = new ConectarBancoServico()
        return new EmpresaServico(servicoConectarBanco)
    }
}
