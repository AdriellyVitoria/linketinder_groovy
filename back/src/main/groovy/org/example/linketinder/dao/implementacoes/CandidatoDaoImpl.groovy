package org.example.linketinder.dao.implementacoes

import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaFisica

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CandidatoDaoImpl implements CandidatoDao {
    private ConectarBanco conectarBanco

    CandidatoDaoImpl(
            ConectarBanco conectarBanco
    ) {
        this.conectarBanco = conectarBanco
    }

    @Override
    PessoaFisica entradaCandidato(LoginRequest request) {
        String sql = verificacaoParalogin()
        try {
            Connection conexao = conectarBanco.getConexao()
            PreparedStatement candidato = conexao.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            candidato.setString(1, request.email)
            candidato.setString(2, request.senha)
            ResultSet res = candidato.executeQuery()

            res.last()
            int qtd = res.getRow()
            res.beforeFirst()

            if (qtd > 0) {
                while (res.next()) {
                    PessoaFisica c = new PessoaFisica(
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5),
                            res.getString(6),
                            res.getInt(7),
                            res.getString(8),
                    )
                    return c
                }
            }
        } catch (Exception exception){
            System.err.println("Erro em entrar");
        }
        return null
    }

    private String verificacaoParalogin() {
        return "SELECT c.cpf_candidato," +
                "c.nome_candidato, " +
                "c.email_candidato, " +
                "c.telefone_candidato, " +
                "c.cep_candidato, " +
                "c.descricao_candidato, " +
                "c.idade_candidato, " +
                "c.estado_candidato " +
                "FROM linlketinder.candidato As c " +
                "WHERE email_candidato=? AND senha_candidato=?"
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
