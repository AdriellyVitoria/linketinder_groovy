package org.example.linketinder.dao.implementacoes

import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.PessoaFisica

class CandidatoDaoImpl implements CandidatoDao{
    private ConectarBanco conectarBanco

    @Override
    PessoaFisica entradaCandidato(String email_candidato, String senha_candidato) {
        return null
    }

    @Override
    boolean inserir(PessoaFisica candidato) {
        return false
    }

    @Override
    boolean atualizar(PessoaFisica candidato) {
        return false
    }

    @Override
    boolean deletar(String cpf_candidato) {
        return false
    }
}
