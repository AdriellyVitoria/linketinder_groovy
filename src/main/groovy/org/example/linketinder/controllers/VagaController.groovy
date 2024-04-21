package org.example.linketinder.controllers

import org.example.linketinder.modelos.Vaga
import org.example.linketinder.service.factory.ServiceFactory
import org.example.linketinder.service.interfaces.VagaService
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.views.CandidatoViews
import org.example.linketinder.views.VagaViews

class VagaController {
    private static VagaService vagaService = ServiceFactory.criarInstanciaVaga()

    static void exibirListarVagas() {
        CandidatoViews.listaVagas(vagaService.listarTodas())
    }

    static void menuVagas() {
        VagaViews.menuVagas()
    }

    static Vaga informacoesParaVaga() {
        VagaViews.imformacoesParaVaga()
    }

    static Vaga inserir(Vaga vaga) {
        return vagaService.criar(vaga)
    }

    static void deletar(Integer id_vaga) {
        vagaService.deletar(id_vaga)
    }

    static void atualizarDescricao(Integer id_vaga) {
        VagaViews.atualizarDescricao(id_vaga)
    }

    static boolean atualizar(Integer id_vaga, Vaga vaga) {
        vagaService.atualizar(id_vaga, vaga)
    }

    static boolean listarPorCnpj() {
        String cnpj = LoginManager.empresa.cnpj
        VagaViews.listar(
                vagaService.listar(cnpj)
        )
    }
}
