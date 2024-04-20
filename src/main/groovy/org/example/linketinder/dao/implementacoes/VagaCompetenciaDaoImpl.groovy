package org.example.linketinder.dao.implementacoes

import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class VagaCompetenciaDaoImpl implements VagaCompetenciaDao {
    private ConectarBanco conectarBanco

    VagaCompetenciaDaoImpl(ConectarBanco conectarBanco) {
        this.conectarBanco = conectarBanco
    }

    @Override
    ArrayList<Competencia> listarCompetencia(Integer id_vaga) {
        String sql = montarSqlBuscarCompetencia()
        try {
            Connection conexao = conectarBanco.getConexao()
            PreparedStatement compentenciasQuery = conexao.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            compentenciasQuery.setInt(1, id_vaga)
            ResultSet res = compentenciasQuery.executeQuery()

            res.last()
            int qtd = res.getRow()
            res.beforeFirst()

            ArrayList competencias = []
            if (qtd > 0) {
                while (res.next()) {
                    Competencia c = new Competencia(
                            res.getInt(1),
                            res.getString(2)
                    )
                    competencias.add(c)
                }
            }
            return competencias
        } catch (Exception exception) {
            System.err.println("Erro ao buscar competencia")
        }
        return null
    }

    private String montarSqlBuscarCompetencia() {
        return "SELECT " +
                "c.id_competencia, " +
                "c.descricao_competencia " +
                "FROM linlketinder.vaga_competencia vc " +
                "JOIN linlketinder.competencia c ON c.id_competencia = vc.id_competencia " +
                "WHERE vc.id_vaga = ?"
    }

    @Override
    boolean deletar(Integer id_competencia, Integer id_vaga) {
        String sql = montarSqlDeletar()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement del = conn.prepareStatement(sql)
            del.setInt(1, id_vaga)
            del.setInt(2, id_competencia)

            del.executeUpdate()
            del.close()
            return true
        } catch (Exception exception) {
            System.err.println("Erro em deletar Competencia em vaga")
        }
        return false
    }

    private String montarSqlDeletar() {
        return "DELETE FROM linlketinder.vaga_competencia " +
                "WHERE id_vaga= ? AND id_competencia = ?"
    }

    @Override
    boolean inserir(Integer id_competencia, Integer id_vaga) {
        String sql = montarSlqInserir()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement salvar = conn.prepareStatement(sql);
            salvar.setInt(1, id_competencia)
            salvar.setInt(2, id_vaga)

            salvar.executeUpdate();
            salvar.close();
            return true
        } catch (Exception exception) {
            System.err.println("Erro em inserir")
            if (exception.message.contains("vaga_competencia_pkey")){
                System.err.println("Voce já inserir essa competencia")
            }
            if (exception.message.contains("vaga_competencia_id_competencia_fkey")){
                System.err.println("essa competencia não existe")
            }

        }
        return false
    }

    private String montarSlqInserir() {
        return "INSERT INTO linlketinder.vaga_competencia(id_competencia, id_vaga) " +
                "VALUES (?, ?)"
    }
}
