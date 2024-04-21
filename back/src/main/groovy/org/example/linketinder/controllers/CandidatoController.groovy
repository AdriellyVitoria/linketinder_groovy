package org.example.linketinder.controllers

import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.service.factory.ServiceFactory
import org.example.linketinder.service.interfaces.CandidatoService
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.views.CandidatoViews
import org.example.linketinder.views.EditarPerfilCandidatoView
import org.example.linketinder.views.EntradaCandidatoViews

class CandidatoController {
    private static CandidatoService candidatoService = ServiceFactory.criarInstanciaCandidato()

    static void logout() {
        LoginManager.logout()
    }

    static boolean inserir(PessoaFisica candidato) {
        return candidatoService.inserir(candidato)
    }

    static boolean editarPerfil() {
        return EditarPerfilCandidatoView.editarPerfil()
    }

    static void verPerfil() {
        PessoaFisica candidato = LoginManager.candidato
        EditarPerfilCandidatoView.verPerfil(candidato)
    }

    static void menuPrincipalCandidato() {
        CandidatoViews.menuPrincipalCandidato()
    }

    static void editarDescricao() {
        EditarPerfilCandidatoView.editarDescricao()
    }

    static PessoaFisica editarInformacoesCandidato() {
        String cpf = LoginManager.candidato.cpf
        return EntradaCandidatoViews.imformacoesCandidato(cpf)
    }

    static PessoaFisica preencherInformacoesCandidato(String cpf) {
        return EntradaCandidatoViews.imformacoesCandidato(cpf)
    }

    static boolean atualizarCandidato(PessoaFisica candidato) {
        return candidatoService.atualizar(candidato)
    }

    static void menuEditarCompetencia() {
        EditarPerfilCandidatoView.menuEditarCompentecia()
    }

    static boolean exibirDeletarPerfil() {
        return EditarPerfilCandidatoView.deletarPerfil()
    }

    static void deletarPerfil() {
        String cpf = LoginManager.candidato.cpf
        candidatoService.deletar(cpf)
    }

    static void exibirEditarCompetencia() {
        EditarPerfilCandidatoView.editarCompetencia()
    }

    static boolean loginCandidato() {
        EntradaCandidatoViews.loginCandidato()
    }

    static boolean cadastraCandidato() {
        EntradaCandidatoViews.cadastraCandidato()
    }

    static PessoaFisica entradaCandidato(String email, String senha) {
        LoginRequest request = new LoginRequest(email, senha)
        return candidatoService.entradaCandidato(request)
    }
}
