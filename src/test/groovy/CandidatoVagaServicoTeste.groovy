import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Vaga
import org.example.linketinder.servicos.CandidatoVagaServico
import org.example.linketinder.servicos.VagaCompetenciaServico
import org.junit.Assert
import org.junit.Test

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CandidatoVagaServicoTeste {
    private CandidatoVagaServico candidatoVagaServico

    CandidatoVagaServicoTeste(){
        Connection connectionMock = mock(Connection.class)
        ConectarBanco servicoConectarBancoMock = mock(ConectarBanco.class)
        PreparedStatement prepareStatementMock = mock(PreparedStatement.class)
        ResultSet resultSetMock = mock(ResultSet.class)
        VagaCompetenciaServico vagaCompetenciaServico = new VagaCompetenciaServico(servicoConectarBancoMock)

        when(servicoConectarBancoMock.getConexao()).thenReturn(connectionMock)
        when(connectionMock.prepareStatement(anyString())).thenReturn(prepareStatementMock)
        when(connectionMock.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(prepareStatementMock)
        when(prepareStatementMock.executeQuery()).thenReturn(resultSetMock)

        candidatoVagaServico = new CandidatoVagaServico(servicoConectarBancoMock, vagaCompetenciaServico)
    }

    @Test
    void testeInserirCandidatoVagaComSucesso(){
        // Arrange
        Integer id_vaga = 2
        String cpfTeste = '123'
        // Act
        boolean retorno = candidatoVagaServico.aplicar(id_vaga, cpfTeste)
        // Assert
        Assert.assertTrue(retorno)
    }

    @Test
    void testeListarVagaAplicadas(){
        // Arrange
        String cpfTeste = '123'
        // Act
        ArrayList<Vaga> retorno = candidatoVagaServico.listarPorCpf(cpfTeste)
        // Assert
        Assert.assertNotNull(retorno)
    }
}
