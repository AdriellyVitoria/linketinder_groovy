package org.example.linketinder.views

import org.example.linketinder.controllers.CandidatoController
import org.example.linketinder.controllers.CandidatoVagaController
import org.example.linketinder.controllers.VagaController
import org.example.linketinder.modelos.Vaga
import org.example.linketinder.utils.InputValidation

class CandidatoViews {
    private static InputValidation input = new InputValidation()

    static void menuPrincipalCandidato() {
        while (true) {
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("-----MENU-----" +
                    "\n1- Listar todas as vagas\n2- Vagas aplicada\n3- Editar perfil\n4- Sair do programa",
                    1, 4)
            if (opcao == 1) {
                VagaController.exibirListarVagas()
            } else if (opcao == 2){
                CandidatoVagaController.exibirListarVagasAplicadas()
            } else if(opcao == 3) {
                boolean delete = CandidatoController.editarPerfil()
                if (delete) {
                    CandidatoController.logout()
                    break
                }
            } else {
                println("Saindo do programa...")
                CandidatoController.logout()
                break
            }
        }
    }

    static void listaVagas(ArrayList<Vaga> lista){
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
                boolean candidatoInserirComSucesso = CandidatoVagaController.aplicarEmVaga(vagaAplicar)
                if (candidatoInserirComSucesso){
                    println("Aplicação em vaga com sucesso")
                }
            } else {
                break
            }
        }
    }

    static void listarVagasAplicadas(ArrayList<Vaga> listaAplicada) {
        for (Vaga verVagas : listaAplicada) {
            println("Id " + verVagas.getId() + " : " + verVagas.getTitulo())
            println("Descricao: " + verVagas.getDescricao())
            println("Local: " + verVagas.getLocal())
            println("Compentecias: " + verVagas.getCompetencias())
            println("---------------------------------------")
        }
    }
}
