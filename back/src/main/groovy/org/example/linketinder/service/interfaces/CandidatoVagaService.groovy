package org.example.linketinder.service.interfaces

import org.example.linketinder.modelos.Vaga

interface CandidatoVagaService {
    boolean aplicar(Integer id_vaga, String cpf)
    ArrayList<Vaga>  listarPorCpf(String cpf_candidato)
}