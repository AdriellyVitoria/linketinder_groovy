package org.example.linketinder.dao.interfaces

import org.example.linketinder.modelos.PessoaFisica

interface CandidatoDao {
    PessoaFisica entradaCandidato(String email_candidato, String senha_candidato)
    boolean inserir(PessoaFisica candidato)
    boolean atualizar(PessoaFisica candidato)
    boolean deletar(String cpf_candidato)
}