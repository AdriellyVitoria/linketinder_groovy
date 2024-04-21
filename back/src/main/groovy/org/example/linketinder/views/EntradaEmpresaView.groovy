package org.example.linketinder.views

import org.example.linketinder.controllers.EmpresaController
import org.example.linketinder.modelos.PessoaJuridica
import org.example.linketinder.utils.InputValidation
import org.example.linketinder.utils.LoginManager

class EntradaEmpresaView {
    private static InputValidation input = new InputValidation()
    private static Scanner scanner = new Scanner(System.in)

    static void opcaoLoginCadastroEmpresa() {
        while (true){
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("1- Login | 2- Cadastra | 3- Voltar",
                    1, 3)
            if (opcao == 1) {
                println("Email: ")
                String email_empresa = scanner.nextLine();

                println("Senha: ")
                String senha_empresa = scanner.nextLine();

                PessoaJuridica empresa = EmpresaController.entradaEmpresa(email_empresa, senha_empresa)

                if (empresa != null) {
                    LoginManager.setEmpresa(empresa)
                    EmpresaController.menuPrincipalEmpresa()
                    break
                } else {
                    println("Email ou senha incorretos")
                }
            } else if(opcao == 2) {

                println("Informe o cnpj")
                String cnpj = scanner.nextLine()
                PessoaJuridica empresa = EmpresaController.preencherInformacoesEmpresa(cnpj)
                boolean inserir = EmpresaController.inserir(empresa)
                if (inserir){
                    println("Empresa " + empresa.getNome() + " foi inserido com sucesso")
                    LoginManager.setEmpresa(empresa)
                    EmpresaController.menuPrincipalEmpresa()
                    break
                }
            } else {
                break
            }
        }
    }

    static PessoaJuridica imformacoesEmpresa(String cnpj) {
        PessoaJuridica empresa = new PessoaJuridica()
        empresa.setCnpj(cnpj)

        println("Informe o nome")
        empresa.setNome(scanner.nextLine())

        println("Informe o email")
        empresa.setEmail(scanner.nextLine())

        println("Informe a senha")
        empresa.setSenha(scanner.nextLine())

        println("Informe o telefone")
        empresa.setTelefone(scanner.nextLine())

        println("Informe o cep")
        empresa.setCep(scanner.nextLine())

        println("Informe o estado")
        empresa.setEstado(scanner.nextLine())

        println("Informe o Pais")
        empresa.setPais(scanner.nextLine())

        println("Informe a descricao")
        empresa.setDescricao(scanner.nextLine())

        return empresa
    }
}
