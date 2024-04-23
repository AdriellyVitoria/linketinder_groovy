package org.example.linketindermicrosservico.dao.implementacoes

import org.example.linketindermicrosservico.dao.interfaces.EmpresaDao
import org.example.linketindermicrosservico.database.ConectarBanco
import org.example.linketindermicrosservico.modelos.PessoaJuridica

import java.sql.Connection
import java.sql.PreparedStatement

class EmpresaDaoImpl implements EmpresaDao {
    private ConectarBanco conectarBanco

    EmpresaDaoImpl(ConectarBanco conectarBanco) {
        this.conectarBanco = conectarBanco
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
            if (exception.message.contains("key")) {
                throw new Exception("CNPJ já cadastrado!")
            }
            if (exception.message.contains("email")) {
                throw new Exception("Email já cadastrado!")
            }
            throw new Exception("ERRO AO CADASTRAR")
        }
    }

    private String montarSqlInserir() {
        return "INSERT INTO linlketinder.empresa(cnpj_empresa, nome_empresa, email_empresa,\n" +
                "                    senha_empresa, telefone_empresa, cep_empresa,\n" +
                "                    estado_empresa, pais_empresa, descricao_empresa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
    }
}
