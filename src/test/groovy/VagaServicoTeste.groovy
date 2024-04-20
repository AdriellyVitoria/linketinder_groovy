import org.example.linketinder.dao.implementacoes.VagaCompetenciaDaoImpl
import org.example.linketinder.dao.implementacoes.VagaDaoImpl
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.Vaga
import org.junit.Assert
import org.junit.Test

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class VagaServicoTeste {

    private VagaDaoImpl vagaDaoImpl

    VagaServicoTeste() {
        Connection connectionMock = mock(Connection.class)
        ConectarBanco servicoConectarBancoMock = mock(ConectarBanco.class)
        PreparedStatement prepareStatementMock = mock(PreparedStatement.class)
        ResultSet resultSetMock = mock(ResultSet.class)

        VagaCompetenciaDaoImpl vagaCompetenciaDaoImpl = new VagaCompetenciaDaoImpl(servicoConectarBancoMock)

        when(servicoConectarBancoMock.getConexao()).thenReturn(connectionMock)
        when(connectionMock.prepareStatement(anyString())).thenReturn(prepareStatementMock)
        when(connectionMock.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(prepareStatementMock)
        when(prepareStatementMock.executeQuery()).thenReturn(resultSetMock)

        vagaDaoImpl = new VagaDaoImpl(servicoConectarBancoMock, vagaCompetenciaDaoImpl)
    }

    private Vaga criarVaga() {
        return new Vaga(
                1,
                'descricao',
                'vaga',
                'local'
        )
    }

    @Test
    void testeCriarVaga() {
        Vaga vaga = criarVaga()
        boolean retorno = vagaDaoImpl.criar(vaga)

        Assert.assertNotNull(retorno)
    }

    @Test
    void testeBuscarIdVagaCriada() {
        boolean retorno = vagaDaoImpl.buscaIdVagaCriada()

        Assert.assertNotNull(retorno)
    }

    @Test
    void testeListarTodasAsVagas(){

        ArrayList<Vaga> retorno = vagaDaoImpl.listarTodas()

        Assert.assertNotNull(retorno)
    }

    @Test
    void testeListarVagaEmpresa() {
        String cnpj_teste = '123'

        ArrayList<Vaga> retorno = vagaDaoImpl.listar(cnpj_teste)

        Assert.assertNotNull(retorno)
    }

    @Test
    void testeAtualizarVaga() {
        Integer id_vaga = 1
        Vaga vaga = criarVaga()

        boolean retorno = vagaDaoImpl.atualizar(id_vaga, vaga)

        Assert.assertNotNull(retorno)
    }

    @Test
    void testeDeletarVaga() {
        Integer id_vaga = 1

        boolean retorno = vagaDaoImpl.deletar(id_vaga)

        Assert.assertNotNull(retorno)
    }
}
