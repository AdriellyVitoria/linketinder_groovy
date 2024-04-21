package org.example.linketinder.service.implementacoes

import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.service.interfaces.VagaCompetenciaService

class VagaCompetenciaServiceImpl implements VagaCompetenciaService{
    private VagaCompetenciaDao vagaCompetenciaDao

    VagaCompetenciaServiceImpl(VagaCompetenciaDao vagaCompetenciaDao) {
        this.vagaCompetenciaDao = vagaCompetenciaDao
    }

    @Override
    ArrayList<Competencia> listarCompetencia(Integer id_vaga) {
        return vagaCompetenciaDao.listarCompetencia(id_vaga)
    }

    @Override
    boolean deletar(Integer id_competencia, Integer id_vaga) {
        return vagaCompetenciaDao.deletar(id_competencia, id_vaga)
    }

    @Override
    boolean inserir(Integer id_competencia, Integer id_vaga) {
        return vagaCompetenciaDao.inserir(id_competencia, id_vaga)
    }
}
