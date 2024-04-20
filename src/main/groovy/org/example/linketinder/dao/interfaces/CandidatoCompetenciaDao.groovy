package org.example.linketinder.dao.interfaces

import org.example.linketinder.modelos.Competencia

interface CandidatoCompetenciaDao {
    ArrayList<Competencia> listarCompetencia(String cpf_candidato)
    boolean deletar(Integer id_competencia, String cpf_candidato)
    boolean inserir(Integer id_competencia, String cpf_candidato)
}