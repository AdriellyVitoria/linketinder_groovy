package org.example.linketinder.dao.interfaces

import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.modelos.PessoaJuridica

interface LoginDao {
    PessoaFisica entradaCandidato(LoginRequest request)
    PessoaJuridica entradaEmpresa(LoginRequest loginRequest)
}
