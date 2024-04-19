package org.example.linketinder.views

import org.example.linketinder.factorys.CandidatoCompetenciaServicoFactory
import org.example.linketinder.factorys.CompetenciaServicoFactory
import org.example.linketinder.factorys.VagaCompetenciaServicoFactory
import org.example.linketinder.servicos.CandidatoCompetenciaServico
import org.example.linketinder.servicos.CompetenciaServico
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.servicos.VagaCompetenciaServico
import org.example.linketinder.utils.InputValidation
import org.example.linketinder.modelos.Competencia

class CompetenciaViews {
    private Competencia competencia
    private CandidatoCompetenciaServico candidatoCompetencia
    private InputValidation input
    private opcao
    private Scanner scanner
    private CompetenciaServico servicoCompetencia
    private VagaCompetenciaServico vagaCompetencia

    CompetenciaViews() {
        competencia = new Competencia()
        candidatoCompetencia = CandidatoCompetenciaServicoFactory.criarInstancia()
        input = new InputValidation()
        scanner = new Scanner(System.in)
        servicoCompetencia = CompetenciaServicoFactory.criarInstancia()
        vagaCompetencia = VagaCompetenciaServicoFactory.criarInstancia()
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
                LoginManager.candidato.getCpf()
        )
        for (Competencia com : listar) {
            println("Id " + com.getId() + ":" + com.getDescricao())
        }
        println("Digite o id competencia que deseja apagar:")
        Integer idApagar = Integer.parseInt(scanner.nextLine())
        boolean apagarCompetencia = candidatoCompetencia.deletar(idApagar, LoginManager.candidato.getCpf())
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
