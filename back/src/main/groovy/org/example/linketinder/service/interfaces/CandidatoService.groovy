package org.example.linketinder.service.interfaces

import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaFisica

interface CandidatoService {
    PessoaFisica entradaCandidato(LoginRequest request)
    boolean inserir(PessoaFisica candidato)
    boolean atualizar(PessoaFisica candidato)
    boolean deletar(String cpf_candidato)
}