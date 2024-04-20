package org.example.linketinder.dao.implementacoes

import org.example.linketinder.dao.interfaces.EmpresaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.PessoaJuridica

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class EmpresaDaoImpl implements EmpresaDao{

    private ConectarBanco conectarBanco

    EmpresaDaoImpl(ConectarBanco conectarBanco) {
        this.conectarBanco = conectarBanco
    }

    @Override
    PessoaJuridica entradaEmpresa(String email_empresa, String senha_empresa) {
        String sql = montarSqlLogin()
        try {
            Connection conexao = conectarBanco.getConexao()
            PreparedStatement empresa = conexao.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            empresa.setString(1, email_empresa)
            empresa.setString(2, senha_empresa)
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

    @Override
    boolean inserir(PessoaJuridica empresa) {
        String sql = montarSqlInserir()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement salvar = conn.prepareStatement(sql);

            salvar.setString(1, empresa.getCnpj());
            salvar.setString(2, empresa.getNome());
            salvar.setString(3, empresa.getEmail());
            salvar.setString(4, empresa.getSenha());
            salvar.setString(5, empresa.getTelefone());
            salvar.setString(6, empresa.getCep());
            salvar.setString(7, empresa.getEstado());
            salvar.setString(8, empresa.getPais());
            salvar.setString(9, empresa.getDescricao());

            salvar.executeUpdate();
            salvar.close();
            return true
        } catch (Exception exception) {
            System.err.println ("ERRO AO CADASTRAR")
            if (exception.message.contains("key")) {
                System.err.println("CPF já cadastrado!");
            }
            if (exception.message.contains("email")) {
                System.err.println("Email já cadastrado!");
            }
        }
        return false
    }

    private String montarSqlInserir() {
        return "INSERT INTO linlketinder.empresa(cnpj_empresa, nome_empresa, email_empresa,\n" +
                "                    senha_empresa, telefone_empresa, cep_empresa,\n" +
                "                    estado_empresa, pais_empresa, descricao_empresa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
    }

    @Override
    boolean atualizar(PessoaJuridica empresa) {
        String sql = montarSqlAtualizar()
        try {
            Connection conn = conectarBanco.getConexao()

            PreparedStatement salvar = conn.prepareStatement(sql);

            salvar.setString(1, empresa.getNome())
            salvar.setString(2, empresa.getEmail())
            salvar.setString(3, empresa.getSenha())
            salvar.setString(4, empresa.getTelefone())
            salvar.setString(5, empresa.getCep())
            salvar.setString(6, empresa.getEstado())
            salvar.setString(7, empresa.getPais())
            salvar.setString(8, empresa.getDescricao())
            salvar.setString(9, empresa.getCnpj())

            salvar.executeUpdate()
            salvar.close()
            return true

        } catch (Exception exeption) {
            System.err.println("Erro em atualizar Empresa")
        }
        return false
    }

    private String montarSqlAtualizar() {
        return "UPDATE linlketinder.empresa " +
                "SET nome_empresa=?, email_empresa=?," +
                "senha_empresa=?, telefone_empresa=?, cep_empresa=?," +
                "estado_empresa=?, pais_empresa=?, descricao_empresa=? " +
                "WHERE cnpj_empresa=?"
    }

    @Override
    boolean deletar(String cnpj_empresa) {
        String sql = montarSqlDeletar()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement del = conn.prepareStatement(sql)
            del.setString(1, cnpj_empresa)
            del.executeUpdate()
            del.close()

        } catch (Exception exception) {
            System.err.println("Erro em deletar Competencia Empresa");
        }
        return null
    }

    private String montarSqlDeletar() {
        return "DELETE FROM linlketinder.empresa WHERE cnpj_empresa=?"
    }
}
