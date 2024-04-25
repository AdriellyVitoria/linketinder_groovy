package org.example.linketinder.service.implementacoes

import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.service.interfaces.CandidatoService

class CandidatoServiceImpl implements CandidatoService {
    private CandidatoDao candidatoDao

    CandidatoServiceImpl(
            CandidatoDao candidatoDao
    ) {
        this.candidatoDao = candidatoDao
    }

    @Override
    PessoaFisica inserir(PessoaFisica candidato) {
        return candidatoDao.inserir(candidato)
    }

    @Override
    boolean atualizar(PessoaFisica candidato) {
        return candidatoDao.atualizar(candidato)
    }

    @Override
    boolean deletar(String cpf_candidato) {
        return candidatoDao.deletar(cpf_candidato)
    }
}
