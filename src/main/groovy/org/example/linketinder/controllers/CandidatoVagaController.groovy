package org.example.linketinder.controllers

import org.example.linketinder.service.factory.ServiceFactory
import org.example.linketinder.service.interfaces.CandidatoVagaService
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.views.CandidatoViews

class CandidatoVagaController {
    private static CandidatoVagaService candidatoVagaService = ServiceFactory.criarInstanciaCandidatoVaga()

    static boolean aplicarEmVaga(Integer idVaga) {
        String cpf = LoginManager.candidato.cpf
        candidatoVagaService.aplicar(idVaga, cpf)
    }

    static void exibirListarVagasAplicadas() {
         CandidatoViews.listarVagasAplicadas(
                candidatoVagaService.listarPorCpf(
                        LoginManager.candidato.cpf
                )
        )
    }
}
