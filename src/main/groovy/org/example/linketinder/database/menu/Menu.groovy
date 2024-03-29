package org.example.linketinder.database.menu

import org.example.linketinder.database.utils.InputValidation
import org.example.linketinder.database.views.CandidatoViews
import org.example.linketinder.database.views.EmpresaViews

class Menu {
    private InputValidation input
    private opcao
    private CandidatoViews candidato
    private EmpresaViews empresa

    Menu(){
        input = new InputValidation()
        candidato = new CandidatoViews()
        empresa = new EmpresaViews()
    }

    void menuInicial(){
        while (true) {
            opcao = input.validaEntradaDeInteiro("Olá, Bem vindo ao LINKETINDER\n" +
                    "Entrar como:\n1 - Candidato\n2 - Empresa\n3 - Sair", 1, 3)
            if (opcao == 1) {
                candidato.entradaCandidato()
            } else if (opcao == 2) {
                empresa.opcaoLoginCadastroEmpresa()
            } else {
                println("Saindo do programa... Volter sempre")
                break
            }
        }
    }
}
