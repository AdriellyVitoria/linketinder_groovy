import org.example.linketinder.database.database.ConectarBancoServico
import org.example.linketinder.database.servicos.VagaCompetenciaServico
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

    private VagaCompetenciaServico vagaCompetenciaServico

    VagaCompetenciaServicoTeste(){
        Connection connectionMock = mock(Connection.class)
        ConectarBancoServico servicoConectarBancoMock = mock(ConectarBancoServico.class)
        PreparedStatement prepareStatementMock = mock(PreparedStatement.class)
        ResultSet resultSetMock = mock(ResultSet.class)

        when(servicoConectarBancoMock.conectar()).thenReturn(connectionMock)
        when(connectionMock.prepareStatement(anyString())).thenReturn(prepareStatementMock)
        when(connectionMock.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(prepareStatementMock)
        when(prepareStatementMock.executeQuery()).thenReturn(resultSetMock)

        vagaCompetenciaServico = new VagaCompetenciaServico(servicoConectarBancoMock)
    }

    @Test
    void testeInserirCompetenciaEmVaga(){

        Integer id_competencia = 2
        Integer id_vaga = 2

        boolean retorno = vagaCompetenciaServico.inserir(id_competencia, id_vaga)

        Assert.assertTrue(retorno)
    }

    @Test
    void testeDeletarCompetenciaVaga() {
        Integer id_competencia = 2
        Integer id_vaga = 2

        boolean retorno = vagaCompetenciaServico.deletar(id_competencia, id_vaga)

        Assert.assertTrue(retorno)

    }
}

