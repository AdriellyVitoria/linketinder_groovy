package org.example.linketinder.utils


import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.modelos.PessoaJuridica

class LoginManager {
    static PessoaJuridica empresa
    static PessoaFisica candidato

    static logout() {
        empresa = null
        candidato = null
    }
}
