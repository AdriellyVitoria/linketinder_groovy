package org.example.linketinder.dao.implementacoes

import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.exceptions.DadosDuplicadosException
import org.example.linketinder.modelos.PessoaFisica

import java.sql.Connection
import java.sql.PreparedStatement

class CandidatoDaoImpl implements CandidatoDao {
    private ConectarBanco conectarBanco

    CandidatoDaoImpl(
            ConectarBanco conectarBanco
    ) {
        this.conectarBanco = conectarBanco
    }


    @Override
    PessoaFisica inserir(PessoaFisica candidato) {
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
            return candidato
        }catch (Exception e) {
            if (e.message.contains("key")) {
                throw new DadosDuplicadosException("CPF já cadastrado!")
            }
            if (e.message.contains("email")) {
                throw new DadosDuplicadosException("Email já cadastrado!")
            }
            throw new Exception("ERRO AO CADASTRAR")
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

    @Override
    boolean atualizar(PessoaFisica candidato) {
        String sql = montarSqlAtualizar()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement salvar = conn.prepareStatement(sql);

            salvar.setString(1, candidato.getNome())
            salvar.setString(2, candidato.getEmail())
            salvar.setString(3, candidato.getSenha())
            salvar.setString(4, candidato.getTelefone())
            salvar.setString(5, candidato.getEstado())
            salvar.setString(6, candidato.getCep())
            salvar.setString(7,candidato.getDescricao())
            salvar.setInt(8,candidato.getIdade())
            salvar.setString(9, candidato.getCpf())

            salvar.executeUpdate();
            salvar.close();
            return true

        } catch (Exception exeption) {
            System.err.println("Erro em atualizar Descricao")
        }
        return false
    }

    private String montarSqlAtualizar() {
        return "UPDATE linlketinder.candidato " +
                "SET nome_candidato =?, email_candidato =?, " +
                "senha_candidato =?, telefone_candidato =?," +
                " estado_candidato =?, cep_candidato =?, " +
                "descricao_candidato =?, idade_candidato =? " +
                "WHERE cpf_candidato= ?"
    }

    @Override
    boolean deletar(String cpf_candidato) {
        String sql = montarSqlDeletar()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement del = conn.prepareStatement(sql)
            del.setString(1, cpf_candidato)
            del.executeUpdate()
            del.close()
            return true
        } catch (Exception exception) {
            System.err.println("Erro em deletar Competencia Candidato");
        }
        return false
    }

    private String montarSqlDeletar() {
        return "DELETE FROM linlketinder.candidato WHERE cpf_candidato=?"
    }
}
