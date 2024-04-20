import org.example.linketinder.dao.implementacoes.CompetenciaDaoImpl
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Competencia
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
    private CompetenciaDaoImpl competenciaDaoImpl

    CompetenciaServicoTeste() {
        Connection connectionMock = mock(Connection.class)
        ConectarBanco servicoConectarBancoMock = mock(ConectarBanco.class)
        PreparedStatement prepareStatementMock = mock(PreparedStatement.class)
        ResultSet resultSetMock = mock(ResultSet.class)

        when(servicoConectarBancoMock.getConexao()).thenReturn(connectionMock)
        when(connectionMock.prepareStatement(anyString())).thenReturn(prepareStatementMock)
        when(connectionMock.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(prepareStatementMock)
        when(prepareStatementMock.executeQuery()).thenReturn(resultSetMock)

        competenciaDaoImpl = new CompetenciaDaoImpl(servicoConectarBancoMock)
    }

    @Test
    void testeInserirCompeteciaComSucesso() {
        String competencia = 'Java'

        boolean retorno =competenciaDaoImpl.inserir(competencia)

        Assert.assertTrue(retorno)
    }

    @Test
    void testeListarTodasCompetencias() {
        ArrayList<Competencia> retorno = competenciaDaoImpl.listarTodas()

        Assert.assertNotNull(retorno)
    }
}
