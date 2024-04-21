package org.example.linketinder.dao.interfaces

import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaFisica

interface CandidatoDao {
    PessoaFisica entradaCandidato(LoginRequest request)
    boolean inserir(PessoaFisica candidato)
    boolean atualizar(PessoaFisica candidato)
    boolean deletar(String cpf_candidato)
}