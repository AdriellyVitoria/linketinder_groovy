package org.example.linketindermicrosservico.dao.interfaces

import org.example.linketindermicrosservico.modelos.PessoaJuridica

interface EmpresaDao {
    boolean inserir(PessoaJuridica empresa)
}