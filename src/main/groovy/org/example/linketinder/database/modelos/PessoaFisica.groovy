package org.example.linketinder.database.modelos

class PessoaFisica extends Pessoa {
    String cpf
    int idade
    ArrayList<Competencia> competencias

    PessoaFisica() {}

    PessoaFisica(
            String cpf,
            String nome,
            String email,
            String telefone,
            String cep,
            String descricao,
            Integer idade,
            String estado
    ){
        super(nome, email, telefone, estado, cep, descricao)
        this.cpf = cpf
        this.idade = idade
    }

    @Override
    String toString() {
        return " CPF: ${cpf}\n" +
                " Nome: ${nome}\n" +
                " Email: ${email}\n" +
                " Idade: ${idade}\n" +
                " Estado: ${estado}\n" +
                " CEP: ${cep}\n" +
                " Telefone: ${telefone}\n" +
                " Descrição: ${descricao}\n"
    }
}
