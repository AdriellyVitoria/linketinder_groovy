package org.example.linketindermicrosservico.dao.interfaces

import org.example.linketindermicrosservico.modelos.PessoaFisica

interface CandidatoDao {
    boolean inserir(PessoaFisica candidato)
}