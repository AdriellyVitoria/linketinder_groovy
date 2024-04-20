import org.example.linketinder.dao.implementacoes.CandidatoCompetenciaDaoImpl
import org.example.linketinder.dao.implementacoes.CandidatoDaoImpl
import org.example.linketinder.dao.interfaces.CandidatoCompetenciaDao
import org.example.linketinder.dao.interfaces.CandidatoDao
import org.example.linketinder.database.ConectarBanco
import org.example.linketinder.modelos.PessoaFisica
import org.junit.Assert
import org.junit.Test

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class CandidatoServicoTeste {
    private CandidatoDao candidatoDao

    CandidatoServicoTeste() {
        Connection connectionMock = mock(Connection.class)
        ConectarBanco servicoConectarBancoMock = mock(ConectarBanco.class)
        PreparedStatement prepareStatementMock = mock(PreparedStatement.class)
        ResultSet resultSetMock = mock(ResultSet.class)
        CandidatoCompetenciaDao candidatoCompetenciaDao = new CandidatoCompetenciaDaoImpl(servicoConectarBancoMock)

        when(servicoConectarBancoMock.getConexao()).thenReturn(connectionMock)
        when(connectionMock.prepareStatement(anyString())).thenReturn(prepareStatementMock)
        when(connectionMock.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(prepareStatementMock)
        when(prepareStatementMock.executeQuery()).thenReturn(resultSetMock)

        candidatoDao = new CandidatoDaoImpl(servicoConectarBancoMock, candidatoCompetenciaDao)
    }

    private PessoaFisica criarPessoarFisica(){
        return new PessoaFisica(
                '123',
                'Adrielly',
                'mendes@',
                '2323',
                '2222',
                'teste',
                23,
                'PB'
        )
    }

    @Test
    void testeInserirCandidato(){
        PessoaFisica pessoaFisica = criarPessoarFisica()

        boolean retorno = candidatoDao.inserir(pessoaFisica)

        Assert.assertNotNull(retorno)
    }

    @Test
    void testeAtualizarPessoa() {
    PessoaFisica pessoaFisica = criarPessoarFisica()

    boolean retorno = candidatoDao.atualizar(pessoaFisica)

    Assert.assertTrue(retorno)
    }

    @Test
    void testeDeletarCandidato() {
        String cpf_teste = '123'

        boolean retorno = candidatoDao.deletar(cpf_teste)

        Assert.assertTrue(retorno)
    }
}
