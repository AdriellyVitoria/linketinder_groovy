import org.example.linketinder.database.database.ConectarBancoServico
import org.example.linketinder.database.modelos.Competencia
import org.example.linketinder.database.servicos.CompetenciaServico
import org.junit.Test
import org.junit.Assert

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class CompetenciaServicoTeste {
    private CompetenciaServico servicoCompetencia

    CompetenciaServicoTeste() {
        Connection connectionMock = mock(Connection.class)
        ConectarBancoServico servicoConectarBancoMock = mock(ConectarBancoServico.class)
        PreparedStatement prepareStatementMock = mock(PreparedStatement.class)
        ResultSet resultSetMock = mock(ResultSet.class)

        when(servicoConectarBancoMock.conectar()).thenReturn(connectionMock)
        when(connectionMock.prepareStatement(anyString())).thenReturn(prepareStatementMock)
        when(connectionMock.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(prepareStatementMock)
        when(prepareStatementMock.executeQuery()).thenReturn(resultSetMock)

        servicoCompetencia = new CompetenciaServico(servicoConectarBancoMock)
    }

    @Test
    void testeInserirCompeteciaComSucesso() {
        String competencia = 'Java'

        boolean retorno = servicoCompetencia.inserir(competencia)

        Assert.assertTrue(retorno)
    }

    @Test
    void testeListarTodasCompetencias() {
        ArrayList<Competencia> retorno = servicoCompetencia.listarTodas()

        Assert.assertNotNull(retorno)
    }
}
