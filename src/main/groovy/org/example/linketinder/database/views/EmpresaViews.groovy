package org.example.linketinder.database.views

import org.example.linketinder.database.factorys.EmpresaServicoFactory
import org.example.linketinder.database.modelos.PessoaJuridica
import org.example.linketinder.database.servicos.EmpresaServico
import org.example.linketinder.database.utils.LoginManager
import org.example.linketinder.database.utils.InputValidation

class EmpresaViews {
    private opcao
    private InputValidation input
    private EmpresaServico servicoEmpresa
    private PessoaJuridica empresa
    private VagaViews vaga
    private EntradaEmpresaView entradaEmpresaView


    EmpresaViews(EntradaEmpresaView entradaEmpresaView) {
        input = new InputValidation()
        servicoEmpresa = EmpresaServicoFactory.criarInstancia()
        empresa = new PessoaJuridica()
        vaga = new VagaViews(this)
        this.entradaEmpresaView = entradaEmpresaView
    }

    void menuPrincipalEmpresa() {
        while (true) {
            opcao = input.validaEntradaDeInteiro("-----MENU-----" +
                    "\n1- Menu vaga\n2- Editar perfil\n3- Sair do programa",
                    1, 3)
            if (opcao == 1){
                vaga.menuVagas()
            } else if (opcao == 2){
                boolean delete = menuEditarPerfilEmpresa()
                if (delete) {
                    LoginManager.logout()
                    break
                }
            } else {
                println("Volte Sempre...")
                LoginManager.logout()
                break
            }
        }
    }

    boolean menuEditarPerfilEmpresa(){
        while (true){
            opcao = input.validaEntradaDeInteiro(
                    "1- Ver perfil\n2- Editar perfil\n3- Excluir Perfil\n4- Voltar para o menu principal",
                    1, 4)
            if (opcao == 1){
                PessoaJuridica empresa = LoginManager.getEmpresa()
                println(empresa)
            } else if (opcao == 2){
                EditarPerfil()
            } else if(opcao == 3){
                boolean deletarPerfilComSucesso = deletarPerfil()
                if (deletarPerfilComSucesso) {
                    return true
                }
            } else {
                break
            }
        }
        return false
    }

    void EditarPerfil() {
        empresa.setCnpj(
                LoginManager.getEmpresa().cnpj
        )
        PessoaJuridica empresa = entradaEmpresaView.imformacoesEmpresa()
        String verificacaoAtualizacao = servicoEmpresa.atualizar(empresa)
        if (verificacaoAtualizacao) {
            LoginManager.setEmpresa(empresa)
            println("A empresa foi atualizando com sucesso")
        } else {
            println("tente novamente")
        }
    }

    boolean deletarPerfil(){
        opcao = input.validaEntradaDeInteiro(
                "Certeza que deseja exluir empresa:\n 1- Sim | 2- Não", 1, 2)
        if (opcao == 1){
            String cnpj = LoginManager.getEmpresa().cnpj
            servicoEmpresa.deletar(cnpj)
            println("Apagado com sucesso")
            return true
        }
        return false
    }
}
