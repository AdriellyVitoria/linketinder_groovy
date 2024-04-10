package org.example.linketinder.database.menu

import org.example.linketinder.database.utils.InputValidation
import org.example.linketinder.database.views.EmpresaViews
import org.example.linketinder.database.views.EntradaCandidatoViews
import org.example.linketinder.database.views.EntradaEmpresaView


class Menu {
    private InputValidation input
    private opcao
    private EntradaEmpresaView entradaEmpresaView
    private EntradaCandidatoViews entradaCandidatoViews

    Menu(){
        input = new InputValidation()
        entradaEmpresaView = new EntradaEmpresaView()
        entradaCandidatoViews = new EntradaCandidatoViews()
    }

    void menuInicial(){
        while (true) {
            opcao = input.validaEntradaDeInteiro("Olá, Bem vindo ao LINKETINDER\n" +
                    "Entrar como:\n1 - Candidato\n2 - Empresa\n3 - Sair", 1, 3)
            if (opcao == 1) {
                entradaCandidatoViews.entradaCandidato()
            } else if (opcao == 2) {
                entradaEmpresaView.opcaoLoginCadastroEmpresa()
            } else {
                println("Saindo do programa... Volter sempre")
                break
            }
        }
    }
}
