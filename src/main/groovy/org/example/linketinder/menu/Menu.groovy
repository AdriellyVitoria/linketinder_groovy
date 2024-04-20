package org.example.linketinder.menu


import org.example.linketinder.views.EntradaCandidatoViews
import org.example.linketinder.views.EntradaEmpresaView
import org.example.linketinder.utils.InputValidation


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
            opcao = input.validaEntradaDeInteiroComOpcoes("Olá, Bem vindo ao LINKETINDER\n" +
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
