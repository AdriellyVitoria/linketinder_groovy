package org.example.linketinder.service.interfaces

import org.example.linketinder.modelos.Competencia

interface CandidatoCompetenciaServico {
    ArrayList<Competencia> listarCompetencia(String cpf_candidato)
    boolean deletar(Integer id_competencia, String cpf_candidato)
    boolean inserir(Integer id_competencia, String cpf_candidato)
}