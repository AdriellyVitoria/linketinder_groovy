package org.example.linketinder.views

import org.example.linketinder.controllers.VagaCompetenciaController
import org.example.linketinder.controllers.VagaController
import org.example.linketinder.modelos.Vaga
import org.example.linketinder.utils.LoginManager
import org.example.linketinder.utils.InputValidation

class VagaViews {
    private static InputValidation input = new InputValidation()
    private static Scanner scanner = new Scanner(System.in)

    static void menuVagas() {
        while (true){
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("-----MENU-----" +
                    "\n1- Criar Vaga\n2- Listar vagas\n3- Atualizar vaga\n4- Deletar vaga\n5- Voltar",
                    1, 5)
            if (opcao == 1) {
                criarVaga()
            } else if(opcao == 2){
                VagaController.listarPorCnpj()
            } else if (opcao == 3) {
                atualizar()
            } else if(opcao == 4) {
                deletarVaga()
            } else {
                break
            }
        }
    }

    static void criarVaga() {
        Vaga vaga = VagaController.informacoesParaVaga()
        Vaga vagaCriada = VagaController.inserir(vaga)
        if (vagaCriada == null) {
            println("Erro ao criar vaga")
        } else {
            boolean addCompetencia = VagaCompetenciaController.inserirCompetenciaVaga(vagaCriada.getId())
            if (addCompetencia) {
                println("Vaga criada com sucesso")
            }
        }
    }

    static boolean listar(ArrayList<Vaga> vagas){
        if (!vagas.isEmpty()) {
            for (Vaga vaga : vagas) {
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
        }
        return false
    }

    static void atualizar(){
        VagaController.listarPorCnpj()
        Integer id_vaga = input.validaEntradaDeInteiro("Digite o id da vaga que deseja editar: ")
        Integer opcao = input.validaEntradaDeInteiroComOpcoes("1- Atualizar detalhes | 2- Atualizar Competencias" +
                " | 3- Voltar" , 1, 3)
        if (opcao == 1){
            VagaController.atualizarDescricao(id_vaga)
        } else if (opcao == 2) {
            atualizarCompetencia(id_vaga)
        }
    }

    static void atualizarCompetencia(Integer id_vaga) {
        while (true){
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("1- Add nova Competencia | 2- Apagar Competencia |" +
                    " 3- Voltar", 1, 3)
            if (opcao == 1){
                boolean addCompetencia = VagaCompetenciaController.inserirCompetenciaVaga(id_vaga)
                if (addCompetencia){
                    println("Atualizando com sucesso")
                } else {
                    println("Erro")
                }
            } else if (opcao == 2) {
                VagaCompetenciaController.deletarCompetenciaVaga(id_vaga)
            } else {
                break
            }
        }
    }

    static void atualizarDescricao(Integer id_vaga){
        Vaga vaga = VagaController.informacoesParaVaga()
        boolean confirmacaoAtualizar = VagaController.atualizar(id_vaga, vaga)
        if (confirmacaoAtualizar){
            println("Vaga atualizada com sucesso")
        } else {
            println("Tente novamente")
        }
    }

    static Vaga imformacoesParaVaga() {
        Vaga vaga = new Vaga()

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

    static void deletarVaga() {
        boolean listarVaga = VagaController.listarPorCnpj()
        if (listarVaga) {
            println("Digite o id da vaga para excluir: ")
            Integer id_vaga = Integer.parseInt(scanner.nextLine())
            Integer opcao = input.validaEntradaDeInteiroComOpcoes("Certeza que deseja excluir:\n1- Sim | 2-Não",
                    1, 2)
            if (opcao == 1){
                VagaController.deletar(id_vaga)
                println("Vaga apagada com sucesso")
            }
        }
    }
}
