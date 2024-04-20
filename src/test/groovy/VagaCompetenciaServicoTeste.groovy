import org.example.linketinder.dao.implementacoes.VagaCompetenciaDaoImpl
import org.example.linketinder.dao.interfaces.VagaCompetenciaDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Competencia
import org.example.linketinder.servicos.VagaCompetenciaServico
import org.junit.Assert
import org.junit.Test

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class VagaCompetenciaServicoTeste {

    private VagaCompetenciaDaoImpl vagaCompetenciaDaoImpl

    VagaCompetenciaServicoTeste() {
        Connection connectionMock = mock(Connection.class)
        ConectarBanco servicoConectarBancoMock = mock(ConectarBanco.class)
        PreparedStatement prepareStatementMock = mock(PreparedStatement.class)
        ResultSet resultSetMock = mock(ResultSet.class)

        when(servicoConectarBancoMock.getConexao()).thenReturn(connectionMock)
        when(connectionMock.prepareStatement(anyString())).thenReturn(prepareStatementMock)
        when(connectionMock.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(prepareStatementMock)
        when(prepareStatementMock.executeQuery()).thenReturn(resultSetMock)

        vagaCompetenciaDaoImpl = new VagaCompetenciaDaoImpl(servicoConectarBancoMock)
    }

    @Test
    void testeInserirCompetenciaEmVaga() {

        Integer id_competencia = 2
        Integer id_vaga = 2

        boolean retorno = vagaCompetenciaDaoImpl.inserir(id_competencia, id_vaga)

        Assert.assertTrue(retorno)
    }

    @Test
    void testeDeletarCompetenciaVaga() {
        Integer id_competencia = 2
        Integer id_vaga = 2

        boolean retorno = vagaCompetenciaDaoImpl.deletar(id_competencia, id_vaga)

        Assert.assertTrue(retorno)
    }

    @Test
    void testeListarCompetenciaVaga() {
        Integer id_Vaga = 1

        ArrayList<Competencia> retorno = vagaCompetenciaDaoImpl.listarCompetencia(id_Vaga)

        Assert.assertNotNull(retorno)
    }
}