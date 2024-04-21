package org.example.linketinder.dao.interfaces

import org.example.linketinder.modelos.Competencia

interface VagaCompetenciaDao {
    ArrayList<Competencia> listarCompetencia(Integer id_vaga)
    boolean deletar(Integer id_competencia, Integer id_vaga)
    boolean inserir(Integer id_competencia, Integer id_vaga)
}