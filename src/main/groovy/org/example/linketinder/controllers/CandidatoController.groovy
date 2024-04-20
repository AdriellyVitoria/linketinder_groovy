package org.example.linketinder.controllers

import org.example.linketinder.service.interfaces.CandidatoService
import org.example.linketinder.service.interfaces.CandidatoVagaService
import org.example.linketinder.service.interfaces.VagaService
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.views.CandidatoViews

class CandidatoController {
    private CandidatoService candidatoService
    private CandidatoVagaService candidatoVagaService
    private VagaService vagaService
    private CandidatoViews candidatoViews

    CandidatoController(
            CandidatoService candidatoService,
            VagaService vagaService,
            CandidatoVagaService candidatoVagaService
    ) {
        this.candidatoService = candidatoService
        this.vagaService = vagaService
        this.candidatoVagaService = candidatoVagaService
        candidatoViews = new CandidatoViews(this)
    }
    
    void exibirListarVagas() {
        candidatoViews.listaVagas(vagaService.listarTodas())
    }

    void exibirListarVagasAplicadas() {
        candidatoViews.listarVagasAplicadas(
                vagaService.listarPorCpf(
                        LoginManager.candidato.getCpf()
                )
        )
    }

    boolean aplicarVaga(Integer vaga) {
        String cpf = LoginManager.candidato.getCpf()
        return candidatoVagaService.aplicar(vaga, cpf)
    }

    void logout() {
        LoginManager.logout()
    }
}
