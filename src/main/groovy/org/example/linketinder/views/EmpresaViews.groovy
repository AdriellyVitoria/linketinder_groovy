package org.example.linketinder.views

import org.example.linketinder.factorys.EmpresaServicoFactory
import org.example.linketinder.servicos.EmpresaServico
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.utils.InputValidation
import org.example.linketinder.modelos.PessoaJuridica

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
            opcao = input.validaEntradaDeInteiroComOpcoes("-----MENU-----" +
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
            opcao = input.validaEntradaDeInteiroComOpcoes(
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
        opcao = input.validaEntradaDeInteiroComOpcoes(
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
