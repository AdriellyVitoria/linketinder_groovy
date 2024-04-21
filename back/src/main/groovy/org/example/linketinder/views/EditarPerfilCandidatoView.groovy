package org.example.linketinder.views

import org.example.linketinder.controllers.CandidatoCompetenciaController
import org.example.linketinder.controllers.CandidatoController
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.utils.InputValidation

class EditarPerfilCandidatoView {
    private static InputValidation input = new InputValidation()

    static boolean editarPerfil() {
        while (true) {
            Integer opcao = input.validaEntradaDeInteiroComOpcoes(
                    "1- Ver perfil\n2- Editar perfil\n3- Excluir Perfil\n4- Voltar para o menu principal",
                    1, 4)
            if (opcao == 1){
                CandidatoController.verPerfil()
            } else if(opcao == 2) {
                CandidatoController.menuEditarCompetencia()
            } else if (opcao == 3) {
                boolean deletarPerfilComSucesso = CandidatoController.exibirDeletarPerfil()
                if (deletarPerfilComSucesso){
                    return true
                }
            } else {
                break
            }
        }
        return false
    }

    static void verPerfil(PessoaFisica candidato) {
        println(candidato)
        CandidatoCompetenciaController.verCompetencias()
    }

    static void verCompetencias(ArrayList<Competencia> lista) {
        println("Competêcias")
        for (Competencia com : lista) {
            println("Id " + com.getId() + ":" + com.getDescricao())
        }
    }

    static void menuEditarCompentecia() {
        Integer opcao = input.validaEntradaDeInteiroComOpcoes("1- Editar detalhes | 2- Editar Competencia | 3- Voltar",
                1, 3)
        if (opcao == 1) {
            CandidatoController.editarDescricao()
        } else if (opcao == 2) {
            CandidatoController.exibirEditarCompetencia()
        }
    }

    static void editarDescricao(){
        PessoaFisica candidato = CandidatoController.editarInformacoesCandidato()
        boolean vericacaoAtualizacao = CandidatoController.atualizarCandidato(candidato)
        if (vericacaoAtualizacao) {
            LoginManager.setCandidato(candidato)
            println("O candidato foi atualizando com sucesso")
        } else {
            println("tente novamente")
        }
    }

    static void editarCompetencia(){
        while (true){
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("1- Add nova Competencia | 2- Apagar Competencia | 3- Voltar",
                    1, 3)
            if (opcao == 1){
                boolean addCompetencias = CandidatoCompetenciaController.editarCandidatoCompetencia()
                if (addCompetencias) {
                    println("Atualizando com sucesso")
                } else {
                    println("Erro")
                }
            } else if (opcao == 2){
                CandidatoCompetenciaController.exibirDeletarCompetenciaCandidato()
            } else if (opcao == 3) {
                break
            }
        }
    }

    static boolean deletarPerfil(){
        Integer opcao = input.validaEntradaDeInteiroComOpcoes(
                "Certeza que deseja exluir perfil:\n 1- Sim | 2- Não", 1, 2)
        if (opcao == 1){
            CandidatoController.deletarPerfil()
            println("Apagado com sucesso")
            return true
        }
        return false
    }
}
