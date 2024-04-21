package org.example.linketinder.service.implementacoes

import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.service.interfaces.CandidatoCompetenciaServico

class CandidatoCompetenciaServiceImpl implements CandidatoCompetenciaServico{
    private CandidatoCompetenciaDao candidatoCompetenciaDao

    CandidatoCompetenciaServiceImpl(CandidatoCompetenciaDao candidatoCompetenciaDao) {
        this.candidatoCompetenciaDao = candidatoCompetenciaDao
    }

    @Override
    ArrayList<Competencia> listarCompetencia(String cpf_candidato) {
        return candidatoCompetenciaDao.listarCompetencia(cpf_candidato)
    }

    @Override
    boolean deletar(Integer id_competencia, String cpf_candidato) {
        return candidatoCompetenciaDao.deletar(id_competencia, cpf_candidato)
    }

    @Override
    boolean inserir(Integer id_competencia, String cpf_candidato) {
        return candidatoCompetenciaDao.inserir(id_competencia, cpf_candidato)
    }
}
