package org.example.linketinder.views


import org.example.linketinder.factorys.EmpresaServicoFactory
import org.example.linketinder.modelos.PessoaJuridica
import org.example.linketinder.servicos.EmpresaServico
import org.example.linketinder.utils.InputValidation
import org.example.linketinder.utils.LoginManager

class EntradaEmpresaView {
    private opcao
    private InputValidation input
    private Scanner scanner
    private EmpresaServico servicoEmpresa
    private PessoaJuridica empresa
    private EmpresaViews empresaViews

    EntradaEmpresaView(){
        input = new InputValidation()
        scanner = new Scanner(System.in)
        servicoEmpresa = EmpresaServicoFactory.criarInstancia()
        empresa = new PessoaJuridica()
        empresaViews = new EmpresaViews(this)
    }

    void opcaoLoginCadastroEmpresa() {
        while (true){
            opcao = input.validaEntradaDeInteiro("1- Login | 2- Cadastra | 3- Voltar",
                    1, 3)
            if (opcao == 1) {
                println("Email: ")
                String email_empresa = scanner.nextLine();

                println("Senha: ")
                String senha_empresa = scanner.nextLine();

                PessoaJuridica empresa = servicoEmpresa.entradaEmpresa(email_empresa, senha_empresa)

                if (empresa != null) {
                    LoginManager.setEmpresa(empresa)
                    empresaViews.menuPrincipalEmpresa()
                    break
                } else {
                    println("Email ou senha incorretos")
                }
            } else if(opcao == 2) {

                println("Informe o cnpj")
                empresa.setCnpj(scanner.nextLine())

                boolean inserir = servicoEmpresa.inserir(imformacoesEmpresa())
                if (inserir){
                    println("Empresa " + empresa.getNome() + " foi inserido com sucesso")
                    LoginManager.setEmpresa(empresa)
                    empresaViews.menuPrincipalEmpresa()
                    break
                }
            } else {
                break
            }
        }
    }

    PessoaJuridica imformacoesEmpresa() {
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
