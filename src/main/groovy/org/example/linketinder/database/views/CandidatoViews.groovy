package org.example.linketinder.database.views

import org.example.linketinder.database.modelos.Vaga
import org.example.linketinder.database.servicos.ServicoCandidato
import org.example.linketinder.database.servicos.ServicoCandidatoVaga
import org.example.linketinder.database.servicos.ServicoLogin
import org.example.linketinder.database.servicos.ServicoVaga
import org.example.linketinder.database.utils.InputValidation

class CandidatoViews {
    private Integer opcao
    private InputValidation input
    private ServicoCandidato servicoCandidato
    private ServicoVaga servicoVaga
    private ServicoCandidatoVaga servicoCandidatoVaga
    private EditarPerfilCandidatoView editarPerfilCandidatoView
    private Scanner scanner

    CandidatoViews(EntradaCandidatoViews entradaCandidatoViews){
        input = new InputValidation()
        servicoCandidato = new ServicoCandidato()
        servicoVaga =  new ServicoVaga()
        servicoCandidatoVaga = new ServicoCandidatoVaga()
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
                    ServicoLogin.logout()
                    break
                }
            } else {
                println("Saindo do programa...")
                ServicoLogin.logout()
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
        ArrayList<Vaga> listaAplicada = servicoCandidatoVaga.listarPorCpf(ServicoLogin.candidato.getCpf())
        for (Vaga verVagas : listaAplicada) {
            println("Id " + verVagas.getId() + " : " + verVagas.getTitulo())
            println("Descricao: " + verVagas.getDescricao())
            println("Local: " + verVagas.getLocal())
            println("Compentecias: " + verVagas.getCompetencias())
            println("---------------------------------------")
        }
    }
}
