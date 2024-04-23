package org.example.linketindermicrosservico.services.implemetacoes

import org.example.linketindermicrosservico.dao.interfaces.EmpresaDao
import org.example.linketindermicrosservico.modelos.PessoaJuridica
import org.example.linketindermicrosservico.services.interfaces.EmpresaService

class EmpresaServiceImpl implements EmpresaService{
    private EmpresaDao empresaDao

    EmpresaServiceImpl(EmpresaDao empresaDao) {
        this.empresaDao = empresaDao
    }

    @Override
    boolean inserir(PessoaJuridica empresa) {
        return empresaDao.inserir(empresa)
    }
}
