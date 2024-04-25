package org.example.linketinder.service.implementacoes

import org.example.linketinder.dao.interfaces.EmpresaDao
import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaJuridica
import org.example.linketinder.service.interfaces.EmpresaService

class EmpresaServiceImpl implements EmpresaService {
    private EmpresaDao empresaDao

    EmpresaServiceImpl(EmpresaDao empresaDao) {
        this.empresaDao = empresaDao
    }

    @Override
    boolean inserir(PessoaJuridica empresa) {
        return empresaDao.inserir(empresa)
    }

    @Override
    boolean atualizar(PessoaJuridica empresa) {
        return empresaDao.atualizar(empresa)
    }

    @Override
    boolean deletar(String cnpj_empresa) {
        return empresaDao.deletar(cnpj_empresa)
    }
}
