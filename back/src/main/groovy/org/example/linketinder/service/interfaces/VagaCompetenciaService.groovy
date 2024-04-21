package org.example.linketinder.service.interfaces

import org.example.linketinder.modelos.Competencia

interface VagaCompetenciaService {
    ArrayList<Competencia> listarCompetencia(Integer id_vaga)
    boolean deletar(Integer id_competencia, Integer id_vaga)
    boolean inserir(Integer id_competencia, Integer id_vaga)
}