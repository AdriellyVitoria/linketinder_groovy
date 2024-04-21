package org.example.linketinder.views

import org.example.linketinder.controllers.CandidatoCompetenciaController
import org.example.linketinder.controllers.CandidatoController
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.utils.InputValidation

class EntradaCandidatoViews {
    private static InputValidation input = new InputValidation()
    private static Scanner scanner = new Scanner(System.in)

    static void entradaCandidato() {
        while (true){
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("1- Login | 2- Cadastra | 3- Voltar",
                    1 , 3)
            if (opcao == 1) {
                boolean loginComSucesso = CandidatoController.loginCandidato()
                if (loginComSucesso) {
                    break
                }
            } else if (opcao == 2) {
                boolean cadastroComSucesso = CandidatoController.cadastraCandidato()
                if (cadastroComSucesso) {
                    break
                }
            } else {
                break
            }
        }
    }

    static boolean cadastraCandidato() {
        System.out.println("Informe o cpf")
        String cpf = scanner.nextLine()
        PessoaFisica candidato = CandidatoController.preencherInformacoesCandidato(cpf)
        boolean inserir = CandidatoController.inserir(candidato)
        if (inserir) {
            LoginManager.setCandidato(
                    CandidatoController.entradaCandidato(candidato.getEmail(), candidato.getSenha())
            )
            println("Candidato " + candidato.getNome() + " foi inserido com sucesso")
            boolean addCompetencias = CandidatoCompetenciaController.editarCandidatoCompetencia()
            if (addCompetencias) {
                CandidatoController.menuPrincipalCandidato()
                return true
            }
        } else {
            println("Erro ao inserir candidato")
        }
        return false
    }

    static boolean loginCandidato() {
        println("Email: ")
        String email_candidato = scanner.nextLine()
        println("Senha: ")
        String senha_candidato = scanner.nextLine()
        PessoaFisica candidato = CandidatoController.entradaCandidato(email_candidato, senha_candidato)

        if (candidato != null) {
            LoginManager.setCandidato(candidato)
            CandidatoController.menuPrincipalCandidato()
            return true
        } else {
            println("Email ou senha incorretos")
            return false
        }
    }


    static PessoaFisica imformacoesCandidato(String cpf){
        PessoaFisica candidato = new PessoaFisica()
        candidato.cpf = cpf

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
