package org.example.linketinder.service.interfaces

import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaJuridica

interface EmpresaService {
    PessoaJuridica entradaEmpresa(LoginRequest loginRequest)
    boolean inserir(PessoaJuridica empresa)
    boolean atualizar(PessoaJuridica empresa)
    boolean deletar(String cnpj_empresa)
}