package org.example.linketinder.dao.interfaces

import org.example.linketinder.modelos.PessoaJuridica

interface EmpresaDao {
    boolean inserir(PessoaJuridica empresa)
    boolean atualizar(PessoaJuridica empresa)
    boolean deletar(String cnpj_empresa)
}