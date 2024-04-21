package org.example.linketinder.service.implementacoes

import org.example.linketinder.dao.interfaces.CandidatoVagaDao
import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.modelos.Vaga
import org.example.linketinder.service.interfaces.CandidatoVagaService

class CandidatoVagaServiceImpl implements CandidatoVagaService{
    private CandidatoVagaDao candidatoVagaDao
    private VagaCompetenciaDao vagaCompetenciaDao

    CandidatoVagaServiceImpl(CandidatoVagaDao candidatoVagaDao, VagaCompetenciaDao vagaCompetenciaDao) {
        this.candidatoVagaDao = candidatoVagaDao
        this.vagaCompetenciaDao = vagaCompetenciaDao
    }

    @Override
    boolean aplicar(Integer id_vaga, String cpf) {
        return candidatoVagaDao.aplicar(id_vaga, cpf)
    }

    @Override
    ArrayList<Vaga> listarPorCpf(String cpf_candidato) {
        ArrayList<Vaga> vagas = candidatoVagaDao.listarPorCpf(cpf_candidato)
        vagas.forEach {
            ArrayList<Competencia> competencias = vagaCompetenciaDao.listarCompetencia(it.id)
            it.setCompetencias(competencias)
        }
        return vagas
    }
}
