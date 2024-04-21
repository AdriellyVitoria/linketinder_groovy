package org.example.linketinder.service.implementacoes

import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.service.interfaces.CandidatoService

class CandidatoServiceImpl implements CandidatoService {
    private CandidatoDao candidatoDao
    private CandidatoCompetenciaDao candidatoCompetenciaDao

    CandidatoServiceImpl(
            CandidatoDao candidatoDao,
            CandidatoCompetenciaDao candidatoCompetenciaDao
    ) {
        this.candidatoDao = candidatoDao
        this.candidatoCompetenciaDao = candidatoCompetenciaDao
    }

    @Override
    PessoaFisica entradaCandidato(LoginRequest request) {
        PessoaFisica candidato = candidatoDao.entradaCandidato(request)
        ArrayList<Competencia> competencias = candidatoCompetenciaDao.listarCompetencia(candidato.cpf)
        candidato.setCompetencias(competencias)
        return candidato
    }

    @Override
    boolean inserir(PessoaFisica candidato) {
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
