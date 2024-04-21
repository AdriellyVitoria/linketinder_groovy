package org.example.linketinder.controllers

import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaJuridica
import org.example.linketinder.service.factory.ServiceFactory
import org.example.linketinder.service.interfaces.EmpresaService
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.views.EmpresaViews
import org.example.linketinder.views.EntradaEmpresaView

class EmpresaController {
    private static EmpresaService empresaService = ServiceFactory.criarInstanciaEmpresa()

    static boolean menuEditarPerfilEmpresa() {
        return EmpresaViews.menuEditarPerfilEmpresa(LoginManager.getEmpresa())
    }

    static void menuPrincipalEmpresa() {
        EmpresaViews.menuPrincipalEmpresa()
    }

    static void editarPerfil() {
        EmpresaViews.editarPerfil()
    }

    static boolean inserir(PessoaJuridica empresa) {
        return empresaService.inserir(empresa)
    }

    static PessoaJuridica entradaEmpresa(String email, String senha) {
        LoginRequest request = new LoginRequest(email, senha)
        return empresaService.entradaEmpresa(request)
    }

    static PessoaJuridica editarInformacoesEmpresa() {
        String empresaCnpj = LoginManager.empresa.cnpj
        return EntradaEmpresaView.imformacoesEmpresa(empresaCnpj)
    }

    static PessoaJuridica preencherInformacoesEmpresa(String cnpj) {
        return EntradaEmpresaView.imformacoesEmpresa(cnpj)
    }

    static boolean atualizar(PessoaJuridica empresa) {
        empresaService.atualizar(empresa)
    }

    static boolean exibirDeletarPerfil() {
        EmpresaViews.deletarPerfil()
    }

    static void deletarPerfil() {
        String empresaCnpj = LoginManager.empresa.cnpj
        empresaService.deletar(empresaCnpj)
    }
}
