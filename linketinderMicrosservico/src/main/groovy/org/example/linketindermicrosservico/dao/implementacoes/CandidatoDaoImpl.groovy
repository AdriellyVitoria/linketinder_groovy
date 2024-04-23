package org.example.linketindermicrosservico.dao.implementacoes

import org.example.linketindermicrosservico.dao.interfaces.CandidatoDao
import org.example.linketindermicrosservico.database.ConectarBanco
import org.example.linketindermicrosservico.modelos.PessoaFisica

import java.sql.Connection
import java.sql.PreparedStatement

class CandidatoDaoImpl implements CandidatoDao{

    private ConectarBanco conectarBanco

    CandidatoDaoImpl(
            ConectarBanco conectarBanco
    ) {
        this.conectarBanco = conectarBanco
    }

    @Override
    boolean inserir(PessoaFisica candidato) {
        String sql = montarSqlInserir()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement salvar = conn.prepareStatement(sql);

            salvar.setString(1, candidato.getCpf());
            salvar.setString(2, candidato.getNome());
            salvar.setString(3, candidato.getEmail());
            salvar.setString(4, candidato.getSenha());
            salvar.setString(5, candidato.getTelefone());
            salvar.setString(6, candidato.getCep());
            salvar.setString(7, candidato.getEstado());
            salvar.setInt(8, candidato.getIdade());
            salvar.setString(9, candidato.getDescricao());

            salvar.executeUpdate();
            salvar.close();
            return true
        }catch (Exception e) {
            System.err.println ("ERRO AO CADASTRAR")
            if (e.message.contains("key")) {
                System.err.println("CPF já cadastrado!");
            }
            if (e.message.contains("email")) {
                System.err.println("Email já cadastrado!");
            }
            return false
        }
    }

    private String montarSqlInserir() {
        return "INSERT INTO linlketinder.Candidato(" +
                "cpf_candidato, " +
                "nome_candidato, " +
                "email_candidato, " +
                "senha_candidato, " +
                "telefone_candidato, " +
                "cep_candidato, " +
                "estado_candidato, " +
                "idade_candidato, " +
                "descricao_candidato) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) "
    }

}
