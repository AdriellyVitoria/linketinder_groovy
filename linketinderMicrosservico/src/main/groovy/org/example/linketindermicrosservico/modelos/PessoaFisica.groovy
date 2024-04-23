package org.example.linketindermicrosservico.modelos

class PessoaFisica extends Pessoa {
    String cpf
    int idade

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

}
