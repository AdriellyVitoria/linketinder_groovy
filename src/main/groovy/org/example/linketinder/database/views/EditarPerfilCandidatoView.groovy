package org.example.linketinder.database.views

import org.example.linketinder.database.factorys.ServicoCandidatoFactory
import org.example.linketinder.database.modelos.Competencia
import org.example.linketinder.database.modelos.PessoaFisica
import org.example.linketinder.database.servicos.ServicoCandidato
import org.example.linketinder.database.servicos.ServicoCandidatoCompetencia
import org.example.linketinder.database.servicos.ServicoLogin
import org.example.linketinder.database.utils.InputValidation

class EditarPerfilCandidatoView {
    private Integer opcao
    private InputValidation input
    private ServicoCandidato servicoCandidato
    private PessoaFisica candidato
    private CompetenciaViews competenciaViews
    private ServicoCandidatoCompetencia servicoCandidatoCompetencia
    private EntradaCandidatoViews entradaCandidatoViews
    private Scanner scanner

    EditarPerfilCandidatoView(EntradaCandidatoViews entradaCandidatoViews){
        input = new InputValidation()
        servicoCandidato = ServicoCandidatoFactory.criarInstancia()
        candidato = new PessoaFisica()
        competenciaViews = new CompetenciaViews()
        servicoCandidatoCompetencia = new ServicoCandidatoCompetencia()
        this.entradaCandidatoViews = entradaCandidatoViews
        scanner = new Scanner(System.in)
    }

    boolean editarPerfil() {
        while (true) {
            opcao = input.validaEntradaDeInteiro(
                    "1- Ver perfil\n2- Editar perfil\n3- Excluir Perfil\n4- Voltar para o menu principal",
                    1, 4)
            if (opcao == 1){
                verPerfil()
            } else if(opcao == 2) {
                menuEditarCompenteciaDescricao()
            } else if (opcao == 3) {
                boolean deletarPerfilComSucesso = deletarPerfil()
                if (deletarPerfilComSucesso){
                    return true
                }
            } else {
                break
            }
        }
        return false
    }

    private void verPerfil() {
        PessoaFisica candidato = ServicoLogin.getCandidato()
        println(candidato)
        println("Competêcias")
        ArrayList<Competencia> listar = servicoCandidatoCompetencia.listarCompetencia(
                ServicoLogin.candidato.getCpf()
        )
        for (Competencia com : listar) {
            println("Id " + com.getId() + ":" + com.getDescricao())
        }
    }

    private void menuEditarCompenteciaDescricao() {
        candidato.cpf = ServicoLogin.candidato.cpf
        opcao = input.validaEntradaDeInteiro("1- Editar detalhes | 2- Editar Competencia | 3- Voltar",
                1, 3)
        if (opcao == 1) {
            editarDescricao()
        } else if (opcao == 2) {
            editarCompetencia()
        }
    }

    void editarDescricao(){
        PessoaFisica candidato = entradaCandidatoViews.imformacoesCandidato()
        boolean vericacaoAtualizacao = servicoCandidato.atualizar(candidato)
        if (vericacaoAtualizacao) {
            ServicoLogin.setCandidato(candidato)
            println("O candidato foi atualizando com sucesso")
        } else {
            println("tente novamente")
        }
    }

    void editarCompetencia(){
        while (true){
            opcao = input.validaEntradaDeInteiro("1- Add nova Competencia | 2- Apagar Competencia | 3- Voltar",
                    1, 3)
            if (opcao == 1){
                boolean addCompetencias = competenciaViews.inserirCompetenciaCandidato(candidato.cpf)
                if (addCompetencias) {
                    println("Atualizando com sucesso")
                } else {
                    println("Erro")
                }
            } else if (opcao == 2){
                competenciaViews.deletarCompetenciaCandidato()
            } else if (opcao == 3) {
                break
            }
        }
    }

    boolean deletarPerfil(){
        opcao = input.validaEntradaDeInteiro(
                "Certeza que deseja exluir perfil:\n 1- Sim | 2- Não", 1, 2)
        if (opcao == 1){
            servicoCandidato.deletar(ServicoLogin.getCandidato().cpf)
            println("Apagado com sucesso")
            return true
        }
        return false
    }
}
