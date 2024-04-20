package org.example.linketinder.dao.implementacoes

import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CandidatoCompetenciaDaoImpl implements CandidatoCompetenciaDao{
    private ConectarBanco conectarBanco

    CandidatoCompetenciaDaoImpl(ConectarBanco conectarBanco) {
        this.conectarBanco = conectarBanco
    }

    @Override
    ArrayList<Competencia> listarCompetencia(String cpf_candidato) {
        String sql = montarQueryBuscarPorCpf()
        try {
            Connection conexao = conectarBanco.getConexao()
            PreparedStatement compentenciasQuery = conexao.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            compentenciasQuery.setString(1, cpf_candidato);
            ResultSet res = compentenciasQuery.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

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
        } catch (Exception exception) {
            System.err.println("Erro ao buscar competencia")
        }
        return null
    }

    private String montarQueryBuscarPorCpf() {
        return "select " +
                "\tc.id_competencia, " +
                "\tc.descricao_competencia " +
                "from " +
                "\tlinlketinder.candidato_competencia AS cc " +
                "\tjoin linlketinder.competencia AS c on c.id_competencia = cc.id_competencia " +
                "where " +
                "\tcc.cpf_candidato = ?"
    }

    @Override
    boolean deletar(Integer id_competencia, String cpf_candidato) {
        String sql = montarSqlDeletar()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement del = conn.prepareStatement(sql)
            del.setString(1, cpf_candidato)
            del.setInt(2, id_competencia)
            del.executeUpdate()
            del.close()
            return true
        } catch (Exception exception) {
            System.err.println("Voce não possue essa competencia")
        }
        return false
    }

    private String montarSqlDeletar() {
        return "DELETE FROM linlketinder.candidato_competencia\n " +
                "WHERE cpf_candidato =? AND id_competencia =?"
    }

    @Override
    boolean inserir(Integer id_competencia, String cpf_candidato) {
        String sql = montarSqlInserir()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement salvar = conn.prepareStatement(sql)

            salvar.setInt(1, id_competencia)
            salvar.setString(2, cpf_candidato)
            salvar.executeUpdate();
            salvar.close();
            return true
        } catch (Exception exception) {
            System.err.println("Erro ao add competencia")
            if (exception.message.contains("candidato_competencia_id_competencia_fkey")){
                System.err.println("Essa competencia não existe")
            }
            if (exception.message.contains("candidato_competencia_pkey")){
                System.err.println("Voce já inserir essa competencia")
            }
        }
        return false
    }

    private String montarSqlInserir() {
        return "INSERT INTO linlketinder.candidato_competencia(id_competencia, cpf_candidato)" +
                " VALUES (?, ?)"
    }
}
