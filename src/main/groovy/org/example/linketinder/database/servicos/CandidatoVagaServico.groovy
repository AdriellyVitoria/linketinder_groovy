package org.example.linketinder.database.servicos

import org.example.linketinder.database.database.ConectarBanco
import org.example.linketinder.database.modelos.Vaga

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CandidatoVagaServico {
    private ConectarBanco servicoConectar
    private VagaCompetenciaServico servicoVaga

    CandidatoVagaServico(
            ConectarBanco servicoConectar,
            VagaCompetenciaServico servicoVaga
    ){
        this.servicoConectar = servicoConectar
        this.servicoVaga = servicoVaga
    }

    String montarQueryBuscar() {
        return "SELECT id_vaga, descricao_vaga, titulo_vaga, local_vaga " +
                "FROM linlketinder.vaga"
    }

    String listarVagasAplicadas(){
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

    boolean aplicar(Integer id_vaga, String cpf){
        String APLICAR = "insert into linlketinder.candidato_vaga(cpf_candidato, id_vaga) " +
                " Values (?, ?)"
        try {
            Connection conn = servicoConectar.getConexao()
            PreparedStatement vaga = conn.prepareStatement(
                    montarQueryBuscar(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )

            ResultSet res = vaga.executeQuery()
            res.last()
            int qtd = res.getRow()
            res.beforeFirst();

            if (qtd > 0) {
                PreparedStatement del = conn.prepareStatement(APLICAR)
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

    ArrayList<Vaga>  listarPorCpf(String cpf_candidato) {
        try {
            Connection conn = servicoConectar.getConexao()
            PreparedStatement vaga = conn.prepareStatement(
                    listarVagasAplicadas(),
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
                    v.setCompetencias(
                            servicoVaga.buscarCompetencia(v.id)
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
}
