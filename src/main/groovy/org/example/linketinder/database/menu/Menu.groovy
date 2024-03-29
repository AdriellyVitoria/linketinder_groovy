package org.example.linketinder.database.menu

import org.example.linketinder.database.utils.InputValidation

class Menu {
    private InputValidation input
    private opcao

    Menu(){
        input = new InputValidation()
    }

    void menuInicial(){
        while (true) {
            opcao = input.validaEntradaDeInteiro("Olá, Bem vindo ao LINKETINDER\n" +
                    "Entrar como:\n1 - Candidato\n2 - Empresa\n3 - Sair", 1, 3)
            if (opcao == 1) {
                println("candidato.entradaCandidato()")
            } else if (opcao == 2) {
                println("empresa.opcaoLoginCadastroEmpresa()")
            } else {
                println("Saindo do programa... Volter sempre")
                break
            }
        }
    }
}
