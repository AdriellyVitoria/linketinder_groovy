package org.example.linketinder.service.implementacoes

import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.dao.interfaces.EmpresaDao
import org.example.linketinder.dao.interfaces.LoginDao
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.modelos.PessoaJuridica
import org.example.linketinder.service.interfaces.LoginService

class LoginServiceImpl implements LoginService{
    private LoginDao loginDao
    private CandidatoCompetenciaDao candidatoCompetenciaDao

    LoginServiceImpl(LoginDao loginDao, CandidatoCompetenciaDao candidatoCompetenciaDao) {
        this.loginDao = loginDao
        this.candidatoCompetenciaDao = candidatoCompetenciaDao
    }

    @Override
    PessoaFisica entradaCandidato(LoginRequest request) {
        PessoaFisica candidato = loginDao.entradaCandidato(request)
        ArrayList<Competencia> competencias = candidatoCompetenciaDao.listarCompetencia(candidato.cpf)
        candidato.setCompetencias(competencias)
        return candidato
    }

    @Override
    PessoaJuridica entradaEmpresa(LoginRequest loginRequest) {
        return loginDao.entradaEmpresa(loginRequest)
    }
}
