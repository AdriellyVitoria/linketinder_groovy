package org.example.linketinder.views

import org.example.linketinder.controllers.CandidatoController
import org.example.linketinder.modelos.Vaga
import org.example.linketinder.utils.InputValidation

class CandidatoViews {
    private InputValidation input
    private CandidatoController candidatoController

    CandidatoViews(CandidatoController candidatoController){
        input = new InputValidation()
        this.candidatoController = candidatoController
    }

    void menuPrincipalCandidato() {
        while (true) {
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("-----MENU-----" +
                    "\n1- Listar todas as vagas\n2- Vagas aplicada\n3- Editar perfil\n4- Sair do programa",
                    1, 4)
            if (opcao == 1) {
                candidatoController.exibirListarVagas()
            } else if (opcao == 2){
                candidatoController.exibirListarVagasAplicadas()
            } else if(opcao == 3) {
                boolean delete = editarPerfilCandidatoView.editarPerfil()
                if (delete) {
                    candidatoController.logout()
                    break
                }
            } else {
                println("Saindo do programa...")
                candidatoController.logout()
                break
            }
        }
    }

    void listaVagas(ArrayList<Vaga> lista){
        while (true){
            for (Vaga verVagas : lista){
                println("Id " + verVagas.getId() + ":" + verVagas.getTitulo())
                println("Descricao: " + verVagas.getDescricao())
                println("Local: " + verVagas.getLocal())
                println("Compentecias: " + verVagas.getCompetencias())
                println("---------------------------------------")
            }
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("1- Aplicar em vaga| 2- Sair", 1, 2)
            if (opcao == 1){
                Integer vagaAplicar = input.validaEntradaDeInteiro("Digite o id da vaga para aplicar: ")
                boolean candidatoInserirComSucesso = candidatoController.aplicarVaga(vagaAplicar)
                if (candidatoInserirComSucesso){
                    println("Aplicação em vaga com sucesso")
                }
            } else {
                break
            }
        }
    }

    void listarVagasAplicadas(ArrayList<Vaga> listaAplicada) {
        for (Vaga verVagas : listaAplicada) {
            println("Id " + verVagas.getId() + " : " + verVagas.getTitulo())
            println("Descricao: " + verVagas.getDescricao())
            println("Local: " + verVagas.getLocal())
            println("Compentecias: " + verVagas.getCompetencias())
            println("---------------------------------------")
        }
    }
}
