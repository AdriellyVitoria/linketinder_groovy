package org.example.linketinder.database.servicos

import org.example.linketinder.database.modelos.PessoaFisica
import org.example.linketinder.database.modelos.PessoaJuridica

class ServicoLogin {
    static PessoaJuridica empresa
    static PessoaFisica candidato

    static logout() {
        empresa = null
        candidato = null
    }
}
