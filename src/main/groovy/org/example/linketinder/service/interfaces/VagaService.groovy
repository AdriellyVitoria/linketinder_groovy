package org.example.linketinder.service.interfaces

import org.example.linketinder.modelos.Vaga

interface VagaService {
    Vaga criar(Vaga vaga)
    ArrayList<Vaga> listarTodas()
    ArrayList<Vaga> listar(String cnpj_empresa)
    boolean atualizar(Integer id_vaga, Vaga vaga)
    boolean deletar(Integer id_vaga)
}