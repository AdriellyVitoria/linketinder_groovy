package org.example.linketinder.dao.interfaces

import org.example.linketinder.modelos.Vaga

interface CandidatoVagaDao {
    boolean aplicar(Integer id_vaga, String cpf)
    ArrayList<Vaga>  listarPorCpf(String cpf_candidato)
}