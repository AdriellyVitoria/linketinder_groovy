package org.example.linketinder.database.servicos

import org.example.linketinder.database.database.ServicoConectarBanco
import org.example.linketinder.database.modelos.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class ServicoCandidatoCompetencia {
    def servicoConectar = new ServicoConectarBanco()

    String montarQueryBuscarPorCpf() {
        return "select " +
                "\tc.id_competencia, " +
                "\tc.descricao_competencia " +
                "from " +
                "\tlinlketinder.candidato_competencia AS cc " +
                "\tjoin linlketinder.competencia AS c on c.id_competencia = cc.id_competencia " +
                "where " +
                "\tcc.cpf_candidato = ?"
    }

    ArrayList<Competencia> listarCompetencia(String cpf_candidato){
        try {
            Connection conexao = servicoConectar.conectar();
            PreparedStatement compentenciasQuery = conexao.prepareStatement(
                    montarQueryBuscarPorCpf(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            compentenciasQuery.setString(1, cpf_candidato);
            ResultSet res = compentenciasQuery.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            def competencias = []
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
            System.exit(-42)
        }
    }

    boolean deletar(Integer id_competencia, String cpf_candidato){
        String DELETAR = "DELETE FROM linlketinder.candidato_competencia\n " +
                "WHERE cpf_candidato =? AND id_competencia =?"
        try {
            Connection conn = servicoConectar.conectar();
            PreparedStatement candidato = conn.prepareStatement(
                    montarQueryBuscarPorCpf(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )

            candidato.setString(1, cpf_candidato);
            ResultSet res = candidato.executeQuery();
            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                PreparedStatement del = conn.prepareStatement(DELETAR)
                del.setString(1, cpf_candidato)
                del.setInt(2, id_competencia)
                del.executeUpdate()
                del.close()
                servicoConectar.desconectar(conn)
            }
            return true
        } catch (Exception exception) {
            System.err.println("voce não possue essa competencia")
        }
    }

    boolean inserir(Integer id_competencia, String cpf_candidato){
        String INSERIR = "INSERT INTO linlketinder.candidato_competencia(id_competencia, cpf_candidato)" +
                " VALUES (?, ?)"
        try {
            Connection conn = servicoConectar.conectar()
            PreparedStatement salvar = conn.prepareStatement(INSERIR)

            salvar.setInt(1, id_competencia)
            salvar.setString(2, cpf_candidato)
            salvar.executeUpdate();
            salvar.close();
            servicoConectar.desconectar(conn);
            return true
        } catch (Exception exception) {
            System.err.println("Erro ao aplicar para vaga")
            if (exception.message.contains("candidato_vaga_id_vaga_fkey")){
                System.err.println("Essa vaga não existe")
            }
            if (exception.message.contains("candidato_competencia_pkey")){
                System.err.println("Voce já inserir essa competencia")
            }
        }
        return false
    }
}
