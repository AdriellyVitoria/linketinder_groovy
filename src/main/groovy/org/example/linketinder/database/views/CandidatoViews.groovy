package org.example.linketinder.database.views

import org.example.linketinder.database.factorys.CandidatoServicoFactory
import org.example.linketinder.database.factorys.CandidatoVagaServicoFactory
import org.example.linketinder.database.factorys.VagaServicoFactory
import org.example.linketinder.database.modelos.Vaga
import org.example.linketinder.database.servicos.CandidatoServico
import org.example.linketinder.database.servicos.CandidatoVagaServico
import org.example.linketinder.database.utils.LoginManager
import org.example.linketinder.database.servicos.VagaServico
import org.example.linketinder.database.utils.InputValidation

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
                servicoCandidatoVaga.aplicar(vagaAplicar)
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
