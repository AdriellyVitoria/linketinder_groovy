package org.example.linketinder.views

import org.example.linketinder.factorys.VagaServicoFactory
import org.example.linketinder.modelos.Vaga
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.servicos.VagaServico
import org.example.linketinder.utils.InputValidation

class VagaViews {
    private InputValidation inputValidation
    private VagaServico servicoVaga
    private Vaga vaga
    private Scanner scanner
    private Integer opcao
    private EmpresaViews empresa
    private CompetenciaViews competenciaViews

    VagaViews(EmpresaViews empresa) {
        inputValidation = new InputValidation()
        servicoVaga = VagaServicoFactory.criarInstancia()
        vaga = new Vaga()
        scanner = new Scanner(System.in)
        this.empresa = empresa
        competenciaViews = new CompetenciaViews()
    }

    void menuVagas() {
        while (true){
            opcao = inputValidation.validaEntradaDeInteiro("-----MENU-----" +
                    "\n1- Criar Vaga\n2- Listar vagas\n3- Atualizar vaga\n4- Deletar vaga\n5- Voltar",
                    1, 5)
            if (opcao == 1) {
                criarVaga()
            } else if(opcao == 2){
                listar(LoginManager.getEmpresa().cnpj)
            } else if (opcao == 3) {
                atualizar()
            } else if(opcao == 4) {
                deletarVaga()
            } else {
                break
            }
        }
    }

   void criarVaga() {
        Vaga criar = servicoVaga.criar(imformacoesParaVaga())
        if (criar != null) {
            println("Erro ao criar vaga")
        } else {
            boolean addCompetencia = competenciaViews.inserirCompetenciaVaga(pegarId())
            if (addCompetencia) {
                println("Vaga criada com sucesso")
                menuVagas()
            }
        }
    }

    Integer pegarId() {
        return servicoVaga.buscaIdVagaCriada()
    }

    boolean listar(String cnpj_empresa){
        ArrayList listarVaga  = servicoVaga.listar(cnpj_empresa)
        if (!listarVaga.isEmpty()) {
            for (Vaga vaga : listarVaga) {
                println("ID: " + vaga.getId());
                println("Descrição: " + vaga.getDescricao());
                println("Título: " + vaga.getTitulo());
                println("Local: " + vaga.getLocal());
                println("Compentecias: " + vaga.getCompetencias())
                println("----------------------------------");
            }
            return true
        } else {
            println("Nenhuma vaga encontrada para o CNPJ especificado.")
            menuVagas()
        }
    }

    void atualizar(){
        listar(LoginManager.empresa.getCnpj())
        println("Digite o id da vaga que deseja editar: ")
        Integer id_vaga = Integer.parseInt(scanner.nextLine())
        opcao = inputValidation.validaEntradaDeInteiro("1- Atualizar detalhes | 2- Atualizar Competencias" +
                " | 3- Voltar" , 1, 3)
        if (opcao == 1){
            atualizarDescricao(id_vaga)
        } else if (opcao == 2) {
            atualizarCompetencia(id_vaga)
        }
    }

    void atualizarCompetencia(Integer id_vaga) {
        while (true){
            opcao = inputValidation.validaEntradaDeInteiro("1- Add nova Competencia | 2- Apagar Competencia |" +
                    " 3- Voltar", 1, 3)
            if (opcao == 1){
                boolean addCompetencia = competenciaViews.inserirCompetenciaVaga(id_vaga)
                if (addCompetencia){
                    println("Atualizando com sucesso")
                } else {
                    println("Erro")
                }
            } else if (opcao == 2) {
                competenciaViews.deletarCompetenicaVaga(id_vaga)
            } else {
                break
            }
        }
    }

    void atualizarDescricao(Integer id_vaga){
        Vaga vaga = imformacoesParaVaga()
        boolean confirmacaoAtualizar = servicoVaga.atualizar(id_vaga, vaga)
        if (confirmacaoAtualizar){
            println("Vaga atualizada com sucesso")
        } else {
            println("Tente novamente")
        }
    }

    Vaga imformacoesParaVaga() {
        println("Descricao da vaga:")
        vaga.setDescricao(scanner.nextLine())

        println("Titulo da vaga:")
        vaga.setTitulo(scanner.nextLine())

        println("Local da vaga:")
        vaga.setLocal(scanner.nextLine())

        vaga.setCnpj_empresa(
                LoginManager.getEmpresa().cnpj
        )
        return vaga
    }

    void deletarVaga() {
        boolean listarVaga = listar(LoginManager.getEmpresa().cnpj)
        if (listarVaga) {
            println("Digite o id da vaga para excluir: ")
            Integer id_vaga = Integer.parseInt(scanner.nextLine())
            opcao = inputValidation.validaEntradaDeInteiro("Certeza que deseja excluir:\n1- Sim | 2-Não",
                    1, 2)
            if (opcao == 1){
                servicoVaga.deletar(id_vaga)
                println("Vaga apagada com sucesso")
            }
        }
    }
}
