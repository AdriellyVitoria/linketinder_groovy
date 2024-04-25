package org.example.linketinder.service.interfaces

import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.modelos.PessoaJuridica

interface LoginService {
    PessoaFisica entradaCandidato(LoginRequest request)
    PessoaJuridica entradaEmpresa(LoginRequest loginRequest)
}
