package org.example.linketinder.service.implementacoes

import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.dao.interfaces.VagaDao
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.modelos.Vaga
import org.example.linketinder.service.interfaces.VagaService

class VagaServiceImpl implements VagaService{

    private VagaDao vagaDao
    private VagaCompetenciaDao vagaCompetenciaDao

    VagaServiceImpl(VagaDao vagaDao, VagaCompetenciaDao vagaCompetenciaDao) {
        this.vagaDao = vagaDao
        this.vagaCompetenciaDao = vagaCompetenciaDao
    }

    @Override
    boolean criar(Vaga vaga) {
        return vagaDao.criar(vaga)
    }

    @Override
    Integer buscaIdVagaCriada() {
        return vagaDao.buscaIdVagaCriada()
    }

    @Override
    ArrayList<Vaga> listarTodas() {
        ArrayList<Vaga> vagas = vagaDao.listarTodas()
        vagas.forEach {
            ArrayList<Competencia> competencias = vagaCompetenciaDao.listarCompetencia(it.id)
            it.setCompetencias(competencias)
        }
        return vagas
    }

    @Override
    ArrayList<Vaga> listar(String cnpj_empresa) {
        ArrayList<Vaga> vagas = vagaDao.listarPorCnpj(cnpj_empresa)
        vagas.forEach {
            ArrayList<Competencia> competencias = vagaCompetenciaDao.listarCompetencia(it.id)
            it.setCompetencias(competencias)
        }
        return vagas
    }

    @Override
    boolean atualizar(Integer id_vaga, Vaga vaga) {
        return vagaDao.atualizar(id_vaga, vaga)
    }

    @Override
    boolean deletar(Integer id_vaga) {
        return vagaDao.deletar(id_vaga)
    }
}
