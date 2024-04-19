package org.example.linketinder.database.servicos

import org.example.linketinder.database.database.ConectarBanco
import org.example.linketinder.database.modelos.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciaServico {
    private ConectarBanco servicoConectar

    CompetenciaServico(
            ConectarBanco servicoConectarBanco
    ){
        servicoConectar = servicoConectarBanco
    }

    String montarQueryBuscarTodos() {
        return "SELECT id_competencia, descricao_competencia FROM linlketinder.competencia"
    }

    boolean inserir(String competencia) {
        String INSERIR = "INSERT INTO linlketinder.competencia(descricao_competencia) VALUES (?)"
        try {
            Connection conn = servicoConectar.getConexao()
            PreparedStatement salvar = conn.prepareStatement(INSERIR)
            salvar.setString(1, competencia)
            salvar.executeUpdate();
            salvar.close();
            return true
        } catch (Exception exception) {
            System.err.println("Erro em inserir");
        }
        return false
    }

    ArrayList<Competencia> listarTodas() {
        try {
            Connection conexao = servicoConectar.getConexao()
            PreparedStatement empresa = conexao.prepareStatement(
                    montarQueryBuscarTodos(),
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
            System.err.println("Erro em listar");
        }
        return null
    }
}
