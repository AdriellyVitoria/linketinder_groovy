package org.example.linketinder.dao.implementacoes

import org.example.linketinder.dao.interfaces.VagaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Vaga

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class VagaDaoImpl implements VagaDao{

    private ConectarBanco conectarBanco
    private VagaCompetenciaDaoImpl vagaCompetenciaDao

    VagaDaoImpl (ConectarBanco conectarBanco,
     VagaCompetenciaDaoImpl vagaCompetenciaDao) {
        this.conectarBanco = conectarBanco
        this.vagaCompetenciaDao = vagaCompetenciaDao
    }

    @Override
    boolean criar(Vaga vaga) {
        String sql = montarSqlCriarVaga()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement salvar = conn.prepareStatement(sql);

            salvar.setString(1, vaga.getDescricao())
            salvar.setString(2, vaga.getTitulo())
            salvar.setString(3, vaga.getLocal())
            salvar.setString(4, vaga.getCnpj_empresa())

            salvar.executeUpdate();
            salvar.close();
        } catch (Exception exception) {
            System.err.println("Erro em criar" )
        }
        return null
    }

    private String montarSqlCriarVaga() {
        return "INSERT INTO linlketinder.vaga " +
                "(descricao_vaga, titulo_vaga, local_vaga, cnpj_empresa) " +
                "VALUES (?, ?, ?, ?)"
    }

    @Override
    Integer buscaIdVagaCriada() {
        String sql = montarSqlBuscarIdVagaCriada()

        Connection conn = conectarBanco.getConexao()
        PreparedStatement vaga = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        )
        ResultSet res = vaga.executeQuery();
        res.last();
        int qtd = res.getRow();
        res.beforeFirst();

        if(qtd > 0) {
            while (res.next()) {
                return res.getInt(1)
            }
        }
        return null
    }

    private String montarSqlBuscarIdVagaCriada() {
        return  "SELECT v.id_vaga " +
                "FROM linlketinder.vaga v " +
                "ORDER BY v.id_vaga DESC " +
                "LIMIT 1"
    }

    @Override
    ArrayList<Vaga> listarTodas() {
        String sql = montarQueryBuscarTodas()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement vaga = conn.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
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
                    v.setCompetencias(
                            vagaCompetenciaDao.listarCompetencia(v.id)
                    )
                    vagas.add(v)
                }
            }
            return vagas
        }catch(Exception exception){
            System.err.println("Erro em listar" )
        }
        return null
    }

    private String montarQueryBuscarTodas() {
        return "SELECT id_vaga, descricao_vaga, titulo_vaga, local_vaga " +
                "FROM linlketinder.vaga"
    }

    @Override
    ArrayList<Vaga> listar(String cnpj_empresa) {
        String sql = montarQueryBuscarPorCnpj()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement vaga = conn.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            vaga.setString(1, cnpj_empresa)
            ResultSet res = vaga.executeQuery();
            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            ArrayList<Vaga> vagas = []
            if(qtd > 0) {
                while (res.next()) {
                    Vaga v = new Vaga (
                            res.getInt(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4)
                    )
                    v.setCompetencias(
                            vagaCompetenciaDao.listarCompetencia(v.getId())
                    )
                    vagas.add(v)
                }
            }
            return vagas
        } catch (Exception exception) {
            System.err.println("Erro em listar" )
        }
        return null
    }

    private String montarQueryBuscarPorCnpj() {
        return "SELECT id_vaga, descricao_vaga, titulo_vaga, local_vaga " +
                "FROM linlketinder.vaga WHERE cnpj_empresa=?"
    }

    @Override
    boolean atualizar(Integer id_vaga, Vaga vaga) {
        String sql = montarSqlAtualizar()
        String verificarPorId = montarQueryBuscarPorId()

        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement atualizarVaga = conn.prepareStatement(
                    verificarPorId,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
            atualizarVaga.setInt(1, id_vaga)
            ResultSet res = atualizarVaga.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if(qtd >0){
                PreparedStatement salvar = conn.prepareStatement(sql);

                salvar.setString(1, vaga.getDescricao())
                salvar.setString(2, vaga.getTitulo())
                salvar.setString(3, vaga.getLocal())
                salvar.setString(4, vaga.getCnpj_empresa())
                salvar.setInt(5, id_vaga)

                salvar.executeUpdate();
                salvar.close();
                return vaga
            }
        } catch (Exception exeption) {
            System.err.println("Erro em atualizar descricao");
            System.exit(-42);
        }
        return null
    }

    private String montarSqlAtualizar() {
        return "UPDATE linlketinder.vaga " +
                "SET descricao_vaga=?, titulo_vaga =?, local_vaga =? " +
                " WHERE cnpj_empresa =? AND id_vaga = ?"
    }

    private  String montarQueryBuscarPorId() {
        return "SELECT id_vaga, descricao_vaga, titulo_vaga, local_vaga\n " +
                "FROM linlketinder.vaga WHERE id_vaga =?"
    }

    @Override
    boolean deletar(Integer id_vaga) {
        String sql = montarSqlDeletar()
        try {
            Connection conn = conectarBanco.getConexao()
            PreparedStatement del = conn.prepareStatement(sql)
            del.setInt(1, id_vaga)
            del.executeUpdate()
            del.close()
        } catch (Exception exception) {
            System.err.println("Erro em deletar vaga");
        }
        return null
    }

    private String montarSqlDeletar() {
        return "DELETE FROM linlketinder.vaga WHERE id_vaga =?"
    }
}
