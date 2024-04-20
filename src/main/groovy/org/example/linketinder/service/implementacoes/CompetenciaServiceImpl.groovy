package org.example.linketinder.service.implementacoes

import org.example.linketinder.dao.interfaces.CompetenciaDao
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.service.interfaces.CompetenciaService

class CompetenciaServiceImpl implements CompetenciaService{
    private CompetenciaDao competenciaDao

    CompetenciaServiceImpl(CompetenciaDao competenciaDao) {
        this.competenciaDao = competenciaDao
    }

    @Override
    boolean inserir(String competencia) {
        return competenciaDao.inserir(competencia)
    }

    @Override
    ArrayList<Competencia> listarTodas() {
        return competenciaDao.listarTodas()
    }
}
