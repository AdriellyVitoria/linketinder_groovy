package org.example.linketinder.views

import org.example.linketinder.controllers.CandidatoCompetenciaController
import org.example.linketinder.controllers.CompetenciaController
import org.example.linketinder.controllers.VagaCompetenciaController
import org.example.linketinder.utils.InputValidation
import org.example.linketinder.modelos.Competencia

class CompetenciaViews {
    private static InputValidation input = new InputValidation()
    private static Scanner scanner = new Scanner(System.in)

    static void listarCompetencia(ArrayList<Competencia> competencias) {
        for (Competencia com : competencias) {
            println("Id " + com.getId() + " : " + com.getDescricao())
        }
    }

    static boolean inserirCompetenciaCandidato(){
        while (true){
            CompetenciaController.listarCompetencias()
            Integer id_competencia = input.validaEntradaDeInteiro("Digite 1 id por vez para add a competencia")
            CandidatoCompetenciaController.inserir(id_competencia)
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("1- Adicionar mais Competencia| " +
                    "2- Criar Competencia | 3- Concluido",
                    1, 3)
            if (opcao == 2) {
                CompetenciaController.exibirInserirNovaCompetencia()
            } else if (opcao == 3){
                return true
            }
        }
    }

    static boolean inserirCompetenciaVaga(Integer id_vaga) {
        while (true){
            CompetenciaController.listarCompetencias()
            Integer id_competencia = input.validaEntradaDeInteiro("Digite 1 id por vez para add a competencia")
            VagaCompetenciaController.inserir(id_competencia, id_vaga)
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("1- Adicionar mais Competencia | " +
                    "2- Criar Competencia | 3- Concluido",
                    1, 3)
            if (opcao == 2) {
                CompetenciaController.exibirInserirNovaCompetencia()
            } else if (opcao == 3){
                return true
            }
        }
    }

    static void inserirNovaCompetencia() {
        println("Digite sua nova Compencia:")
        String competenciaNova = scanner.nextLine()
        CompetenciaController.criarCompetencia(competenciaNova)
    }

    static void deletarCompetenciaCandidato(){
        CandidatoCompetenciaController.verCompetencias()
        Integer idApagar = input.validaEntradaDeInteiro("Digite o id competencia que deseja apagar:")
        boolean apagarCompetencia = CandidatoCompetenciaController.deletar(idApagar)
        if (apagarCompetencia){
            println("Apagando com sucesso")
        }
    }

    static void listarCompetenciasVaga(ArrayList<Competencia> competencias) {
        for (Competencia com : competencias) {
            println("Id " + com.getId() + ":" + com.getDescricao())
        }
    }

    static void deletarCompetencicaVaga(Integer id_vaga){
        VagaCompetenciaController.listarCompetenciasVaga(id_vaga)
        Integer idApagar = input.validaEntradaDeInteiro("Digite o id competencia que deseja apagar:")
        boolean apagarCompetencia = VagaCompetenciaController.deletar(idApagar, id_vaga)
        if (apagarCompetencia){
            println("Apagado com sucesso")
        }
    }
}
