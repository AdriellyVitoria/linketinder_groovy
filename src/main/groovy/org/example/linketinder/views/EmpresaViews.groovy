package org.example.linketinder.views

import org.example.linketinder.controllers.CandidatoController
import org.example.linketinder.controllers.EmpresaController
import org.example.linketinder.controllers.VagaController
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.utils.InputValidation
import org.example.linketinder.modelos.PessoaJuridica

class EmpresaViews {
    private static InputValidation input = new InputValidation()

    static void menuPrincipalEmpresa() {
        while (true) {
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("-----MENU-----" +
                    "\n1- Menu vaga\n2- Editar perfil\n3- Sair do programa",
                    1, 3)
            if (opcao == 1){
                VagaController.menuVagas()
            } else if (opcao == 2){
                boolean delete = EmpresaController.menuEditarPerfilEmpresa()
                if (delete) {
                    CandidatoController.logout()
                    break
                }
            } else {
                println("Volte Sempre...")
                CandidatoController.logout()
                break
            }
        }
    }

    static boolean menuEditarPerfilEmpresa(PessoaJuridica empresa){
        while (true){
            Integer opcao = input.validaEntradaDeInteiroComOpcoes(
                    "1- Ver perfil\n2- Editar perfil\n3- Excluir Perfil\n4- Voltar para o menu principal",
                    1, 4)
            if (opcao == 1){
                println(empresa)
            } else if (opcao == 2){
                EmpresaController.editarPerfil()
            } else if(opcao == 3){
                boolean deletarPerfilComSucesso = EmpresaController.exibirDeletarPerfil()
                if (deletarPerfilComSucesso) {
                    return true
                }
            } else {
                break
            }
        }
        return false
    }

    static void editarPerfil() {
        PessoaJuridica empresa = EmpresaController.editarInformacoesEmpresa()
        String verificacaoAtualizacao = EmpresaController.atualizar(empresa)
        if (verificacaoAtualizacao) {
            LoginManager.setEmpresa(empresa)
            println("A empresa foi atualizando com sucesso")
        } else {
            println("tente novamente")
        }
    }

    static boolean deletarPerfil(){
        Integer opcao = input.validaEntradaDeInteiroComOpcoes(
                "Certeza que deseja exluir empresa:\n 1- Sim | 2- Não", 1, 2)
        if (opcao == 1){
            EmpresaController.deletarPerfil()
            println("Apagado com sucesso")
            return true
        }
        return false
    }
}
