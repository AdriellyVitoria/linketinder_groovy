package org.example.linketinder.utils

class InputValidation {
    private Scanner scanner

    InputValidation() {
        scanner = new Scanner(System.in)
    }

    Integer validaEntradaDeInteiroComOpcoes(String pergunta, Integer opcaoInicial, Integer opcaoFinal) {
        while (true) {
            System.out.println(pergunta);
            try {
                Integer opcao = Integer.parseInt(scanner.next());
                if (opcao >= opcaoInicial && opcao <= opcaoFinal) {
                    return opcao;
                }
                throw new Exception();
            } catch (Exception e) {
                System.err.println("Opcao Inválida... Tente novamente\n")
            }
        }
    }

    Integer validaEntradaDeInteiro(String pergunta) {
        while (true) {
            System.out.println(pergunta);
            try {
                return Integer.parseInt(scanner.next());
            } catch (Exception e) {
                System.err.println("Opcao Inválida... Tente novamente\n")
            }
        }
    }
}
