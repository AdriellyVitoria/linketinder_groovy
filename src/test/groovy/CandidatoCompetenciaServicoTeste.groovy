import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.servicos.CandidatoCompetenciaServico
import org.junit.Assert
import org.junit.Test

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CandidatoCompetenciaServicoTeste {
    private CandidatoCompetenciaServico candidatoCompetenciaServico

    CandidatoCompetenciaServicoTeste(){
        Connection connectionMock = mock(Connection.class)
        ConectarBanco servicoConectarBancoMock = mock(ConectarBanco.class)
        PreparedStatement prepareStatementMock = mock(PreparedStatement.class)
        ResultSet resultSetMock = mock(ResultSet.class)

        when(servicoConectarBancoMock.getConexao()).thenReturn(connectionMock)
        when(connectionMock.prepareStatement(anyString())).thenReturn(prepareStatementMock)
        when(connectionMock.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(prepareStatementMock)
        when(prepareStatementMock.executeQuery()).thenReturn(resultSetMock)

        candidatoCompetenciaServico = new CandidatoCompetenciaServico(servicoConectarBancoMock)
    }

    @Test
    void testeInserirCompetenciaEmCandidatoComSucesso(){
        Integer id_competencia = 2
        String cpf_teste = "123"

        boolean retorno = candidatoCompetenciaServico.inserir(id_competencia, cpf_teste)

        Assert.assertTrue(retorno)
    }

    @Test
    void testeListarCompetenciaCandidato(){
        String cpf_teste = "123"

        ArrayList<Competencia> retorno = candidatoCompetenciaServico.listarCompetencia(cpf_teste)

        Assert.assertNotNull(retorno)
    }

    @Test
    void testeDeletarCompetenciaCandidato(){
        Integer id_competencia = 2
        String cpf_teste = "123"

        boolean retorno = candidatoCompetenciaServico.deletar(id_competencia, cpf_teste)

        Assert.assertTrue(retorno)
    }
}
