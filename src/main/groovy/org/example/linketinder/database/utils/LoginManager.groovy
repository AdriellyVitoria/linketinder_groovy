package org.example.linketinder.database.utils

import org.example.linketinder.database.modelos.PessoaFisica
import org.example.linketinder.database.modelos.PessoaJuridica

class LoginManager {
    static PessoaJuridica empresa
    static PessoaFisica candidato

    static logout() {
        empresa = null
        candidato = null
    }
}
