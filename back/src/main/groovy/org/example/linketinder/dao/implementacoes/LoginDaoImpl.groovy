package org.example.linketinder.dao.implementacoes

import org.example.linketinder.dao.interfaces.LoginDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.LoginRequest
import org.example.linketinder.modelos.PessoaFisica
import org.example.linketinder.modelos.PessoaJuridica

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class LoginDaoImpl implements LoginDao{

    private ConectarBanco conectarBanco

    LoginDaoImpl(ConectarBanco conectarBanco) {
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
    PessoaJuridica entradaEmpresa(LoginRequest loginRequest) {
        String sql = montarSqlLogin()
        try {
            Connection conexao = conectarBanco.getConexao()
            PreparedStatement empresa = conexao.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            empresa.setString(1, loginRequest.email)
            empresa.setString(2, loginRequest.senha)
            ResultSet res = empresa.executeQuery()

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                while (res.next()) {
                    PessoaJuridica e = new PessoaJuridica(
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5),
                            res.getString(6),
                            res.getString(7),
                            res.getString(8)
                    )
                    return e
                }
            }
        } catch (Exception exception){
            System.err.println("Erro em entrar");
            System.exit(-42);
        }
        return null
    }

    private String montarSqlLogin() {
        return "SELECT " +
                "e.cnpj_empresa," +
                "e.nome_empresa," +
                "e.email_empresa," +
                "e.telefone_empresa," +
                "e.cep_empresa," +
                "e.descricao_empresa," +
                "e.estado_empresa," +
                "e.pais_empresa" +
                " FROM " +
                "linlketinder.empresa AS e " +
                "WHERE e.email_empresa = ? AND e.senha_empresa = ?"
    }

}
