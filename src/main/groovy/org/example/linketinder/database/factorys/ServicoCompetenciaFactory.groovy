package org.example.linketinder.database.factorys

import org.example.linketinder.database.database.ServicoConectarBanco
import org.example.linketinder.database.servicos.ServicoCompetencia

class ServicoCompetenciaFactory {
    static ServicoCompetencia criarInstancia() {
        ServicoConectarBanco servicoConectarBanco = new ServicoConectarBanco()
        return new ServicoCompetencia(servicoConectarBanco)
    }
}
