package org.example.linketinder.dao.implementacoes

import org.example.linketinder.dao.interfaces.CompetenciaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciaDaoImpl implements CompetenciaDao {

    private ConectarBanco servicoConectar

    CompetenciaDaoImpl(ConectarBanco servicoConectarBanco) {
        servicoConectar = servicoConectarBanco
    }

    @Override
    boolean inserir(String competencia) {
        String sql = montarSqlInserir()
        try {
            Connection conn = servicoConectar.getConexao()
            PreparedStatement salvar = conn.prepareStatement(sql)
            salvar.setString(1, competencia)
            salvar.executeUpdate();
            salvar.close();
            return true
        } catch (Exception exception) {
            System.err.println("Erro em inserir");
        }
        return false
    }

    private String montarSqlInserir() {
        return "INSERT INTO linlketinder.competencia(descricao_competencia) VALUES (?)"
    }

    @Override
    ArrayList<Competencia> listarTodas() {
        String sql = montarSqlListarTodas()
        try {
            Connection conexao = servicoConectar.getConexao()
            PreparedStatement empresa = conexao.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet res = empresa.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst()
            ArrayList competencias = []
            if (qtd > 0){
                while (res.next()) {
                    Competencia c = new Competencia (
                            res.getInt(1),
                            res.getString(2)
                    )
                    competencias.add(c)
                }
            }
            return competencias
        }catch(Exception exception){
            System.err.println("Erro em listarPorCnpj");
        }
        return null
    }

    private String montarSqlListarTodas() {
        return "SELECT id_competencia, descricao_competencia FROM linlketinder.competencia"
    }
}
