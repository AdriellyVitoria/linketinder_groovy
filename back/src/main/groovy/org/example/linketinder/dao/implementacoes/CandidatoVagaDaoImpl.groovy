package org.example.linketinder.dao.implementacoes

import org.example.linketinder.dao.interfaces.CandidatoVagaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Vaga

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CandidatoVagaDaoImpl implements CandidatoVagaDao{
    private ConectarBanco conectarBanco

    CandidatoVagaDaoImpl(ConectarBanco conectarBanco) {
        this.conectarBanco = conectarBanco
    }

    @Override
    boolean aplicar(Integer id_vaga, String cpf) {
        String sql = montarSqlAplicarEmVaga()
        String sqlBuscarVaga = montarQueryBuscar()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement vaga = conn.prepareStatement(
                    sqlBuscarVaga,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )

            ResultSet res = vaga.executeQuery()
            res.last()
            int qtd = res.getRow()
            res.beforeFirst();

            if (qtd > 0) {
                PreparedStatement del = conn.prepareStatement(sql)
                del.setString(1, cpf)
                del.setInt(2, id_vaga)
                del.executeUpdate()
                del.close()
            }
            return true
        } catch (Exception exception) {
            System.err.println("Erro ao aplicar para vaga")
            if (exception.message.contains("candidato_vaga_id_vaga_fkey")){
                System.err.println("Essa vaga não existe")
            }
            if (exception.message.contains("candidato_vaga_pkey")){
                System.err.println("Voce já aplicou nessa vaga")
            }
        }
        return false
    }

    private String montarQueryBuscar() {
        return "SELECT id_vaga, descricao_vaga, titulo_vaga, local_vaga " +
                "FROM linlketinder.vaga"
    }

    private String montarSqlAplicarEmVaga() {
        return "insert into linlketinder.candidato_vaga(cpf_candidato, id_vaga) " +
                " Values (?, ?)"
    }

    @Override
    ArrayList<Vaga> listarPorCpf(String cpf_candidato) {
        String sql = listarVagasAplicadas()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement vaga = conn.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            vaga.setString(1, cpf_candidato)

            ResultSet res = vaga.executeQuery()
            res.last()
            int qtd = res.getRow()
            res.beforeFirst()

            ArrayList<Vaga> vagas = []
            if(qtd > 0) {
                while (res.next()) {
                    Vaga v = new Vaga (
                            res.getInt(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4)
                    )
                    vagas.add(v)
                }
            }
            return vagas
        }catch(Exception exception){
            System.err.println("Erro em listarPorCnpj" )
        }
        return null
    }

    private String listarVagasAplicadas(){
        return  "SELECT " +
                "v.id_vaga, " +
                "v.descricao_vaga," +
                "v.titulo_vaga," +
                "v.local_vaga " +
                "FROM " +
                "linlketinder.vaga v " +
                "JOIN linlketinder.candidato_vaga cv ON cv.id_vaga = v.id_vaga " +
                "WHERE cv.cpf_candidato = ?"
    }
}
