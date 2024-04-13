package org.example.linketinder.database.views

import org.example.linketinder.database.factorys.ServicoCompetenciaFactory
import org.example.linketinder.database.modelos.Competencia
import org.example.linketinder.database.servicos.ServicoCandidatoCompetencia
import org.example.linketinder.database.servicos.ServicoCompetencia
import org.example.linketinder.database.servicos.ServicoLogin
import org.example.linketinder.database.servicos.ServicoVagaCompetencia
import org.example.linketinder.database.utils.InputValidation

class CompetenciaViews {
    private Competencia competencia
    private ServicoCandidatoCompetencia candidatoCompetencia
    private InputValidation input
    private opcao
    private Scanner scanner
    private ServicoCompetencia servicoCompetencia
    private ServicoVagaCompetencia vagaCompetencia

    CompetenciaViews() {
        competencia = new Competencia()
        candidatoCompetencia = new ServicoCandidatoCompetencia()
        input = new InputValidation()
        scanner = new Scanner(System.in)
        servicoCompetencia = ServicoCompetenciaFactory.criarInstancia()
        vagaCompetencia = new  ServicoVagaCompetencia()
    }

    void listarCompetencia() {
        println("Add suas competencias (Digite 1 id por vez):")
        ArrayList listar = servicoCompetencia.listarTodas()
        for (Competencia com : listar) {
            println("Id " + com.getId() + " : " + com.getDescricao())
        }
    }

    boolean inserirCompetenciaCandidato(String cpf){
        while (true){
            listarCompetencia()
            println("Digite 1 id por vez para add a competencia")
            Integer id_competencia = Integer.parseInt(scanner.nextLine())
            candidatoCompetencia.inserir(id_competencia, cpf)
            opcao = input.validaEntradaDeInteiro("1- Adicionar mais Competencia| " +
                    "2- Criar Competencia | 3- Concluido",
                    1, 3)
            if (opcao == 2) {
                inserirNovaCompetencia()
            } else if (opcao == 3){
                return true
            }
        }
    }

    boolean inserirCompetenciaVaga(Integer id_vaga) {
        while (true){
            listarCompetencia()
            println("Digite 1 id por vez para add a competencia")
            Integer id_competencia = Integer.parseInt(scanner.nextLine())
            vagaCompetencia.inserir(id_competencia, id_vaga)
            opcao = input.validaEntradaDeInteiro("1- Adicionar mais Competencia | " +
                    "2- Criar Competencia | 3- Concluido",
                    1, 3)
            if (opcao == 2) {
                inserirNovaCompetencia()
            } else if (opcao == 3){
                return true
            }
        }
    }

    void inserirNovaCompetencia() {
        println("Digite sua nova Compencia:")
        String competenciaNova = scanner.nextLine()
        servicoCompetencia.inserir(competenciaNova)
    }

    void deletarCompetenciaCandidato(){
        ArrayList listar = candidatoCompetencia.listarCompetencia(
                ServicoLogin.candidato.getCpf()
        )
        for (Competencia com : listar) {
            println("Id " + com.getId() + ":" + com.getDescricao())
        }
        println("Digite o id competencia que deseja apagar:")
        Integer idApagar = Integer.parseInt(scanner.nextLine())
        boolean apagarCompetencia = candidatoCompetencia.deletar(idApagar, ServicoLogin.candidato.getCpf())
        if (apagarCompetencia){
            println("Apagando com sucesso")
        }
    }

    void deletarCompetenicaVaga(Integer id_vaga){
        ArrayList<Competencia> listar = vagaCompetencia.listarCompetencia(id_vaga)
        for (Competencia com : listar) {
            println("Id " + com.getId() + ":" + com.getDescricao())
        }
        println("Digite o id competencia que deseja apagar:")
        Integer idApagar = Integer.parseInt(scanner.nextLine())
        boolean apagarCompetencia = vagaCompetencia.deletar(idApagar, id_vaga)
        if (apagarCompetencia){
            println("Apagando com sucesso")
        }
    }
}
