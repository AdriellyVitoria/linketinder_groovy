package org.example.linketindermicrosservico.services.implemetacoes

import org.example.linketindermicrosservico.dao.interfaces.CandidatoDao
import org.example.linketindermicrosservico.modelos.PessoaFisica
import org.example.linketindermicrosservico.services.interfaces.CandidatoService

class CandidatoServiceImpl implements CandidatoService{
    private CandidatoDao candidatoDao

    CandidatoServiceImpl (CandidatoDao candidatoDao) {
        this.candidatoDao = candidatoDao
    }

    @Override
    boolean inserir(PessoaFisica candidato) {
        return candidatoDao.inserir(candidato)
    }
}
