package org.example.linketinder.database.views

import org.example.linketinder.database.factorys.ServicoCandidatoFactory
import org.example.linketinder.database.modelos.PessoaFisica
import org.example.linketinder.database.servicos.ServicoCandidato
import org.example.linketinder.database.servicos.ServicoLogin
import org.example.linketinder.database.utils.InputValidation

class EntradaCandidatoViews {
    private opcao
    private InputValidation input
    private PessoaFisica candidato
    private ServicoCandidato servicoCandidato
    private CandidatoViews candidatoViews
    private CompetenciaViews competenciaViews
    private Scanner scanner

    EntradaCandidatoViews(){
        input = new InputValidation()
        candidato = new PessoaFisica()
        servicoCandidato = ServicoCandidatoFactory.criarInstancia()
        candidatoViews = new CandidatoViews(this)
        competenciaViews = new CompetenciaViews()
        scanner = new Scanner(System.in)
    }

    void entradaCandidato() {
        while (true){
            opcao = input.validaEntradaDeInteiro("1- Login | 2- Cadastra | 3- Voltar",
                    1 , 3)
            if (opcao == 1) {
                boolean loginComSucesso = loginCandidato()
                if (loginComSucesso) {
                    break
                }
            } else if (opcao == 2) {
                boolean cadastroComSucesso = cadastraCandidato()
                if (cadastroComSucesso) {
                    break
                }
            } else {
                break
            }
        }
    }

     boolean cadastraCandidato() {
        System.out.println("Informe o cpf")
        candidato.cpf = scanner.nextLine()
        boolean inserir = servicoCandidato.inserir(imformacoesCandidato())
        if (inserir) {
            boolean addCompetencias = competenciaViews.inserirCompetenciaCandidato(candidato.cpf)
            if (addCompetencias) {
                ServicoLogin.setCandidato(
                        servicoCandidato.entradaCandidato(candidato.getEmail(), candidato.getSenha())
                )
                println("Candidato " + candidato.getNome() + " foi inserido com sucesso")
                candidatoViews.menuPrincipalCandidato()
                return true
            }
        } else {
            println("Erro ao inserir candidato")
        }
        return false
    }

     boolean loginCandidato() {
        println("Email: ")
        String email_empresa = scanner.nextLine()
        println("Senha: ")
        String senha_empresa = scanner.nextLine()
        PessoaFisica candidato = servicoCandidato.entradaCandidato(email_empresa, senha_empresa)
        if (candidato != null) {
            ServicoLogin.setCandidato(candidato)
            candidatoViews.menuPrincipalCandidato()
        } else {
            println("Email ou senha incorretos")
        }
    }


    PessoaFisica imformacoesCandidato(){
        println("Informe o nome ");
        candidato.nome = scanner.nextLine();

        println("Informe o email ");
        candidato.email = scanner.nextLine()

        println("Informe a senha ");
        candidato.senha = scanner.nextLine()

        println("Informe o telefone ");
        candidato.telefone = scanner.nextLine()

        println("Informe o cep ");
        candidato.cep = scanner.nextLine()

        println("Informe o estado ");
        candidato.estado = scanner.nextLine()

        println("Informe a descricao");
        candidato.descricao = scanner.nextLine();

        println("Informe a idade ");
        candidato.idade = Integer.parseInt(scanner.nextLine())

        return candidato
    }

}
