package org.example.linketinder.views

import org.example.linketinder.factorys.CandidatoServicoFactory
import org.example.linketinder.factorys.CandidatoVagaServicoFactory
import org.example.linketinder.factorys.VagaServicoFactory
import org.example.linketinder.modelos.Vaga
import org.example.linketinder.servicos.CandidatoServico
import org.example.linketinder.servicos.CandidatoVagaServico
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.servicos.VagaServico
import org.example.linketinder.utils.InputValidation

class CandidatoViews {
    private Integer opcao
    private InputValidation input
    private CandidatoServico servicoCandidato
    private VagaServico servicoVaga
    private CandidatoVagaServico servicoCandidatoVaga
    private EditarPerfilCandidatoView editarPerfilCandidatoView
    private Scanner scanner

    CandidatoViews(EntradaCandidatoViews entradaCandidatoViews){
        input = new InputValidation()
        servicoCandidato = CandidatoServicoFactory.criarInstancia()
        servicoVaga =  VagaServicoFactory.criarInstancia()
        servicoCandidatoVaga = CandidatoVagaServicoFactory.criarInstancia()
        editarPerfilCandidatoView = new EditarPerfilCandidatoView(entradaCandidatoViews)
        scanner = new Scanner(System.in)
    }

    void menuPrincipalCandidato() {
        while (true) {
            opcao = input.validaEntradaDeInteiro("-----MENU-----" +
                    "\n1- Listar todas as vagas\n2- Vagas aplicada\n3- Editar perfil\n4- Sair do programa",
                    1, 4)
            if (opcao == 1) {
                listaVagas()
            } else if (opcao == 2){
                listarVagasAplicadas()
            } else if(opcao == 3) {
                boolean delete = editarPerfilCandidatoView.editarPerfil()
                if (delete) {
                    LoginManager.logout()
                    break
                }
            } else {
                println("Saindo do programa...")
                LoginManager.logout()
                break
            }
        }
    }

    void listaVagas(){
        while (true){
            ArrayList listar = servicoVaga.listarTodas()
            for (Vaga verVagas : listar){
                println("Id " + verVagas.getId() + ":" + verVagas.getTitulo())
                println("Descricao: " + verVagas.getDescricao())
                println("Local: " + verVagas.getLocal())
                println("Compentecias: " + verVagas.getCompetencias())
                println("---------------------------------------")
            }
            opcao = input.validaEntradaDeInteiro("1- Aplicar em vaga| 2- Sair", 1, 2)
            if (opcao == 1){
                println("Digite o id da vaga para aplicar: ")
                Integer vagaAplicar = Integer.parseInt( scanner.nextLine())
                String cpf = LoginManager.candidato.getCpf()
                boolean candidatoInserirComSucesso = servicoCandidatoVaga.aplicar(vagaAplicar, cpf)
                if (candidatoInserirComSucesso){
                    println("Aplicação em vaga com sucesso")
                }
            } else {
                break
            }
        }
    }

    void listarVagasAplicadas() {
        ArrayList<Vaga> listaAplicada = servicoCandidatoVaga.listarPorCpf(LoginManager.candidato.getCpf())
        for (Vaga verVagas : listaAplicada) {
            println("Id " + verVagas.getId() + " : " + verVagas.getTitulo())
            println("Descricao: " + verVagas.getDescricao())
            println("Local: " + verVagas.getLocal())
            println("Compentecias: " + verVagas.getCompetencias())
            println("---------------------------------------")
        }
    }
}
