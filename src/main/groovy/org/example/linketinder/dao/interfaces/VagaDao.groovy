package org.example.linketinder.dao.interfaces

import org.example.linketinder.modelos.Vaga

interface VagaDao {
    boolean criar(Vaga vaga)
    Integer buscaIdVagaCriada()
    ArrayList<Vaga> listarTodas()
    ArrayList<Vaga> listar(String cnpj_empresa)
    boolean atualizar(Integer id_vaga, Vaga vaga)
    boolean deletar(Integer id_vaga)
}